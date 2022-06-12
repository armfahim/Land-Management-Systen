package com.odcl.lms.purchase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.odcl.lms.purchase.model.LandOwner;
import com.odcl.lms.purchase.model.Requisition;

@Repository
public interface LandOwnerPageableRepository extends PagingAndSortingRepository<LandOwner, Long> {
	Page<LandOwner> findAll(Pageable pageable);

	// Page<LandOwner> findAllByOwnerName(String ownerName, Pageable pageable);
	Page<LandOwner> findByOwnerNameContainingIgnoreCase(String ownerName, Pageable pageable);

	Page<LandOwner> findAllByRequisition(Requisition req, Pageable pageable);

	@Query(value = "SELECT id,owner_name,owner_name as ownername,date_of_birth,age,owned_Land,attorney,nid,photo_Path,ownership_Type,req_id,total_records,created_at,created_by,updated_at,updated_by FROM land_owner WHERE req_id = :#{#req.id} AND lower(owner_name) like lower(concat('%', :#{#searchParam},'%')) OR req_id = :#{#req.id} AND lower(attorney) like lower(concat('%', :#{#searchParam},'%')) OR req_id = :#{#req.id} AND lower(ownership_Type) like lower(concat('%', :#{#searchParam},'%'))", nativeQuery = true)
	Page<LandOwner> findAllByRequisitionAndSearchParamContainingIgnoreCase(@Param("req") Requisition req,
			@Param("searchParam") String searchParam, Pageable pageable);

}
