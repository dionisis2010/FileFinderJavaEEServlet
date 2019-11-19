package ru.dionisis.homework12.servlet;

import com.sun.org.glassfish.gmbal.ParameterNames;
import ru.dionisis.homework12.beans.FileFinder;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * сервлет, который выводит на любой гет запрос возвращает дерево фалов из проекта
 * параметром depth можно указать глубину посика по вложенным каталогам
 */
@WebServlet("/servlet")
public class MyServlet extends HttpServlet {

    @EJB
    FileFinder fileFinder;

    @Override
    @ParameterNames("depth")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String param = req.getParameter("depth");
        int depth = param != null ? Integer.parseInt(param) : 0;

        resp.setStatus(200);
        try (PrintWriter writer = resp.getWriter()) {
            fileFinder.getFileTree(depth).forEach(writer::write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
