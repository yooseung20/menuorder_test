package com.menu_project.menuservice.util.error_Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // User
    PRIVACY_AGREE_FALSE("privacy", "PRIVACY_AGREE_FALSE", "이용약관, 개인정보 수집에 동의하지 않으면 회원가입할 수 없습니다.", HttpStatus.BAD_REQUEST),
    DUPLICATED_MEMBER("duplication", "DUPLICATED_MEMBER", "이미 가입된 회원입니다.", HttpStatus.BAD_REQUEST),
    MEMBER_NOT_FOUNDED("phone", "MEMBER_NOT_FOUNDED","존재하지 않은 회원입니다.", HttpStatus.BAD_REQUEST);

    private String cause;
    private String code;
    private String message;
    private HttpStatus httpStatus;


    ErrorCode(String cause, String code, String message, HttpStatus httpStatus) {
        this.cause = cause;
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }


}
