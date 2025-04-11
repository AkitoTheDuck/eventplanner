package Assigner;

import DataWrapper.Company;
import DataWrapper.Student;

import java.util.ArrayList;

public class EventAssigner {

    public static int score = 0;

    /*
    @author AkitoTheDuck, MrsNoName
     */
    public void assignPupil(ArrayList<Company> companies){
        for(int index = 0; index < 6; index++){
            for(Company company : companies) {
                System.out.println("Company: " + company.getName());
                for (Student student : company.getSchuelerListe()) {
                    if(student.getChoices().get(index) == Integer.parseInt(company.getNr())){
                        System.out.println("Versuche Schüler " + student.getFirstName() + " in " + company.getName() + " hinzuzufügen...");
                        for(int i = 0; i < company.getRoomList().size(); i++){
                            if(i == 5){
                                System.out.println("Zuweisung war nicht möglich");
                                break;
                            }
                            if(student.getAssignings().get(i)){
                                System.out.println("Schüler schon assigned in Slot" + (i + 1) + ". Versuche nächster Slot");
                                continue;
                            }
                            if(company.getRoomList().get(i) == null){
                                System.out.println("Aktueller Raum ist null");
                                continue;
                            }
                            if(company.getStudenSlotsList().get(i).size() < company.getMaxStudents()){
                                //Hinzufügen
                                company.getStudenSlotsList().get(i).add(student);
                                student.setAssigned(i, Integer.parseInt(company.getNr()));
                                student.finalList(i, company, index);
                                System.out.println("Schüler " + student.getFirstName() + " in " + company.getName() + " hinzugefügt");
                                System.out.println("Slot: " + (i+1));
                                //Score berechnen
                                calcScore(index);
                                break;
                            }
                            else if(company.getRoomList().get(index) == null){
                                System.out.println("Nächster Slot ist null. Keine Zuweisung möglich");
                                break;
                            }
                            else{
                                System.out.println("Gehe zum nächsten Slot");
                            }
                        }
                    }
                }
                System.out.println("=========================================");
            }
        }
    }

    /**
     * @author MafffimGit
     */
    public static double gewichtung(int studentCount){
        return (double) score / (studentCount * 20);
    }

    public static void calcScore(int index){
            switch(index){
                case 0:
                    score += 6;
                    break;
                case 1:
                    score += 5;
                    break;
                case 2:
                    score += 4;
                    break;
                case 3:
                    score += 3;
                    break;
                case 4:
                    score += 2;
                    break;
                case 5:
                    score += 1;
                    break;
            }
    }

    /*
    @author AkitoTheDuck, MrsNoName
     */
    public void assignRestWishes(ArrayList<Student> students, ArrayList<Company> companies, int modus) {
        for(Company company : companies){
            for(Student student : company.getSchuelerListe()){
                for(int i = 0; i < student.getNotFulFilled().size(); i++){
                    if(modus == 0){
                        if(student.getNotFulFilled().get(i) == Integer.parseInt(company.getNr())){
                            for(int j = 0; j < student.getAssignings().size(); j++){
                                if(!student.getAssignings().get(j)){
                                    if(company.getStudenSlotsList().get(j).size() < company.getMaxStudents() && company.getRoomList().get(j) != null){
                                        company.getStudenSlotsList().get(j).add(student);
                                        student.setAssigned(j, Integer.parseInt(company.getNr()));
                                        student.finalListByNumber(j, company, student.getNotFulFilled().get(i));
                                        System.out.println("Schüler " + student.getFirstName() + " in " + company.getName() + " hinzugefügt");
                                        System.out.println("Slot: " + (j+1));
                                        //Score berechnen
                                        calcScore(j);
                                        student.getNotFulFilled().remove(i);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else{
                        for(int j = 0; j < student.getAssignings().size(); j++){
                            if(!student.getAssignings().get(j)){
                                if(company.getStudenSlotsList().get(j).size() < company.getMaxStudents()  && company.getRoomList().get(j) != null){
                                    company.getStudenSlotsList().get(j).add(student);
                                    student.setAssigned(j, Integer.parseInt(company.getNr()));
                                    student.finalListByNumber(j, company, Integer.parseInt(company.getNr()));
                                    System.out.println("Schüler " + student.getFirstName() + " in " + company.getName() + " hinzugefügt");
                                    System.out.println("Slot: " + (j+1));
                                    student.getNotFulFilled().remove(i);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void assignRest(ArrayList<Student> students, ArrayList<Company> companies){
        for(Student student : students){
            for(Company company : companies){
                for(int i = 0; i < student.getAssignings().size(); i++){
                    if(!student.getAssignings().get(i)){
                        if(company.getStudenSlotsList().get(i).size() < company.getMaxStudents()){
                                if(company.getRoomList().get(i) != null){
                                    company.getStudenSlotsList().get(i).add(student);
                                    student.setAssigned(i, Integer.parseInt(company.getNr()));
                                    student.finalListByNumber(i, company, Integer.parseInt(company.getNr()));
                                }
                            }
                            System.out.println("Schüler " + student.getFirstName() + " in " + company.getName() + " hinzugefügt");
                            System.out.println("Slot: " + (i+1));
                            break;
                        }
                    }
                }
            }
        }

    }