package Assigner;

import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;

import java.util.ArrayList;

public class Event{

    private String timing = "";
    private ArrayList<Student> studentList = new ArrayList<>();

    private Company company;
    private ClassRoom room;

    enum eventTime{
        A, B, C, D, E
    }

    public Event(ClassRoom room, Company company){
        this.company = company;
        this.room = room;
        machMalPlan(); //ein einziges Event, ein Zeitslot, Eine Präsi mit <= maxStudents

        this.timing = company.getEarliestStart();
    }

    private void machMalPlan() {
        ArrayList<Student> remainingStudents = new ArrayList<>(company.getStudentList());
        for(Student student : company.getStudentList()){
            if(studentList.size() < Integer.parseInt(room.getCapacity())){
                studentList.add(student);
                remainingStudents.remove(student);
                System.out.println("Schueler zum neuen Event hinzugefügt!");
            }
            else{
                System.out.println("Ein Event hat die Maximalkapazität erreicht!");
                break;
            }

        }
        company.setStudentList(remainingStudents);
    }

    //copy constructor with the next time slot
    public Event(Event event){
        this.timing = event.timing;
        this.company = event.company;
        this.room = event.room;
        event.nextTimeSlot();
    }

    public boolean lastSlot(){
        return this.timing.equals("E");
    }
    public void nextTimeSlot(){
        if(!timing.equals("E")){
            switch(timing){
                case "A":
                    timing = "B";
                    break;
                case "B":
                    timing = "C";
                    break;
                case "C":
                    timing = "D";
                    break;
                case "D":
                    timing = "E";
                    break;
                default:
                    break;
            }
        }
    }

    public String getTiming(){
        return timing;
    }

    /*


    public int getCapacity(){
        return capacity;
    }

    public int getTotalCapacity(){
        return totalCapacity;
    }

    public int getNumber(){
        return number;
    }

    public String printInfo(){
        return "timing.: " + timing + "\n" +
                "total cap.: " + totalCapacity + "\n" +
                "nummer company.: " + number + "\n";
    }
     */
}
