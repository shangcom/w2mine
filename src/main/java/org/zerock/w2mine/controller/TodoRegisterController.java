package org.zerock.w2mine.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2mine.dto.TodoDTO;
import org.zerock.w2mine.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoRegisterController", value = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register GET...................");

        HttpSession session = req.getSession();

        // 세션 신규 여부 판단.
        // 기존에 JSESSIONID가 없는 새로운 사용자. 즉, 사용자가 로그인하지 않고 직접 "/todo/register"로 접근
        if (session.isNew()) {
            log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
            resp.sendRedirect("/login"); // 사용자를 로그인 페이지로 이동시킴.
            return;  // 실행 종료. 메서드의 나머지 부분 실행하지 않음.
        }

        //JSESSIONID는 있지만 해당 세션 컨텍스트의 loginInfo라는 이름으로 저장된 객체가 없는 경우.
        //즉 JSESSIONID 쿠키는 있지만, 세션에 'loginInfo'와 같은 로그인 인증 정보가 저장되어 있지 않은 경우. 사용자는 사이트에 세션은 있지만, 실제로 로그인을 하지 않은 상태임.
        if(session.getAttribute("loginInfo") ==null) {
            log.info("로그인 정보가 없는 사용자");
            resp.sendRedirect("login");
            return;
        }

        //정상적인 경우라면 register.jsp로 이동.
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate")))
                .build();
        log.info("TodoRegister의 doPost.......................");
        log.info("todoDTO : " + todoDTO);

        try {
            todoService.register(todoDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/todo/list");
    }
}
