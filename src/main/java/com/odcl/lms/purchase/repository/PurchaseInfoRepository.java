package com.odcl.lms.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odcl.lms.purchase.model.PurchaseInfo;

@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfo, Long> {

}
