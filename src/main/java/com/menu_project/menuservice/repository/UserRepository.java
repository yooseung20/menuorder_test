package com.menu_project.menuservice.repository;

import com.menu_project.menuservice.entity.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository -> findAll, save 사용가능
public interface UserRepository extends JpaRepository<User, Long> {
    // authrities 정보를 같이 가져옴
    //@EntityGraph(attributePaths = "authorities")
    // userPhone을 기준으로 user 정보를 가져옴
    Optional<User> findOneWithAuthoritiesByUserPhone(String userPhone);



    User findByUserPhone(String userPhone);

}