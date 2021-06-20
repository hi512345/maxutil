package com.max.util;

public class FileInfo {
    //private String filePath;
    private String folder;
    private String fileName;
    private long fileSize;
    private String sha256;
    private String md5;

    public FileInfo() {
    }

    public FileInfo(String folder, String fileName, long fileSize, String sha256, String md5) {
        this.folder = folder;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.sha256 = sha256;
        this.md5 = md5;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
