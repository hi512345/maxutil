package com.max.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.sun.deploy.util.SystemUtils.getFileChecksum;

public class DoCalculateSha256 extends DoPrintFileAdaptor {
    protected static DoCalculateSha256 instance = null;
    protected static DoCalculateSha256 getInstance(String description){
      if(instance == null)  {
          instance = new DoCalculateSha256(description);
      }
      return instance;
    };
    protected DoCalculateSha256(String description) {
        super(description);
    }

    @Override
    public int doFile(File file) throws FileNotFoundException {
        String checksum = "";


//Use Sha-256 algorithm
        try {
            MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
            checksum = getFileChecksum(file, "MD5");
        } catch (NoSuchAlgorithmException e){
            System.out.print("error");
        }


//Get the checksum


//see checksum
        System.out.print(file.getAbsolutePath() + "\t\t\t\t");
        System.out.println(checksum);

        return 0;
    }


    @Override
    public int doFolder(File folder) {
        System.out.println(folder.getAbsolutePath());
        return 0;
    }
}
