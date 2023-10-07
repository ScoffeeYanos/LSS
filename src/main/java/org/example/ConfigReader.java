package org.example;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {
    public static String getConfig(String config) throws UnknownMission {
        File configFile = new File("D:\\proj\\FIRESIM\\src\\main\\resources\\config.properties");
        try{
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);
            String ret = props.getProperty(config);
            reader.close();
            return ret;
        }catch (Exception e){
            throw new UnknownMission("In Loadup");
        }
    }
}
