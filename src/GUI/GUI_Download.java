package GUI;


import DataWrapper.Company;
import FileReader.ClassRoomReader;
import FileReader.CompanyReader;
import FileReader.StudentReader;
import FileWriter.CompanyTimeTableWriter;
import FileWriter.FileWriter;
import RunningSheet.RunningSheetMain;

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

    //TODO: wir brauchen den Algorithmus von Kilian,welcher zu erst alle datein einlist, dannach soll erst die switch case funktion ausgeführt werden

    public void executeAlgorithm(String selectedOption, Map<String, File> dropPanelFiles) {
        String filePath1 = String.valueOf(dropPanelFiles.get("Schülerauswahl"));
        String filePath2 = String.valueOf(dropPanelFiles.get("Veranstaltungsliste"));
        String filePath3 = String.valueOf(dropPanelFiles.get("Raumliste"));

        CompanyReader coReader = new CompanyReader(filePath2);
        StudentReader stReader = new StudentReader(filePath1);
        ClassRoomReader clReader = new ClassRoomReader(filePath3);

        switch (selectedOption) {
            case "Download all":
                break;
            case "Raumplan":
                ArrayList<Company> companies =  coReader.parse();
                FileWriter<Company> writer = new CompanyTimeTableWriter();
                writer.write(companies);
                break;
            case "Laufzettel":
                //TODO: Maxims code einbinden, ähnlich wie bei Raumplan (Christian)
                RunningSheetMain.main(new String[] {});
                break;
            case"Anwesenheitsliste":
                break;
        }
    }
}
