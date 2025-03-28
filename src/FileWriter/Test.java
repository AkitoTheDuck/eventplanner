package FileWriter;

import DataWrapper.Company;
import DataWrapper.Student;
import FileReader.CompanyReader;
import FileReader.StudentReader;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {


        String filename3 = "ressources/import/2024/Veranstaltungen.xlsx";
        CompanyReader coReader = new CompanyReader(filename3);
        ArrayList<Company> companies =  coReader.parse();

        FileWriter<Company> writer = new CompanyTimeTableWriter();
        writer.write(companies);

        String filename4 = "ressources/import/2024/Wahl BOT 23_24.xlsx";
        StudentReader studentReader = new StudentReader(filename4);
        ArrayList<Student> students = studentReader.parse();

        FileWriter<Student> studentFileWriter = new StudentScheduleWriter();
        studentFileWriter.write(students);
    }
}
