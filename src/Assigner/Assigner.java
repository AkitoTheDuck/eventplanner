package Assigner;

import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;

import java.io.File;
import java.util.ArrayList;

/**
 * @author AkitoTheDuck
 */
public class Assigner {

    private ArrayList<Student> schuelerListe;
    private ArrayList<Company> companies;
    private int score = 0;
    private boolean fullKap = false;

    public Assigner() {

    }

    public Assigner(ArrayList<Student> schueler, ArrayList<Company> companies, ArrayList<ClassRoom> classRooms) {
        this.schuelerListe = schueler;
        this.companies = companies;
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
        //System.out.println("Veranstaltung " + wish);
        //System.out.println("Iteration " + iteration);
        int wishFulfilled = 0;

        if(wish != 0){
            if(companies.get(wish - 1).getMaxStudents() < companies.get(wish - 1).getMaxStudents()*companies.get(wish - 1).getMaxEvents() && schueler.getWishFulfilled() < 5){
                companies.get(wish - 1).addSchueler(schueler);
                //System.out.println("Schüler " + schueler.getLastName() + ", " + schueler.getFirstName() + " in " + companies.get(wish - 1).getName() + " hinzugefügt!");
                wishFulfilled++;
                schueler.iterateWishFulfilled();
                //System.out.println("Aktuelle Anzahl an Wünsche erfüllt für " + schueler.getLastName() + ", " + schueler.getFirstName() + ": " + schueler.getWishFulfilled());
            }
            else{
                //System.out.println("Kapazität von " + companies.get(wish - 1).getName() + " erreicht!");
                //System.out.println("--------------------------------");
                fullKap = true;
            }
        }

        //calculate score
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
    }

    public void assign(){
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
        System.out.println("Score: " + score);
    }
}
