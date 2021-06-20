package com.max.util;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import static com.sun.deploy.util.SystemUtils.getFileChecksum;


public class FileUtil007 {
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

    public void copyfile(File file, File destination)throws IOException{

        if (!destination.exists() && !destination.canWrite()) {
            destination.delete();
        }
        boolean newFile = destination.createNewFile();
        InputStream is = new FileInputStream(file);
        OutputStream os = new FileOutputStream(destination);
        byte[] buffer = new byte[1024];
        int length = 0;
        while(0 < (length = is.read(buffer))){
            os.write(buffer,0, length);
        }
        is.close();
        os.close();
    };
    public void fileTableInit(String name){
        Connection connect = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/max_work?"
                            + "user=max&password=Qawert432$#@");

            // Statements allow to issue SQL queries to the database
            preparedStatement = connect.prepareStatement("create table ? ( ID int NOT NULL, filepath TEXT(255) NOT NULL, filesize LONG(255), sha-256 TEXT(255), TEXT(255), PRIMARY KEY (ID))");

        } catch(Exception e){
            e.printStackTrace();
        } finally {

        }
    };
    public void closeport(ResultSet resultSet, Statement statement, PreparedStatement preparedStatement, Connection connect){
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    };
    public SqlTable fileTableInit(boolean create, String lockedName, String url, String password, String username){
        SqlTable Table = new SqlTable();
        String[] Tableformat = new String[]{"ID","file_name", "folder","file_size","sha256","md5"};
        String[] TableformatType = new String[]{"INT(80)","TEXT(80)","TEXT(80)","TEXT(80)","TEXT(80)","TEXT(80)","PRIMARY KEY (ID)"};
        if (create){
            Table.create(lockedName, Tableformat, TableformatType, url, password, username);
        } else{
            Table.connect(lockedName, Tableformat, url, password, username);
        }
        return Table;
    }
    public long storeAllInside(File file, SqlTable Table) throws NoSuchAlgorithmException, IOException, AccessDeniedException {
        long count = 0;
        SqlTable FileTable = Table;
        for(int i = 0; i < file.listFiles().length; i++){
            System.out.println(file.listFiles()[i]);
            if (file.listFiles()[i].isDirectory()){
                storeAllInside(file.listFiles()[i], Table);
            } else{
                String checksum;
                String checksum2;
                MessageDigest md5Digest = MessageDigest.getInstance("MD5");
                md5Digest.update(Files.readAllBytes(Paths.get(file.listFiles()[i].getPath())));
                byte[] digest = md5Digest.digest();
                checksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
                MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
                shaDigest.update(Files.readAllBytes(Paths.get(file.listFiles()[i].getPath())));
                byte[] digest2 = shaDigest.digest();
                checksum2 = DatatypeConverter.printHexBinary(digest2).toUpperCase();
                Table.add(new String[]{file.listFiles()[i].getName(), file.listFiles()[i].getAbsolutePath(), String.valueOf(file.listFiles()[i].length()), checksum2, checksum},  new String[]{"file_name", "folder","file_size","sha256","md5"});
                count++;
            }
        }
        return count;
    };

    public ResultSet filetableout(boolean print, SqlTable Table) throws SQLException, NullPointerException {
        if(Table != null && Table.resultSet !=null) {
            if (print = true) {
                while (Table.resultSet.next()) {
                    for (int i = 0; i < Table.resultSet.getMetaData().getColumnCount(); i++) {
                        System.out.print(Table.resultSet.getString(i));
                    }
                    System.out.println("\r\n");
                }
            }
            return Table.resultSet;
        }
        return null;
    };

    public boolean addFileInfo(FileInfo fileInfo, SqlTable Table) {
        try{
            Table.add(new String[]{fileInfo.getFileName(), fileInfo.getFolder(), String.valueOf(fileInfo.getFileSize()), fileInfo.getSha256(), fileInfo.getMd5()},  new String[]{"file_name", "folder","file_size","sha256","md5"});
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public FileInfo findFile(String folder, String fileName, SqlTable Table) throws Exception{
        ResultSet resultSet = Table.search("file_name, folder, file_size, sha256, md5 ", "folder='" + folder + "' AND file_name='" + fileName + "'");
        FileInfo fileInfo = new FileInfo();
        if(resultSet != null && resultSet.next()) {
            fileInfo.setFileName(resultSet.getString(6));
            fileInfo.setFolder(resultSet.getString(5));
            fileInfo.setMd5(resultSet.getString(4));
            fileInfo.setSha256(resultSet.getString(3));
            fileInfo.setFileSize(resultSet.getInt(2));
        }
        return fileInfo;
    }
}

