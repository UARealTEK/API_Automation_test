package base;

import io.restassured.RestAssured;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeAll;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import utils.Endpoints;
import utils.ResponseHeaders;

import static io.restassured.RestAssured.*;

public class BaseApiTest {

    private static final Log log = LogFactory.getLog(BaseApiTest.class);
    protected static Properties config;

    @BeforeAll
    public static void setup() {
        loadConfig();
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

    public String getXChallengerSessionID() {
        return new BasePOSTMethods().postChallenge()
                .extract()
                .header(ResponseHeaders.CHALLENGER.getHeader());
    }

}
