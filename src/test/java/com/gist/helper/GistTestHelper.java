package com.gist.helper;

import java.util.HashMap;
import java.util.Map;

public class GistTestHelper {
    public static String javaFileName= "HelloWorld.java";
    public static String textFileName= "HelloWorld.txt";
    public static String fileJavaContent = "class HelloWorld {" +
            "    public static void main(String[] args) {" +
            "        System.out.println(\"Hello, World!\"); " +
            "    }" +
            "}";
    public static String textFileContent ="Hai , this is my first gist created using API";
    public static String gistDescription= "My First Gist";
    public static Boolean isPublicGist=true;

    public Map<String, Object> constructGistPayload(){
        Map<String, Object> gistPayload = new HashMap<>();
        gistPayload.put("description", gistDescription);
        gistPayload.put("public", isPublicGist);
        Map<String,Map<String,String>> fileValue = new HashMap<>();
        Map<String,String> fileJavaContentMap = new HashMap<>();
        Map<String,String> fileTextContentMap = new HashMap<>();
        fileJavaContentMap.put("content", fileJavaContent);
        fileValue.put(javaFileName, fileJavaContentMap);
        fileTextContentMap.put("content", textFileContent);
        fileValue.put(textFileName, fileTextContentMap);
        gistPayload.put("files", fileValue);
        return gistPayload;
    }
    public String constructFilePath(String fileName, String endPath  ){
        String filePath = new StringBuilder().append("files.\"").append(fileName).append("\".").append(endPath).toString();
        return filePath;
    }
}
