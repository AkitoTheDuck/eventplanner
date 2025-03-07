package FileReader;

import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {

        String filename = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\2024\\Wahl BOT 23_24.xlsx";
        StudentReader reader = new StudentReader(filename);
        ArrayList<Student> students =  reader.parse();


        String filename2 = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\2024\\Raeume.xlsx";
        ClassRoomReader cReader = new ClassRoomReader(filename2);
        ArrayList<ClassRoom> classRooms =  cReader.parse();

        String filename3 = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\2024\\Veranstaltungen.xlsx";
        CompanyReader coReader = new CompanyReader(filename3);
        ArrayList<Company> companies =  coReader.parse();

        int p = 0;

    }

}
