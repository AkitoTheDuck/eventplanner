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


        System.out.println("Veranstaltungen");
        for(Company company : companies){
            System.out.println("Nr.: " + company.getNr());
            System.out.println("Name: " + company.getName());
            System.out.println("Frühster Start: " + company.getEarliestStart());
            System.out.println("Max Events: " + company.getMaxEvents());
            System.out.println("Studienfeld: " + company.getFieldOfStudy());
            System.out.println("=====================");
        }

        System.out.println("Räume");

        for(ClassRoom classRoom : classRooms){
            System.out.println("Raumnummer: " + classRoom.getRoomNumber());
            System.out.println("Kapazität: " + classRoom.getCapacity());
            System.out.println("=====================");
        }

        System.out.println("Students");

        for(Student student : students){
            System.out.println("Nachname: " + student.getLastName());
            System.out.println("Vorname: " + student.getFirstName());
            System.out.println("Klasse: " + student.getKlasse());
            System.out.println("Wunsch 1: " + student.getChoice1());
            System.out.println("Wunsch 2: " + student.getChoice2());
            System.out.println("Wunsch 3: " + student.getChoice3());
            System.out.println("Wunsch 4: " + student.getChoice4());
            System.out.println("Wunsch 5: " + student.getChoice5());
            System.out.println("Wunsch 6: " + student.getChoice6());
            System.out.println("=====================");
        }

        int p = 0;

    }

}
