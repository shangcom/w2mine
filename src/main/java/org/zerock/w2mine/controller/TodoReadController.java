package org.zerock.w2mine.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2mine.dto.TodoDTO;
import org.zerock.w2mine.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoReadController", value = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE; // 바로 초기화 시켜줘야 한다.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));
            log.info("todoService.get(tno) 호출");

            TodoDTO todoDTO = todoService.get(tno);
            log.info("todoDTO : " +todoDTO);

            req.setAttribute("dto", todoDTO);
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }
}
