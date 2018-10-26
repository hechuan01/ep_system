package com.zx.common.utils;

/**
 * Created by wang on 2017/3/18.
 */
public class FileReturn {
    private String fileName;//上传文件文件名
    private String filePath;//文件存储真实路径
    public FileReturn(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}