package tests;

import base.BaseChallengerMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static base.BaseGETMethods.getAllChallenges;

@Tag("CHALLENGER_tests")
@Execution(ExecutionMode.CONCURRENT)
public class ChallengerTests extends BaseChallengerMethods {

    @Test
    @Description("Check_GET_Challenger")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void getAllChallengesStatus() {
        Assertions.assertEquals(200,getAllChallenges().then().extract().statusCode());
    }

    @Test
    @Description("Check_GET_Challenger")
    @Feature("GET_TestFeature")
    @Story("Story1")
    public void getChallengerGuid() {
        Assertions.assertEquals(200,
                getChallenger()
                        .then()
                        .log().all()
                        .extract()
                        .statusCode());
    }

}
