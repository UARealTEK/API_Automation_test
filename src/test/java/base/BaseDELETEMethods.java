package base;

import io.restassured.response.Response;
import utils.Endpoints;
import utils.RequestHeaders;

import static io.restassured.RestAssured.given;

public class BaseDELETEMethods extends BaseApiTest {

    public Response deleteTodo(Integer todoID) {
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), BaseApiTest.getChallengerID())
                .when()
                .delete(Endpoints.TODOS.getEndpoint() + "/" + todoID);
    }

    public Response deleteRandomTodo() throws Exception {
        Integer randomID = BaseGETMethods.getRandomTodoID();
        return given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), BaseApiTest.getChallengerID())
                .when()
                .delete(Endpoints.TODOS.getEndpoint() + "/" + randomID);
    }
}
