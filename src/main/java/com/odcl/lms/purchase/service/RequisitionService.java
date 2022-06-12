package com.odcl.lms.purchase.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.odcl.lms.common.utils.BasicAudit;
import com.odcl.lms.common.utils.Response;
import com.odcl.lms.purchase.enumeration.PurchaseRequisitionStatus;
import com.odcl.lms.purchase.model.Requisition;
import com.odcl.lms.purchase.repository.RequisitionRepository;

/**
 * 
 * @author A.R.M. Fahim
 * @since
 */
@Service
public class RequisitionService {

	@Autowired
	RequisitionRepository requisitionRepository;

	/**
	 * @param requisition instance
	 * @return instance
	 */
	public Requisition create(Requisition requisition) {
		BasicAudit.setAttributeForCreateUpdate(requisition, "create");
		requisition.setPresentStatus(PurchaseRequisitionStatus.Draft);
		return this.requisitionRepository.save(requisition);
	}

	public Requisition findLatestRequisition() {
		return requisitionRepository.findFirstByOrderByRequisitionNoDesc();
	}

	public ResponseEntity<Map<String, Object>> findAll(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Requisition> page = requisitionRepository.findAll(paging);
		List<Requisition> listData = page.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("objectList", listData);
		response.put("currentPage", page.getNumber());
		response.put("totalPages", page.getTotalPages());
		response.put("totalItems", page.getTotalElements());

//        response.put("sortField", ps.sortField);
//        response.put("sortDir", ps.sortDir);
//        response.put("reverseSortDir", (ps.sortDir.equals("asc") ? "desc" : "asc"));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public Page<Requisition> findByStatus(PurchaseRequisitionStatus presentStatus, Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		return requisitionRepository.findByPresentStatusOrderByRequisitionNoDesc(presentStatus, paging);
	}

	public Optional<Requisition> findById(Long id) {
		System.out.println(requisitionRepository.findById(id).get().getDistrict());
		return requisitionRepository.findById(id);
	}

	public Response gridList(HttpServletRequest request) {
		Response response = new Response();
		String searchParam = request.getParameter("search[value]");
		String start = request.getParameter("start");
		String size = request.getParameter("length");
		String presentStatus = request.getParameter("presentStatus");

		int page = Integer.parseInt(start) / Integer.parseInt(size);
		Pageable pageable = PageRequest.of(page, Integer.parseInt(size));
		Page<Requisition> requisition;

		if (!presentStatus.isEmpty()) {
			PurchaseRequisitionStatus status = PurchaseRequisitionStatus.valueOf(presentStatus);
			if (searchParam == null || searchParam.isEmpty() || searchParam.equals("")
					|| searchParam.trim().equals("")) {
				requisition = requisitionRepository.findByPresentStatusOrderByRequisitionNoDesc(status, pageable);
			} else
				requisition = requisitionRepository.findByPresentStatusAndBrokerNameContainingIgnoreCase(status,
						searchParam, pageable);
		} else
			requisition = requisitionRepository.findAll(pageable);

		response.setObj(requisition);
		return response;
	}

	public Requisition update(Requisition requisition) {
		BasicAudit.setAttributeForCreateUpdate(requisition, "update");
		return requisitionRepository.save(requisition);
	}

	public Map<String, Object> deleteById(Long id) {
		Map<String, Object> status = new HashMap<>();
		try {
			requisitionRepository.deleteById(id);
			status.put("deleteStatus", true);
		} catch (Exception e) {
			status.put("deleteStatus", false);
			status.put("errorMsg", e.getMessage() + "id:" + id);
		}
		return status;
	}
}
