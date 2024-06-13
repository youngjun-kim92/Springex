package com.springex.controller.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class CheckboxFormatter implements Formatter<Boolean> {

    @Override  // true or false 로 받아야해서 Boolean
    public Boolean parse(String text, Locale locale) throws ParseException {
        if(text == null) {
            return false;  // 체크박스가 비어있으면 false
        }

        return text.equals("on");  // "on"값이 있으면 true를 반환
    }

    @Override
    public String print(Boolean object, Locale locale) {
        // true or false로 출력
        return object.toString();
    }
}