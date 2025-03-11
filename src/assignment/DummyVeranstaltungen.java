package assignment;

import java.util.ArrayList;

public class DummyVeranstaltungen {

    private String name;
    private int capacity = 0;
    private int totalCap;
    private int nummer;
    private ArrayList<DummySchueler> schuelerListe;
    private static int number = 0;

    public DummyVeranstaltungen(String name, int capacity){
        this.name = name;
        this.schuelerListe = new ArrayList<>();
        this.totalCap = capacity;
        number++;
        this.nummer += number;
    }

    public String getName(){
        return name;
    }

    public int getKap(){
        return capacity;
    }

    public void addSchueler(DummySchueler schueler){
        schuelerListe.add(schueler);
        capacity++;
    }

    public ArrayList<DummySchueler> getSchuelerListe(){
        return schuelerListe;
    }

    public int getNummer(){
        return nummer;
    }

    public int getTotalCap(){
        return totalCap;
    }
}
