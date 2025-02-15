package base;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.Endpoints;
import utils.RequestHeaders;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

public class BaseGETMethods extends BaseApiTest {

    public Response getAllChallenges() {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.CHALLENGES.getEndpoint());
    }

    public ValidatableResponse getAllTodos() {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint())
                .then()
                .log().all();
    }

    public Integer getRandomTodoID() {
        Random random = new Random();
        return getTodoIDList().isEmpty() ? null : getTodoIDList().get(random.nextInt(getTodoIDList().size()));
    }

    public List<Integer> getTodoIDList() {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("todos.id", Integer.class);
    }

    public ValidatableResponse getSpecificTodo(Integer toDoID) {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint() + "/" + toDoID)
                .then()
                .log()
                .body();
    }

    public ValidatableResponse getLastTodo() {
        Integer toDoID = getTodoIDList().stream().max(Comparator.comparing(Integer::intValue)).orElse(null);
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint() + "/" + toDoID)
                .then()
                .log()
                .body();
    }
}
