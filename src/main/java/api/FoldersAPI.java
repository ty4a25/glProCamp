package api;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ValidatableResponse;
import helpers.configurationReaders.CommonConfigReader;
import helpers.configurationReaders.PathConfigReader;

import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;

public class FoldersAPI extends  BaseAPI {

    public static String url;
    public static  String pathFolderV2;

    public FoldersAPI(CommonConfigReader commonConfig, PathConfigReader pathConfig) {
        super(commonConfig, pathConfig);
        this.url = commonConfig.getUrl();
        this.pathFolderV2 = pathConfig.getFilePathV2();
    }


    public static HashMap<String, String> setParamsRootFolder(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("breadcrumbs","1");
        map.put("offset","0");
        map.put("limit","1000");
        map.put("_","1623234616900");
        return map;
    }

    public static ValidatableResponse getFolder (HashMap map){
        ValidatableResponse res = given()
                .contentType(ContentType.JSON)
                .queryParams(map)
                .headers("x-token",BaseAPI.getToken())
                .when()
                .get(url+pathFolderV2)
                .then();
        Log.info(String.valueOf(res.extract().response().getStatusCode()));
        return res;
    }

    public static String getFolderName(){
       String folderName = getFolder(setParamsRootFolder()).extract().response().jsonPath().getString("name");
       Log.info("Root folder name: " + folderName);
       return folderName;
    }

    public static HashMap<String, String> setSpecFolder(){
        HashMap<String,String> map = FoldersAPI.setParamsRootFolder();
        map.put("folder_id","84c966d5-8dce-429d-8f92-44d5e28b1581");
        return map;
    }
    //Count number in the item array
    public static long countItemsNumber (ValidatableResponse res){
        long itemsNumber = res.extract().response().jsonPath().getList("items").stream().count();
        System.out.println("Items counted in response: "+ itemsNumber);
        return itemsNumber;
    }
    //get number of files from response property "total"
    public static int getItemsTotal (ValidatableResponse res){
        int itemsTotal = res.extract().response().jsonPath().getInt("total");
        System.out.println("Items total in response: " + itemsTotal);
        return itemsTotal;
    }
    //method for get runs, analysis, artifacts
    public static ValidatableResponse getFile ( String path, String id, String type){
        ValidatableResponse res = given()
                .contentType(ContentType.JSON)
                .headers("x-token",BaseAPI.getToken())
                .when()
                .get(url+ path + id + type)
                .then();
        Log.info(String.valueOf(res.extract().response().getStatusCode()));
        return res;
    }


    //For testing
    public static void main(String[] args) {
//       FoldersAPI  s = new FoldersAPI(commonConfig,pathConfig);
//       ValidatableResponse res = FoldersAPI.getFile(pathConfig.getFilePathV1(),"7f4c7326-0a4e-4b56-a8d0-8ce002191672");
//       res.extract().response().prettyPrint();

    }
}
