import api.BaseAPI;
import api.FoldersAPI;
import api.Login;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import helpers.configurationReaders.CommonConfigReader;
import helpers.configurationReaders.PathConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FolderTests {
    public static CommonConfigReader commonConfig = new CommonConfigReader("src/main/resources/common.properties");
    public static PathConfigReader pathConfig = new PathConfigReader("src/main/resources/path.properties");
    FoldersAPI foldersAPI = new FoldersAPI(commonConfig,pathConfig);
    Login login = new Login(commonConfig, pathConfig);
    Response loginRes = BaseAPI.login(commonConfig.getUserLogin());

    @Test
    public void Login(){
        login.assertLogin(loginRes);
    }

    @Test
    public void FakeLogin(){
        login.assertFakeLogin(loginRes);
    }

    @Test
    public void rootFolderRequestStatus(){
        HashMap<String,String> rootFolderParams = foldersAPI.setParamsRootFolder();
        ValidatableResponse response = foldersAPI.getFolder(rootFolderParams);
        response.assertThat().statusCode(foldersAPI.SUCCESS);
    }

    @Test
    public void rootFolderName(){
        Assert.assertEquals(foldersAPI.getFolderName(),BaseAPI.ROOT_FOLDER_NAME);
    }

    @Test
    public void specFolder(){
        HashMap<String, String> specFolderParams = FoldersAPI.setSpecFolder();
        ValidatableResponse response = FoldersAPI.getFolder(specFolderParams);
        Assert.assertEquals(FoldersAPI.countItemsNumber(response),FoldersAPI.getItemsTotal(response));
    }

    @Test
    public void fileRunsStatus(){
        ValidatableResponse response = FoldersAPI.getFile(
                pathConfig.getFilePathV1(),commonConfig.getFile(),BaseAPI.RUNS
        );
        response.assertThat().statusCode(BaseAPI.SUCCESS);
    }

    @Test
    public void fileAnalysisStatus(){
        ValidatableResponse response = FoldersAPI.getFile(
                pathConfig.getRunPath(),commonConfig.getRun(),BaseAPI.ANALYSIS);
        response.assertThat().statusCode(BaseAPI.SUCCESS);
    }

    @Test
    public void fileArtifactStatus() {
        ValidatableResponse response = FoldersAPI.getFile(
                pathConfig.getRunPath(),commonConfig.getRun(),BaseAPI.ARTIFACTS);
        response.assertThat().statusCode(BaseAPI.SUCCESS);
    }
}

