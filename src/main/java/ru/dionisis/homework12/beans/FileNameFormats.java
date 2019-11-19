package ru.dionisis.homework12.beans;

import java.io.File;

public enum FileNameFormats implements FormatFileName {
    /**
     * возврращает имя фала обернутое в html теги <pre></pre> с добавлением пробелов, количество которых соответсвует
     * параметру depth
     */
    DEF_FORMAT{
        @Override
        public String format(File file, int depth) {
            return "<pre>" + tabulate(depth) + file.getName() + "</pre>\n";
        }
        String tabulate(int depth) {
            StringBuilder tabs = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                tabs.append(' ');
            }
            return tabs.toString();
        }
    }
}
