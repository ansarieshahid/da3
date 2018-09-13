package com.dh.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TTReportRepository extends JpaRepository<TTReport, Long>{
	boolean existsByReportId(String reportId);
}