package com.springex.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
// 특정한 기간에만 SampleDAO를 다른 객체로 변경해야 하는 경우
@Qualifier("event")
public class EventSampleDAOImpl implements SampleDAO {

}
