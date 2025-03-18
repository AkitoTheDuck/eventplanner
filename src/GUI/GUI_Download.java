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

    public void executeAlgorithm(String selectedOption, Map<String, File> dropPanelFiles) {
        String filePath1 = String.valueOf(dropPanelFiles.get("Schülerauswahl"));
        String filePath2 = String.valueOf(dropPanelFiles.get("Veranstaltungsliste"));
        String filePath3 = String.valueOf(dropPanelFiles.get("Raumliste"));
        System.out.println(filePath1);
        System.out.println(filePath2);
        System.out.println(filePath3);

        switch (selectedOption) {
            case "Download all":
                break;
            case "Raumplan":
                String filename3 = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\2024\\Veranstaltungen.xlsx";
                CompanyReader coReader = new CompanyReader(filename3);
                ArrayList<Company> companies =  coReader.parse();
                FileWriter<Company> writer = new CompanyTimeTableWriter();
                writer.write(companies);
                break;
            case "Laufzettel":
                //TODO: Maxims code einbinden, ähnlich wie bei Raumplan (Christian)
                break;
            case"Anwesenheitsliste":
                break;
        }
    }
}
