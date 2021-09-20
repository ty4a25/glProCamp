package helpers.configurationReaders;

public class PathConfigReader extends ConfigReader {
    public PathConfigReader(String configFilePath){super(configFilePath);}

    public String getLoginPath() {return this.getProperty("loginPath");}
    //path to root and specific folder
    public String getFilePathV2() {return this.getProperty("filesPathV2");}
    //path to File results - Get runs
    public String getFilePathV1() {return this.getProperty("filesPathV1");}
    public String getRunPath() {return this.getProperty("runsPath");}
}
