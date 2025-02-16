package base;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Body;
import utils.Endpoints;
import utils.RequestHeaders;

import static io.restassured.RestAssured.given;

public class BasePUTMethods extends BaseApiTest {

    public Response putTodo(Integer todoID,String title, Boolean doneStatus , String descriptionValue) throws Exception {
        Body body = new Body();
        body.setTitle(title);
        body.setDoneStatus(doneStatus);
        body.setDescription(descriptionValue);
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .contentType(ContentType.JSON)
                .body(body.toJson())
                .when()
                .put(Endpoints.TODOS.getEndpoint() + "/" + todoID);
    }

    public Response putTodo(Integer todoID, Body body) throws Exception {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .contentType(ContentType.JSON)
                .body(body.toJson())
                .when()
                .put(Endpoints.TODOS.getEndpoint() + "/" + todoID);
    }

    public Response putRandomTodo() throws Exception {
        Integer randomID = BaseGETMethods.getRandomTodoID();
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .contentType(ContentType.JSON)
                .body(Body.getRandomBody())
                .when()
                .put(Endpoints.TODOS.getEndpoint() + "/" + randomID);
    }
}
