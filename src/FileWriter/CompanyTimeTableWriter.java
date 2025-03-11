package FileWriter;

import DataWrapper.Company;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompanyTimeTableWriter extends FileWriter {


    @Override
    public void write() {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Raumplan");

        Map<String,Object[]> data = new TreeMap<>();

        data.put("1", new Object[] {"Organisationsplan für den Berufsorientierungstag"});
        data.put("2", new Object[] {"8:30 bis 8:45 Uhr Begrüßung und Einführung in der Aula"});
        data.put("3", new Object[] {"13:10 bis 13:20 Uhr Abschluss im Klassenverbund"});
        data.put("4", new Object[] {""});
        data.put("5", new Object[] {"","8:45 – 9:30","9:50 – 10:35","10:35 – 11:20","11:40 – 12:25","12:25 – 13:10"});
        data.put("6", new Object[] {"","A","B","C","D","E"});

        Set<String> keyset = data.keySet();
        int rownum = 0;

        for (String key : keyset) {

            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);

                if (obj instanceof String)
                    cell.setCellValue((String)obj);

                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        try {

            FileOutputStream out = new FileOutputStream(
                    new File("src/FileWriter/test1.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println(
                    "test1.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
