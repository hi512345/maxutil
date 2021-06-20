package com.max.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //main001(args);
        //main002(args);
        //main003(args);
        //main004(args);
        //main005(args);
        //main006(args);
        main007(args);
    }


//    private static void main001(String[] args) {
//        FileUtil001 util = new FileUtil001();
//        int ln = args.length;
//        System.out.println("args length = " + ln);
//
//        File file0 = new File(args[0]);
//        System.out.println("File is " + ((util.isFolder(file0))? "Folder" : "File"));
//
//        String fileName = args[0];
//        String message = args[1];
//        File file = new File(fileName);
//        try {
//            util.saveData(file, message);
//            System.out.println("Write data successfully");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        File file2 = new File(args[0]);
//        String data = util.readData(file2);
//        System.out.println("Data read is " + data);
//
//
//        File file3 = new File("c:\\tmp\\maxtess");
//        util.insideDirectory(file3);
//    }
//
//    private static void main002(String[] args) {
////        File file = new File(args[0]);
////        FileUtil003 util = new FileUtil003();
////        util.printFilesStack(file);
//    }
//
//    private static void main003(String[] args) {
//        File file = new File(args[0]);
//        FileUtil003 util = new FileUtil003();
//        util.printFileRecursion(file);
//    }

//    private static void main004(String[] args) {
//        File file = new File(args[0]);
//        FileUtil004 util = new FileUtil004();
//        util.printFileRecursion(file);
//        System.out.println("_____________________________________");
//        util.printFileLength(file);
//        System.out.println("_____________________________________");
//        try {
//            util.printFileFirst10(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("_____________________________________");
//        try {
//            util.printFileFirstXor(file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //util.printFileFirsMd5(file);
//        //util.printFileFirstShaThree256(file);
//    }

    private static boolean isValidArgs(String[] args) {
        if (2 != args.length) {
            return false;
        } else if (!args[0].startsWith("--")) {
            return false;
        } else {
            return true;
        }
    }

    private static void printUsage(String[] args) {
        System.out.println("Usage: java -cp {class_path} com.max.util.Main  --{command} {folderPath}");
        System.out.println("List of Commands:");
        System.out.println("name       - Print out file name");
        System.out.println("length     - Shows file length");
        System.out.println("xor        - Xor all character in the file in hex format");
        System.out.println("firstTen   - Show the first ten characters of the files");
        System.out.println("Sha-256    - Shows the sha-256 digest");
        System.out.println("Md5        - Shows the Md5 digest");
        System.out.println("copy       -copy a single file");
    }
//    private static void main005(String[] args) {
//        if (!isValidArgs(args)) {
//            printUsage(args);
//            return;
//        }
//        String command = args[0].substring(2); //2 is length of "--"
//        String folderName =args[1];
//        System.out.println("command="  + command );
//        System.out.println("folderName="  + folderName) ;
//
//        File parentFolder = new File(folderName);
//        FileUtil005 util = new FileUtil005();
//        Visitable visitor = CommandFactory.getCommand(command);
//        if (null == visitor) {
//            printUsage(args);
//            return;
//        }
//
//        try {
//            visitor.printDescription();
//            util.processFiles(parentFolder, visitor);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Visitable visitor2 = CommandFactory.getCommand(command);
//
//        //util.printFileFirsMd5(file);
//        //util.printFileFirstShaThree256(file);
//    }
    private static void main006(String[] args) {
        Scanner input = new Scanner(System.in);
        if (!isValidArgs(args)) {
            printUsage(args);
            return;
        }
        String command = args[0].substring(2); //2 is length of "--"
        String folderName =args[1];
        System.out.println("command="  + command );
        System.out.println("folderName="  + folderName) ;

        File parentFolder = new File(folderName);
        FileUtil006 util = new FileUtil006();
        if(!command.equals("copy")) {
            Visitable visitor = CommandFactory.getCommand(command);

            if (null == visitor) {
                printUsage(args);
                return;
            }

            try {
                visitor.printDescription();
                util.processFiles(parentFolder, visitor);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("where do you want to copy the file");
            String reply = input.nextLine();
            File destionation = new File(reply);
            try {
                util.copyfile(parentFolder,destionation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void main007(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
        FileUtil007 util = new FileUtil007();
        SqlTable Table = util.fileTableInit(false, "file_info", "jdbc:mysql://localhost:3306/max_work", "Qawert432$#@", "max");
        try {
            util.storeAllInside(new File(args[0]), Table);
            System.out.println("---------------");
            util.findFile("*", "*", Table);
            System.out.println("---------------");
            util.filetableout(true, Table);
            System.out.println("---------------");
            FileInfo Info = new FileInfo("testpath", "test", (long)123, "testsha", "testmd5");
            System.out.println("---------------");
            util.addFileInfo(Info,Table);
            System.out.println("---------------");
            FileInfo info = util.findFile("C:\\tmp\\maxtess - Copy\\d2\\d21", "f211.txt", Table);
            System.out.println(info.getFileName());
            System.out.println("---------------");
            util.filetableout(true, Table);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
