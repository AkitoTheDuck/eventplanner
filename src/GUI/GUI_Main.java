package GUI;// Autoren: Lisa & Jacqueline

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GUI_Main {
    private JFrame frame = new JFrame("Eventplaner"); // Hauptfenster
    private JLabel select = new JLabel("Schülerwahl"); // Label für Schülerwahl
    private JLabel vlist = new JLabel("Veranstaltungsliste"); // Label für Veranstaltungen
    private JLabel rlist = new JLabel("Raumliste"); // Label für Räume
    private JButton download = new JButton("download"); // Download-Button
    private String[] endfiles = {"Download all", "Raumplan", "Laufzettel", "Anwesenheitsliste"};
    private JComboBox<String> more = new JComboBox<>(endfiles); // Auswahlbox für Downloads
    private Map<JPanel, File> dropPanelFiles = new HashMap<>(); // Speicherung der Dateien

    public GUI_Main() {
        frame.setSize(1100, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel titelLabel = new JLabel("Eventplaner", SwingConstants.CENTER);
        titelLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titelLabel);
        frame.add(mainPanel);

        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
        mainPanel.add(upperPanel);

        // Upload-Bereich mit drei Panels
        JPanel titleDropPanel1 = createDropPanel(select);
        JPanel titleDropPanel2 = createDropPanel(vlist);
        JPanel titleDropPanel3 = createDropPanel(rlist);

        upperPanel.add(titleDropPanel1);
        upperPanel.add(titleDropPanel2);
        upperPanel.add(titleDropPanel3);

        JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lowerPanel.add(more);
        lowerPanel.add(download);
        mainPanel.add(lowerPanel);

        if(dropPanelFiles.size() == 3) {
            download.addActionListener(e -> GUI_Download.downloadFiles(more, dropPanelFiles)); //download-Button führt den download aus
        } else {
            download.addActionListener(i -> JOptionPane.showMessageDialog(null, "Bitte lege in jedes der 3 Felder die dazugehörige Datei ab!"));
        }
        frame.setVisible(true);
    }


    // Erstellt ein Drop-Panel mit Drag-and-Drop-Funktionalität
    private JPanel createDropPanel(JLabel label) {
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
                            handleFileDrop(dropPanel, fileLabel, droppedFile);
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
    public void handleFileDrop(JPanel dropPanel, JLabel fileLabel, File droppedFile) {
        String fileName = droppedFile.getName();

        // Überprüfen, ob die Datei eine Excel-Datei ist (.xls oder .xlsx)
        if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
            dropPanelFiles.put(dropPanel, droppedFile);
            fileLabel.setText(droppedFile.getName()); // Setzt den Dateinamen als Text
            System.out.println("Excel-Datei für Panel " + dropPanel.hashCode() + " gespeichert: " + droppedFile.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(null, "Nur Excel-Dateien (.xls, .xlsx) sind erlaubt.");
        }
    }
}
