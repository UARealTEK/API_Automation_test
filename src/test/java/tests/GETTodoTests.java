package tests;

import base.BaseApiTest;
import base.BaseGETMethods;
import base.BasePOSTMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import utils.Body;
import utils.Endpoints;
import utils.RequestHeaders;

import java.util.Comparator;

import static io.restassured.RestAssured.given;

@Tag("GET_tests")
@Execution(ExecutionMode.CONCURRENT)
public class GETTodoTests extends BaseGETMethods {

    private static final Log log = LogFactory.getLog(GETTodoTests.class);

    @Test
    @Description("Check_GET_Challenger")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void getAllChallengesStatus() {
        Assertions.assertEquals(200,getAllChallenges().then().extract().statusCode());
    }

    @Test
    @Description("Check_GET_All_Todos")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void getAllTodosList() {
        Assertions.assertEquals(200,getAllTodos().extract().statusCode());
    }

    @Test
    @Description("Check_GET_Validate_plural")
    @Feature("GET_TestFeature")
    @Story("Story1")
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
    @Description("Check_GET_Specific_Todo")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void checkSpecificTodo() {
        Integer randomID = getRandomTodoID();
        Integer receivedInt = getSpecificTodo(randomID).extract()
                .jsonPath()
                .getInt("todos[0].id");

        Assertions.assertEquals(randomID,receivedInt);
        log.info(randomID + "/" + receivedInt);
    }

    @Test
    @Description("Check_GET_Invalid_Todo")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void checkInvalidSpecificTodo() {
        getTodoIDList().stream()
                .max(Comparator.comparing(Integer::intValue))
                .ifPresent(maxInt -> Assertions.assertEquals(404, getSpecificTodo(maxInt + 1)
                        .extract()
                        .statusCode()));

    }

    @Test
    @Description("Check_GET_Todo_Done")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void checkTodoWithDoneStatus() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();

        Body body = new Body();
        body.setTitle(Body.getRandomString(50));
        body.setDescription(Body.getRandomString(50));
        body.setDoneStatus(true);

        Assertions.assertEquals(true, post.postTodo(body,ContentType.JSON,ContentType.JSON)
                .then().extract().body().jsonPath().get("doneStatus"));
    }

    @Test
    @Description("Check_GET_AllTodo_WithXML")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void checkAllTodoWithXML() {
        Assertions.assertEquals(200,getAllTodos(ContentType.XML)
                .log()
                .all()
                .extract()
                .statusCode());
    }

    @Test
    @Description("Check_GET_AllTodo_WithJSON")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void checkAllTodoWithJSON() {
        Assertions.assertEquals(200,getAllTodos(ContentType.JSON)
                .log()
                .all()
                .extract()
                .statusCode());
    }

    @Test
    @Description("Check_GET_AllTodo_With_DEFAULT_Format")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void checkAllTodoWithDefaultAcceptFormat() {
        Assertions.assertEquals(200,getAllTodos(ContentType.ANY)
                .log()
                .all()
                .extract()
                .statusCode());
    }

    @Test
    @Description("Check_GET_AllTodo_With_PREFERRED_Format")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void checkAllTodoWithPreferredAcceptFormat() {
        Assertions.assertEquals(200,getAllTodos(ContentType.JSON,ContentType.XML)
                .log()
                .all()
                .extract()
                .statusCode());
    }

    @Test
    @Description("Check_GET_AllTodo_With_Invalid_Accept_Format")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void checkAllTodoWithInvalidAcceptFormat() {
        Assertions.assertEquals(406, given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .header(RequestHeaders.ACCEPT.getRequestHeader(), ContentType.URLENC)
                .when()
                .get(Endpoints.TODOS.getEndpoint())
                .then()
                .log().all()
                .extract().statusCode());
    }

}
