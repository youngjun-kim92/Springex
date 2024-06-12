package com.springex.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    // getTime()을 호출하면 select문이 실행된다.
    @Select("select now()")
    String getTime();
}
