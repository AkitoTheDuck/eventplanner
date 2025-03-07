package FileReader;

import DataWrapper.Company;
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

public class CompanyReader extends FileReader<Company> {


    private String filename;

    public CompanyReader(String filename) {
        this.filename = filename;
    }

    @Override
    public ArrayList<Company> parse() {
        ArrayList<Company> companyArrayList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(this.filename));

            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);


            Row headerRow = sheet.getRow(0);
            Map<Integer, String> headers = new HashMap<>();
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                headers.put(i, headerRow.getCell(i).getStringCellValue().trim());
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> lineMap = new HashMap<>();

                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    String header = headers.get(j);
                    String value = getCellValue(row.getCell(j));
                    lineMap.put(header, value);
                }


                String nr = lineMap.get("Nr.");
                String companyName = lineMap.get("Unternehmen");
                String fieldOfStudy = lineMap.get("Fachrichtung");

                String maxS = lineMap.get("Max. Teilnehmer");
                int max;
                if(maxS == null) {
                    max = 0;
                } else {
                    max = Integer.parseInt(maxS);
                }

                String maxE = lineMap.get("Max. Veranstaltungen");
                int intMaxE;
                if(maxE == null) {
                    intMaxE = 0;
                } else {
                    intMaxE = Integer.parseInt(maxE);
                }

                String earlist = lineMap.get("Frühester Zeitpunkt");

                Company company = new Company(nr,companyName,fieldOfStudy,max, intMaxE,earlist);

                companyArrayList.add(company);
            }

            workbook.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        return companyArrayList;
    }
}
