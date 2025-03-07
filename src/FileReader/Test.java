import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;
import FileReader.ClassRoomReader;
import FileReader.CompanyReader;
import FileReader.StudentReader;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {

        String filename = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\IMPORT BOT2_Wahl.xlsx";
        StudentReader reader = new StudentReader(filename);
        ArrayList<Student> students =  reader.parse();


        String filename2 = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\IMPORT BOT0_Raumliste.xlsx";
        ClassRoomReader cReader = new ClassRoomReader(filename2);
        ArrayList<ClassRoom> classRooms =  cReader.parse();

        String filename3 = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\IMPORT BOT1_Veranstaltungsliste.xlsx";
        CompanyReader coReader = new CompanyReader(filename3);
        ArrayList<Company> companies =  coReader.parse();

        int p = 0;

    }

}
