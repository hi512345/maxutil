package com.max.util;

public class CommandFactory {
    public static Visitable getCommand(String command) {
        Visitable visitor = null;
        if ("name".equals(command)) {
            visitor = DoPrintFileName.getInstance("Print out file name");
        } else  if ("firstTen".equals(command)) {
            visitor = DoPrintFirstTen.getInstance("Show the first ten characters of the files");
        }else  if ("length".equals(command)) {
            visitor = DoPrintFileLength.getInstance("Shows file length");
        }else  if ("xor".equals(command)) {
            visitor = DoPrintFileXor.getInstance("Xor all character in the file in hex format");
        }else  if ("Md5".equals(command)) {
            visitor = DoCalculateMd5.getInstance("calculate Md5 of all files");
        }else  if ("Sha-256".equals(command)) {
            visitor = DoCalculateSha256.getInstance("calculate Sha-256 of all files");
        }

        return visitor;
    }
}

// Patterns learned:
// Visitor
// Factory
// Adaptor
// Singleton