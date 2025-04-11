package GUI;


import Algorithm.TimeTable;
import Assigner.Assigner;
import Assigner.EventAssigner;
import Assigner.Tuple;
import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;
import FileReader.*;
import FileWriter.*;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class GUI_Download {

    // Lädt die gespeicherten Dateien herunter
    //auch soll hier die algothmen aufgerufen werden, bei der auwahl der jeweiligen files. Also bei der auswahl Raumplan soll zum Beispiel der algorithmus für die Überarbeitung des Raumplanes durchgeführt werden
    public void downloadFiles(JComboBox<String> more, Map<String, File> dropPanelFiles) {
        String selectedOption = (String)more.getSelectedItem();
        if (selectedOption == null) {
            return;
        } else {
            executeAlgorithm(selectedOption, dropPanelFiles);
        }
        String downloadPath = System.getProperty("user.home") + "/Downloads/" + selectedOption + ".slsx";
        File processedFile = new File(downloadPath);

        if ("Download all".equals(selectedOption)){
            System.out.println("Alle Datein werden heruntergeladen");
            JOptionPane.showMessageDialog(null,"Alle verarbeitete Datein wurden heruntergeladen");
        } else {
            System.out.println("Herunterladen: " + processedFile.getAbsolutePath());
            JOptionPane.showMessageDialog(null,selectedOption + " wurde heruntergeladen");
        }
    }

    //durchläuft die Zuweisung und schreibt dann die ausgewählte Datei
    public void executeAlgorithm(String selectedOption, Map<String, File> dropPanelFiles) {
        String filePath1 = String.valueOf(dropPanelFiles.get("Schülerauswahl"));
        String filePath2 = String.valueOf(dropPanelFiles.get("Veranstaltungsliste"));
        String filePath3 = String.valueOf(dropPanelFiles.get("Raumliste"));

        CompanyReader coReader = new CompanyReader(filePath2);
        StudentReader stReader = new StudentReader(filePath1);
        ClassRoomReader clReader = new ClassRoomReader(filePath3);

        FileWriter<Company> raumplan = new CompanyTimeTableWriter();
        FileWriter<Student> laufzettel = new StudentScheduleWriter();
        FileWriter<Company> anwesendheit = new AttendanceListWriter();

        ArrayList<Student> students =  stReader.parse();
        ArrayList<ClassRoom> rooms =  clReader.parse();
        ArrayList<Company> companies =  coReader.parse();

        Assigner assigner = new Assigner(students, companies, rooms);
        assigner.assign();

        TimeTable algo = new TimeTable(companies, rooms);
        algo.assign();

        EventAssigner eventAssigner = new EventAssigner();
        eventAssigner.assignPupil(companies);

        for(Student student : students){
            ArrayList<Integer> wishes = new ArrayList<>(student.getChoices());
            for(Tuple tuple : student.getZuweisungsListe()){
                for(int i = 0; i < student.getChoices().size(); i++){
                    if(Integer.parseInt(tuple.getWunsch()) == student.getChoices().get(i)){
                        wishes.remove(student.getChoices().get(i));
                    }
                }
            }
            student.setNotFulFilled(wishes);
        }
        eventAssigner.assignRestWishes(students, companies, 0);
        eventAssigner.assignRestWishes(students, companies, 1);
        int iter = 0;
        for(Student student : students){
            if(student.getNotFulFilled().size() > 1){
                System.out.println("Schüler " + student.getFirstName() + " hat nicht alle Zuweisungen");
                System.out.println("Zahl: " + iter);
            }
            iter++;
        }

        switch (selectedOption) {
            case "Download all":
                raumplan.write(companies);
                laufzettel.write(students);
                anwesendheit.write(companies);
                break;
            case "Raumplan":
                raumplan.write(companies);
                break;
            case "Laufzettel":
                laufzettel.write(students);
                break;
            case"Anwesenheitsliste":
                anwesendheit.write(companies);
                break;
        }

        JOptionPane.showMessageDialog(null,"Erfüllungsscore: " + EventAssigner.gewichtung(students.size()));
        EventAssigner.score = 0;
    }
}
