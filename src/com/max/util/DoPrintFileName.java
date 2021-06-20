package com.max.util;

import java.io.File;
import java.util.function.DoublePredicate;

public class DoPrintFileName implements Visitable {

    protected static DoPrintFileName instance = null;

    public static DoPrintFileName getInstance(String description) {
        if (null == instance) {
            instance = new DoPrintFileName(description);
        }
        return instance;
    }

    private String title;

    protected DoPrintFileName(String title) {
        this.title = title;
    }

    @Override
    public void printDescription() {
        System.out.println(title);
    }

    @Override
    public int doFile(File file) {
        System.out.print("File\t\t\t");
        System.out.println(file);
        return 0;
    }

    @Override
    public int doFolder(File folder) {
        System.out.print("Folder\t\t\t");
        System.out.println(folder);
        return 0;
    }
}
