package com.max.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static com.sun.deploy.util.SystemUtils.getFileChecksum;

public class DoCalculateMd5 extends DoPrintFileAdaptor {
    protected static DoCalculateMd5 instance = null;
    protected static DoCalculateMd5 getInstance(String description){
      if(instance == null)  {
          instance = new DoCalculateMd5(description);
      }
      return instance;
    };
    protected DoCalculateMd5(String description) {
        super(description);
    }

    @Override
    public int doFile(File file) throws FileNotFoundException {
        String checksum = "";


//Use MD5 algorithm
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            checksum = getFileChecksum(file, "MD5");
        } catch (NoSuchAlgorithmException e){
            System.out.print("error");
        }


//Get the checksum


//see checksum
        System.out.print(file.getAbsolutePath() + "\t\t\t\t");
        System.out.println(checksum);

        return 0;
        /*
         *
//Use SHA-1 algorithm
MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");

//SHA-1 checksum
String shaChecksum = getFileChecksum(shaDigest, file);
         */
    }


    @Override
    public int doFolder(File folder) {
        System.out.println(folder.getAbsolutePath());
        return 0;
    }
}
