package com.menu_project.menuservice.util;


import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

/**
 * jpa를 사용할 때 boolean 타입을 선언하면 DB에는 BIT타입(0과 1)으로 저장한다.
 * boolean 타입을 true, false로 저장될 수 있게 만든다.
 */

@Convert
public class JpaBooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        // 입력받은 값이  null이 아니고, false가 아닐때
        if(attribute != null && attribute){
            return "TRUE";
        }
        return "FALSE";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        // 대소문자 관련없이 문자열 비교
        if("TRUE".equalsIgnoreCase(dbData)){
            return true;
        }
        return false;
    }
}
