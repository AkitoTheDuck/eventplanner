package assignment;

import java.util.ArrayList;
/**
 * Test main()
 */
public class Main {

    ArrayList<DummySchueler> schueler = new ArrayList<DummySchueler>();
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
        DummySchueler mustafa = new DummySchueler("ITF221", "Ehrenfeld", "Mustafa", 3, 2, 5, 1, 6, 4);
        DummySchueler joshua = new DummySchueler("ITF223", "Mannherr", "Joshua", 1, 5, 6, 2, 4, 3);
        DummySchueler manni = new DummySchueler("ITF223", "Loiser", "Manni", 1, 6, 5, 2, 3, 4);
        DummySchueler max = new DummySchueler("ITF223", "Steffens", "Max", 1, 6, 5, 2, 3, 4);
        DummySchueler stefan = new DummySchueler("ITF223", "Rein", "Stefan", 2, 5, 6, 1, 4, 3);
        DummySchueler jasmin = new DummySchueler("ITF221", "Jade", "Jasmin", 1, 2, 3, 4, 5, 6);
        DummySchueler rene = new DummySchueler("ITF221", "Adler", "Rene", 6, 5, 4, 3, 2, 1);
        DummySchueler lara = new DummySchueler("ITF222", "Laus", "Lara", 1, 2, 3, 6, 5, 4);
        DummySchueler max2 = new DummySchueler("ITF223", "Muskel", "Max", 3, 2, 1, 4, 5, 6);

        DummyVeranstaltungen kosmetikGmbH = new DummyVeranstaltungen("Kosmetik GmbH", 10); // 1
        DummyVeranstaltungen modernAG = new DummyVeranstaltungen("Modern AG", 10); // 2
        DummyVeranstaltungen exclusiveEx = new DummyVeranstaltungen("ExclusiveEx", 10); // 3
        DummyVeranstaltungen arzneiAG = new DummyVeranstaltungen("Arznei AG", 10); // 4
        DummyVeranstaltungen leeroysWelt = new DummyVeranstaltungen("Leeroys Welt", 10); // 5
        DummyVeranstaltungen aktuereAG = new DummyVeranstaltungen("Aktuere AG", 10); // 6

        veranstaltungen.add(kosmetikGmbH);
        veranstaltungen.add(modernAG);
        veranstaltungen.add(exclusiveEx);
        veranstaltungen.add(arzneiAG);
        veranstaltungen.add(leeroysWelt);
        veranstaltungen.add(aktuereAG);

        schueler.add(mustafa);
        schueler.add(joshua);
        schueler.add(manni);
        schueler.add(max);
        schueler.add(stefan);
        schueler.add(jasmin);
        schueler.add(rene);
        schueler.add(lara);
        schueler.add(max2);

        Assigner assigner = new Assigner(schueler, veranstaltungen);
        assigner.testAssignement();
        assigner.printInfo();
    }
}
