package base;

import io.restassured.RestAssured;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import utils.Endpoints;
import utils.RequestHeaders;
import utils.ResponseHeaders;

import static io.restassured.RestAssured.*;

public class BaseApiTest {

    protected static Properties config;

    @BeforeAll
    public static void setup() {
        loadConfig(); // Load properties from a config file
        RestAssured.baseURI = config.getProperty("base.url");
    }

    private static void loadConfig() {
        config = new Properties();
        try (FileInputStream file = new FileInputStream("src/test/resources/config.properties")) {
            config.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getXChallengerSessionID() {
        return given()
                .when()
                .post(config.getProperty("base.url") + Endpoints.CHALLENGER.getEndpoint())
                .then()
                .statusCode(201)
                .extract()
                .header(ResponseHeaders.CHALLENGER.getHeader());
    }

    public Integer getRandomTodoID() {
        Random random = new Random();
        List<Integer> todoIDs = given()
                .header(RequestHeaders.X_CHALLENGER.getRequestHeader(), new BaseApiTest().getXChallengerSessionID())
                .when()
                .get(Endpoints.TODOS.getEndpoint())
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("todos.id", Integer.class);

        return todoIDs.isEmpty() ? null : todoIDs.get(random.nextInt(todoIDs.size()));
    }
}
