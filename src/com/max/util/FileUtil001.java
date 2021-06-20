package com.max.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;


public class FileUtil001 {
    public boolean saveData(File file, String data) throws IOException {
        FileWriter write = new FileWriter(file.getPath());
        write.write(data);
        write.close();
        return true;
    }
    public String readData(File file){
        String a = "";
        File myObj = new File(file.getPath());
        try {
            Scanner myReader = new Scanner(myObj);
            a = myReader.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return a;
    }
    public boolean isFolder(File file){
        return file.isDirectory();
    }
    public HashMap<String, String> insideDirectory(File file){
        HashMap<String, String> list = new HashMap<String, String>();
        if(file.isDirectory()){
            for (int i = 0; i<file.listFiles().length; i++){
                System.out.println("File TYpe: " + (file.listFiles()[i].isDirectory()? "Folder" : "File")
                        + "\t\t" + "File Path:" + file.listFiles()[i].getName()
                );

                System.out.print(file.listFiles()[i]);
            }

        }
        return list;
    }
}

