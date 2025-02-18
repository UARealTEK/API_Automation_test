package base;

import io.restassured.RestAssured;
import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeAll;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import utils.ResponseHeaders;

@Getter
public class BaseApiTest {

    private static final Log log = LogFactory.getLog(BaseApiTest.class);
    protected static Properties config;
    @Getter
    private static String challengerID = "6eb3d294-a034-43f6-81da-69f0f7e12af9";

    @BeforeAll
    public static void setup() {
        loadConfig();
        RestAssured.baseURI = config.getProperty("base.url");

        if (challengerID == null) {
            challengerID = getXChallengerSessionID();
        }
        log.info("My Challenger is: " + getChallengerID());
    }

    private static void loadConfig() {
        config = new Properties();
        try (FileInputStream file = new FileInputStream("src/test/resources/config.properties")) {
            config.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String getXChallengerSessionID() {
        return new BasePOSTMethods().postChallenge()
                .extract()
                .header(ResponseHeaders.CHALLENGER.getHeader());
    }

}
