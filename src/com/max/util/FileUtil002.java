package com.max.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class FileUtil002 {
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
    protected String tabcount(String fullPath){
        String returnval = "";
        String[] paths = fullPath.split("\\\\");
        for(int i = 0; i < paths.length; i++){
            returnval = returnval + "\t";
        }
        return returnval;
    }
    public boolean isFolder(File file){
        return file.isDirectory();
    }
    public LinkedList[] insideDirectory(File file){
        LinkedList[] list = new LinkedList[2];
        LinkedList fileName = new LinkedList();
        LinkedList fileInfo = new LinkedList();
        String save1 = "";
        String save2 = "";
        String save4 = "";
        String save5 = "";
        File save3;
        boolean check = false;
        int directoryNum = 0;
        if(file.isDirectory()){
            for (int i = 0; i<file.listFiles().length; i++){
                System.out.println("File TYpe: " + (file.listFiles()[i].isDirectory()? "Folder" : "File")
                        + "\t\t" + "File Path:" + file.listFiles()[i].getName()
                );
                fileName.addFirst(file.listFiles()[i].getAbsolutePath());
                fileInfo.addFirst(file.listFiles()[i].isDirectory()? "Folder" : "File");

                if (file.listFiles()[i].isDirectory()){
                    directoryNum++;
                }

            }

                for (int i = 0; i<fileName.size(); i++) {
                    save3 = new File((String) fileName.get(i));
                    if (fileInfo.get(i) == "Folder" && save3.listFiles() != null){
                        for(int g = 0; g < save3.listFiles().length;g++){
                            save2 = save3.listFiles()[g].getAbsolutePath();
                            save5 = save3.listFiles()[g].isDirectory()? "Folder" : "File";
                            for(int h = i; h<fileName.size();h++){
                                try{
                                    if(fileName.get(h+1) != null || save3.listFiles() != null){
                                        check = true;
                                    }
                                }catch (Exception e){
                                    check = false;
                                }
                                if(check) {
                                    save1 = (String) fileName.get(h + 1);
                                    fileName.set(h + 1, save2);
                                    save2 = save1;
                                    save4 = (String) fileInfo.get(h);
                                    fileInfo.set(h + 1, save5);
                                    save5 = save4;
                                } else{
                                    fileName.add(h + 1, save2);
                                    fileInfo.add(h + 1, save5);
                                    break;
                                }
                            }
                        }

                    }
                    //fileName.set(i,tabcount(save3.getAbsolutePath()) + save3.getName());
                    fileName.set(i,tabcount(save3.getAbsolutePath()) + save3.getAbsolutePath());

                }


            list[0] = fileName;
            list[1] = fileInfo;

        }
        return list;
    }
}

