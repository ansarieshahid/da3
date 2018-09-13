package com.dh.test;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTrackRepository extends JpaRepository<TimeTrack, Long>{
	@Query(value = "SELECT CURRENT_TIMESTAMP;", nativeQuery = true)
	Timestamp getCurrentTime(); 
	
	@Query(value = "SELECT CAST(EMPLOYEE_ID AS INT) AS EMPLOYEE_ID, CONCAT_WS(' ~ ', PERIOD_START, PERIOD_END), SUM(HOURS_WORKED) * rate " + 
			"FROM " + 
			"(" + 
			"SELECT T.*, CONCAT_WS('/', YEAR(DATE), LPAD(MONTH(DATE), 2, 0), CASEWHEN((DAY(DATE) - 15) >= 0, 16, 1)) AS Period_Start, CONCAT_WS('/', YEAR(DATE), LPAD(MONTH(DATE), 2, 0), CASEWHEN((DAY(DATE) - 15) >= 0, (SELECT DAY(TIMESTAMPADD(DAY, -DAY(TIMESTAMPADD(MONTH,1,DATE)), TIMESTAMPADD(MONTH,1,DATE)))), 15)) AS Period_End, R.rate " + 
			"FROM TIME_TRACK T LEFT JOIN rates R ON T.JOB_GROUP = R.JOB_GROUP" + 
			") GROUP BY Period_Start, Period_End, EMPLOYEE_ID, rate " +
			"ORDER BY EMPLOYEE_ID,  PERIOD_START", nativeQuery = true)
	List<Object> getReports();
}