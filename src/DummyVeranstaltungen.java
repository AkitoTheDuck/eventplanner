import java.util.ArrayList;

public class DummyVeranstaltungen {

    private String name;
    private int kapazitaet = 0;
    private int gesamtKapazität;
    private int nummer;
    private ArrayList<DummySchueler> schuelerListe;
    private static int number = 0;

    public DummyVeranstaltungen(String name, int kapazitaet){
        this.name = name;
        this.schuelerListe = new ArrayList<>();
        this.gesamtKapazität = kapazitaet;
        number++;
        this.nummer += number;
    }

    public String getName(){
        return name;
    }

    public int getKap(){
        return kapazitaet;
    }

    public void addSchueler(DummySchueler schueler){
        schuelerListe.add(schueler);
    }

    public ArrayList<DummySchueler> getSchuelerListe(){
        return schuelerListe;
    }

    public int getNummer(){
        return nummer;
    }

    public int getGesamtKapazität(){
        return gesamtKapazität;
    }
}
