package tests;

import base.BaseDELETEMethods;
import base.BaseGETMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Tag("DELETE_tests")
@Execution(ExecutionMode.CONCURRENT)
public class DELETETodoTests extends BaseDELETEMethods {

    @Test
    @Description("Check_DELETE_Specific_Todo")
    @Feature("DELETE_TestFeature")
    @Story("Story1")
    public void checkSpecificTodoDelete() {
        Integer randomID = BaseGETMethods.getRandomTodoID();
        Assertions.assertEquals(200,
                deleteTodo(randomID)
                        .then()
                        .log().all()
                        .extract()
                        .statusCode());
    }

    @Test
    @Description("Check_DELETE_Random_Todo")
    @Feature("DELETE_TestFeature")
    @Story("Story1")
    public void checkRandomTodoDELETE() throws Exception {
        Assertions.assertEquals(200,
                deleteRandomTodo()
                        .then()
                        .log().all()
                        .extract()
                        .statusCode());
    }
}
