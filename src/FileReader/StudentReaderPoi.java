package FileReader;


import DataWrapper.Student;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;


public class StudentReaderPoi {

    private String filename;

    private String delimiter;

    public StudentReaderPoi(String filename, String delimiter) {
        this.filename = filename;
        this.delimiter = delimiter;
    }

    public ArrayList<Student> parse() {


        return null;
    }

}
