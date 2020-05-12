import io.restassured.RestAssured;
import org.testng.annotations.Test;


public class Test1 {

    @Test
    public void FormulaTest1(){
         RestAssured.given()
                 .when()
                    .get("http://ergast.com/api/f1/2017/circuits.json")
                 .then()
                    .assertThat()
                    .body();


    }


}
