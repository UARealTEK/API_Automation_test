package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.ChallengerBody;
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

    public Response restoreChallenger() throws JsonProcessingException {
        String sessionID = BaseApiTest.getChallengerID();
        ChallengerBody body = new ChallengerBody();
        String fullBody = body.createFullChallengerJSONBody();
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), sessionID)
                .body(fullBody)
                .when()
                .put(Endpoints.CHALLENGER.getEndpoint() + "/" + sessionID);
    }
}
