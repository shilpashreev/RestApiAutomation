import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class Test1 {

    @Test(dataProvider = "season")
    public void FormulaTest1(String year, int count){
         RestAssured.given()
                 .pathParam("year",year)
                 .when()
                    .get("http://ergast.com/api/f1/{year}/circuits.json")
                 .then()
                    .assertThat()
//                    .body("MRData.CircuitTable.Circuits.circuitId",hasSize(count))
                    .contentType(ContentType.JSON);
    }

    @DataProvider(name="season")
    public Object[][] testData(){
        return new Object[][]{{"2018",10}, {"2019",20}};
    }

    @Test
    public void Testmultipleapicalls(String year, int count){
        String circuitId =RestAssured.given()
                .pathParam("year",year)
                .when()
                .get("http://ergast.com/api/f1/{year}/circuits.json")
                .then()
                .extract()
                    .path("MRData.CircuitTable.Circuits[0].circuitId");

        RestAssured.given()
                .pathParam("circuitId",circuitId)
                .when()
                    .get("http://ergast.com/api/f1/circuits/{circuitId}.json")
                .then()
                    .statusCode(200);

    }

}
