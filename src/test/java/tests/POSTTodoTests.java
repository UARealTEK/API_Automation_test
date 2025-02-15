package tests;

import base.BaseApiTest;
import base.BasePOSTMethods;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import utils.Endpoints;

import static io.restassured.RestAssured.given;
@Tag("POST_tests")
@Execution(ExecutionMode.CONCURRENT)
public class POSTTodoTests extends BaseApiTest {

    private static final Log log = LogFactory.getLog(POSTTodoTests.class);

    @Test
    public void checkPostChallenge() {
        Assertions.assertEquals(201,
                new BasePOSTMethods().postChallenge().extract().statusCode());
    }

    @Test
    public void checkPostTodo() throws Exception {
        BasePOSTMethods post = new BasePOSTMethods();
        Assertions.assertEquals(201,
                post.postRandomTodo().getStatusCode());
    }
}
