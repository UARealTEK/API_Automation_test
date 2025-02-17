package tests;

import base.BaseApiTest;
import base.BaseHEADMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Tag("HEAD_tests")
@Execution(ExecutionMode.CONCURRENT)
public class HEADTodoTests extends BaseApiTest {

    @Test
    @Description("Check_HEAD_ALL")
    @Feature("HEAD_TestFeature")
    @Story("Story1")
    public void getAllHead() {
        BaseHEADMethods head = new BaseHEADMethods();
        Assertions.assertEquals(200,head.headAllTodos().extract().statusCode());
    }
}
