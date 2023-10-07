package org.example;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Printall {
    public static void main(String[] args){
        File configFile = new File("D:\\proj\\FIRESIM\\src\\main\\resources\\config.properties");
        try{
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            System.out.println(props);
            System.out.println(props.getProperty("MissionTotal"));
            reader.close();
        }catch (Exception e){
        }
    }
}
