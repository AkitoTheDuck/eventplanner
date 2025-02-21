import java.util.ArrayList;
/**
 * Test main()
 */
public class Main {

    ArrayList<DummySchueler> schueler = new ArrayList<DummySchueler>();
    ArrayList<DummyVeranstaltungen> veranstaltungen = new ArrayList<DummyVeranstaltungen>();

    public static void main(String[] args){
        Main main = new Main();
        main.doThings();
    }

    public void doThings(){
        DummySchueler mustafa = new DummySchueler("ITF221", "Ehrenfeld", "Mustafa", 3, 2, 5, 1, 6, 4);
        DummySchueler joshua = new DummySchueler("ITF223", "Mannherr", "Joshua", 1, 6, 5, 2, 3, 4);

        DummyVeranstaltungen kosmetikGmbH = new DummyVeranstaltungen("Kosmetik GmbH", 3);
        DummyVeranstaltungen modernAG = new DummyVeranstaltungen("Modern AG", 2);
        DummyVeranstaltungen exclusiveEx = new DummyVeranstaltungen("ExclusiveEx", 1);
        DummyVeranstaltungen arzneiAG = new DummyVeranstaltungen("Arznei AG", 8);
        DummyVeranstaltungen leeroysWelt = new DummyVeranstaltungen("Leeroys Welt", 7);
        DummyVeranstaltungen aktuereAG = new DummyVeranstaltungen("Aktuere AG", 7);

        veranstaltungen.add(kosmetikGmbH);
        veranstaltungen.add(modernAG);
        veranstaltungen.add(exclusiveEx);
        veranstaltungen.add(arzneiAG);
        veranstaltungen.add(leeroysWelt);
        veranstaltungen.add(aktuereAG);

        schueler.add(mustafa);
        schueler.add(joshua);

        Assigner assigner = new Assigner(schueler, veranstaltungen);
        assigner.assignPupil();
        //printInfo();

    }

    public void printInfo(){
        for(DummySchueler schueler : schueler){
            System.out.println("Klasse:" + schueler.getKlasse());
            System.out.println("Name: " + schueler.getName());
            System.out.println("Vorname: " + schueler.getVorname());
            System.out.println("Wahl1: " + schueler.getWahl1());
            System.out.println("Wahl2: " + schueler.getWahl2());
            System.out.println("Wahl3: " + schueler.getWahl3());
            System.out.println("Wahl4: " + schueler.getWahl4());
            System.out.println("Wahl5: " + schueler.getWahl5());
            System.out.println("Wahl6: " + schueler.getWahl6());
        }
        System.out.println("-------------------------------------------------");
        for(DummyVeranstaltungen veranstaltungen : veranstaltungen){
            System.out.println("Name: " + veranstaltungen.getName());
            System.out.println("kapazität: " + veranstaltungen.getKap());
            System.out.println("Nummer: " + veranstaltungen.getNummer());
        }
    }
}
