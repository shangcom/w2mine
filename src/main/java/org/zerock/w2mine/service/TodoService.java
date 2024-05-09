package org.zerock.w2mine.service;


import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.w2mine.dao.TodoDAO;
import org.zerock.w2mine.domain.TodoVO;
import org.zerock.w2mine.dto.TodoDTO;
import org.zerock.w2mine.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
//        System.out.println("todoVO : " + todoVO);
        log.info("todoVO : " + todoVO);
        dao.insert(todoVO);
    }

    public List<TodoDTO> listAll() throws Exception {

        List<TodoVO> voList = dao.selectAll();

        log.info("voList.................");
        log.info("TodoService - listAll()의 voList() : " + voList);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public TodoDTO get(Long tno) throws Exception {
        log.info("TodoService get(" + tno + ")에서 dao.selectOne(tno) 호출..............");
        TodoVO todoVO = dao.selectOne(tno);
        log.info("todoVO : " + todoVO);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    public void remove(Long tno) throws Exception {
        log.info("todoService의 remove(" + tno + ")에서 dao.deleteOne(tno) 호출...................");
        dao.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO) throws Exception {
        log.info("todoService의 modify(" + todoDTO + ")에서 dao.updateOne(todoVO) 호출................");
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(todoVO);
    }
}
