//author Lisa & Jacqueline
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.util.Map;

public class GUI_Main {
    private JFrame frame = new JFrame("Eventplaner");
    private JLabel select = new JLabel("Schülerwahl");
    private JLabel vlist = new JLabel("Veranstaltungsliste");
    private JLabel rlist = new JLabel("Raumliste");
    private JButton save = new JButton("save");
    private JButton download = new JButton("download");

    private String [] endfiles = {"Download all", "Raumplan", "Laufzettel", "Anwesenheitsliste"};
    private JComboBox more = new JComboBox(endfiles);

    public GUI_Main () {
        frame.setSize(1100,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        //mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel titelLabel = new JLabel("Eventplaner", SwingConstants.CENTER);
        titelLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titelLabel.setAlignmentY(Component.LEFT_ALIGNMENT);
        mainPanel.add(titelLabel, BorderLayout.NORTH);
        frame.add(mainPanel);


        //create upper Panel
        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
        mainPanel.add(upperPanel);
        //create upload Panel
        JPanel uploadPanel = new JPanel();
        //uploadPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        uploadPanel.setLayout(new BoxLayout(uploadPanel, BoxLayout.X_AXIS));

        //create titleDrop Panels
        JPanel titleDropPanel1 = new JPanel();
        titleDropPanel1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        titleDropPanel1.setLayout(new BoxLayout(titleDropPanel1, BoxLayout.Y_AXIS));
        uploadPanel.add(titleDropPanel1);
        JPanel titleDropPanel2 = new JPanel();
        titleDropPanel2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        titleDropPanel2.setLayout(new BoxLayout(titleDropPanel2, BoxLayout.Y_AXIS));
        uploadPanel.add(titleDropPanel2);
        JPanel titleDropPanel3 = new JPanel();
        titleDropPanel3.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        titleDropPanel3.setLayout(new BoxLayout(titleDropPanel3, BoxLayout.Y_AXIS));
        uploadPanel.add(titleDropPanel3);

        //create title Panels
        /*JPanel titlePanel1 = new JPanel();
        titlePanel1.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
        titlePanel1.setLayout(new BoxLayout(titlePanel1, BoxLayout.Y_AXIS));
        titleDropPanel1.add(titlePanel1);
        JPanel titlePanel2 = new JPanel();
        titlePanel2.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        titlePanel2.setLayout(new BoxLayout(titlePanel2, BoxLayout.Y_AXIS));
        titleDropPanel2.add(titlePanel2);
        JPanel titlePanel3 = new JPanel();
        titlePanel3.setBorder(BorderFactory.createLineBorder(Color.PINK, 2));
        titlePanel3.setLayout(new BoxLayout(titlePanel3, BoxLayout.Y_AXIS));
        titleDropPanel3.add(titlePanel3);*/
        
        //create drop Panels
        JPanel dropPanel1 = new JPanel();
        dropPanel1.setBackground(Color.LIGHT_GRAY);
        dropPanel1.setPreferredSize(new Dimension(300,250));
        dropPanel1.setLayout(new FlowLayout());
        titleDropPanel1.add(select, BorderLayout.NORTH);
        titleDropPanel1.add(dropPanel1, BorderLayout.SOUTH);
        JPanel dropPanel2 = new JPanel();
        dropPanel2.setBackground(Color.LIGHT_GRAY);
        dropPanel2.setPreferredSize(new Dimension(300,250));
        dropPanel2.setLayout(new FlowLayout());
        titleDropPanel2.add(vlist);
        titleDropPanel2.add(dropPanel2, BorderLayout.CENTER);
        JPanel dropPanel3 = new JPanel();
        dropPanel3.setBackground(Color.LIGHT_GRAY);
        dropPanel3.setPreferredSize(new Dimension(300,250));
        dropPanel3.setLayout(new FlowLayout());
        titleDropPanel3.add(rlist, BorderLayout.NORTH);
        titleDropPanel3.add(dropPanel3, BorderLayout.CENTER);

        // Drop Panel 1
        dropPanel1.setDropTarget(new DropTarget(dropPanel1, DnDConstants.ACTION_COPY, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transferable = dtde.getTransferable();
                    List<File> data = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    if (!data.isEmpty()) {
                        File droppedFile = data.get(0);
                        handleFileDrop(dropPanel1, droppedFile); // Dateidrop verarbeiten
                    }
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }));

        dropPanel2.setDropTarget(new DropTarget(dropPanel2, DnDConstants.ACTION_COPY, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transferable = dtde.getTransferable();
                    List<File> data = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    StringBuilder files = new StringBuilder("");
                    for (File file : data) {
                        files.append(file.getName()).append(", ");
                    }
                    files.setLength(files.length() - 2);
                    JLabel droppedLabel = new JLabel(files.toString());
                    droppedLabel.setFont(adjustFontToFit(droppedLabel, dropPanel1));
                    droppedLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
                    dropPanel2.removeAll();
                    dropPanel2.add(droppedLabel);
                    dropPanel2.revalidate();
                    dropPanel2.repaint();
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }));
        dropPanel3.setDropTarget(new DropTarget(dropPanel3, DnDConstants.ACTION_COPY, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transferable = dtde.getTransferable();
                    List<File> data = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    StringBuilder files = new StringBuilder("");
                    for (File file : data) {
                        files.append(file.getName()).append(", ");
                    }
                    files.setLength(files.length() - 2);
                    JLabel droppedLabel = new JLabel(files.toString());
                    droppedLabel.setFont(adjustFontToFit(droppedLabel, dropPanel1));
                    droppedLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
                    dropPanel3.removeAll();
                    dropPanel3.add(droppedLabel);
                    dropPanel3.revalidate();
                    dropPanel3.repaint();
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }));

        // Save-Button ActionListener hinzufügen
        save.addActionListener(e -> {
            // Dateien speichern, wenn der Save-Button geklickt wird
            saveFiles();
        });


        upperPanel.add(uploadPanel, BorderLayout.WEST);
        //create save Panel
        JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        savePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        savePanel.add(Box.createRigidArea(new Dimension(10, 250)));
        savePanel.add(save);
        upperPanel.add(savePanel, BorderLayout.EAST);



        //create lower Panel
        JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //lowerPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        lowerPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        lowerPanel.add(more);
        lowerPanel.add(download);
        mainPanel.add(lowerPanel);
        frame.setVisible(true);
    }

    // map erstellen, die die Dateien speichert
    private Map<JPanel, File> dropPanelFiles = new HashMap<>(); // Map to store files for each DropPanel

    public void handleFileDrop(JPanel dropPanel, File droppedFile) {
        // Speichere die Datei für das jeweilige DropPanel
        dropPanelFiles.put(dropPanel, droppedFile);
        System.out.println("Datei für Panel " + dropPanel.hashCode() + " gespeichert: " + droppedFile.getAbsolutePath());
    }

    public File getFileForDropPanel(JPanel dropPanel) {
        return dropPanelFiles.get(dropPanel); // Hole die Datei für das jeweilige DropPanel
    }

    public void saveFiles() {
        for (Map.Entry<JPanel, File> entry : dropPanelFiles.entrySet()) {
            JPanel dropPanel = entry.getKey();
            File file = entry.getValue();

            if (file != null) {
                // Hier speichern wir die Datei (du kannst hier entscheiden, wo und wie sie gespeichert wird)
                try {
                    File saveLocation = new File("path/to/save/directory", file.getName());
                    Files.copy(file.toPath(), saveLocation.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Datei für Panel " + dropPanel.hashCode() + " gespeichert: " + saveLocation.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Font adjustFontToFit(JLabel label, JPanel panel) {
        String text = label.getText();
        Font font = new Font("Arial", Font.BOLD, 12); // Startschriftgröße
        FontMetrics metrics = panel.getFontMetrics(font);
        int width = panel.getWidth();
        int textWidth = metrics.stringWidth(text);

        // Wenn der Text zu breit ist, verkleinere die Schriftgröße
        while (textWidth > width) {
            font = font.deriveFont(font.getSize2D() - 1); // Verringere die Schriftgröße
            metrics = panel.getFontMetrics(font);
            textWidth = metrics.stringWidth(text);
        }

        return font;
    }
}