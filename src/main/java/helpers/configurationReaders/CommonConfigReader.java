package helpers.configurationReaders;

public class CommonConfigReader extends ConfigReader {
    public CommonConfigReader(String configFilePath){super(configFilePath);}

    public String getUrl() {return this.getProperty("url");}
    public String getUserLogin() {return this.getProperty("login");}
    public String getUserPassword() {return this.getProperty("pass");}
}
