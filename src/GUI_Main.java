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
import java.util.List;
import java.io.*;

public class GUI_Main {
    private JFrame frame = new JFrame("Eventplaner");
    private JButton select = new JButton("Schülerwahl");
    private JButton vlist = new JButton("Veranstaltungsliste");
    private JButton rlist = new JButton("Raumliste");
    private JButton save = new JButton("save");
    private JButton download = new JButton("download");

    private String [] endfiles = {"Download all", "Raumplan", "Laufzettel", "Anwesenheitsliste"};
    private JComboBox more = new JComboBox(endfiles);

    public GUI_Main () {
        frame.setSize(800,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel titelLabel = new JLabel("Eventplaner", SwingConstants.CENTER);
        titelLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titelLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        mainPanel.add(titelLabel, BorderLayout.NORTH);
        frame.add(mainPanel);


        //create upper Panel
        JPanel upperPanel = new JPanel();
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
        mainPanel.add(upperPanel);
        //create upload Panel
        JPanel uploadPanel = new JPanel();
        uploadPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        //create drop Panels
        JPanel dropPanel1 = new JPanel();
        dropPanel1.setBackground(Color.LIGHT_GRAY);
        dropPanel1.setPreferredSize(new Dimension(200,200));
        dropPanel1.setLayout(new FlowLayout());
        uploadPanel.add(dropPanel1, BorderLayout.CENTER);
        JPanel dropPanel2 = new JPanel();
        dropPanel2.setBackground(Color.LIGHT_GRAY);
        dropPanel2.setPreferredSize(new Dimension(200,200));
        dropPanel2.setLayout(new FlowLayout());
        uploadPanel.add(dropPanel2, BorderLayout.CENTER);
        JPanel dropPanel3 = new JPanel();
        dropPanel3.setBackground(Color.LIGHT_GRAY);
        dropPanel3.setPreferredSize(new Dimension(200,200));
        dropPanel3.setLayout(new FlowLayout());
        uploadPanel.add(dropPanel3, BorderLayout.CENTER);

        dropPanel1.setDropTarget(new DropTarget(dropPanel1, DnDConstants.ACTION_COPY, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable transferable = dtde.getTransferable();
                    List<File> data = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    StringBuilder files = new StringBuilder("Dropped: ");
                    for (File file : data) {
                        files.append(file.getName()).append(", ");
                    }
                    files.setLength(files.length() - 2);
                    JLabel droppedLabel = new JLabel(files.toString());
                    droppedLabel.setFont(adjustFontToFit(droppedLabel, dropPanel1));
                    droppedLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
                    dropPanel1.removeAll();
                    dropPanel1.add(droppedLabel);
                    dropPanel1.revalidate();
                    dropPanel1.repaint();
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dropPanel1, "Error processing the drop. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
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
                    StringBuilder files = new StringBuilder("Dropped: ");
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
                    JLabel droppedLabel = new JLabel("Dropped: " + data);
                    dropPanel3.add(droppedLabel);
                    dropPanel3.revalidate();
                    dropPanel3.repaint();
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }));

        upperPanel.add(uploadPanel, BorderLayout.WEST);
        //create save Panel
        JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        savePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        savePanel.add(Box.createRigidArea(new Dimension(10, 300)));
        savePanel.add(save);
        upperPanel.add(savePanel, BorderLayout.EAST);



        //create lower Panel
        JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        lowerPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        lowerPanel.add(more);
        lowerPanel.add(download);
        mainPanel.add(lowerPanel);
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
