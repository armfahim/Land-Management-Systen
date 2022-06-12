package com.odcl.lms.auth.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.odcl.lms.auth.jwt.JwtUtils;
import com.odcl.lms.auth.model.ERole;
import com.odcl.lms.auth.model.JwtRequest;
import com.odcl.lms.auth.model.JwtResponse;
import com.odcl.lms.auth.model.Role;
import com.odcl.lms.auth.model.User;
import com.odcl.lms.auth.repository.RoleRepository;
import com.odcl.lms.auth.repository.UserRepository;
import com.odcl.lms.auth.service.LmsUserDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author A.R.M. Fahim
 * @since Feb 28, 2022
 */
@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticateController {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private LmsUserDetailsService userDetailsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	HttpSession session;

	@Autowired
	PasswordEncoder encoder;

	/**
	 * 
	 * @param jwtRequest
	 * @return jwt response token
	 * @throws Exception
	 * @ModelAttribute @RequestBody
	 */
	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@Valid @RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

		} catch (NotFoundException e) {
			throw new Exception("User not found");
		}

		// authenticate
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		boolean validate = this.jwtUtils.validateToken(token, userDetails);
		log.info("Token is validate [{}]", validate);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			throw new Exception("User Disabled" + e.getMessage());
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials");
		}
	}

	/**
	 * Get current user details
	 * @param principal
	 * @return
	 */
	@GetMapping("/currentUser")
	public Optional<User> getCurrentUser(Principal principal) {
		String name;
		String username = (String) session.getAttribute("username");
		if (username != null) {
			name = username;
		} else {
			name = principal.getName();
		}
		return userRepository.findByUserName(name);
	}

	/**
	 * Register new user
	 * @param user
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
		if (Boolean.TRUE.equals(userRepository.existsByUserName(user.getUserName()))) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}
		if (Boolean.TRUE.equals(userRepository.existsByEmail(user.getEmail()))) {
			return ResponseEntity.badRequest().body("Error: Email is already in use!");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		Set<Role> strRoles = user.getRoles();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER.toString())
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role.toString()) {
				case "admin":
					Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN.toString())
							.orElseThrow(() -> new RuntimeException("Admin Role is not found."));
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = roleRepository.findByRoleName(ERole.ROLE_SUPER_ADMIN.toString())
							.orElseThrow(() -> new RuntimeException("Super Admin Role is not found."));
					roles.add(modRole);
					break;
				default:
					Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER.toString())
							.orElseThrow(() -> new RuntimeException("User Role is not found."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok("User registered successfully!");
	}

	@PostMapping("/test")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok("Test Successfully");
	}
}
