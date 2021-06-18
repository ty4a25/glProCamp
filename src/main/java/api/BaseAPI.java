package api;

import com.jayway.restassured.response.Response;
import helpers.configurationReaders.CommonConfigReader;
import helpers.configurationReaders.PathConfigReader;
import io.qameta.allure.Step;

import java.util.logging.Logger;

import static com.jayway.restassured.RestAssured.given;

public class BaseAPI {
    public static CommonConfigReader commonConfig = new CommonConfigReader("src/main/resources/common.properties");
    public static PathConfigReader pathConfig = new PathConfigReader("src/main/resources/path.properties");
    public static Logger Log = Logger.getLogger(org.apache.commons.logging.Log.class.getName());
    public static final int SUCCESS = 200;
    public static final int UNATHORIZE = 401;
    public static final String ROOT_FOLDER_NAME = "ROOT";
    public static final String RUNS = "/runs";
    public static final String ANALYSIS = "/analysis";
    public static final String ARTIFACTS = "/artifacts";
    public static String login;
    public static String fakeLogin;
    public static String pass;
    public static String path;
    public static String url;


    public BaseAPI (CommonConfigReader commonConfig, PathConfigReader pathConfig){
        this.url = commonConfig.getUrl();
        this.login = commonConfig.getUserLogin();
        this.fakeLogin = commonConfig.getFakeLogin();
        this.pass = commonConfig.getUserPassword();
        this.path = pathConfig.getLoginPath();

    }
    @Step ("Login")
    public static Response login(String login){
        Response res = null;
        try {
             res = given()
                    .auth()
                    .preemptive().basic(login, pass)
                    .when()
                    .post(url + path)
                    .then()
                    .extract().response();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }
    @Step("Get token")
    public static String getToken(){
        String token = login(BaseAPI.login).jsonPath().getString("token");
        return token;
    }

}
