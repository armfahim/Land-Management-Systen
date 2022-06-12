package com.odcl.lms.setup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odcl.lms.setup.model.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

}
