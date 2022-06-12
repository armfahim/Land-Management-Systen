package com.odcl.lms.purchase.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.odcl.lms.common.utils.BasicAudit;
import com.odcl.lms.common.utils.Response;
import com.odcl.lms.purchase.model.LandOwner;
import com.odcl.lms.purchase.model.Requisition;
import com.odcl.lms.purchase.repository.LandOwnerPageableRepository;
import com.odcl.lms.purchase.repository.LandOwnerRepository;

/**
 * 
 * @author Md. Sanowar Ali
 */
@Service
public class LandOwnerService {

	@Autowired
	LandOwnerRepository landOwnerRepository;

	@Autowired
	LandOwnerPageableRepository landOwnerPageableRepository;

	public LandOwner createLandOwner(LandOwner reqObj) {
		BasicAudit.setAttributeForCreateUpdate(reqObj, "create");
		return this.landOwnerRepository.save(reqObj);
	}

	public LandOwner editLandOwner(LandOwner reqObj) {
		BasicAudit.setAttributeForCreateUpdate(reqObj, "update");
		return landOwnerRepository.save(reqObj);
	}

	public List<LandOwner> getAllListOfLandOwner(String reqObj) {
		return landOwnerRepository.findAll();
	}

	public Optional<LandOwner> getLandOwnerById(Long id) {
		return landOwnerRepository.findById(id);
	}

	public void remove(Long id) {
		landOwnerRepository.deleteById(id);
	}

	public Response gridList(HttpServletRequest request) {
		Response response = new Response();
		Page<LandOwner> landOwner;

		// Request Parameter
		String reqId = request.getParameter("requisitionId");
		Requisition req = new Requisition();
		if (reqId != null) req.setId(Long.parseLong(reqId));

		// For Searching
		String searchParam = request.getParameter("search[value]");

		// For Sorting
		String orderType = request.getParameter("order[0][dir]");
		String orderColumnIndex = request.getParameter("order[0][column]");
		int orderColIndex = Integer.parseInt(orderColumnIndex);
		String orderBy = request.getParameter("columns[" + orderColIndex + "][data]");

		// For Pagination
		String start = request.getParameter("start");
		String size = request.getParameter("length");
		int page = Integer.parseInt(start) / Integer.parseInt(size);

		Pageable pageable = null;
		if (orderType != null && orderType.equalsIgnoreCase("asc")) {
			pageable = PageRequest.of(page, Integer.parseInt(size), Sort.by(Sort.Direction.ASC, orderBy));
		} else {
			pageable = PageRequest.of(page, Integer.parseInt(size), Sort.by(Sort.Direction.DESC, orderBy));
		}

		if (req.getId() != null) {
			if (searchParam == null || searchParam.isEmpty() || searchParam.equals("") || searchParam.trim().equals("")) {
				landOwner = landOwnerPageableRepository.findAllByRequisition(req, pageable);
			} else {
				landOwner = landOwnerPageableRepository.findAllByRequisitionAndSearchParamContainingIgnoreCase(req,
						searchParam, pageable);
			}
		} else {
			landOwner = null;
		}

		response.setObj(landOwner);
		return response;
	}

}
