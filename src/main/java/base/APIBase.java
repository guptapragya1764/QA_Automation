package base;

import static utils.FileUtility.getProperties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Properties;

public class APIBase {

  private APIBase() {

  }

  public static Properties commonProp;
  public static Properties envProp;

  static {
    commonProp = getProperties("common.properties");
    envProp = getProperties(commonProp.get("env") + ".properties");
  }


  public static Response doRequest(String method, String url, String body) {
    return RestAssured.given().log().all()
        .contentType(ContentType.JSON).body(body)
        .when().request(method, url)
        .then().log().all()
        .extract().response();
  }
}
