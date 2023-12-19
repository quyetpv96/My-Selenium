package practiceAddData.provider;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.File;

import practiceAddData.common.MyCsvData;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddTaskProvider {

    // region Basic CSV
    @DataProvider(name = "RISE_AddTask_Data_CSV")
    public Object[][] RISE_AddTask_Data_CSV() {
        String csvFilePath = System.getProperty("user.dir") + File.separator + "src/test/java/practiceAddData/data/AddTaskData.csv";
        List<MyCsvData> dataList = readCsvData(csvFilePath);

        Object[][] data = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            MyCsvData myCsvData = dataList.get(i);
            Map<String, String> testData = new HashMap<>();
            testData.put("title", myCsvData.getTitle());
            testData.put("description", myCsvData.getDescription());
            testData.put("relatedTo", myCsvData.getRelatedTo());
            testData.put("project", myCsvData.getProject());
            testData.put("points", myCsvData.getPoints());
            testData.put("milestone", myCsvData.getMilestone());
            testData.put("assignTo", myCsvData.getAssignTo());
            testData.put("collaborators", myCsvData.getCollaborators());
            testData.put("status", myCsvData.getStatus());
            testData.put("priority", myCsvData.getPriority());
            testData.put("labels", myCsvData.getLabels());
            testData.put("startDate", myCsvData.getStartDate());
            testData.put("deadLine", myCsvData.getDeadLine());
            testData.put("repeat", myCsvData.getRepeat());
            testData.put("frequency", myCsvData.getFrequency());
            testData.put("cycle", myCsvData.getCycle());
            testData.put("uploadFile", myCsvData.getUploadFile());
            data[i][0] = testData;
        }

        return data;
    }

    /**
     * Read data from CSV file
     *
     * @param csvFilePath : the file path
     * @return CSVObject
     */
    private List<MyCsvData> readCsvData(String csvFilePath) {
        try (Reader reader = new FileReader(csvFilePath)) {
            CsvToBean<MyCsvData> csvToBean = new CsvToBeanBuilder<MyCsvData>(reader)
                    .withType(MyCsvData.class)
                    .withSeparator(',')
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
        }
    }

    // endregion//

    // region Improve CSV

    // endregion
    @DataProvider(name = "RISE_AddTask_Data_Excel")
    public Object[][] RISE_AddTask_Data_Excel() {
        String excelFilePath = System.getProperty("user.dir") + File.separator + "src/test/java/practiceAddData/data/AddTaskDataExcel.xlsx";
        return readExcelData(excelFilePath);
    }


    /**
     * Read data from Excel file
     *
     * @param excelFilePath : the file path
     * @return CSVObject
     */
    private Object[][] readExcelData(String excelFilePath) {
        try (FileInputStream fileInputStream = new FileInputStream(excelFilePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rows - 1][1];

            for (int i = 1; i < rows; i++) {
                Map<String, String> testData = new HashMap<>();
                Row row = sheet.getRow(i);

                for (int j = 0; j < cols; j++) {
                    Cell cellHeader = sheet.getRow(0).getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    String header = cellHeader.toString().trim();
                    String value = cell.toString().trim();

                    testData.put(header, value);
                }

                data[i - 1][0] = testData;
            }

            return data;
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }
}
