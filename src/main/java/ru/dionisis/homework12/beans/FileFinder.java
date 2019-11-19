package ru.dionisis.homework12.beans;

import javax.ejb.Stateless;
import java.io.File;
import java.util.*;

/**
 * bean класс для получения дерева файлов из текущего проекта
 */
@Stateless
public class FileFinder {

    public static final File HOME_DIR = new File(System.getProperty("user.home"));
    private FormatFileName formatFileName;
    private int depth;
    private int maxDepth;

    public List<String> getFileTree() {
        return getFileTree(HOME_DIR, 0, FileNameFormats.DEF_FORMAT);
    }

    public List<String> getFileTree(int depth) {
        return getFileTree(HOME_DIR, depth, FileNameFormats.DEF_FORMAT);
    }

    /**
     * @param rooFile дирректория из которой нужно вернуть список файлов (по умолчанию - домашний каталог пользователя)
     * @param maxDepth максимальная глубина поиска по вложенным дирректориям (если равно 0, то глубина поиска
     *                 не ограничивается)
     * @param formatFileName паттерн форматирования для каждого файла (по умолчанию, выводится имя файла со смещением
     *                       на количество символов, равное глубине вложенности этого файла)
     * @return список отформатированных строк
     */
    public List<String> getFileTree(File rooFile, int maxDepth, FormatFileName formatFileName) {
        this.depth = 0;
        this.maxDepth = maxDepth;
        this.formatFileName = formatFileName;

        return getFiles(new ArrayList<>(), rooFile);
    }

    private List<String> getFiles(List<String> fileTree, File file) {
        fileTree.add(formatFileName.format(file, depth));
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
                    getFiles(fileTree, currentFile);
                    depth--;
                });
            }
        }
        return fileTree;
    }
}



