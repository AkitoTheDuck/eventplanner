package RunningSheet;

import Assigner.DummyVeranstaltungen;
import Assigner.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RunningSheet {
    public static void createExcel(ArrayList<Student> schuelers, ArrayList<DummyVeranstaltungen> veranstaltungen) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Laufzettel");

        // 🔹 Stil für die Kopfzeile
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 🔹 Stil für Zellen mit Rahmen
        CellStyle borderedStyle = workbook.createCellStyle();
        borderedStyle.setBorderTop(BorderStyle.THIN);
        borderedStyle.setBorderBottom(BorderStyle.THIN);
        borderedStyle.setBorderLeft(BorderStyle.THIN);
        borderedStyle.setBorderRight(BorderStyle.THIN);
        borderedStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 🔹 Kopfzeile erstellen
        String[] headers = {"Vorname", "Nachname", "Klasse", "Veranstaltung1", "Veranstaltung2", "Veranstaltung3", "Veranstaltung4", "Veranstaltung5"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowIndex = 1;
        int schuelerProSeite = 4;

        for (int i = 0; i < schuelers.size(); i++) {
            Student s = schuelers.get(i);
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(s.getVorname());
            row.createCell(1).setCellValue(s.getName());
            row.createCell(2).setCellValue(s.getKlasse());

            // Veranstaltungen in separate Zellen schreiben
            ArrayList<Integer> eventIds = s.getFulfilledWishes();
            for (int j = 0; j < 5; j++) {
                Cell cell = row.createCell(3 + j);
                if (j < eventIds.size()) {
                    cell.setCellValue(getEventNameById(eventIds.get(j), veranstaltungen));
                } else {
                    cell.setCellValue("—");
                }
                cell.setCellStyle(borderedStyle);
            }

            // 🔹 Nach 4 Schülern einen Seitenumbruch setzen
            if ((i + 1) % schuelerProSeite == 0) {
                sheet.setRowBreak(rowIndex); // Seitenumbruch setzen nach dem 4. Schüler
            }
        }

        // 🔹 Automatische Spaltenbreite
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Datei speichern
        FileOutputStream fileOut = new FileOutputStream("Laufzettel.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
        System.out.println("Laufzettel erfolgreich erstellt!");
    }

    private static String getEventNameById(int eventId, ArrayList<DummyVeranstaltungen> veranstaltungen) {
        for (DummyVeranstaltungen v : veranstaltungen) {
            if (v.getNummer() == eventId) {
                return v.getName();
            }
        }
        return "Nicht zugewiesen";
    }
}
