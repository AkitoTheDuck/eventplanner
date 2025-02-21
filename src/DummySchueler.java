import java.util.ArrayList;

public class DummySchueler {
    private String klasse;
    private String name;
    private String vorname;
    private int wahl1;
    private int wahl2;
    private int wahl3;
    private int wahl4;
    private int wahl5;
    private int wahl6;

    public DummySchueler(String klasse, String name, String vorname, int wahl1, int wahl2, int wahl3, int wahl4, int wahl5, int wahl6){
        this.klasse = klasse;
        this.name = name;
        this.vorname = vorname;
        this.wahl1 = wahl1;
        this.wahl2 = wahl2;
        this.wahl3 = wahl3;
        this.wahl4 = wahl4;
        this.wahl5 = wahl5;
        this.wahl6 = wahl6;
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

    public int getWahl1(){
        return wahl1;
    }

    public int getWahl2(){
        return wahl2;
    }

    public int getWahl3(){
        return wahl3;
    }

    public int getWahl4(){
        return wahl4;
    }

    public int getWahl5() { return wahl5; }

    public int getWahl6(){
        return wahl6;
    }
}
