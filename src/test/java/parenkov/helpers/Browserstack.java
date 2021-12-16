package parenkov.helpers;

import org.aeonbits.owner.ConfigFactory;
import parenkov.config.BrowserStackConfig;

import static io.restassured.RestAssured.given;

public class Browserstack {

    private static BrowserStackConfig config =
            ConfigFactory.create(BrowserStackConfig.class, System.getProperties());

    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic(config.getUser(), config.getKey())
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId + ".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response()
                .path("automation_session.video_url");

    }
}
