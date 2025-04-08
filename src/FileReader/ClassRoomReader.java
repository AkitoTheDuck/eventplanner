package FileReader;

import DataWrapper.ClassRoom;
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
 *  * von ClassRoom-Objekten zu erstellen.
 */
public class ClassRoomReader extends FileReader <ClassRoom> {

    private String filename;

    public ClassRoomReader(String filename) {
        super(filename);
    }

    @Override
    public ArrayList<ClassRoom> parse() {
        ArrayList<ClassRoom> classRoomArrayList = new ArrayList<>();
        try (Workbook workbook = openWorkbook()) {
            Sheet sheet = workbook.getSheetAt(0);
            Map<Integer, String> headers = getHeaders(sheet);
            ArrayList<Map<String, String>> rows = extractRows(sheet, headers);

            for (Map<String, String> lineMap : rows) {
                ClassRoom classRoom = new ClassRoom(
                        lineMap.get("Raum"),
                        lineMap.get("Kapazität")
                );
                classRoomArrayList.add(classRoom);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return classRoomArrayList;
    }
}
