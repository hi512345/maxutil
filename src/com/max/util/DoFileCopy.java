package com.max.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DoFileCopy extends DoPrintFileAdaptor {
    protected static DoFileCopy instance = null;
    protected static DoFileCopy getInstance(String description){
        if(instance == null)  {
            instance = new DoFileCopy(description);
        }
        return instance;
    };
    protected DoFileCopy(String description) {
        super(description);
    }

    @Override
    public int doFile(File file) throws FileNotFoundException {
        //file path is c:\tmp\maxtest Copy\d1\d12\abc.txt
        //copy to a hard coded folder c:\tmp\dest
        //target file will be c:\tmp\dest\d1\d12\abc.txt
//        File Old = new file("c:\\tmp\\dest\\d1\\d12\\abc.txt");
        return 0;
    }

    @Override
    public int doFolder(File folder) {
        System.out.println(folder.getAbsolutePath());
        return 0;
    }
}
