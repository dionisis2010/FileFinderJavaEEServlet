package ru.dionisis.homework12.servlet;

import com.sun.org.glassfish.gmbal.ParameterNames;
import ru.dionisis.homework12.filefinder.FileFinder;
import ru.dionisis.homework12.filefinder.ListFiles;
import ru.dionisis.homework12.fileformat.FileFormat;
import ru.dionisis.homework12.fileformat.FileStringFormat;

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
    ListFiles fileFinder;

    @Override
    @ParameterNames({"depth", "format"})
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String param = req.getParameter("depth");
        int depth = param != null ? Integer.parseInt(param) : 0;

        FileFormat fileNameFormat = FileStringFormat.get(req.getParameter("format"));

        resp.setStatus(200);
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
                fileFinder.getFiles(FileFinder.HOME_DIR, depth, fileNameFormat).forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
