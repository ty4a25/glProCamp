package api;

import com.jayway.restassured.response.Response;
import helpers.configurationReaders.CommonConfigReader;
import helpers.configurationReaders.PathConfigReader;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.HashMap;

public class Login extends BaseAPI {
    HashMap<String,String> map = new HashMap<String,String>();
    public static String login;
    public static String pass;
    public static String path;
    public static String url;

    public Login(CommonConfigReader commonConfig, PathConfigReader pathConfig) {
        super(commonConfig, pathConfig);
        this.url = commonConfig.getUrl();
        this.login = commonConfig.getUserLogin();
        this.pass = commonConfig.getUserPassword();
        this.path = pathConfig.getLoginPath();
    }

    @Step("Login validation - Positive case")
    public void assertLogin (Response response){
        Assert.assertEquals(response.getStatusCode(),BaseAPI.SUCCESS);
        Log.info("Login validation - Status code: " + response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Step("Login validation - Negative case")
    public void assertFakeLogin (Response response){
        Assert.assertEquals(response.getStatusCode(),BaseAPI.UNATHORIZE);
        Log.info("Login validation - Status code: " + response.getStatusCode());
        Log.info(response.getBody().prettyPrint());
    }
}
