package DataWrapper;

import java.util.ArrayList;
import java.util.Map;

public class Company extends DataWrapper {

    private String nr;

    private String name;

    private String fieldOfStudy;

    private int maxStudents;

    private int maxEvents;

    private int earliestStart;

    private ArrayList<Student> slotA = new ArrayList<>();
    private ArrayList<Student> slotB = new ArrayList<>();
    private ArrayList<Student> slotC = new ArrayList<>();
    private ArrayList<Student> slotD = new ArrayList<>();
    private ArrayList<Student> slotE = new ArrayList<>();

    private ClassRoom slotAClass;
    private ClassRoom slotBClass;
    private ClassRoom slotCClass;
    private ClassRoom slotDClass;
    private ClassRoom slotEClass;

    private ArrayList<Student> schuelerListe = new ArrayList<>();

    public Company(String nr, String name, String fieldOfStudy, int maxStudents, int maxEvents, int earliestStart) {
        setNr(nr);
        setName(name);
        setFieldOfStudy(fieldOfStudy);
        setMaxStudents(maxStudents);
        setmaxEvents(maxEvents);
        setEarliestStart(earliestStart);
    }

    public int calcAmountEvents() {

        int count = 1;

        int sd = schuelerListe.size() / maxStudents;

        if(sd > count) {
            count = sd;
        }

        if (count >= maxEvents) {
            return maxEvents;
        } else {
            return count;
        }
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getMaxEvents() {
        return maxEvents;
    }

    public void setmaxEvents(int maxEvents) {
        this.maxEvents = maxEvents;
    }

    public void setMaxEvents(int maxEvents) {
        this.maxEvents = maxEvents;
    }

    public ClassRoom getSlotAClass() {
        return slotAClass;
    }

    public void setSlotAClass(ClassRoom slotAClass) {
        this.slotAClass = slotAClass;
    }

    public ClassRoom getSlotBClass() {
        return slotBClass;
    }

    public void setSlotBClass(ClassRoom slotBClass) {
        this.slotBClass = slotBClass;
    }

    public ClassRoom getSlotCClass() {
        return slotCClass;
    }

    public void setSlotCClass(ClassRoom slotCClass) {
        this.slotCClass = slotCClass;
    }

    public ClassRoom getSlotDClass() {
        return slotDClass;
    }

    public void setSlotDClass(ClassRoom slotDClass) {
        this.slotDClass = slotDClass;
    }

    public ClassRoom getSlotEClass() {
        return slotEClass;
    }

    public void setSlotEClass(ClassRoom slotEClass) {
        this.slotEClass = slotEClass;
    }

    public int getEarliestStart() {
        return this.earliestStart;
    }

    public void setEarliestStart(int earliestStart) {
        this.earliestStart = earliestStart;
    }

    public void addSchueler(Student schueler){
        schuelerListe.add(schueler);
    }

    public ArrayList<Student> getSlotA() {
        return slotA;
    }

    public void setSlotA(ArrayList<Student> slotA) {
        this.slotA = slotA;
    }

    public ArrayList<Student> getSlotB() {
        return slotB;
    }

    public void setSlotB(ArrayList<Student> slotB) {
        this.slotB = slotB;
    }

    public ArrayList<Student> getSlotC() {
        return slotC;
    }

    public void setSlotC(ArrayList<Student> slotC) {
        this.slotC = slotC;
    }

    public ArrayList<Student> getSlotD() {
        return slotD;
    }

    public void setSlotD(ArrayList<Student> slotD) {
        this.slotD = slotD;
    }

    public ArrayList<Student> getSlotE() {
        return slotE;
    }

    public void setSlotE(ArrayList<Student> slotE) {
        this.slotE = slotE;
    }

    public ArrayList<Student> getSchuelerListe() {
        return schuelerListe;
    }

    public void setSchuelerListe(ArrayList<Student> schuelerListe) {
        this.schuelerListe = schuelerListe;
    }
}
