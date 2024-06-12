package springex.sample;

import com.springex.sample.SampleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2
@ExtendWith(SpringExtension.class)  // JUnit5버전에서 spring-test를 이용하기 위한 설정, JUnit4버전 @RunWith
// 스프링의 설정 정보를 로딩하기 위해 사용. 현재 프로젝트의 경우 xml로 설정되어있어서 locations 속성을 이용,
// 자바 설정을 이용하는 경우에는 classes 속성을 사용
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class SampleTests {
    
    @Autowired  // 자동연결. 스프링에서 사용하는 의존성 주입 관련된 에너테이션
    // 만일 해당 타입의 빈(Bean)이 존재하면 여기에 주입해 주기를 원한다
    private SampleService sampleService;

    // root-context.xml에 선언된 HickariCP를 주입받기 위해서 DataSource의 변수를 선언하고 Autowired를 이용해서 주입받도록 구성
    @Autowired
    private DataSource dataSource;

    @Test
    public void testService1() {
        log.info(sampleService);
        Assertions.assertNotNull(sampleService);  // null이 아닌지 확인. null이 아니면 테스트 진행, null인 경우 테스트 실패.
    }

    @Test
    public void testConnection() throws Exception {

        Connection connection = dataSource.getConnection();
        log.info(connection);
        Assertions.assertNotNull(connection);

        connection.close();

    }

}
