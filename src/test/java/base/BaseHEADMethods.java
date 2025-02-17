package base;

import io.restassured.response.ValidatableResponse;
import utils.Endpoints;
import utils.RequestHeaders;

import static io.restassured.RestAssured.given;

public class BaseHEADMethods extends BaseApiTest{

    public ValidatableResponse headAllTodos() {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), BaseApiTest.getChallengerID())
                .when()
                .head(Endpoints.TODOS.getEndpoint())
                .then()
                .log().all();
    }
}
