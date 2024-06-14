package springex.mapper;

import com.springex.domain.TodoVO;
import com.springex.dto.PageRequestDTO;
import com.springex.mapper.TodoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)// 스프링컨테이너와 관련된 기능을 테스트에서 사용할수 있게 해준다.
// 테스트 클래스내에서 스프링 빈을 사용할수 있도록 한다. 스프링 애플리케이션의 설정을 테스트 환경에
// 가져오고 테스트 할수 있도록 동작을 검증할수 있다.
@ContextConfiguration(locations ="file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {


    @Autowired(required = false)// TodoMapper가 존재하지 않아도 객체를 사용할수 있도록 도와준다.
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert() {

        TodoVO todoVO = TodoVO.builder()
                .title("스프링테스트")
                .dueDate(LocalDate.of(2024, 12, 12))
                .writer("user22")
                .build();
        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectAll() {

        List<TodoVO> voList = todoMapper.selectAll();

        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectOne() {

        TodoVO todoVO = todoMapper.selectOne(3L);

        log.info(todoVO);

    }
    @Test
    public void testSelectList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)// 가져올 데이터 수
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo -> log.info(vo));
    }
    @Test
    public void testSelectSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
//                .types(new String[]{"t","w"})
//                .keyword("스프링")
//                .finished(true)
                .from(LocalDate.of(2024,6,14))
                .to(LocalDate.of(2024,6,15))
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo -> log.info(vo));
    }
}