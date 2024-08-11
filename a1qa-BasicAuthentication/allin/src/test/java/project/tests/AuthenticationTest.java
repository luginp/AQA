package project.tests;

import com.google.gson.Gson;
import framework.utils.ApiUtils;
import org.testng.annotations.Test;
import project.models.ApiResponse;
import project.models.BasicAuth;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AuthenticationTest extends BaseTest {
    @Test
    public void authenticationTest() {
        ApiResponse apiResponse = ApiUtils.sendBasicAuthRequest();
        BasicAuth basicAuth = new BasicAuth();
        basicAuth.setAuthenticated(new Gson().fromJson(apiResponse.getResponse(), BasicAuth.class).getAuthenticated());
        basicAuth.setUser(new Gson().fromJson(apiResponse.getResponse(), BasicAuth.class).getUser());
        assertTrue(basicAuth.getAuthenticated());
        assertEquals(basicAuth.getUser(), "user");
    }
}
