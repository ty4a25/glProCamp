import java.io.*;
import java.util.Properties;

public class ConfigFile {
    private final String propertyFilePath= "src/main/resources/config.properties";
    private File configFile = new File(propertyFilePath);
    private Properties prop;

    public void configFileReader(){
        try{
            FileReader reader = new FileReader(configFile);
            prop = new Properties();
            prop.load(reader);
            reader.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + propertyFilePath);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
// Get property from config.file or environment variables
    public String getProperty(String key){
        configFileReader();
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
