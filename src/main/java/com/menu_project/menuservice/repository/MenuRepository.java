package com.menu_project.menuservice.repository;

import com.menu_project.menuservice.entity.menu_order.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository {
    List<Menu> findAllByOrderByCategoryAsc();
}
