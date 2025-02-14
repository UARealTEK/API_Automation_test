package tests;

import base.BaseApiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Endpoints;
import utils.RequestHeaders;

import static io.restassured.RestAssured.given;

public class GETTodoTests extends BaseApiTest {

    @Test
    public void getAllTodos() {
        given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void checkPluralValidation() {
        given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint().substring(0,Endpoints.TODOS.getEndpoint().length()-1))
                .then()
                .log().status()
                .statusCode(404);
    }

    @Test
    public void checkSpecificTodo() {
        Integer randomID = getRandomTodoID();
        Integer receivedInt = given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint() + "/" + randomID)
                .then()
                .log()
                .body()
                .extract()
                .jsonPath()
                .getInt("todos[0].id");

        Assertions.assertEquals(randomID,receivedInt);
    }
}
