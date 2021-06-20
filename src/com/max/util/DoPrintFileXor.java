package com.max.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DoPrintFileXor extends DoPrintFileAdaptor {
    protected static DoPrintFileXor instance = null;
    protected static DoPrintFileXor getInstance(String description){
      if(instance == null)  {
          instance = new DoPrintFileXor(description);
      }
      return instance;
    };
    protected DoPrintFileXor(String description) {
        super(description);
    }

    @Override
    public int doFile(File file) throws FileNotFoundException {
        String returnVal = "";
        String currentLine;
        int indexmod = 0;
        boolean firstchar = true;
        Scanner read = new Scanner(file);
        currentLine = read.nextLine();
        int g = 0;
        while(currentLine.length() > g - indexmod||read.hasNextLine()){
            if(currentLine.length() > g - indexmod + 1){
                if(firstchar){
                    returnVal = Character.toString( currentLine.charAt(g - indexmod));
                    firstchar = false;
                }else {
                    returnVal = Integer.toHexString( (char) (returnVal.charAt(0) ^  currentLine.charAt(g - indexmod)));
                }
            } else if (read.hasNextLine()){
                indexmod = g;
                currentLine = read.nextLine();
            } else{
                break;
            }
            g++;
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
