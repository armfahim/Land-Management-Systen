package com.odcl.lms.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odcl.lms.common.model.CommonData;

@Repository
public interface CommonDataRepository extends JpaRepository<CommonData, Long> {

}
