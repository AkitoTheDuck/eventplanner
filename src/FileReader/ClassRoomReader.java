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

public class ClassRoomReader extends FileReader <ClassRoom> {

    private String filename;

    public ClassRoomReader(String filename) {
        this.filename = filename;
    }

    public ArrayList<ClassRoom> parse() {
        ArrayList<ClassRoom> classRoomArrayList = new ArrayList<>();
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
                if (row == null) {
                    continue;
                }

                Map<String, String> lineMap = new HashMap<>();

                for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                    String header = headers.get(j);
                    String value = getCellValue(row.getCell(j));
                    lineMap.put(header, value);
                }

                ClassRoom classRoom = new ClassRoom(lineMap.get("Raum") ,lineMap.get("Kapazität"));
                classRoomArrayList.add(classRoom);

            }

            workbook.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        return classRoomArrayList;
    }
}
