package com.odcl.lms.setup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odcl.lms.setup.model.DagInformation;

@Repository
public interface DagInfoRepository extends JpaRepository<DagInformation, Long> {
	List<DagInformation> findById(DagInformation dagInfo);
}
