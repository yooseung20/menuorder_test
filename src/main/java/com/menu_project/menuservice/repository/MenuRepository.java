package com.menu_project.menuservice.repository;

import com.menu_project.menuservice.entity.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT p FROM Menu p ORDER BY p.id DESC")
    List<Menu> findAllDesc();
}

