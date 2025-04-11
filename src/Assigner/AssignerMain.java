package Assigner;

import Algorithm.TimeTable;
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
public class AssignerMain {

    public static void main(String[] args){
        long nanoTime = System.nanoTime();



        AssignerMain main = new AssignerMain();
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
        assigner.assign();

        TimeTable algo = new TimeTable(companies, classRooms);
        algo.assign();

        EventAssigner eventAssigner = new EventAssigner();

        eventAssigner.assignPupil(companies);

        for(Student student : students){
            ArrayList<Integer> wishes = new ArrayList<>(student.getChoices());
                for(Tuple tuple : student.getZuweisungsListe()){
                    for(int i = 0; i < student.getChoices().size(); i++){
                        if(Integer.parseInt(tuple.getWunsch()) == student.getChoices().get(i)){
                            wishes.remove(student.getChoices().get(i));
                        }
                    }
                }
                student.setNotFulFilled(wishes);
        }
        eventAssigner.assignRestWishes(students, companies, 0);
        eventAssigner.assignRestWishes(students, companies, 1);
        int iter = 0;
        for(Student student : students){
            if(student.getNotFulFilled().size() > 1){
                System.out.println("Schüler " + student.getFirstName() + " hat nicht alle Zuweisungen");
                System.out.println("Zahl: " + iter);
            }
            iter++;
        }
        System.out.println("Gewichtung: " + EventAssigner.gewichtung(students.size()));
    }
}
