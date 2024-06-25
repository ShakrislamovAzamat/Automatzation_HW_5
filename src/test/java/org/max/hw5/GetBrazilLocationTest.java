package org.max.hw5;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.hw5.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetBrazilLocationTest extends AccuweatherAbstractTest {

    @Test
    void getLocation_search_return_Brazil() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Brazil")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(8,response.size());
        Assertions.assertEquals("Brazil", response.get(0).getEnglishName());
    }
}