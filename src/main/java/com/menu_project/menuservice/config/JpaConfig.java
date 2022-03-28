package com.menu_project.menuservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
// audit을 이용하면 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어줄 수 있다.
@EnableJpaAuditing // JPA Auditing 활성화
public class JpaConfig {
}
