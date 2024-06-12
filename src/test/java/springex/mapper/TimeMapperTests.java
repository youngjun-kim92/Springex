package springex.mapper;

import com.springex.mapper.TimeMapper;
import com.springex.mapper.TimeMapper2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTests {

    // 해당 객체를 주입받지 못하더라도 예외가 발생하지 않는데,
    // 인텔리제이, 이클립스의 경우 @Service, @Repository와 같이 직접 스프링의 빈(Bean)으로 등록된 경우가 아니면 경고가 발생하므로 이를 방지하기 위해 사용된다
    @Autowired(required = false)
    private TimeMapper2 timeMapper2;

    @Test
    public void testGetTime() {
        log.info(timeMapper2.getNow());
    }
}