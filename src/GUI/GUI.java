package GUI; // Autoren: Lisa & Jacqueline

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.*;

public class GUI {
    private JFrame frame = new JFrame("Eventplaner"); // Hauptfenster
    private JLabel select = new JLabel("Schülerwahl"); // Label für Schülerwahl
    private JLabel vlist = new JLabel("Veranstaltungsliste"); // Label für Veranstaltungen
    private JLabel rlist = new JLabel("Raumliste"); // Label für Räume
    private JButton download = new JButton("Download"); // Download-Button
    private String[] endfiles = {"Download all", "Raumplan", "Laufzettel", "Anwesenheitsliste"};
    private JComboBox<String> more = new JComboBox<>(endfiles); // Auswahlbox für Downloads
    private Map<String, File> dropPanelFiles = new HashMap<>(); // Speicherung der Dateien
    private JButton infoButton = new JButton("i");
    private JTextArea textArea = new JTextArea();

    public GUI() {
        frame.setSize(1100, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel titelLabel = new JLabel("Eventplaner", SwingConstants.CENTER);
        titelLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titelLabel);
        frame.add(mainPanel);

        infoButton.setPreferredSize(new Dimension(40, 30));
        infoButton.setToolTipText("Mehr Infos anzeigen");
        infoButton.setFocusPainted(false); // Kein Fokus-Effekt
        infoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        infoButton.addActionListener(e -> openInfoWindow());


        // Panel für die obere rechte Ecke
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        infoPanel.add(infoButton);
        frame.add(infoPanel, BorderLayout.NORTH);

        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
        mainPanel.add(upperPanel);

        // Upload-Bereich mit drei Panels
        JPanel titleDropPanel1 = createDropPanel(select, "Schülerauswahl");
        JPanel titleDropPanel2 = createDropPanel(vlist, "Veranstaltungsliste");
        JPanel titleDropPanel3 = createDropPanel(rlist, "Raumliste");

        upperPanel.add(titleDropPanel1);
        upperPanel.add(titleDropPanel2);
        upperPanel.add(titleDropPanel3);

        JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lowerPanel.add(more);
        more.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        download.setEnabled(false);
        lowerPanel.add(download);
        mainPanel.add(lowerPanel);

        frame.setVisible(true);
    }

