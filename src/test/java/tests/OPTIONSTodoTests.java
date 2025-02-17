package tests;

import base.BaseOPTIONSMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import utils.ResponseHeaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag("OPTIONS_tests")
@Execution(ExecutionMode.CONCURRENT)
public class OPTIONSTodoTests extends BaseOPTIONSMethods {

    private static final Log log = LogFactory.getLog(OPTIONSTodoTests.class);

    @Test
    @Description("Check_OPTIONS_Specific_Todo")
    @Feature("OPTIONS_TestFeature")
    @Story("Story1")
    public void checkOptionsRequest() {
        List<String> currentOptionsList = new ArrayList<>(Arrays.asList(optionsTodo().then().extract().header(ResponseHeaders.ALLOW.getHeader()).split(", ")));

        Assertions.assertEquals(currentOptionsList.size(),ResponseHeaders.getListOfAllowedOptions().size());
        for (int i = 0; i < currentOptionsList.size(); i++) {
            Assertions.assertTrue(ResponseHeaders.getListOfAllowedOptions().contains(currentOptionsList.get(i)));
        }
        Assertions.assertEquals(200, optionsTodo().then().extract().statusCode());

        log.info(currentOptionsList);
        log.info(ResponseHeaders.getListOfAllowedOptions());
    }
}
