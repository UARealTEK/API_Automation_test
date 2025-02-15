package tests;

import base.BaseGETMethods;
import base.BasePOSTMethods;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import utils.Endpoints;
import utils.RequestHeaders;

import java.util.Comparator;

import static io.restassured.RestAssured.given;

@Tag("GET_tests")
@Execution(ExecutionMode.CONCURRENT)
public class GETTodoTests extends BaseGETMethods {

    private static final Log log = LogFactory.getLog(GETTodoTests.class);

    @Test
    public void getAllChallengesStatus() {
        BaseGETMethods getMethods = new BaseGETMethods();
        Assertions.assertEquals(200,getMethods.getAllChallenges().then().extract().statusCode());
    }

    @Test
    public void getAllTodosList() {
        Assertions.assertEquals(200,getAllTodos().extract().statusCode());
    }

    @Test
    public void checkPluralValidation() {
        given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint().substring(0,Endpoints.TODOS.getEndpoint().length()-1))
                .then()
                .log().status()
                .statusCode(404);
    }

    @Test
    public void checkSpecificTodo() {
        Integer randomID = getRandomTodoID();
        Integer receivedInt = getSpecificTodo(randomID).extract()
                .jsonPath()
                .getInt("todos[0].id");

        Assertions.assertEquals(randomID,receivedInt);
        log.info(randomID + "/" + receivedInt);
    }

    @Test
    public void checkInvalidSpecificTodo() {
        getTodoIDList().stream()
                .max(Comparator.comparing(Integer::intValue))
                .ifPresent(maxInt -> Assertions.assertEquals(404, getSpecificTodo(maxInt + 1)
                        .extract()
                        .statusCode()));

    }

    @Test
    public void checkTodoWithDoneStatus() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        Assertions.assertEquals(true, post.postTodo("title",true,"description")
                .then().extract().body().jsonPath().get("doneStatus"));
    }
}
