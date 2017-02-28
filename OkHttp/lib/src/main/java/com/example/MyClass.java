package com.example;

public class MyClass {

    public static void main(String[] args){
        System.out.println( getFileName("d:/as/github/123.png"));
    }

    public static String getFileName(String filePath){
        return filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
    }
}
