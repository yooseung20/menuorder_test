package com.menu_project.menuservice.repository;

import com.menu_project.menuservice.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;


// JpaRepository -> findAll, save 사용가능
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // userPhone을 기준으로 user 정보를 가져온다.
    Optional<User> findByphonenumber(String userPhone);
}