package utils;

import static constants.Constants.DATA_GENERATOR_METHOD_SEPARATOR;
import static constants.Constants.INITIAL_INDEX;
import static constants.Constants.VALUE_SEPARATOR;
import static java.util.Objects.requireNonNull;
import static utils.JavaUtility.getClassByString;

import constants.ExcelColumnContants;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.testng.Assert;

public class ExcelReader {

  private static final int FIRST_INDEX = 0;

  private ExcelReader() {

  }

  public static Object[] getData(String path, int index) {
    Sheet sheet = getSheetByIndex(path, index);
    return readData(sheet);
  }


  private static Sheet getSheetByIndex(String excelPath, int index) {
    return getWorkBook(excelPath).getSheetAt(index);
  }


  private static Workbook getWorkBook(String excelPath) {
    Workbook wb = null;
    try {
      wb = WorkbookFactory.create(new FileInputStream(excelPath));
    } catch (
        IOException e) {
      e.printStackTrace();
    }
    return wb;
  }

  private static Object[] readData(Sheet sheet) {
    int rowcount = sheet.getLastRowNum();
    int colRount = sheet.getRow(INITIAL_INDEX).getLastCellNum();
    List<Object> dataAll = new ArrayList<>();
    for (int i = FIRST_INDEX; i <= rowcount; i++) {
      String executionStatus = sheet.getRow(i).getCell(ExcelColumnContants.EXECUTION_STATUS)
          .getStringCellValue();
      if (executionStatus.equals("Y")) {
        Map<String, String> mp = new HashMap<>();
        for (int j = 1; j < colRount; j++) {
          String header = sheet.getRow(INITIAL_INDEX).getCell(j).getStringCellValue();
          Cell cell = sheet.getRow(i).getCell(j);
          String cellVal = "";
          if (cell.getCellType() == CellType.NUMERIC) {
            cellVal = NumberToTextConverter.toText(cell.getNumericCellValue());
          }
          if (cell.getCellType() == CellType.STRING) {
            cellVal = cell.getStringCellValue();
          }

          mp.put(header, cellVal);
        }
        mp.replaceAll((k, v) -> transformData(v));
        dataAll.add(mp);
//                val++;
      }

    }

    return dataAll.toArray();

  }


  private static String transformData(String value) {
    if (value != null && value.toLowerCase().startsWith(DATA_GENERATOR_METHOD_SEPARATOR)) {
      String[] parts = value.split(VALUE_SEPARATOR);
      String dataClass = "utils.dataUtils.DataGenerator";
      Method method = null;
      try {
        method = getClassByString(dataClass).getMethod(parts[FIRST_INDEX]);

      } catch (Exception ex) {
        Assert.fail(ex.getMessage());
      }

      try {
        return (String) requireNonNull(method).invoke(null);
      } catch (Exception ex) {
        Assert.fail(ex.getMessage());
      }
    }
    return requireNonNull(value).equals("REMOVE_KEY") ? null : value;
  }
}
