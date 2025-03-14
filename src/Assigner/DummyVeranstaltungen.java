package Assigner;

import java.util.ArrayList;

public class DummyVeranstaltungen {

    private static int number = 0;
    private String name;
    private int capacity = 0;
    private int totalCap;
    private int nummer;
    private ArrayList<Student> schuelerListe;


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

    public void addSchueler(Student schueler){
        schuelerListe.add(schueler);
        capacity++;
    }

    public ArrayList<Student> getSchuelerListe(){
        return schuelerListe;
    }

    public int getNummer(){
        return nummer;
    }

    public int getTotalCap(){
        return totalCap;
    }
}
