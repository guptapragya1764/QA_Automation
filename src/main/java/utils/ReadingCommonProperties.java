package utils;

import java.util.Properties;

public class ReadingCommonProperties {

  public static Properties properties = new Properties();

  public static void setProperty() {
    properties.putAll(FileUtility.getProperties("config/common.properties"));
  }
}
