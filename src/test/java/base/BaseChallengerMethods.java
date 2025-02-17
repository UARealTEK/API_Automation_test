package base;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.Endpoints;
import utils.RequestHeaders;

import static io.restassured.RestAssured.given;

public class BaseChallengerMethods extends BaseApiTest {

    public Response getChallenger() {
        String sessionID = BaseApiTest.getChallengerID();
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), sessionID)
                .when()
                .get(Endpoints.CHALLENGER.getEndpoint() + "/" + sessionID);
    }
}
