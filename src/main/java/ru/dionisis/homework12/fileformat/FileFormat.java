package ru.dionisis.homework12.fileformat;

import java.io.File;


public interface FileFormat {

    /**
     * форматирует файл в строку
     * @param depth глубина вложенности файла/дирректории
     */
    String format(File file, int depth);
}

