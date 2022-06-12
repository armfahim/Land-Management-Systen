package com.odcl.lms.setup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odcl.lms.setup.model.District;
import com.odcl.lms.setup.model.Thana;

@Repository
public interface ThanaRepository extends JpaRepository<Thana, Long> {

	List<Thana> findAllByDistrict(District district);

}
