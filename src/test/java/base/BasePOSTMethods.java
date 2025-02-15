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

    public Response postTodo(String title, Boolean doneStatus , String descriptionValue) throws Exception {
        Body body = new Body();
        body.setTitle(title);
        body.setDoneStatus(doneStatus);
        body.setDescription(descriptionValue);
        log.info(body.toJson());
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .contentType(ContentType.JSON)
                .body(body.toJson())
                .when()
                .post(Endpoints.TODOS.getEndpoint());
    }

    public Response postRandomTodo() throws Exception {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .contentType(ContentType.JSON)
                .body(Body.getRandomBody())
                .when()
                .post(Endpoints.TODOS.getEndpoint());
    }
}
