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

        Body bodyWithInvalidTitle = new Body();
        bodyWithInvalidTitle.setTitle(invalidTitle);
        bodyWithInvalidTitle.setDescription(validTitle);
        bodyWithInvalidTitle.setDoneStatus(ThreadLocalRandom.current().nextBoolean());

        Body bodyWithValidTitle = new Body();
        bodyWithValidTitle.setTitle(validTitle);
        bodyWithValidTitle.setDescription(validTitle);
        bodyWithValidTitle.setDoneStatus(ThreadLocalRandom.current().nextBoolean());

        Assertions.assertEquals(201,
                post.postTodo(bodyWithValidTitle,ContentType.JSON,ContentType.JSON).then().extract().statusCode());
        Assertions.assertEquals(400,
                post.postTodo(bodyWithInvalidTitle,ContentType.JSON,ContentType.JSON).then().extract().statusCode());

    }

    @Test
    @Description("Check_Posting_TODO_Item_With_Invalid_Length")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostTodoWithInvalidDescriptionLength() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        String invalidDescription = Body.getRandomString(201); // just above the limit
        String validDescription = Body.getRandomString(200);

        Body bodyWithInvalidDescription = new Body();
        bodyWithInvalidDescription.setTitle(Body.getRandomString(50));
        bodyWithInvalidDescription.setDescription(invalidDescription);
        bodyWithInvalidDescription.setDoneStatus(ThreadLocalRandom.current().nextBoolean());

        Body bodyWithValidDescription = new Body();
        bodyWithValidDescription.setTitle(Body.getRandomString(50));
        bodyWithValidDescription.setDescription(validDescription);
        bodyWithValidDescription.setDoneStatus(ThreadLocalRandom.current().nextBoolean());


        Assertions.assertEquals(201,
                post.postTodo(bodyWithValidDescription,ContentType.JSON,ContentType.JSON).then().extract().statusCode());
        Assertions.assertEquals(400,
                post.postTodo(bodyWithInvalidDescription,ContentType.JSON,ContentType.JSON).then().extract().statusCode());

    }

    @Test
    @Description("Check_Posting_TODO_Item_With_MaxLength")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostTodoWidthMaximumAllowedLength() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        String validDescription = Body.getRandomString(200); // just above the limit
        String validTitle = Body.getRandomString(50);

        Body validBody = new Body();
        validBody.setTitle(validTitle);
        validBody.setDescription(validDescription);
        validBody.setDoneStatus(ThreadLocalRandom.current().nextBoolean());

        Assertions.assertEquals(201,
                post.postTodo(validBody, ContentType.JSON, ContentType.JSON).then().extract().statusCode());
    }

    @Test
    @Description("Check_Posting_TODO_Item_With_MaxPayloadLength")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkPostTodoWidthMaximumPayloadLength() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        String invalidPayload = Body.getRandomString(5000);
        String validPayload = Body.getRandomString(50);

        Body bodyWithInvalidTitle = new Body();
        bodyWithInvalidTitle.setTitle(invalidPayload);
        bodyWithInvalidTitle.setDescription(validPayload);
        bodyWithInvalidTitle.setDoneStatus(ThreadLocalRandom.current().nextBoolean());

        Body bodyWithInvalidDescription = new Body();
        bodyWithInvalidDescription.setTitle(validPayload);
        bodyWithInvalidDescription.setDescription(invalidPayload);
        bodyWithInvalidDescription.setDoneStatus(ThreadLocalRandom.current().nextBoolean());

        Assertions.assertEquals(413,
                post.postTodo(bodyWithInvalidTitle, ContentType.JSON, ContentType.JSON).then().extract().statusCode());
        Assertions.assertEquals(413,
                post.postTodo(bodyWithInvalidDescription,ContentType.JSON,ContentType.JSON).then().extract().statusCode());
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

//    @Test
//    @Description("Check_POST_Width_newID")
//    @Feature("POST_TestFeature")
//    @Story("Story1")
//    public void checkPostingWithXML() {
//
//    }
}
