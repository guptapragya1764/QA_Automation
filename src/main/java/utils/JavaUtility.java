package utils;

public class JavaUtility {

  private JavaUtility() {

  }

  public static Class getClassByString(String className) {
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

}
