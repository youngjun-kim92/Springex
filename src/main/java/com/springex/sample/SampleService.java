package com.springex.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@ToString  // 객체를 간편하게 문자열로 표현
@Service  // 서비스의 대한 내용
@RequiredArgsConstructor  // 알아서 SampleService 생성자를 만들 때 sampleDAO를 파라미터로 받는 생성자를 생성한다.
public class SampleService {

    @Qualifier("normal")
    private final SampleDAO sampleDAO;

}
