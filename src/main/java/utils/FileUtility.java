package utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class FileUtility {

  private FileUtility() {

  }

  public static List<String> readFileAsList(String fileName) {

    List<String> readFile = new ArrayList<>();
    try {
      readFile = FileUtils.readLines(resource(fileName), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return readFile;
  }


  /**
   * helper method to read file from fileName
   *
   * @param fileName file which is to be read
   * @return content of the file
   */
  public static String readFile(String fileName) {
    String content = null;
    try {
      content = FileUtils.readFileToString(resource(fileName), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }

  /**
   *
   */
  public static File resource(String fileName) {
    return new File(
        Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(fileName)).getFile());

  }

  /**
   * helper method to get the properties of the file
   *
   * @param fileName od the file to be read
   * @return properties present in the file
   */
  public static Properties getProperties(String fileName) {
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    Properties prop = new Properties();
    InputStream ip = loader.getResourceAsStream(fileName);
    loader.getResource(fileName);
    try {
      prop.load(ip);
    } catch (IOException e) {
      System.out.println("Unable to open [" + fileName + "]");
    }
    return prop;
  }

  /**
   * helper method to write content into the path
   *
   * @param path path of the file where it should be written
   * @param content of the file which should be written
   */
  public static void writeContent(String path, String content) {

    try {
      PrintWriter pw = new PrintWriter(path);
      pw.println(content);
      pw.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


  }

  public static String getResourcePath(String fileName) {
    return Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(fileName))
        .getPath();
  }

  public static String getFileContent(String filePath) {

    File file = new File(filePath);
    String content = null;
    try {
      content = new String(Files.readAllBytes(Paths.get(file.toURI())));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return content;
  }

  public static JSONObject jsonFileToJsonObject(String filePath) {
    JSONObject jsonObject = null;
    try {
      jsonObject = new JSONObject(getFileContent(filePath));
    } catch (JSONException exception) {
      exception.printStackTrace();
    }

    return jsonObject;
  }
}
