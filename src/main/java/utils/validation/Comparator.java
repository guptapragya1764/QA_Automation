package utils.validation;

import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class Comparator {

  /**
   * Use this method if it is fine that expected json to have additional attribute than actual json
   * Also following method does not check order of attributes
   */

  public static void jsonComparisonLenient(JSONObject expectedObject, JSONObject actualObject) {
    JSONAssert.assertEquals(expectedObject, actualObject, JSONCompareMode.LENIENT);
  }


  /**
   * Use this method if you need exact comparison Also following method does not check order of
   * attributes
   */

  public static void jsonComparisonNonExtensible(JSONObject expectedObject,
      JSONObject actualObject) {
    JSONAssert.assertEquals(expectedObject, actualObject, JSONCompareMode.NON_EXTENSIBLE);













































































  }


}
