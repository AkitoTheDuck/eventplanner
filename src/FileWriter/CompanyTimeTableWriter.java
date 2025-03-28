package FileWriter;

import DataWrapper.ClassRoom;
import DataWrapper.Company;
import FileWriterHelper.ExcelCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * @author Christian
 */
public class CompanyTimeTableWriter extends FileWriter <Company> {


    @Override
    public void write(ArrayList<Company> list) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Raumplan");

        IndexedColors grey = IndexedColors.GREY_25_PERCENT;

        ExcelCell cellHeader = new ExcelCell("Organisationsplan für den Berufsorientierungstag",0,0, workbook);
        cellHeader.applyBold();
        cellHeader.applyTextHorizontal(HorizontalAlignment.LEFT);
        cellHeader.applyFontSize((short) 14);
        cellHeader.applyFont("Arial");
        cellHeader.applyToSheet(sheet);

        sheet.addMergedRegion(new CellRangeAddress(1,1,0,5));
        String[] aulaRow = {"8:30 bis 8:45 Uhr Begrüßung und Einführung in der Aula","","","","",""};
        for (int i = 0; i < aulaRow.length; i++) {
            ExcelCell cell = new ExcelCell(aulaRow[i], 1, i, workbook);

            if (i == 0) {
                cell.setBorder(ExcelCell.BorderPosition.TOP, BorderStyle.MEDIUM);
                cell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.MEDIUM);
                cell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.MEDIUM);
                cell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
            } else if (i == 5) {
                cell.setBorder(ExcelCell.BorderPosition.TOP, BorderStyle.MEDIUM);
                cell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.MEDIUM);
                cell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
            } else {
                cell.setBorder(ExcelCell.BorderPosition.TOP, BorderStyle.MEDIUM);
                cell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
            }

            cell.applyTextHorizontal(HorizontalAlignment.LEFT);
            cell.applyBold();
            cell.applyBackgroundColor(grey);
            cell.applyToSheet(sheet);
        }

        sheet.addMergedRegion(new CellRangeAddress(2,2,0,5));
        String[] abschlussRow = {"13:10 bis 13:20 Uhr Abschluss im Klassenverbund","","","","",""};
        for (int i = 0; i < abschlussRow.length; i++) {
            ExcelCell abschlussCell = new ExcelCell(abschlussRow[i], 2, i, workbook);

            if (i == 0) {
                abschlussCell.setBorder(ExcelCell.BorderPosition.TOP, BorderStyle.MEDIUM);
                abschlussCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.MEDIUM);
                abschlussCell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
            } else if (i == 5) {
                abschlussCell.setBorder(ExcelCell.BorderPosition.TOP, BorderStyle.MEDIUM);
                abschlussCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.MEDIUM);
                abschlussCell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
            } else {
                abschlussCell.setBorder(ExcelCell.BorderPosition.TOP, BorderStyle.MEDIUM);
                abschlussCell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.MEDIUM);
            }

            abschlussCell.applyBold();
            abschlussCell.applyBackgroundColor(grey);
            abschlussCell.applyToSheet(sheet);
        }

        String[] timeSlots = {"", "8:45 – 9:30", "9:50 – 10:35", "10:35 – 11:20", "11:40– 12:25", "12:25 – 13:10"};
        for (int i = 0; i < timeSlots.length; i++) {
            ExcelCell timeCell = new ExcelCell(timeSlots[i], 4, i, workbook);
            timeCell.applyBackgroundColor(grey);

            if (i == 1 || i == 0) {
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

            timeCell.applyTextCenter();
            timeCell.applyFontSize((short) 10);
            timeCell.applyToSheet(sheet);
        }

        String[] rooms = {"","A", "B", "C", "D", "E"};
        for (int i = 0; i < rooms.length; i++) {
            ExcelCell timeSlotCell = new ExcelCell(rooms[i], 5, i, workbook);
            timeSlotCell.applyBackgroundColor(grey);
            timeSlotCell.applyTextCenter();

            if (i == 1 || i == 0) {
                timeSlotCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.MEDIUM);
                timeSlotCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.THIN);
                timeSlotCell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.THIN);
            } else if (i == 5) {
                timeSlotCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.THIN);
                timeSlotCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.MEDIUM);
                timeSlotCell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.THIN);
            } else {
                timeSlotCell.setBorder(ExcelCell.BorderPosition.LEFT, BorderStyle.THIN);
                timeSlotCell.setBorder(ExcelCell.BorderPosition.RIGHT, BorderStyle.THIN);
                timeSlotCell.setBorder(ExcelCell.BorderPosition.BOTTOM, BorderStyle.THIN);
            }
            timeSlotCell.applyBold();
            timeSlotCell.applyToSheet(sheet);
        }

        int rowCount = 6;
        for (Company company : list) {

            ExcelCell nameCell = new ExcelCell( company.getName(), rowCount, 0, workbook);
            nameCell.setBorder(ExcelCell.BorderPosition.LEFT,BorderStyle.MEDIUM);
            nameCell.setBorder(ExcelCell.BorderPosition.RIGHT,BorderStyle.MEDIUM);
            nameCell.setBorder(ExcelCell.BorderPosition.TOP,BorderStyle.THIN);
            nameCell.setBorder(ExcelCell.BorderPosition.BOTTOM,BorderStyle.THIN);
            nameCell.applyBold();
            nameCell.applyTextHorizontal(HorizontalAlignment.LEFT);
            nameCell.applyToSheet(sheet);

            for (int i = 0; i <= 4; i++) {

                ClassRoom classRoom = null;
                switch (i) {
                    case 0:
                        classRoom = company.getSlotAClass();
                        break;
                    case 1:
                        classRoom = company.getSlotBClass();
                        break;
                    case 2:
                        classRoom = company.getSlotCClass();
                        break;
                    case 3:
                        classRoom = company.getSlotDClass();
                        break;
                    case 4:
                        classRoom = company.getSlotEClass();
                        break;
                }

                if(classRoom != null) {
                    ExcelCell cell = new ExcelCell(classRoom.getRoomNumber(), rowCount, i+1, workbook);
                    cell.applyAllBorder(BorderStyle.THIN);
                    cell.applyBold();
                    cell.applyToSheet(sheet);
                } else {
                    ExcelCell cell = new ExcelCell("", rowCount, i+1, workbook);
                    cell.applyAllBorder(BorderStyle.THIN);
                    cell.applyToSheet(sheet);
                }

            }

            rowCount++;
        }

        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);

        try {

            FileOutputStream out = new FileOutputStream(new File("src/timeTable.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("test1.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
