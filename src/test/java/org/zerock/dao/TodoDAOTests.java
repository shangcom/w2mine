package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w2mine.dao.TodoDAO;
import org.zerock.w2mine.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {
    private TodoDAO todoDAO;

    @BeforeEach
    public void ready() {
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception {
        System.out.println(todoDAO.getTime());
    }

    @Test
    public void testInsert() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .title("Insert() 테스트........")
                .dueDate(LocalDate.of(2024, 12, 31))
                .build();
        todoDAO.insert(todoVO);
    }

    @Test
    public void testList() throws Exception {
        List<TodoVO> list = todoDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void testSelectOne() throws Exception {
        Long tno = 1L;
//        Long tno = 5L; // 없는 tno 번호로 하면 java.sql.SQLDataException: wrong row position

        TodoVO vo = todoDAO.selectOne(tno);
        System.out.println(vo);
    }

    @Test
    public void testDeleteOne() throws Exception {
        Long tno = 2L;
        todoDAO.deleteOne(tno);
    }

    @Test
    public void testUpdateOne() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("update test...")
                .dueDate(LocalDate.of(2033, 12, 31))
                .finished(true)
                .build();

        todoDAO.updateOne(todoVO);
    }
}