    // Erstellt ein Drop-Panel mit Drag-and-Drop-Funktionalität
    private JPanel createDropPanel(JLabel label, String name) {
        JPanel titleDropPanel = new JPanel();
        titleDropPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        titleDropPanel.setLayout(new BoxLayout(titleDropPanel, BoxLayout.Y_AXIS));

        JPanel dropPanel = new JPanel();
        dropPanel.setBackground(Color.LIGHT_GRAY);
        dropPanel.setPreferredSize(new Dimension(300, 250));
        dropPanel.setLayout(new FlowLayout());

        JLabel fileLabel = new JLabel("Datei hier ablegen"); // Anzeige für abgelegte Datei
        dropPanel.add(fileLabel);

        new DropTarget(dropPanel, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent event) {
                event.acceptDrop(DnDConstants.ACTION_COPY);
                Transferable transferable = event.getTransferable();
                try {
                    if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        java.util.List<File> files = (java.util.List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        if (!files.isEmpty()) {
                            File droppedFile = files.get(0);
                            handleFileDrop(name, fileLabel, droppedFile);
                        }
                    }
                } catch (UnsupportedFlavorException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        titleDropPanel.add(label);
        titleDropPanel.add(dropPanel);
        return titleDropPanel;
    }

    // Verarbeitet die abgelegte Datei und zeigt deren Namen an
    private void handleFileDrop(String name, JLabel fileLabel, File droppedFile) {
        String fileName = droppedFile.getName();

        // Wenn die Datei eine Excel-Datei (.xls oder .xlsx) ist, wird sie verarbeitet
        if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
            dropPanelFiles.put(name, droppedFile);
            fileLabel.setText(droppedFile.getName()); // Setzt den Dateinamen als Text
            System.out.println("Excel-Datei für Panel " + name.hashCode() + " gespeichert: " + droppedFile.getAbsolutePath());
            updateDownloadButton();
        } else {
            JOptionPane.showMessageDialog(null, "Nur Excel-Dateien (.xls, .xlsx) sind erlaubt.");
        }
    }

    private void updateDownloadButton() {
        // Alte Listener entfernen, um Dopplungen zu vermeiden
        for (ActionListener al : download.getActionListeners()) {
            download.removeActionListener(al);
        }

        // Neuen Listener setzen basierend auf der Anzahl der Dateien
        if (dropPanelFiles.size() == 3) {
            download.addActionListener(e -> {
                        GUI_Download gui_download = new GUI_Download();
                        gui_download.downloadFiles(more, dropPanelFiles);
                    }
            );

            download.setEnabled(true); // Falls Button disabled war, aktivieren
            download.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            download.addActionListener(i -> JOptionPane.showMessageDialog(null, "Bitte lege in jedes der 3 Felder die dazugehörige Datei ab!"));
            download.setEnabled(false); // Optional: Button deaktivieren, bis 3 Dateien da sind
        }
    }

    private void openInfoWindow() {
        JFrame infoFrame = new JFrame("Info");
        infoFrame.setSize(800, 600);
        infoFrame.setLocationRelativeTo(frame);

        // Layout: Aufteilen in zwei Spalten: Inhaltsverzeichnis und Text
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // Linke Seite: Inhaltsverzeichnis
        JPanel tocPanel = createTOCPanel(textArea);

        // Rechte Seite: Textbereich mit ScrollPane
        textArea.setEditable(false);  // Textbereich bleibt nur lesbar

        // Setze den ersten Abschnitt ("StepbyStep"), wenn das Fenster geöffnet wird
        jumpToSection("StepbyStep", textArea);  // "StepbyStep" als Standardabschnitt

        JScrollPane scrollPane = new JScrollPane(textArea);

        // Setze das ScrollPane als rechten Teil des SplitPanes
        splitPane.setLeftComponent(tocPanel);
        splitPane.setRightComponent(scrollPane);

        // Füge das SplitPane zum JFrame hinzu
        infoFrame.add(splitPane);

        // Stelle sicher, dass das Fenster sichtbar ist
        infoFrame.setVisible(true);
    }

    //TODO: das problem liegt daran das oben wo die funktion aufgerufen wird die datein nicht richtig geladen werden also muss das irgendwie abgeändert werden

    // Lädt den Inhalt der Markdown-Datei
    private String loadMarkdownFile(String fileName) {
        StringBuilder content = new StringBuilder();
        String path = "src/Doku/userInfo/" + fileName;
        System.out.println("Versuche zu laden: " + path);
        File mdFile = new File(path);
        if (!mdFile.exists()) {
            JOptionPane.showMessageDialog(null, "Datei " + fileName + " nicht gefunden.");
            return "";  // Gibt einen leeren String zurück, wenn die Datei nicht existiert
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(mdFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Datei.");
        }

        System.out.println(content.toString());

        return content.toString();
    }


    private JPanel createTOCPanel(JTextArea textArea) {
        JPanel tocPanel = new JPanel();
        tocPanel.setLayout(new BoxLayout(tocPanel, BoxLayout.Y_AXIS));

        // Inhaltsverzeichnis mit Links
        String[] sections = {"StepbyStep", "DragnDrop", "Download" , "Erfüllungsscore"};
        for (String section : sections) {
            JLabel sectionLabel = new JLabel("<html><u>" + section + "</u></html>"); // HTML für unterstrichenen Text
            sectionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cursor als Link-Style
            sectionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jumpToSection(section,textArea);
                }
            });
            tocPanel.add(sectionLabel);
        }

        return tocPanel;
    }
    private void jumpToSection(String section, JTextArea textArea) {
        String fileName = "";
        String fileContent = "";  // Variable zum Speichern des Inhalts der geladenen Datei

        // Bestimme die zu ladende Datei anhand des Abschnitts
        switch (section) {
            case "StepbyStep":
                fileName = "StepbyStep.md";
                fileContent = loadMarkdownFile(fileName);
                break;
            case "DragnDrop":
                fileName = "DragnDrop.md";
                fileContent = loadMarkdownFile(fileName);
                break;
            case "Download":
                fileName = "Download.md";
                fileContent = loadMarkdownFile(fileName);
                break;
            case "Erfüllungsscore":
                fileName = "Erfüllungsscore.md";
                fileContent = loadMarkdownFile(fileName);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Abschnitt nicht gefunden: " + section);
                return;  // Wenn der Abschnitt nicht gefunden wurde, abbrechen
        }

        // Setze den geladenen Inhalt in das JTextArea
        textArea.setText(fileContent);  // Hier wird das JTextArea mit dem Inhalt gefüllt
        textArea.setCaretPosition(0);   // Cursor an den Anfang setzen
    }

}
