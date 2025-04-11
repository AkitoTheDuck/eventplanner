package FileWriter;

import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;
import Assigner.Tuple;
import FileWriterHelper.ExcelCell;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim
 */
public class StudentScheduleWriter extends FileWriter<Student> {

    @Override
    public void write(ArrayList<Student> students) {
        // Prüfen, ob die Liste leer ist
        if (students == null || students.isEmpty()) {
            System.out.println("Keine Schülerdaten zum Schreiben vorhanden.");
            return;
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        int studentCounter = 0;
        int sheetNumber = 1;
        XSSFSheet sheet = createNewSheet(workbook, sheetNumber);
        int rowCount = 0;
        String filePath = "U:\\Documents\\Downloads\\";

        try {
            for (Student student : students) {
                System.out.println("Verarbeite Schüler: " + student.getLastName() + ", " + student.getFirstName());
                rowCount = writeStudentSchedule(sheet, student, rowCount, workbook);
                studentCounter++;

                // Nach 6 Schülern neue Seite beginnen
                if (studentCounter % 6 == 0) {
                    sheetNumber++;
                    sheet = createNewSheet(workbook, sheetNumber);
                    rowCount = 0; // Reset row count für neue Seite
                }
            }

            // Excel-Datei schreiben
            FileOutputStream out = new FileOutputStream(new File( filePath + "Laufzettel.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("LAUFZETTEL geschrieben");

        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben der Excel-Datei: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private XSSFSheet createNewSheet(XSSFWorkbook workbook, int sheetNumber) {
        XSSFSheet sheet = workbook.createSheet("Laufzettel " + sheetNumber);
        setupPrintSettings(sheet);
        return sheet;
    }

    private int writeStudentSchedule(XSSFSheet sheet, Student student, int startRow, XSSFWorkbook workbook) {
        int row = startRow;

        // Name und Klasse des Schülers
        ExcelCell nameCell = new ExcelCell(student.getLastName() + ", " + student.getFirstName() + ", " + student.getKlasse(), row, 0, workbook);
        nameCell.applyBold();
        nameCell.applyFontSize((short) 12);
        nameCell.applyToSheet(sheet);
        sheet.addMergedRegion(new CellRangeAddress(row, row, 0, 4));
        row++;

        // Tabellen-Header: Zeit, Raum, Veranstaltung, Wunsch
        String[] headers = {"Zeit", "Raum", "Veranstaltung","", "Wunsch"};
        for (int i = 0; i < headers.length; i++) {
            ExcelCell headerCell = new ExcelCell(headers[i], row, i, workbook);
            headerCell.applyBold();
            headerCell.applyBackgroundColor(IndexedColors.GREY_25_PERCENT);
            headerCell.applyToSheet(sheet);
            headerCell.applyAllBorder(BorderStyle.MEDIUM);
        }
        row++;


        String[] slots = {"A", "B", "C", "D", "E"};

        Map<String, String> slotTimes = new HashMap<>();
        Map<String, Integer> slotIndexMap = new HashMap<>();
        slotTimes.put("A", "08:45-9:30");
        slotTimes.put("B", "9:50-10:35");
        slotTimes.put("C", "10:35-11:20");
        slotTimes.put("D", "11:40-12:25");
        slotTimes.put("E", "12:25-13:10");

        slotIndexMap.put("A", 0);
        slotIndexMap.put("B", 1);
        slotIndexMap.put("C", 2);
        slotIndexMap.put("D", 3);
        slotIndexMap.put("E", 4);

        ArrayList<Tuple> zuweisungen = student.getZuweisungsListe();

        Map<String, Tuple> zuweisungMap = new HashMap<>();
        for (Tuple zuweisung : zuweisungen) {
            if (zuweisung != null) {
                zuweisungMap.put(zuweisung.getSlot(), zuweisung);
            }
        }

        for (String slot : slots) {
            Tuple zuweisungSlot = zuweisungMap.get(slot);

            if (zuweisungSlot == null) {
                continue; // Falls keine Zuweisung für das Slot gefunden wird
            }

            // Hole die Slot-Zeit und Slot-Index aus den Maps
            String slotTime = slotTimes.get(slot);
            int slotInt = slotIndexMap.get(slot);

            // Erstelle Excel-Zellen
            createExcelCell(slotTime, row, 0, sheet, workbook, BorderStyle.MEDIUM, BorderStyle.THIN);
            String room = zuweisungSlot.getCompany().getRoomList().get(slotInt).getRoomNumber();
            createExcelCell(room, row, 1, sheet, workbook, BorderStyle.NONE, BorderStyle.NONE);
            String companyName = zuweisungSlot.getCompany().getName();
            createExcelCell(companyName, row, 2, sheet, workbook, BorderStyle.THIN, BorderStyle.THIN);
            String companyField = zuweisungSlot.getCompany().getFieldOfStudy();
            createExcelCell(companyField, row, 3, sheet, workbook, BorderStyle.THIN, BorderStyle.THIN);
            String wisch = zuweisungSlot.getWunsch();
            createExcelCell(wisch, row, 4, sheet, workbook, BorderStyle.THIN, BorderStyle.MEDIUM);

            if (row == startRow + slots.length + 1) {
                setBottomBorder(row, sheet, workbook);
            }

            row++;
        }



        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        sheet.setColumnWidth(0, 4000); // Zeit
        sheet.setColumnWidth(1, 3000); // Raum
        sheet.setColumnWidth(2, 10000); // Veranstaltung
        sheet.setColumnWidth(3, 3000); // Wunsch

        row += 1; // Abstand zwischen den Schülern
        return row;
    }

    private void setupPrintSettings(XSSFSheet sheet) {
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setFitWidth((short) 1); // Skaliert auf eine Seite Breite
        printSetup.setFitHeight((short) 0); // Höhe wird nicht skaliert
        sheet.setAutobreaks(true);
        sheet.setHorizontallyCenter(true); // Zentriert auf der Seite

        // Seitenränder verringern
        sheet.setMargin(Sheet.LeftMargin, 0.3);
        sheet.setMargin(Sheet.RightMargin, 0.3);
        sheet.setMargin(Sheet.TopMargin, 0.5);
        sheet.setMargin(Sheet.BottomMargin, 0.5);
    }

    private void createExcelCell(String content, int row, int col, Sheet sheet, XSSFWorkbook workbook, BorderStyle leftBorder, BorderStyle rightBorder) {
        ExcelCell cell = new ExcelCell(content, row, col, workbook);
        cell.applyToSheet(sheet);
        if (leftBorder != BorderStyle.NONE) {
            cell.setBorder(ExcelCell.BorderPosition.LEFT, leftBorder);
        }
        if (rightBorder != BorderStyle.NONE) {
            cell.setBorder(ExcelCell.BorderPosition.RIGHT, rightBorder);
        }
    }

    private void setBottomBorder(int row, Sheet sheet, XSSFWorkbook workbook) {
        ExcelCell timeCell = new ExcelCell("", row, 0, workbook);
        ExcelCell roomCell = new ExcelCell("", row, 1, workbook);
        ExcelCell eventCellname = new ExcelCell("", row, 2, workbook);
        ExcelCell eventCellFachrichtung = new ExcelCell("", row, 3, workbook);
        ExcelCell wishCell = new ExcelCell("", row, 4, workbook);

        timeCell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
        roomCell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
        eventCellname.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
        eventCellFachrichtung.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
        wishCell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
    }
}
