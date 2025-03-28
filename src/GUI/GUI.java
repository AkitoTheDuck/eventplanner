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

        // Wenn die Datei eine .md ist, sie als Text anzeigen
        if (fileName.endsWith(".md")) {
            dropPanelFiles.put(name, droppedFile);
            fileLabel.setText(droppedFile.getName()); // Setzt den Dateinamen als Text
            System.out.println("Markdown-Datei für Panel " + name.hashCode() + " gespeichert: " + droppedFile.getAbsolutePath());
            updateDownloadButton();
        } else {
            JOptionPane.showMessageDialog(null, "Nur Markdown-Dateien (.md) sind erlaubt.");
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
        JPanel tocPanel = createTOCPanel();

        // Rechte Seite: Textbereich mit ScrollPane
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(""); // Initial leer
        JScrollPane scrollPane = new JScrollPane(textArea);

        splitPane.setLeftComponent(tocPanel);
        splitPane.setRightComponent(scrollPane);

        infoFrame.add(splitPane);
        infoFrame.setVisible(true);
    }

    // Lädt den Inhalt der Markdown-Datei
    private String loadMarkdownFile(String fileName) {
        StringBuilder content = new StringBuilder();
        String path = "Doku/userInfo/" + fileName;
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
        return content.toString();
    }


    private JPanel createTOCPanel() {
        JPanel tocPanel = new JPanel();
        tocPanel.setLayout(new BoxLayout(tocPanel, BoxLayout.Y_AXIS));

        // Inhaltsverzeichnis mit Links
        String[] sections = {"Einführung", "Funktionalität", "Fehlerbehebung"};
        for (String section : sections) {
            JLabel sectionLabel = new JLabel("<html><u>" + section + "</u></html>"); // HTML für unterstrichenen Text
            sectionLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cursor als Link-Style
            sectionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jumpToSection(section);
                }
            });
            tocPanel.add(sectionLabel);
        }

        return tocPanel;
    }

    private void jumpToSection(String section) {
        // Beispiel: Wenn der Benutzer auf den Abschnitt "Einführung" klickt
        String fileName = "";
        if (section.equals("Einführung")) {
            fileName = "einfuehrung.md";
        } else if (section.equals("Funktionalität")) {
            fileName = "funktionalitaet.md";
        } else if (section.equals("Fehlerbehebung")) {
            fileName = "fehlerbehebung.md";
        }

        // Nachdem der Dateiname festgelegt wurde, laden wir den Inhalt und setzen ihn im Textbereich
        String fileContent = loadMarkdownFile(fileName);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(fileContent);  // Setzt den geladenen Inhalt in den Textbereich
        textArea.setCaretPosition(0); // Standardposition zu Beginn
    }
}
