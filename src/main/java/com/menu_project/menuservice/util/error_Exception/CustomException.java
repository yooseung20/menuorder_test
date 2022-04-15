package com.menu_project.menuservice.util.error_Exception;

// exception에는 checked exception과 unchecked exception이 존재한다.
// checked exception은 컴파일 단계에서 확인 가능하고,
// 반드시 예외처리한다. RuntimeException에 포함되지 않는다.

// unchecked exception은 실행 단계에서 확인 가능하고,
// 예외처리는 선택이다. RuntionException의 하위클래스들이다.

// CustomException은 실행중에 발생할 것이므로 RuntiomeException으로 설정한다.

public class CustomException extends RuntimeException{

    private ErrorCode errorCode;
    private ErrorResponse errorResponse;

    public CustomException(ErrorCode errorCode) {

        this.errorCode = errorCode;
        this.errorResponse = new ErrorResponse(errorCode);
    }
    public ErrorResponse getErrorResponse(){
        return errorResponse;
    }

    // 주로 테스트에서 사용됨
    public ErrorCode getErrorCode(){
        return errorCode;
    }
}


