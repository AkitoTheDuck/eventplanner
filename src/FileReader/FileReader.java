package FileReader;

import DataWrapper.DataWrapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Christian
 */
public abstract class FileReader <E extends DataWrapper> {

    protected String filename;

    public FileReader(String filename) {
        this.filename = filename;
    }

    public abstract ArrayList<E> parse();

    protected static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf((int)cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }

    // Extract header row and return a Map of column index to header value
    protected Map<Integer, String> getHeaders(Sheet sheet) {
        Row headerRow = sheet.getRow(0);
        Map<Integer, String> headers = new HashMap<>();
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            headers.put(i, headerRow.getCell(i).getStringCellValue().trim());
        }
        return headers;
    }

    // Read the rows of the Excel file and return data in a structured manner
    protected ArrayList<Map<String, String>> extractRows(Sheet sheet, Map<Integer, String> headers) {
        ArrayList<Map<String, String>> rowData = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> lineMap = new HashMap<>();
            for (int j = 0; j < headers.size(); j++) {
                String header = headers.get(j);
                String value = getCellValue(row.getCell(j));
                lineMap.put(header, value);
            }
            rowData.add(lineMap);
        }
        return rowData;
    }

    // Helper method to open the file and create the workbook
    protected Workbook openWorkbook() throws IOException {
        FileInputStream file = new FileInputStream(new File(this.filename));
        return new XSSFWorkbook(file);
    }
}
