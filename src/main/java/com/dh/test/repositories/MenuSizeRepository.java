package com.dh.test.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.test.models.Menu;
import com.dh.test.models.MenuSize;
import com.dh.test.models.MetaStatus;
import com.dh.test.models.Size;

@Repository
public interface MenuSizeRepository extends JpaRepository<MenuSize, Long>{
	Optional<MenuSize> findByMenuAndSizeAndStatus(Menu menu, Size size, MetaStatus status);
	List<MenuSize> findByMenuAndStatus(Menu menu, MetaStatus status);
}