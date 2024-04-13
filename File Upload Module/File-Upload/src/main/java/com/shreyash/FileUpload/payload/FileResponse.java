package com.shreyash.FileUpload.payload;

public class FileResponse {
    String fileName;
    String message;
    public FileResponse(String fileName, String message) {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
