package com.max.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;


public class FileUtil005 {
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
    public void printFilesStack(File file){
        Stack<String> stack = new Stack<String>();
        stack.push(file.getAbsolutePath());
        File save = new File(file.getAbsolutePath());
        int directoryNum = 1;
        while ( directoryNum > 0) {

            save = new File(stack.pop());
            directoryNum--;
            for(int g = 0; g<save.listFiles().length; g++) {
                System.out.println(save.listFiles()[g].getAbsolutePath());
                if (save.listFiles()[g].isDirectory()) {
                    stack.push(save.listFiles()[g].getAbsolutePath());
                    directoryNum++;
                }
            }

        }
    }
    public void processFiles(File parentFolder, Visitable visitor) throws FileNotFoundException {
        for(int i = 0; i < parentFolder.listFiles().length; i++){
            if (parentFolder.listFiles()[i].isDirectory()) {
                visitor.doFolder(parentFolder.listFiles()[i]);
                this.processFiles(parentFolder.listFiles()[i], visitor);
            } else {
                visitor.doFile(parentFolder.listFiles()[i]);
            }
        }
    };





    public void printFileRecursion(File file){
        System.out.println("Print out file name");
        for(int i = 0; i < file.listFiles().length; i++){
            File f = file.listFiles()[i];
            System.out.print((file.listFiles()[i].isDirectory()? "Folder" : "File") + "\t\t\t");
            System.out.println(file.listFiles()[i]);
            if (file.listFiles()[i].isDirectory()){
                printFileRecursion(file.listFiles()[i]);
            }
        }
    };

    public void printFileLength(File file){
        System.out.println("Calculate file length");
        for(int i = 0; i < file.listFiles().length; i++){
            if (file.listFiles()[i].isDirectory()){
                printFileLength(file.listFiles()[i]);
                System.out.println(file.listFiles()[i].getAbsolutePath());
            } else {
                System.out.println(file.listFiles()[i] + "\t\t\t" + file.listFiles()[i].length());

            }
        }
    }

    public void printFileFirst10(File file) throws Exception{
        System.out.println("Print out first 10 bytes of file");
        String returnVal = "";
        String currentLine;
        int indexmod = 0;
        for(int i = 0; i < file.listFiles().length; i++){
            System.out.print(file.listFiles()[i]);
            if (file.listFiles()[i].isDirectory()){
                System.out.println("");
                printFileFirst10(file.listFiles()[i]);
            } else{
                Scanner read = new Scanner(file.listFiles()[i]);
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
                System.out.println("\t\t\t" + returnVal);
            }
        }

    }

    public void printFileFirstXor(File file) throws Exception{
        System.out.println("Calculate XOR of file");
        String returnVal = "";
        String currentLine;
        int indexmod = 0;
        boolean firstchar = true;
        for(int i = 0; i < file.listFiles().length; i++){
            System.out.print(file.listFiles()[i]);
            if (file.listFiles()[i].isDirectory()){
                System.out.println("");
                printFileFirstXor(file.listFiles()[i]);
            } else{
                Scanner read = new Scanner(file.listFiles()[i]);
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
                System.out.println("\t\t\t" + returnVal);
            }
        }
    }

    public void printFileFirsMd5(File file){
        System.out.println("Produce MD5 of file");
        for(int i = 0; i < file.listFiles().length; i++){
            System.out.print((file.listFiles()[i].isDirectory()? "Folder" : "File") + "\t\t\t");
            System.out.println(file.listFiles()[i]);
            if (file.listFiles()[i].isDirectory()){
                printFileRecursion(file.listFiles()[i]);
            }
        }
    }

    public void printFileFirstShaThree256(File file){
        System.out.println("Produce Sha3-256 of file");
        for(int i = 0; i < file.listFiles().length; i++){
            System.out.print((file.listFiles()[i].isDirectory()? "Folder" : "File") + "\t\t\t");
            System.out.println(file.listFiles()[i]);
            if (file.listFiles()[i].isDirectory()){
                printFileRecursion(file.listFiles()[i]);
            }
        }
    }

}

