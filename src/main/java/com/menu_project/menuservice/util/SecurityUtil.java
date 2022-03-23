package com.menu_project.menuservice.util;

import com.menu_project.menuservice.vo.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Optional;
// Security Context의 Authentication 객체를 이용해 username을 리턴해주는 간단한 유틸성 메서드
public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil() {
    }

    public static Optional<String> getCurrentUserPhone() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String userPhone = null;
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails springSecurityUser = (CustomUserDetails) authentication.getPrincipal();
            userPhone = springSecurityUser.getUsername();  // 휴대폰 번호가 return 되도록 설정
        } else if (authentication.getPrincipal() instanceof String) {
            userPhone = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(userPhone);
    }
}