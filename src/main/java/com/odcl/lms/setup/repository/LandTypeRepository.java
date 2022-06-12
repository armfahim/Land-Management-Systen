package com.odcl.lms.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odcl.lms.setup.model.LandType;

@Repository
public interface LandTypeRepository extends JpaRepository<LandType, Long> {

}
