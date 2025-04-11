package DataWrapper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Christian
 */
public class Company extends DataWrapper {

    //alle wünsche auf einmal nicht 1. wunsch und dann die anderen
    //classRooms und slotXClass müssen getauscht werden
    //in finale liste muss hinzugefügt werden beim hinzufügen der students
    //wenn slot X im raum y schon voll ist was ist mit dem nächsten Slot?
    private String nr;

    private String name;

    private String fieldOfStudy;

    private int maxStudents;

    private int maxEvents;

    private int earliestStart;

    private ArrayList<ArrayList<Student>> studentSlotList = new ArrayList<>();

    private ClassRoom slotAClass;
    private ClassRoom slotBClass;
    private ClassRoom slotCClass;
    private ClassRoom slotDClass;
    private ClassRoom slotEClass;
    private ArrayList<ClassRoom> roomList = new ArrayList<>();



    private ArrayList<Student> schuelerListe = new ArrayList<>();

    public Company(String nr, String name, String fieldOfStudy, int maxStudents, int maxEvents, int earliestStart) {
        setNr(nr);
        setName(name);
        setFieldOfStudy(fieldOfStudy);
        setMaxStudents(maxStudents);
        setmaxEvents(maxEvents);
        setEarliestStart(earliestStart);
        this.roomList.add(null);
        this.roomList.add(null);
        this.roomList.add(null);
        this.roomList.add(null);
        this.roomList.add(null);
        studentSlotList.add(new ArrayList<>());
        studentSlotList.add(new ArrayList<>());
        studentSlotList.add(new ArrayList<>());
        studentSlotList.add(new ArrayList<>());
        studentSlotList.add(new ArrayList<>());


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

    public ArrayList<ArrayList<Student>> getStudenSlotsList() {return this.studentSlotList; }

    public ArrayList<ClassRoom> getRoomList() { return this.roomList; }

    public ArrayList<Student> getSchuelerListe() {
        return schuelerListe;
    }

    public void setSchuelerListe(ArrayList<Student> schuelerListe) {
        this.schuelerListe = schuelerListe;
    }

    public static String getSlot(int slotNumber){
        switch(slotNumber){
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            default:
                return "";
        }
    }
}
