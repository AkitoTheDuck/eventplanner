package Assigner;

import java.util.ArrayList;

public class Student {
    private String klasse;
    private String name;
    private String vorname;
    private int wahl1;
    private int wahl2;
    private int wahl3;
    private int wahl4;
    private int wahl5;
    private int wahl6;
    private int fulfilled;
    private ArrayList<Integer> fulfilledWishes = new ArrayList<>();

    public Student(String klasse, String name, String vorname, int wahl1, int wahl2, int wahl3, int wahl4, int wahl5, int wahl6){
        this.klasse = klasse;
        this.name = name;
        this.vorname = vorname;
        this.wahl1 = wahl1;
        this.wahl2 = wahl2;
        this.wahl3 = wahl3;
        this.wahl4 = wahl4;
        this.wahl5 = wahl5;
        this.wahl6 = wahl6;
        this.fulfilled = 0;
    }

    public String getKlasse(){
        return klasse;
    }

    public String getName(){
        return name;
    }

    public String getVorname(){
        return vorname;
    }

    public int getFirstWish(){
        return wahl1;
    }

    public int getSecondWish(){
        return wahl2;
    }

    public int getThirdWish(){
        return wahl3;
    }

    public int getFourthWish(){
        return wahl4;
    }

    public int getFifthWish() { return wahl5; }

    public int getSixthWish(){
        return wahl6;
    }

    public int getFulfilled(){return fulfilled; }

    public void setFulfilled() {
        this.fulfilled++;
    }

    public void addFulfilledWish(int eventId) {
        if (fulfilledWishes.size() < 5) {
            fulfilledWishes.add(eventId);
        }
    }

    public ArrayList<Integer> getFulfilledWishes() {
        return fulfilledWishes;
    }
}
