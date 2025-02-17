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

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

@Tag("POST_tests")
@Execution(ExecutionMode.CONCURRENT)
public class POSTTodoTests extends BaseApiTest {

    private static final Log log = LogFactory.getLog(POSTTodoTests.class);

    @Test
    @Description("Check_Post_Challenge")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostChallenge() {
        Assertions.assertEquals(201,
                new BasePOSTMethods().postChallenge().extract().statusCode());
    }

    @Test
    @Description("Check_Posting_TODO_Item")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostTodo() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        Assertions.assertEquals(201,
                post.postRandomTodo().getStatusCode());
    }

    @Test
    @Description("Check_Posting_Invalid_TODO_Item")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostTodoWithInvalidTitleLength() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        String invalidTitle = Body.getRandomString(51); // just above the limit
        String validTitle = Body.getRandomString(50);
        Assertions.assertEquals(201,
                post.postTodo(validTitle,true,"test").then().extract().statusCode());
        Assertions.assertEquals(400,
                post.postTodo(invalidTitle,true,"test").then().extract().statusCode());

    }

    @Test
    @Description("Check_Posting_TODO_Item_With_Invalid_Length")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostTodoWithInvalidDescriptionLength() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        String invalidDescription = Body.getRandomString(201); // just above the limit
        String validDescription = Body.getRandomString(200);
        Assertions.assertEquals(201,
                post.postTodo("test",true,validDescription).then().extract().statusCode());
        Assertions.assertEquals(400,
                post.postTodo("test",true,invalidDescription).then().extract().statusCode());

    }

    @Test
    @Description("Check_Posting_TODO_Item_With_MaxLength")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostTodoWidthMaximumAllowedLength() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        String validDescription = Body.getRandomString(200); // just above the limit
        String validTitle = Body.getRandomString(50);

        Assertions.assertEquals(201,
                post.postTodo(validTitle,true,validDescription).then().extract().statusCode());
    }

    @Test
    @Description("Check_Posting_TODO_Item_With_MaxPayloadLength")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostTodoWidthMaximumPayloadLength() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        String invalidPayload = Body.getRandomString(5000); // just above the limit

        Assertions.assertEquals(413,
                post.postTodo(invalidPayload,true,"test").then().extract().statusCode());
        Assertions.assertEquals(413,
                post.postTodo("test",true,invalidPayload).then().extract().statusCode());
    }

    @Test
    @Description("Check_Posting_TODO_Item_With_MaxPayloadLength")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostTodoWithInvalidField() throws Exception {
        String randomValidString = Body.getRandomString(50);
        Body body = new Body();
        body.setTitle(randomValidString);
        body.setDescription(randomValidString);
        body.setDoneStatus(ThreadLocalRandom.current().nextBoolean());
        body.setPriority(randomValidString);

        Integer actualStatusCode = given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .contentType(ContentType.JSON)
                .body(body.toJson())
                .when()
                .post(Endpoints.TODOS.getEndpoint())
                .getStatusCode();

        log.info(actualStatusCode);

        Assertions.assertEquals(400,actualStatusCode);
    }

    @Test
    @Description("Check_POST_ExistingID")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkTodoUpdateUsingPOST() throws Exception {
        Integer randomTodoID = BaseGETMethods.getRandomTodoID();
        String randomValue = Body.getRandomString(50);

        Body body = new Body();
        body.setDescription(randomValue);
        body.setTitle(randomValue);
        body.setDoneStatus(ThreadLocalRandom.current().nextBoolean());

        Integer actualStatusCode = given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .contentType(ContentType.JSON)
                .body(body.toJson())
                .when()
                .post(Endpoints.TODOS.getEndpoint() + "/" + randomTodoID)
                .then()
                .log().body()
                .extract().statusCode();

        Assertions.assertEquals(200,actualStatusCode);
    }

    @Test
    @Description("Check_POST_Width_newID")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkTodoUpdateUsingPOSTForInvalidID() throws Exception {
        int newID = BaseGETMethods.getLastTodo().extract().body().jsonPath().getInt("todos[0].id") + 1;
        String randomValue = Body.getRandomString(50);

        Body body = new Body();
        body.setDescription(randomValue);
        body.setTitle(randomValue);
        body.setDoneStatus(ThreadLocalRandom.current().nextBoolean());

        Integer actualStatusCode = given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .contentType(ContentType.JSON)
                .body(body.toJson())
                .when()
                .post(Endpoints.TODOS.getEndpoint() + "/" + newID)
                .then()
                .log().body()
                .extract().statusCode();

        Assertions.assertEquals(404,actualStatusCode);
    }


}
