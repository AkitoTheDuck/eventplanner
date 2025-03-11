package RunningSheet;

import java.util.Comparator;
import java.util.ArrayList;
import assignment.*;

public class RunningSheet {
    // Schüler mit Klasse Namen etc. und den Erfülltenwünschen als Nummern im Array liegt vor (sortiert nach Klasse)
    // Die Nummern können rückläufig auf den Unternehmensnamen verweisen
    public static void laufZettel(ArrayList<DummySchueler> schuelers, ArrayList<DummyVeranstaltungen> veranstaltungens) {
        schuelers.sort(Comparator.comparingInt(DummySchueler::getKlassenZahl).thenComparing(DummySchueler::getKlassenBuchstabe));

        for (DummySchueler s : schuelers) {
            // 4 Schüler auf eine Seite, dann neue Seite
            for (int i = 0; i < 4; i++) {
                // Filewriter Stuff
            }
            // neue Seite
        }
    }
}
