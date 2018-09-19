package com.dh.test.repositories;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dh.test.models.Menu;
import com.dh.test.models.MetaStatus;
import com.dh.test.models.Store;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{
	List<Menu> findByStatusOrderBySortOrderAsc(MetaStatus status);
	List<Menu> findByStoreAndStatusOrderBySortOrderAsc(Store store, MetaStatus status);
	List<Menu> findByStoreAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderBySortOrderAsc(Store store, MetaStatus status, Date date, Date date1);
	@Query(value = "SELECT CURRENT_TIMESTAMP;", nativeQuery = true)
	Timestamp getCurrentTime();
}