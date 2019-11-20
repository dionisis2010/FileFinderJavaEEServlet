package ru.dionisis.homework12.fileformat;

import java.io.File;
import java.security.InvalidParameterException;

public enum FileStringFormat implements FileFormat {
    /**
     * возврращает имя фала обернутое в html теги <pre></pre> с добавлением пробелов, количество которых соответсвует
     * параметру depth
     */
    DEFAULT {
        String name = "default";

        public String getName() {
            return name;
        }

        @Override
        public String format(File file, int depth) {
            return "<p style=\"margin-left: " + depth * 20 + "px\">"
                    + file.getName() + "</p>";
        }
    },
    LIST {
        String name = "list";

        public String getName() {
            return name;
        }

        @Override
        public String format(File file, int depth) {
            return "<br>" + file.getName();
        }
    },
    ABSOLUTE_LIST {
        String name = "absoluteList";

        public String getName() {
            return name;
        }

        @Override
        public String format(File file, int depth) {
            return "<br>" + file.getAbsolutePath();
        }

    };

    public static FileFormat get(String format) {
        if (format == null) {
            return DEFAULT;
        }
        switch (format) {
            case "default":
                return DEFAULT;
            case "list":
                return LIST;
            case "absoluteList":
                return ABSOLUTE_LIST;
            default:
                throw new InvalidParameterException();
        }
    }
}
