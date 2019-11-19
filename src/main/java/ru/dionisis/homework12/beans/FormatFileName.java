package ru.dionisis.homework12.beans;

import java.io.File;


public interface FormatFileName {

    /**
     * форматирует файл в строку
     * @param depth глубина вложенности файла
     */
    String format(File file, int depth);
}

