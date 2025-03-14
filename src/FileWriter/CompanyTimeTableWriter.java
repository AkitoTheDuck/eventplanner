package FileWriter;

import FileWriterHelper.ExcelCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class CompanyTimeTableWriter extends FileWriter {


    @Override
    public void write() {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Raumplan");

        IndexedColors grey = IndexedColors.GREY_25_PERCENT;

        ExcelCell cellHeader = new ExcelCell("Organisationsplan für den Berufsorientierungstag",0,0, workbook);
        cellHeader.applyBold();
        cellHeader.applyFontSize((short) 14);
        cellHeader.applyFont("Arial");
        cellHeader.applyToSheet(sheet);

        ExcelCell cellAula = new ExcelCell("8:30 bis 8:45 Uhr Begrüßung und Einführung in der Aula",1,0,workbook);
        cellAula.applyAllBorder(BorderStyle.MEDIUM);
        cellAula.applyBackgroundColor(grey);
        cellAula.applyToSheet(sheet);

        ExcelCell cellAbschluss = new ExcelCell("13:10 bis 13:20 Uhr Abschluss im Klassenverbund",2,0,workbook);
        cellAbschluss.applyAllBorder(BorderStyle.MEDIUM);
        cellAbschluss.applyBackgroundColor(grey);
        cellAbschluss.applyToSheet(sheet);

        String[] timeSlots = {"", "8:45 – 9:30", "9:50 – 10:35", "10:35 – 11:20", "11:40– 12:25", "12:25 – 13:10"};
        for (int i = 0; i < timeSlots.length; i++) {
            ExcelCell timeCell = new ExcelCell(timeSlots[i], 4, i, workbook);
            timeCell.applyBackgroundColor(grey);

            if (i == 1) {
                timeCell.setBorder(ExcelCell.BorderPosition.TOP, BorderStyle.MEDIUM);
                timeCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.MEDIUM);
                timeCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.THIN);
            } else if (i == 5) {
                timeCell.setBorder(ExcelCell.BorderPosition.TOP, BorderStyle.MEDIUM);
                timeCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.THIN);
                timeCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.MEDIUM);
            } else {
                timeCell.setBorder(ExcelCell.BorderPosition.TOP, BorderStyle.MEDIUM);
                timeCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.THIN);
                timeCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.THIN);
            }

            timeCell.applyToSheet(sheet);
        }
        sheet.autoSizeColumn(4);

        String[] rooms = {"","A", "B", "C", "D", "E"};
        for (int i = 0; i < rooms.length; i++) {
            ExcelCell roomCell = new ExcelCell(rooms[i], 5, i, workbook);
            roomCell.applyBackgroundColor(grey);

            if (i == 1) {
                roomCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.MEDIUM);
                roomCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.THIN);
            } else if (i == 5) {
                roomCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.THIN);
                roomCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.MEDIUM);
            } else {
                roomCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.THIN);
                roomCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.THIN);
            }

            roomCell.applyToSheet(sheet);
        }
        sheet.autoSizeColumn(5);



        try {

            FileOutputStream out = new FileOutputStream(new File("src/FileWriter/test1.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("test1.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
