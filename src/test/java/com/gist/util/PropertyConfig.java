package com.gist.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyConfig {
    private static Properties prop ;

    static {
        prop = new Properties();
        String fileName = "src/test/resources/config.properties";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    public static String getPropertyValue(String key){
        return prop.getProperty(key);
    }

}
