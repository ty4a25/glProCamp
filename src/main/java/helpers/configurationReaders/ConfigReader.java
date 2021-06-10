package helpers.configurationReaders;

import java.io.*;
import java.util.Properties;

public class ConfigReader {
    private String configFilePath;
    private Properties prop;
    File configFile;

    public ConfigReader(String configFilePath){
        this.configFilePath = configFilePath;
        try{
            configFile = new File(configFilePath);
            FileReader reader = new FileReader(configFile);
            prop = new Properties();
            prop.load(reader);
            reader.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("common.properties not found at " + configFilePath);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

// Get property from config.file or environment variables
    public String getProperty(String key){
        String value = prop.getProperty(key);
        String valueEnv = System.getProperty(key);
        if (value != null){
            return value;
        } else if(valueEnv != null) {
            return valueEnv;
        } else throw new RuntimeException("key not specified in the variables");
    }

    public void setFileProperty(String key, String value){
        prop = new Properties();
        prop.setProperty(key, value);
        FileWriter writer = null;
        try {
            writer = new FileWriter(configFile);
            prop.store(writer,"");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEnvProperty(String key, String value){
        System.setProperty(key,value);
    }
}
