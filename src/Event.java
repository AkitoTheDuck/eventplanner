import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;

import java.util.ArrayList;

public class Event{

    private int companyNumber;
    private final String roomNumber;
    private final String companyName;
    private String timing;
    private int capacity = 0;
    private final int maxEvents;
    private int eventCount = 0;
    private final int totalCapacity;
    private ArrayList<Student> schuelerListe;

    enum eventTime{
        A, B, C, D, E, NA
    }

    public Event(ClassRoom room, Company company){
        this.companyNumber = Integer.parseInt(company.getNr());
        this.roomNumber = room.getRoomNumber();
        this.companyName = company.getName();
        this.timing = company.getEarliestStart();
        this.maxEvents = company.getMaxEvents();
        this.totalCapacity = company.getMaxEvents()*company.getMaxStudents();
    }

    public int getCompanyNumber() { return this.companyNumber; }
    public String getCompanyName() { return this.companyName; }
    public String getTiming(){ return this.timing; }
    public int getCap() { return this.capacity; }
    public int getMaxEvents() { return this.maxEvents; }
    public int getTotalCapacity() { return this.totalCapacity; }

    public void nextTiming(){
        if(eventCount < maxEvents){
            switch (timing) {
                case "A" -> timing = "B";
                case "B" -> timing = "C";
                case "C" -> timing = "D";
                case "D" -> timing = "E";
                case "E" -> timing = "";
                default -> timing = "NA";
            };
        }

    }

    public void addSchueler(Student schueler){
        schuelerListe.add(schueler);
        capacity++;
    }

    public String printInfo(){
        return "RNr.: " + roomNumber + "\n" +
                "CompanyName.: " + companyName + "\n" +
                "timing.: " + timing + "\n" +
                "total cap.: " + totalCapacity + "\n" +
                "nummer company.: " + companyNumber + "\n";
    }
}
