import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author AkitoTheDuck
 */
public class Assigner {

    private File myObj;
    private ArrayList<DummySchueler> schuelerListe;
    private ArrayList<DummyVeranstaltungen> veranstaltungen;

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

    public boolean isVoll() {
        return false;
    }

    public int getKap() {
        return 0;
    }

    public void assignPupil(){
        //anstatt getWahl wiederholt zu nutzen, später mit den richtigen indezes arbeiten ?
        for(DummySchueler schueler : schuelerListe) {
            int tempNum = schueler.getWahl1();
            if(veranstaltungen.get(schueler.getWahl1() - 1).getKap() < veranstaltungen.get(schueler.getWahl1() - 1).getGesamtKapazität()){
                veranstaltungen.get(schueler.getWahl1() - 1).addSchueler(schueler);
            }
            if(veranstaltungen.get(schueler.getWahl2() - 1).getKap() < veranstaltungen.get(schueler.getWahl2() - 1).getGesamtKapazität()){
                veranstaltungen.get(schueler.getWahl2() - 1).addSchueler(schueler);
            }
            if(veranstaltungen.get(schueler.getWahl3() - 1).getKap() < veranstaltungen.get(schueler.getWahl3() - 1).getGesamtKapazität()){
                veranstaltungen.get(schueler.getWahl3() - 1).addSchueler(schueler);
            }
            if(veranstaltungen.get(schueler.getWahl4() - 1).getKap() < veranstaltungen.get(schueler.getWahl4() - 1).getGesamtKapazität()){
                veranstaltungen.get(schueler.getWahl4() - 1).addSchueler(schueler);
            }
            if(veranstaltungen.get(schueler.getWahl5() - 1).getKap() < veranstaltungen.get(schueler.getWahl5() - 1).getGesamtKapazität()){
                veranstaltungen.get(schueler.getWahl5() - 1).addSchueler(schueler);
            }
            if(veranstaltungen.get(schueler.getWahl6() - 1).getKap() < veranstaltungen.get(schueler.getWahl6() - 1).getGesamtKapazität()){
                veranstaltungen.get(schueler.getWahl6() - 1).addSchueler(schueler);
            }
        }



        //Test Output
        for(DummyVeranstaltungen ver : veranstaltungen){
            System.out.println("Name: " + ver.getName());
            System.out.println("Gesamtkapazität: " + ver.getGesamtKapazität());
            System.out.println("Nummer: " + ver.getNummer());
            System.out.println("Schüler: ");
            for(DummySchueler schueler : ver.getSchuelerListe()){
                System.out.println("Klasse: " + schueler.getKlasse());
                System.out.println("Name: " + schueler.getName());
                System.out.println("Vorname: " + schueler.getVorname());
            }
        }
    }
}