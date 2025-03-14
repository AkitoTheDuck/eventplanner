import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;
import FileReader.ClassRoomReader;
import FileReader.CompanyReader;
import FileReader.StudentReader;

import java.util.ArrayList;
/**
 * Test main()
 */
public class Main {

    ArrayList<Student> schueler = new ArrayList<Student>();
    ArrayList<DummyVeranstaltungen> veranstaltungen = new ArrayList<DummyVeranstaltungen>();

    public static void main(String[] args){
        long nanoTime = System.nanoTime();



        Main main = new Main();
        main.doThings();



        System.out.println(System.nanoTime() - nanoTime);
        long resultTime = System.nanoTime() - nanoTime;
        // Convert nanotime to seconds and milliseconds
        long seconds = resultTime / 1_000_000_000; // Nanoseconds to seconds
        long milliseconds = (resultTime % 1_000_000_000) / 1_000_000; // Remaining nanoseconds to milliseconds
        // Print the result
        System.out.printf("Seconds: %d, Milliseconds: %d\n", seconds, milliseconds);

    }

    public void doThings(){

        String filename = "ressources/import/IMPORT BOT2_Wahl.xlsx";
        StudentReader reader = new StudentReader(filename);
        ArrayList<Student> students =  reader.parse();


        String filename2 = "ressources/import/IMPORT BOT0_Raumliste.xlsx";
        ClassRoomReader cReader = new ClassRoomReader(filename2);
        ArrayList<ClassRoom> classRooms =  cReader.parse();

        String filename3 = "ressources/import/IMPORT BOT1_Veranstaltungsliste.xlsx";
        CompanyReader coReader = new CompanyReader(filename3);
        ArrayList<Company> companies =  coReader.parse();


        System.out.println("Veranstaltungen");
        for(Company company : companies){
            System.out.println("Nr.: " + company.getNr());
            System.out.println("Name: " + company.getName());
            System.out.println("Frühster Start: " + company.getEarliestStart());
            System.out.println("Max Teilnehmer: " + company.getMaxStudents());
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

        Assigner assigner = new Assigner(students, companies, classRooms);
        assigner.testAssignement();
        //assigner.printInfo();
    }
}
