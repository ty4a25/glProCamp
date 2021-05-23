import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {
    ConfigFile config = new ConfigFile();

    @Test
    void test(){
        config.setFileProperty(Constants.URL,"https://app.cosmosid.com");
        System.out.println(config.getProperty(Constants.URL));
    }

}
