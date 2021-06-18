package helpers.configurationReaders;

public class CommonConfigReader extends ConfigReader {
    public CommonConfigReader(String configFilePath){super(configFilePath);}

    public String getUrl() {return this.getProperty("url");}
    public String getUserLogin() {return this.getProperty("login");}
    public String getFakeLogin() {return this.getProperty("fakeLogin");}
    public String getUserPassword() {return this.getProperty("pass");}
    public String getFile(){return this.getProperty("fileId");}
    public String getRun(){return this.getProperty("runId");}
}
