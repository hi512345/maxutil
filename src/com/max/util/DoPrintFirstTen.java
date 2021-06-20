package com.max.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DoPrintFirstTen extends DoPrintFileAdaptor {
    protected static DoPrintFirstTen instance = null;
    protected static DoPrintFirstTen getInstance(String description){
        if(instance == null)  {
            instance = new DoPrintFirstTen(description);
        }
        return instance;
    };
    protected DoPrintFirstTen(String description) {
        super(description);
    }

    @Override
    public int doFile(File file) throws FileNotFoundException {
        String returnVal = "";
        String currentLine;
        int indexmod = 0;
        Scanner read = new Scanner(file);
        currentLine = read.nextLine();
        for(int g = 0; g < 9; g++){
            if(currentLine.length() > g - indexmod + 1){
                returnVal = returnVal + currentLine.charAt(g - indexmod);
            } else if (read.hasNextLine()){
                indexmod = g;
                currentLine = read.nextLine();
            } else{
                break;
            }
        }
        System.out.print(returnVal + "\t\t\t");
        System.out.println(file.getAbsolutePath());
        return 0;
    }

    @Override
    public int doFolder(File folder) {
        System.out.println(folder.getAbsolutePath());
        return 0;
    }
}
