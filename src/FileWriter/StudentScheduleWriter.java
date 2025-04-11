package FileWriter;

import DataWrapper.Company;
import DataWrapper.Student;
import FileWriterHelper.ExcelCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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

        // Zeiträume und Veranstaltungen
        String[] timeSlots = {"08:45-9:30", "9:50-10:35", "10:35-11:20", "11:40-12:25", "12:25-13:10"};
        int[] choices = {student.getChoice1(), student.getChoice2(), student.getChoice3(), student.getChoice4(), student.getChoice5()};

        //TODO TODO TODO muss noch angepasst werden für echte Raum, Veranstaltung, Wunsch
        //assignings Map <bool, CompanyNr>
        //sortedChoice

        for (int i = 0; i < timeSlots.length; i++) {
            ExcelCell timeCell = new ExcelCell(timeSlots[i], row, 0, workbook);
            timeCell.applyToSheet(sheet);
            timeCell.setBorder(ExcelCell.BorderPosition.LEFT,BorderStyle.MEDIUM);
            timeCell.setBorder(ExcelCell.BorderPosition.RIGHT,BorderStyle.THIN);
            
            ExcelCell roomCell = new ExcelCell("311", row, 1, workbook);
            roomCell.applyToSheet(sheet);

            ExcelCell eventCellname = new ExcelCell("Veranstaltung " + choices[i], row, 2, workbook);
            eventCellname.applyToSheet(sheet);
            eventCellname.setBorder(ExcelCell.BorderPosition.LEFT,BorderStyle.THIN);
            eventCellname.setBorder(ExcelCell.BorderPosition.RIGHT,BorderStyle.THIN);

            ExcelCell eventCellFachrichtung = new ExcelCell("", row, 3, workbook);
            eventCellFachrichtung.applyToSheet(sheet);
            eventCellFachrichtung.setBorder(ExcelCell.BorderPosition.LEFT,BorderStyle.THIN);
            eventCellFachrichtung.setBorder(ExcelCell.BorderPosition.RIGHT,BorderStyle.THIN);

            ExcelCell wishCell = new ExcelCell(String.valueOf(i + 1), row, 4, workbook);
            wishCell.applyToSheet(sheet);
            wishCell.applyTextHorizontal(HorizontalAlignment.CENTER);
            wishCell.setBorder(ExcelCell.BorderPosition.LEFT,BorderStyle.THIN);
            wishCell.setBorder(ExcelCell.BorderPosition.RIGHT,BorderStyle.MEDIUM);

            if(row == startRow+timeSlots.length+1) {
                timeCell.setBorder(ExcelCell.BorderPosition.BOTTOM,BorderStyle.MEDIUM);
                roomCell.setBorder(ExcelCell.BorderPosition.BOTTOM,BorderStyle.MEDIUM);
                eventCellname.setBorder(ExcelCell.BorderPosition.BOTTOM,BorderStyle.MEDIUM);
                eventCellFachrichtung.setBorder(ExcelCell.BorderPosition.BOTTOM,BorderStyle.MEDIUM);
                wishCell.setBorder(ExcelCell.BorderPosition.BOTTOM,BorderStyle.MEDIUM);
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
}
