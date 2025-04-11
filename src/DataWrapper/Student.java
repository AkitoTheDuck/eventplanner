package DataWrapper;

import Assigner.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student extends DataWrapper {

    private String klasse;

    private String lastName;

    private String firstName;

    private int choice1;

    private int choice2;

    private int choice3;

    private int choice4;

    private int choice5;

    private int choice6;

    private int wishFulfilled;

    private ArrayList<Tuple> zuweisungsListe = new ArrayList<>();
    private ArrayList<Integer> notFulFilled = new ArrayList<>();
    
    private boolean assigned_A = false;

    private boolean assigned_B = false;

    private boolean assigned_C = false;

    private boolean assigned_D = false;

    private boolean assigned_E = false;

    //Wo ist der Schüler assigned schon
    private ArrayList<Boolean> assignings = new ArrayList<>();

    private ArrayList<Integer> choices = new ArrayList();

    public Student(String klasse, String lastName, String firstName, int choice1, int choice2, int choice3, int choice4, int choice5, int choice6) {
        this.klasse = klasse;
        this.lastName = lastName;
        this.firstName = firstName;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.choice5 = choice5;
        this.choice6 = choice6;
        this.choices.add(choice1);
        this.choices.add(choice2);
        this.choices.add(choice3);
        this.choices.add(choice4);
        this.choices.add(choice5);
        this.choices.add(choice6);
        this.assignings.add(false);
        this.assignings.add(false);
        this.assignings.add(false);
        this.assignings.add(false);
        this.assignings.add(false);
        this.wishFulfilled = 0;
    }

    public String getKlasse() {
        return klasse;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void iterateWishFulfilled() {
        this.wishFulfilled++;
    }

    public int getChoice1() {
        return choice1;
    }

    public int getChoice2() {
        return choice2;
    }

    public int getChoice3() {
        return choice3;
    }

    public int getChoice4() {
        return choice4;
    }

    public int getChoice5() {
        return choice5;
    }

    public int getChoice6() {
        return choice6;
    }

    public int getWishFulfilled() { return this.wishFulfilled; }

    public void setAssignedA(boolean assigned) {
        this.assigned_A = assigned;
    }

    public void setAssignedB(boolean assigned) {
        this.assigned_B = assigned;
    }

    public void setAssignedC(boolean assigned) {
        this.assigned_C = assigned;
    }

    public void setAssignedD(boolean assigned) {
        this.assigned_D = assigned;
    }

    public void setAssignedE(boolean assigned) {
        this.assigned_E = assigned;
    }

    public ArrayList<Integer> getChoices() { return this.choices; }

    public ArrayList<Boolean> getAssignings() { return this.assignings; }

    public void setAssigned(int index, int companyNr){
        assignings.set(index, true);
        //"sortiert"Assignings.put("B", companyNr);
    }

    //finale liste(?) zuweisungen, welcher slot welche company, welcher wunsch
    //sortiert nach slot a-e
    public void finalList(int slot, Company company, int index){
        zuweisungsListe.add(new Tuple(Company.getSlot(slot + 1), company, getWish(index)));
    }

    public void finalListByNumber(int j, Company company, Integer index) {
        zuweisungsListe.add(new Tuple(Company.getSlot(j + 1), company, String.valueOf(index)));
    }

    public String getWish(int index){
        switch(index){
            case 0:
                return String.valueOf(getChoice1());
            case 1:
                return String.valueOf(getChoice2());
            case 2:
                return String.valueOf(getChoice3());
            case 3:
                return String.valueOf(getChoice4());
            case 4:
                return String.valueOf(getChoice5());
            case 5:
                return String.valueOf(getChoice6());
            default:
                return "";
        }
    }

    public ArrayList<Integer> getNotFulFilled(){
        return this.notFulFilled;
    }

    public void setNotFulFilled(ArrayList<Integer> wishes) {
        this.notFulFilled = wishes;
    }

    public ArrayList<Tuple> getZuweisungsListe() {
        return this.zuweisungsListe;
    }
}
