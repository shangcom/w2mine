package org.zerock.w2mine.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2mine.dto.TodoDTO;
import org.zerock.w2mine.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
            log.info("todoDTO : " + todoDTO);

            // 모델 담기
            req.setAttribute("dto", todoDTO);

            // 쿠키 찾기
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodoCookie.getValue();
            boolean exist = false;

            if (todoListStr != null && todoListStr.indexOf(tno + "-") >= 0) {
                exist = true;
            }
            log.info("exist : " + exist);

            if (!exist) {
                todoListStr += tno + "-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60 * 60 * 24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) {

        Cookie targetCookie = null;

        // 전체 쿠키 배열 중, 인자로 주어진 쿠키 이름(viewTodos)과 같은 이름의 쿠키가 있으면, targetCookie에 대입하고 반복문 벗어난 뒤 targetCookie 반환.
        if (cookies != null && cookies.length > 0) {
            for (Cookie ck : cookies) {
                if (ck.getName().equals(cookieName)) {
                    targetCookie = ck;
                    break;
                }
            }
        }

        // 쿠키 배열이 비어있거나, 같은 이름(viewTodos)의 쿠키가 없는 경우, 인자로 주어진 이름(viewTodos)으로 새로운 쿠키를 만들고 반환한다.
        if (targetCookie == null) {
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }
        return targetCookie;
    }
}
