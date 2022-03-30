package com.menu_project.menuservice.config;


import com.menu_project.menuservice.dto.TokenDto;
import com.menu_project.menuservice.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


// session을 이용하지않고, jwt token 이용
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private SecurityContext securityContext;

    @Override
    // 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단한다.
    // @LoginUser 어노테이션이 붙어있고, 파라미터 클래스 타입이 TokenDto.class인 경우-> 수정하기
    public boolean supportsParameter(MethodParameter parameter){
            boolean isLoginUserAnnotation =
                parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = TokenDto.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    // resolveArgument
    // 파라미터에 전달할 객체를 생성한다.
    // 여기서는 SecurityContext에서 객체를 가져온다.
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderactory)
                    throws Exception {
            return securityContext.getAuthentication();

    }
}
