package dev.edumelo.grfnkl.socialplataforms;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.junit.jupiter.api.Test;

import dev.edumelo.grfnkl.socialplataforms.SocialPlataformEnum;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SocialPlataformResourceTest {

    @Test
    public void testHelloEndpoint() {
    	SocialPlataformEnum socialServiceEnum = SocialPlataformEnum.values()[new Random().nextInt(SocialPlataformEnum.values().length)];
        String userName = "";
		given()
          .when().get(String.format("/social-plataforms/%s/users/%s/followers", socialServiceEnum, userName))
          .then()
             .statusCode(200);
    }

}