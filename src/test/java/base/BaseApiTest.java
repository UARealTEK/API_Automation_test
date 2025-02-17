package base;

import io.restassured.RestAssured;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import utils.ResponseHeaders;

@Getter
public class BaseApiTest {

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
