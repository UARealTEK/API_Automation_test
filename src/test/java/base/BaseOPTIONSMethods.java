package base;

import io.restassured.response.Response;
import lombok.Getter;
import utils.Endpoints;
import utils.RequestHeaders;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
@Getter
public class BaseOPTIONSMethods extends BaseApiTest {

    public Response optionsTodo() {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), BaseApiTest.getChallengerID())
                .when()
                .options(Endpoints.TODOS.getEndpoint());
    }

}
