package com.odcl.lms.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odcl.lms.auth.model.User;

/**
 * @author A.R.M. Fahim
 * Mar 6, 2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String username);

	User getUserByUserName(String username);

	Boolean existsByUserName(String username);

	Boolean existsByEmail(String email);

	User getUserByEmployeeId(String employeeId);
}
