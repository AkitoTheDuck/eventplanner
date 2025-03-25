package RunningSheet;

import Assigner.Student;
import Assigner.DummyVeranstaltungen;
import java.util.ArrayList;

public class RunningSheetMain {
    public static void main(String[] args) {
        // 🔹 Dummy-Liste für Schüler
        ArrayList<Student> schuelerListe = new ArrayList<>();
        schuelerListe.add(new Student("10A", "Müller", "Lena", 1, 2, 3, 4, 5, 6));
        schuelerListe.add(new Student("10A", "Schmidt", "Jonas", 2, 3, 1, 5, 6, 4));
        schuelerListe.add(new Student("10B", "Becker", "Emma", 3, 1, 2, 6, 5, 4));
        schuelerListe.add(new Student("10B", "Klein", "Felix", 4, 3, 2, 1, 6, 5));
        schuelerListe.add(new Student("10C", "Wagner", "Lukas", 1, 2, 3, 4, 5, 6));

        // 🔹 Dummy-Liste für Veranstaltungen
        ArrayList<DummyVeranstaltungen> veranstaltungsListe = new ArrayList<>();
        veranstaltungsListe.add(new DummyVeranstaltungen("Firma A - IT Workshop", 10));
        veranstaltungsListe.add(new DummyVeranstaltungen("Firma B - Ingenieurwesen", 8));
        veranstaltungsListe.add(new DummyVeranstaltungen("Firma C - Wirtschaft", 12));
        veranstaltungsListe.add(new DummyVeranstaltungen("Firma D - Design", 6));
        veranstaltungsListe.add(new DummyVeranstaltungen("Firma E - Biotechnologie", 7));
        veranstaltungsListe.add(new DummyVeranstaltungen("Firma F - Elektrotechnik", 9));

        // 🔹 Erfüllte Wünsche setzen (Hier: Manuell verteilt für Test)
        schuelerListe.get(0).addFulfilledWish(1); // Lena bekommt Firma A
        schuelerListe.get(0).addFulfilledWish(2); // Lena bekommt Firma B
        schuelerListe.get(0).addFulfilledWish(3); // Lena bekommt Firma C
        schuelerListe.get(0).addFulfilledWish(4); // Lena bekommt Firma D
        schuelerListe.get(0).addFulfilledWish(5); // Lena bekommt Firma E

        schuelerListe.get(1).addFulfilledWish(2); // Jonas bekommt Firma B
        schuelerListe.get(1).addFulfilledWish(3);
        schuelerListe.get(1).addFulfilledWish(4);
        schuelerListe.get(1).addFulfilledWish(5);
        schuelerListe.get(1).addFulfilledWish(6);

        schuelerListe.get(2).addFulfilledWish(3);
        schuelerListe.get(2).addFulfilledWish(1);
        schuelerListe.get(2).addFulfilledWish(2);
        schuelerListe.get(2).addFulfilledWish(6);
        schuelerListe.get(2).addFulfilledWish(5);

        // 🔹 Laufzettel als Excel erstellen
        try {
            RunningSheet.createExcel(schuelerListe, veranstaltungsListe);
            System.out.println("Laufzettel wurde erfolgreich als Excel-Datei erstellt!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fehler beim Erstellen des Laufzettels: " + e.getMessage());
        }
    }
}
