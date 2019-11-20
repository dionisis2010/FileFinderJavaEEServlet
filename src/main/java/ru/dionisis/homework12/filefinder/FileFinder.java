package ru.dionisis.homework12.filefinder;

import java.io.File;

public interface FileFinder<R> {

    File HOME_DIR = new File(System.getProperty("user.home"));

    R getFiles(File rootFile, int depth);
}
