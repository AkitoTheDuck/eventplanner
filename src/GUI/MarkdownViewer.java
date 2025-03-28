package GUI;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MarkdownViewer {
    public static void main(String[] args) {
        // Lade die Markdown-Datei
        String markdownContent = loadMarkdownFile("path/to/your/file.md");

        // Konvertiere Markdown in HTML
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String htmlContent = renderer.render(parser.parse(markdownContent));

        // Erstelle das JFrame und JEditorPane
        JFrame frame = new JFrame("Markdown Viewer");
        JEditorPane editorPane = new JEditorPane("text/html", htmlContent);
        editorPane.setEditable(false); // Der Textbereich sollte nicht bearbeitbar sein
        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Setup und Anzeige
        frame.getContentPane().add(scrollPane);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Funktion zum Laden der Markdown-Datei
    private static String loadMarkdownFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}