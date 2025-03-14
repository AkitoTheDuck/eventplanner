import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;

import java.io.File;
import java.util.ArrayList;

/**
 * @author AkitoTheDuck
 */
public class Assigner {

    private File myObj;
    private ArrayList<Student> schuelerListe;
    private ArrayList<Company> companies;
    private ArrayList<ClassRoom> classRooms;
    private int score = 0;
    private boolean fullKap = false;
    /**
     * Wünsche
     * Prio Wunsch 1 - n
     * Kapazität einhalten
     * Schüler(Klasse, Name, Vorname, wahl1, wahl2, wahl3, wahl4, wahl5, wahl6)
     */
    public Assigner() {

    }

    public Assigner(ArrayList<Student> schueler, ArrayList<Company> companies, ArrayList<ClassRoom> classRooms) {
        this.schuelerListe = schueler;
        this.companies = companies;
        this.classRooms = classRooms;
    }

    /**
     * @author MafffimGit
     */
    public static double calcScore(int studentCount, int points){
        return (double) points / studentCount * 20;
    }

    /**
     *
     * @author MaffimGit, AkitoTheDuck
     */
    public void assignPupil(int iteration, int wish, Student schueler) {
        System.out.println("Veranstaltung " + wish);
        System.out.println("Iteration " + iteration);
        int wishFulfilled = 0;

        //veranstaltung muss zu Event geändert werden
        //Event noch nicht vollständig von der Funktionalität und nicht Bereit für die Nutzung
        /*
        if(veranstaltungen.get(wish - 1).getKap() < veranstaltungen.get(wish - 1).getTotalCap() && schueler.getWishFulfilled() < 5){
            veranstaltungen.get(wish - 1).addSchueler(schueler);
            System.out.println("Schüler " + schueler.getLastName() + ", " + schueler.getFirstName() + " in " + veranstaltungen.get(wish - 1).getName() + " hinzugefügt!");
            wishFulfilled++;
            schueler.iterateWishFulfilled();
            System.out.println("Aktuelle Anzahl an Wünsche erfüllt für " + schueler.getLastName() + ", " + schueler.getFirstName() + ": " + schueler.getFulfilled());
        } else{
            System.out.println("Kapazität von " + veranstaltungen.get(wish - 1).getName() + " erreicht!");
            System.out.println("--------------------------------");

            fullKap = true;
        }
        */
        if(wishFulfilled == 1){
            switch(iteration){
                case 1:
                    score += wishFulfilled*6;
                    break;
                case 2:
                    score += wishFulfilled*5;
                    break;
                case 3:
                    score += wishFulfilled*4;
                    break;
                case 4:
                    score += wishFulfilled*3;
                    break;
                case 5:
                    score += wishFulfilled*2;
                    break;
                case 6:
                    score += wishFulfilled;
                    break;
            }
        }

        System.out.println("Score: " + score);

    }
    public void testAssignement(){
        //assigining events to rooms
        //noch nicht fertig
        //assignEvents(companies, classRooms);

        //assigning students to events
        for(Student schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(1, schueler.getChoice1(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(Student schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(2, schueler.getChoice2(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(Student schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(3, schueler.getChoice3(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(Student schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(4, schueler.getChoice4(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(Student schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(5, schueler.getChoice5(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(Student schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(6, schueler.getChoice6(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }
    }

    private void assignEvents(ArrayList<Company> companies, ArrayList<ClassRoom> rooms) {
        ArrayList<ClassRoom> freeRooms = rooms;
        for(Company company : companies){
            for(ClassRoom freeRoom : freeRooms){
                //boolean besetzt?
                if(company.getMaxStudents() <= Integer.parseInt(freeRoom.getCapacity())){
                    //Event wie DummyVeranstaltung planen
                    Event event = new Event(freeRoom, company);
                    freeRooms.remove(freeRoom);
                    System.out.println(event.printInfo());
                }
            }
        }
    }
/*
public void printInfo(){
        //Test Output
        for(Company company : companies){
            System.out.println("--------------------------------");
            System.out.println("Name: " + company.getName());
            System.out.println("Gesamtkapazität: " + company.getNr());
            System.out.println("VNr.: " + company.getNummer());
            System.out.println("Schüler: " + company.getSchuelerListe().size());
            for(Student schueler : company.getSchuelerListe()){
                System.out.println("Klasse: " + schueler.getKlasse());
                System.out.println("Name: " + schueler.getName());
                System.out.println("Vorname: " + schueler.getVorname());
                System.out.println("---");
            }
        }
        System.out.println("Erfüllungsscore: " + calcScore(schuelerListe.size(), score));
        System.out.println("--------------------------------");
    }
 */

}