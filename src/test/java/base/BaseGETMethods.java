package base;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.Endpoints;
import utils.RequestHeaders;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

public class BaseGETMethods extends BaseApiTest {

    public static Response getAllChallenges() {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.CHALLENGES.getEndpoint());
    }

    public static ValidatableResponse getAllTodos() {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint())
                .then()
                .log().all();
    }

    public static Integer getRandomTodoID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return getTodoIDList().isEmpty() ? null : getTodoIDList().get(random.nextInt(getTodoIDList().size()));
    }

    public static List<Integer> getTodoIDList() {
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

    public static ValidatableResponse getSpecificTodo(Integer toDoID) {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint() + "/" + toDoID)
                .then()
                .log()
                .body();
    }

    public static ValidatableResponse getLastTodo() {
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
