package ru.dionisis.homework12.servlet;

import ru.dionisis.homework12.beans.MyBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * сервлет, который выводит на любой гет запрос возвращает дерево фалов из проекта
 */
@WebServlet("/servlet")
public class MyServlet extends HttpServlet {

    @EJB
    MyBean myBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        PrintWriter writer = resp.getWriter();
        myBean.getFiles().forEach(writer::println);
        writer.close();
    }
}
