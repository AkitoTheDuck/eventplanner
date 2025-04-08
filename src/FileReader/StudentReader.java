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

/**
 * @author Christian
 *
 * * Diese Klasse liest eine Excel-Datei und extrahiert Informationen über Unternehmen.
 *  * Sie verwendet Apache POI, um die Excel-Datei zu verarbeiten und eine Liste
 *  * von Student-Objekten zu erstellen.
 */
public class StudentReader extends FileReader <Student> {

    private String filename;

    public StudentReader(String filename) {
        super(filename);
    }

    @Override
    public ArrayList<Student> parse() {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        try (Workbook workbook = openWorkbook()) {
            Sheet sheet = workbook.getSheetAt(0);
            Map<Integer, String> headers = getHeaders(sheet);
            ArrayList<Map<String, String>> rows = extractRows(sheet, headers);

            for (Map<String, String> lineMap : rows) {
                int[] choices = new int[6];

                for (int i = 1; i <= 6; i++) {
                    String key = (i == 6) ? "Wahl 6 (Erstazwunsch)" : "Wahl " + i;
                    String value = lineMap.get(key);
                    if (value != null && !value.isEmpty()) {
                        choices[i - 1] = Integer.parseInt(value);
                    }
                }

                Student student = new Student(
                        lineMap.get("Klasse"),
                        lineMap.get("Nachname"),
                        lineMap.get("Vorname"),
                        choices[0], choices[1], choices[2], choices[3], choices[4], choices[5]
                );
                studentArrayList.add(student);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentArrayList;
    }
}
