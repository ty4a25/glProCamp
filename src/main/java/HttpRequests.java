
import static com.jayway.restassured.RestAssured.*;

public class HttpRequests {
    ConfigFile configFile = new ConfigFile();

    public int getUrlStatusCode() {
//        int statusCode = get(configFile.getFileProperty("url")).getStatusCode();
//        System.out.println(statusCode);
        return 0;
    }

    public static void main(String[] args) {
        HttpRequests r = new HttpRequests();
        r.getUrlStatusCode();
    }
}
