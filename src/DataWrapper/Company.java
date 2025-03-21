package DataWrapper;

import java.util.ArrayList;

public class Company extends DataWrapper {

    private String nr;

    private String name;

    private String fieldOfStudy;

    private int maxStudents;

    private int maxEvents;

    private String earliestStart;

    private ArrayList<Student> schuelerListe = new ArrayList<>();

    public Company(String nr, String name, String fieldOfStudy, int maxStudents, int maxEvents, String earliestStart) {
        setNr(nr);
        setName(name);
        setFieldOfStudy(fieldOfStudy);
        setMaxStudents(maxStudents);
        setmaxEvents(maxEvents);
        setEarliestStart(earliestStart);
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

    public String getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(String earliestStart) {
        this.earliestStart = earliestStart;
    }

    public void addSchueler(Student schueler){
        schuelerListe.add(schueler);
    }
}
