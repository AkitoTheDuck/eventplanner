package GUI;


import DataWrapper.Company;
import FileReader.CompanyReader;
import FileWriter.CompanyTimeTableWriter;
import FileWriter.FileWriter;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class GUI_Download {

    // Lädt die gespeicherten Dateien herunter
    //auch soll hier die algothmen aufgerufen werden, bei der auwahl der jeweiligen files. Also bei der auswahl Raumplan soll zum Beispiel der algorithmus für die Überarbeitung des Raumplanes durchgeführt werden
    public void downloadFiles(JComboBox<String> more, Map<JPanel, File> dropPanelFiles) {
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

    public void executeAlgorithm(String selectedOption, Map<JPanel, File> dropPanelFiles) {

        switch (selectedOption) {
            case "Download all":
                break;
            case "Raumplan":
                Map.Entry<JPanel, File> secondEntry = entryList.get(1); // Index 1 für das zweite Element
                File secondFile = secondEntry.getValue(); // Dateipfad holen
                CompanyReader coReader = new CompanyReader();
                ArrayList<Company> companies =  coReader.parse();
                FileWriter<Company> writer = new CompanyTimeTableWriter();
                writer.write();
                break;
            case "Laufzettel":
                break;
            case"Anwesenheitsliste":
                break;
        }
    }
}
