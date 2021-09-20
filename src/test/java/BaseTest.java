import helpers.configurationReaders.CommonConfigReader;
import helpers.configurationReaders.PathConfigReader;

public class BaseTest {
    CommonConfigReader commonConfig = new CommonConfigReader("src/main/resources/common.properties");
    PathConfigReader pathConfig = new PathConfigReader("src/main/resources/path.properties");

    public void f(){
        System.out.println(commonConfig.getUrl());
    }

    public static void main(String[] args) {
        BaseTest s = new BaseTest();
        s.f();
    }
}

