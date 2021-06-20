package com.max.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public boolean saveData(File file, String data) throws IOException {
        FileWriter write = new FileWriter(file.getPath());
        return true;
    }
    public String readData(File file){

        return "";
    }
    public boolean isFolder(File file){

        return true;
    }
}
