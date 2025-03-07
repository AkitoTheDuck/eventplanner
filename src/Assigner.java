import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author AkitoTheDuck
 */
public class Assigner {

    private File myObj;
    private ArrayList<DummySchueler> schuelerListe;
    private ArrayList<DummyVeranstaltungen> veranstaltungen;
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

    public Assigner(ArrayList<DummySchueler> schueler, ArrayList<DummyVeranstaltungen> veranstaltungen) {
        this.schuelerListe = schueler;
        this.veranstaltungen = veranstaltungen;
    }

    /**
     * @author MafffimGit
     */
    public static double calcScore(int studentCount, int points){
        return (double) points / studentCount * 20;
    }

    public void assignPupil(int iteration, int wish, DummySchueler schueler) {
        System.out.println("Veranstaltung " + wish);
        System.out.println("Iteration " + iteration);
        int wishFulfilled = 0;
        if(veranstaltungen.get(wish - 1).getKap() < veranstaltungen.get(wish - 1).getTotalCap() && schueler.getFulfilled() < 5){
            veranstaltungen.get(wish - 1).addSchueler(schueler);
            System.out.println("Schüler " + schueler.getName() + ", " + schueler.getVorname() + " in " + veranstaltungen.get(wish - 1).getName() + " hinzugefügt!");
            wishFulfilled++;
            schueler.setFulfilled();
            System.out.println("Aktuelle Anzahl an Wünsche erfüllt für " + schueler.getName() + ", " + schueler.getVorname() + ": " + schueler.getFulfilled());
        } else{
            System.out.println("Kapazität von " + veranstaltungen.get(wish - 1).getName() + " erreicht!");
            System.out.println("--------------------------------");

            fullKap = true;
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
        for(DummySchueler schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(1, schueler.getFirstWish(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(DummySchueler schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(2, schueler.getSecondWish(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(DummySchueler schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(3, schueler.getThirdWish(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(DummySchueler schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(4, schueler.getFourthWish(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(DummySchueler schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(5, schueler.getFifthWish(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }

        for(DummySchueler schueler : schuelerListe){
            if(!fullKap) {
                assignPupil(6, schueler.getSixthWish(), schueler);
            } else {
                fullKap = false;
                break;
            }
        }
    }

    public void printInfo(){
        //Test Output
        for(DummyVeranstaltungen ver : veranstaltungen){
            System.out.println("--------------------------------");
            System.out.println("Name: " + ver.getName());
            System.out.println("Gesamtkapazität: " + ver.getTotalCap());
            System.out.println("VNr.: " + ver.getNummer());
            System.out.println("Schüler: " + ver.getSchuelerListe().size());
            for(DummySchueler schueler : ver.getSchuelerListe()){
                System.out.println("Klasse: " + schueler.getKlasse());
                System.out.println("Name: " + schueler.getName());
                System.out.println("Vorname: " + schueler.getVorname());
                System.out.println("---");
            }
        }
        System.out.println("Erfüllungsscore: " + calcScore(schuelerListe.size(), score));
        System.out.println("--------------------------------");
    }
}