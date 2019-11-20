package ru.dionisis.homework12.filefinder;

import ru.dionisis.homework12.fileformat.FileFormat;
import ru.dionisis.homework12.fileformat.FileStringFormat;

import javax.ejb.Stateless;
import java.io.File;
import java.util.*;

/**
 * bean класс для получения дерева файлов из текущего проекта
 */
@Stateless
public class ListFiles{

    private FileFormat format;
    private int depth;
    private int maxDepth;

    public List<String> getFiles(File rootFile, int depth) {
        return getFiles(rootFile, depth, FileStringFormat.DEFAULT);
    }


    /**
     * @param rooFile дирректория из которой нужно вернуть список файлов (по умолчанию - домашний каталог пользователя)
     * @param maxDepth максимальная глубина поиска по вложенным дирректориям (если равно 0, то глубина поиска
     *                 не ограничивается)
     * @param format паттерн форматирования для каждого файла (по умолчанию, выводится имя файла со смещением
     *                       на количество символов, равное глубине вложенности этого файла)
     * @return список отформатированных строк
     */
    public List<String> getFiles(File rooFile, int maxDepth, FileFormat format) {
        this.depth = 0;
        this.maxDepth = maxDepth;
        this.format = format;

        return getListFiles(new ArrayList<>(), rooFile);
    }

    private List<String> getListFiles(List<String> fileTree, File file) {
        fileTree.add(format.format(file, depth));
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return fileTree;
            } else {
                Arrays.stream(files).forEach(currentFile -> {
                    if (maxDepth != 0 && depth >= maxDepth) {
                        return;
                    }
                    depth++;
                    getListFiles(fileTree, currentFile);
                    depth--;
                });
            }
        }
        return fileTree;
    }

}



