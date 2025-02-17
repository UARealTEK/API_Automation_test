package base;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.Body;
import utils.Endpoints;
import utils.RequestHeaders;

import static io.restassured.RestAssured.given;

public class BasePOSTMethods extends BaseApiTest{

    private static final Log log = LogFactory.getLog(BasePOSTMethods.class);

    public ValidatableResponse postChallenge() {
        return given()
                .when()
                .post(Endpoints.CHALLENGER.getEndpoint())
                .then()
                .log().all();
    }

    public Response postTodo(Body body, ContentType acceptType, ContentType bodyType) throws Exception {
        if (bodyType == ContentType.ANY) {
            bodyType = ContentType.JSON;
        }

        String passedInBody = (bodyType == ContentType.XML) ? body.toXML() : body.toJson();

        if (acceptType == ContentType.ANY) {
            acceptType = ContentType.JSON;
        }

        log.info(passedInBody);
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), BaseApiTest.getChallengerID())
                .accept(acceptType)
                .contentType(bodyType)
                .body(passedInBody)
                .when()
                .post(Endpoints.TODOS.getEndpoint());
    }

    public Response postRandomTodo() throws Exception {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), BaseApiTest.getChallengerID())
                .contentType(ContentType.JSON)
                .body(Body.getRandomBody())
                .when()
                .post(Endpoints.TODOS.getEndpoint());
    }
}
