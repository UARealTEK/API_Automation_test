package base;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.ChallengerBody;
import utils.Endpoints;
import utils.RequestHeaders;
import utils.ResponseHeaders;

import static io.restassured.RestAssured.given;

public class BaseChallengerMethods extends BaseApiTest {

    private static final Log log = LogFactory.getLog(BaseChallengerMethods.class);

    public Response getChallenger() {
        String sessionID = BaseApiTest.getChallengerID();
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), sessionID)
                .when()
                .get(Endpoints.CHALLENGER.getEndpoint() + "/" + sessionID);
    }

    public Response restoreChallenger() throws JsonProcessingException {
        String sessionID = getChallenger().then().extract().header(ResponseHeaders.CHALLENGER.getHeader());
        log.info(String.format("Challenger ID is: %s", sessionID));
        ChallengerBody body = new ChallengerBody();
        body.setXChallenger(sessionID);
        String fullBody = body.createFullChallengerJSONBody();
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), sessionID)
                .body(fullBody)
                .when()
                .put(Endpoints.CHALLENGER.getEndpoint() + "/" + sessionID);
    }
}
