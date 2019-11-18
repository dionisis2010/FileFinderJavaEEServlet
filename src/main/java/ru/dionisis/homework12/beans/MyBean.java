package ru.dionisis.homework12.beans;

import javax.ejb.Stateless;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * bean класс для получения дерева файлов из текущего проекта
 */
@Stateless
public class MyBean {

    private static final File file = new File("C:/Users/user/IdeaProjects/homework12 - EJB");
    private final StringBuilder tabulationBuilder = new StringBuilder();

    /**
     * возвращает список всех файлов c добавлением пробелов, для визуализации вложенности
     */
    public List<String> getFiles() {
        List<String> files = new ArrayList<>();
        files = findFiles(files, file);
        return files;
    }

    private List<String> findFiles(List<String> files, File file) {
        Arrays.stream(file.listFiles())
                .forEach(f -> {
                    files.add(tabulationBuilder.toString() + f.getName());
                    if (f.isDirectory()) {
                        tabulationBuilder.append(' ');
                        findFiles(files, f);
                        tabulationBuilder.deleteCharAt(tabulationBuilder.length()-1);
                    }
                });
        return files;
    }
}
