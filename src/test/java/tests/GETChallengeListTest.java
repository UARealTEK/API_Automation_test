package tests;

import base.BaseApiTest;
import org.junit.jupiter.api.Test;
import utils.Endpoints;
import utils.RequestHeaders;
import utils.ResponseHeaders;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class GETChallengeListTest extends BaseApiTest {

    @Test
    public void testGetAllChallenges() {
        String challengerHeaderValue = new BaseApiTest().getXChallengerSessionID();
        given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), challengerHeaderValue)  // Use the GUID from config
                .header("Accept", "*/*")
                .log().all()  // Logs the request for debugging
                .when()
                .get(Endpoints.CHALLENGES.getEndpoint())  // Send GET request
                .then()
                .log().all()  // Logs the response for debugging
                .statusCode(200)  // Assert that the response code is 200 (OK)
                .header(ResponseHeaders.CHALLENGER.getHeader(), equalTo(challengerHeaderValue))  // Validate the X-Challenger header
                .body("size()", greaterThan(0));  // Assert that the response body is not empty
    }
}
