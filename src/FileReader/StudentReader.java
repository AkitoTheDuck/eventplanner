package FileReader;


import DataWrapper.Student;
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

public class StudentReader extends FileReader <Student> {

    private String filename;

    public StudentReader(String filename) {
        this.filename = filename;
    }

    public ArrayList<Student> parse() {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(this.filename));

            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);


            Row headerRow = sheet.getRow(0);
            Map<Integer, String> headers = new HashMap<>();
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                headers.put(i, headerRow.getCell(i).getStringCellValue().trim());
            }

            // Iterate through each row, starting from row 1 (to skip headers)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> lineMap = new HashMap<>();

                // Populate the line map with header-value pairs
                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    String header = headers.get(j);
                    String value = getCellValue(row.getCell(j));
                    lineMap.put(header, value);
                }


                int choice1 = 0, choice2 = 0, choice3 = 0, choice4 = 0, choice5 = 0, choice6 = 0;

                for (int y = 1; y <= 6; y++) {
                    String key = "";

                    if(y == 6) {
                        key = "Wahl 6 (Erstazwunsch)";
                    } else {
                        key = "Wahl " + y;
                    }

                    String value = lineMap.get(key);

                    if (value == null) {
                        continue;
                    }

                    int valueInt = Integer.parseInt(value);

                    if ( ! value.isEmpty()) {
                        switch (y) {
                            case 1:
                                choice1 = valueInt;
                                break;
                            case 2:
                                choice2 = valueInt;
                                break;
                            case 3:
                                choice3 = valueInt;
                                break;
                            case 4:
                                choice4 = valueInt;
                                break;
                            case 5:
                                choice5 = valueInt;
                                break;
                            case 6:
                                choice6 = valueInt;
                                break;
                        }
                    }
                }

                Student student = new Student(
                        lineMap.get("Klasse"),
                        lineMap.get("Nachname"),
                        lineMap.get("Vorname"),
                        choice1,
                        choice2,
                        choice3,
                        choice4,
                        choice5,
                        choice6
                );
                studentArrayList.add(student);
            }

            workbook.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        return studentArrayList;
    }

}
