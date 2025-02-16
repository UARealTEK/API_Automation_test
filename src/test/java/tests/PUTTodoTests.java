package tests;

import base.BaseApiTest;
import base.BaseGETMethods;
import base.BasePUTMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import utils.Body;

import java.util.concurrent.ThreadLocalRandom;

@Tag("PUT_Tests")
@Execution(ExecutionMode.CONCURRENT)
public class PUTTodoTests extends BaseApiTest {

    private static final Log log = LogFactory.getLog(PUTTodoTests.class);

    @Test
    @Description("Check_PUT_WidthID")
    @Feature("PUT_TestFeature")
    @Story("Story1")
    public void checkPUTTodoWithID() throws Exception {
        BasePUTMethods put = new BasePUTMethods();
        Integer nextID = BaseGETMethods.getLastTodo().extract().body().jsonPath().getInt("todos[0].id") + 1;
        String randomValidString = Body.getRandomString(50);

        Assertions.assertEquals(400, put.putTodo(nextID, randomValidString,ThreadLocalRandom.current().nextBoolean(), randomValidString)
                .then()
                .extract().response()
                .statusCode());

        log.info(put.putTodo(nextID,randomValidString,ThreadLocalRandom.current().nextBoolean(), randomValidString).then().log().all());
    }

    @Test
    @Description("Check_Valid_PUT_Request")
    @Feature("PUT_TestFeature")
    @Story("Story1")
    public void checkPUTTodo() throws Exception {
        BasePUTMethods put = new BasePUTMethods();
        Assertions.assertEquals(200,
                put.putRandomTodo()
                        .then()
                        .log()
                        .all()
                        .extract()
                        .statusCode());
    }

    @Test
    @Description("Check_PUT_Width_Partial_Data")
    @Feature("POST_TestFeature")
    @Story("Story1")
    public void checkTodoUpdateWithPartialData() throws Exception {
        BasePUTMethods put = new BasePUTMethods();
        Integer randomID = BaseGETMethods.getRandomTodoID();
        ValidatableResponse actualBody = BaseGETMethods.getSpecificTodo(randomID);
        log.info(actualBody);
        String randomValidString = Body.getRandomString(50);

        Body body = new Body();
        body.setTitle(randomValidString);
        body.setDescription(BaseGETMethods.getSpecificTodo(randomID).extract().body().jsonPath().getString("todos[0].Description"));
        body.setDoneStatus(BaseGETMethods.getSpecificTodo(randomID).extract().body().jsonPath().getBoolean("todos[0].doneStatus"));

        Assertions.assertEquals(200,
                put.putTodo(randomID,body)
                        .then()
                        .log().body()
                        .extract()
                        .statusCode());
    }
}
