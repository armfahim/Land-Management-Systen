package com.odcl.lms.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odcl.lms.setup.model.KhatianType;

@Repository
public interface KhatianTypeRepository extends JpaRepository<KhatianType, Long> {

}
