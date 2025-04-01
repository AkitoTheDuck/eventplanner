package Algorithm;

import Assigner.Assigner;
import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;
import FileReader.ClassRoomReader;
import FileReader.CompanyReader;
import FileReader.StudentReader;
import FileWriter.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {

        String filename = "ressources/import/2024/Wahl BOT 23_24.xlsx";
        StudentReader reader = new StudentReader(filename);
        ArrayList<Student> students =  reader.parse();

        String filename2 = "ressources/import/2024/Raeume.xlsx";
        ClassRoomReader cReader = new ClassRoomReader(filename2);
        ArrayList<ClassRoom> classRooms =  cReader.parse();

        String filename3 = "ressources/import/2024/Veranstaltungen.xlsx";
        CompanyReader coReader = new CompanyReader(filename3);
        ArrayList<Company> companies =  coReader.parse();


        Assigner assigner = new Assigner(students, companies, classRooms);
        assigner.assign();

        TimeTable algo = new TimeTable(companies, classRooms);
        algo.assign();

        CompanyTimeTableWriter writer = new CompanyTimeTableWriter();
        writer.write(companies);

    }

}
