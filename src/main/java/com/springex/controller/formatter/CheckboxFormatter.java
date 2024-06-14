package com.springex.controller.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class CheckboxFormatter implements Formatter<Boolean>{

    @Override   // true or false 로 받아야 해서 Boolean
    public Boolean parse(String text, Locale locale) throws ParseException{
        // parse: String표현을 LocalDate객체로 파싱하는 역할을 한다.
        // Locale: 지역 또는 언와 관련된정보를 나타내는 java 클래스. html lang="ko"
        if(text == null) {  // 체크박스가 비어있으면,,, false
            return false;
        }

        return text.equals("on");// on값이 있으면 true 를 반환.
    }

    @Override
    public String print(Boolean object, Locale locale) {
        // true or false로 출력. 
        return object.toString();
    }
}