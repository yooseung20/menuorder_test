package com.menu_project.menuservice.entity.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT p FROM Menu p ORDER BY p.id DESC")
    List<Menu> findAllDesc();
}

