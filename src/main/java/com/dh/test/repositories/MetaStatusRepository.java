package com.dh.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.test.models.MetaStatus;

@Repository
public interface MetaStatusRepository extends JpaRepository<MetaStatus, Long>{
	MetaStatus findByCode(String code);
}