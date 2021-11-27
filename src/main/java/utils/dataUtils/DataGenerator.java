package utils.dataUtils;

import com.mifmif.common.regex.Generex;

public class DataGenerator {

  private static final String REGREX_SSN = "[0-9]{9}";
  private static final String REGREX_NAME = "([a-z]|[A-Z]){6}([a-z]|[A-Z]){3,12}";
  private static final String REGREX_PASSWORD = "[A-Z]{1}[a-z]{5}[!$@]{1}[0-9][3]";

  public static String usingRegrex(String pattern) {
    Generex generex = new Generex(pattern);
    generex.setSeed(System.nanoTime());
    return generex.random();
  }

  public static String userName() {
    return usingRegrex(REGREX_NAME);
  }

  public static String password() {
    return usingRegrex(REGREX_PASSWORD);
  }
}
