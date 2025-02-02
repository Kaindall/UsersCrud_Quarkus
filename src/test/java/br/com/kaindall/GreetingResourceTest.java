package br.com.kaindall;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void findAllUsersShouldReturn200() {
        given()
          .when().get("/usuarios")
          .then()
             .statusCode(200);
             //.body(is("Hello from Quarkus REST"));
    }

}
