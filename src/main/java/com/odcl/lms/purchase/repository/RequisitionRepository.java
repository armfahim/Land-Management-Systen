package com.odcl.lms.purchase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.odcl.lms.purchase.enumeration.PurchaseRequisitionStatus;
import com.odcl.lms.purchase.model.Requisition;

/**
 * @author A.R.M. Fahim
 * 
 * 
 */
@Repository
public interface RequisitionRepository
		extends JpaRepository<Requisition, Long>, PagingAndSortingRepository<Requisition, Long> {

	Requisition findFirstByOrderByRequisitionNoDesc();

	Page<Requisition> findByPresentStatusOrderByRequisitionNoDesc(PurchaseRequisitionStatus status, Pageable paging);

	Page<Requisition> findByBrokerNameContainingIgnoreCase(String searchParam, Pageable pageable);

	Page<Requisition> findByPresentStatusAndBrokerNameContainingIgnoreCase(PurchaseRequisitionStatus status,
			String searchParam, Pageable pageable);
}
