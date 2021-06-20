package com.max.util;

import java.io.File;

public class DoPrintFileLength extends DoPrintFileAdaptor {
    protected static DoPrintFileLength instance = null;

    public static DoPrintFileLength getInstance(String description) {
        if (null == instance) {
            instance = new DoPrintFileLength(description);
        }
        return instance;
    }

    protected DoPrintFileLength(String description) {
        super(description);
    }

    @Override
    public int doFile(File file) {
        System.out.println(file.length() + "\t\t\t" + file.getAbsolutePath());
        return 0;
    }

    @Override
    public int doFolder(File folder) {
        System.out.println("\t\t\t" + folder.getAbsolutePath());
        return 0;
    }
}
