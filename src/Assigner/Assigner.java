package Assigner;

import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author AkitoTheDuck
 */
public class Assigner {

    private File myObj;

    private ArrayList<Student> schuelerListe;
    private ArrayList<Company> companies;
    private ArrayList<ClassRoom> classRooms;
    private ArrayList<HashMap<Company , ArrayList<Student>>> records;
    

    private ArrayList<Event> events = new ArrayList<>();
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


        HashMap<Company , ArrayList<Student>> recordSet = new HashMap<>();
        ArrayList<Student> students = new ArrayList<>();
        wish -= 1;
        if(wish != -1){
            if(companies.get(wish).getCapacity() < companies.get(wish).getTotalStudents() && schueler.getWishFulfilled() < 5){
                companies.get(wish).addStudent(schueler);






                System.out.println("Schüler " + schueler.getLastName() + ", " + schueler.getFirstName() + " in " + companies.get(wish).getName() + " hinzugefügt!");
                wishFulfilled++;
                schueler.iterateWishFulfilled();
                System.out.println("Aktuelle Anzahl an Wünsche erfüllt für " + schueler.getLastName() + ", " + schueler.getFirstName() + ": " + schueler.getWishFulfilled());
            }
            else{
                System.out.println("Kapazität von " + companies.get(wish).getName() + " erreicht!");
                System.out.println("--------------------------------");

                fullKap = true;
            }
        }

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


        fulfillWishes();

        assignEvents();
        System.out.println("Alle Events assigned");
    }

    private void fulfillWishes() {
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

    private void assignEvents() {
        ArrayList<ClassRoom> freeRooms = new ArrayList<>(classRooms);
        for(Company company : companies){
            for(ClassRoom room : freeRooms){
                if(company.getMaxStudents() <= Integer.parseInt(room.getCapacity()) && !company.getStudentList().isEmpty()){
                    Event event = new Event(room, company);
                    events.add(event);
                    //count < maxEvents? && zeitslot != E? => Neues Event
                    while(!event.lastSlot()){
                        //System.out.println(event.printInfo());
                        //create copy!!!!
                        events.add(new Event(event)); //adds new event with new timing
                    }
                    freeRooms.remove(room);
                    break;
                }
            }

        }
    }
}