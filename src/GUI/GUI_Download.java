package GUI;


import javax.swing.*;
import java.io.File;
import java.util.Map;

public class GUI_Download {

    // Lädt die gespeicherten Dateien herunter
    //auch soll hier die algothmen aufgerufen werden, bei der auwahl der jeweiligen files. Also bei der auswahl Raumplan soll zum Beispiel der algorithmus für die Überarbeitung des Raumplanes durchgeführt werden
    public static void downloadFiles(JComboBox<String> more, Map<JPanel, File> dropPanelFiles) {
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

    public static void executeAlgorithm(String selectedOption, Map<JPanel, File> dropPanelFiles) {
        switch (selectedOption) {
            case "Download all":
                break;
            case "Raumplan":
                break;
            case "Laufzettel":
                break;
            case"Anwesenheitsliste":
                break;
        }
    }
}
