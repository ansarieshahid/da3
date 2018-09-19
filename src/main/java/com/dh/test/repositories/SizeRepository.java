package com.dh.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.test.models.Size;
import com.dh.test.models.Store;
import com.dh.test.models.MetaStatus;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long>{
	List<Size> findByStoreAndStatus(Store store, MetaStatus status);
}