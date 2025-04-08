package FileWriter;

import DataWrapper.Company;
import DataWrapper.Student;
import FileWriterHelper.ExcelCell;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * @author Christian
 */
public class AttendanceListWriter extends FileWriter<Company>{

    private XSSFWorkbook workbook;

    @Override
    public void write(ArrayList<Company> list) {

        if (list == null || list.isEmpty()) {
            System.out.println("Keine CompanyDaten zum Schreiben vorhanden.");
            return;
        }

        this.workbook = new XSSFWorkbook();
        String filePath = "U:\\Documents\\Downloads\\";

        int sheetCount = 0;
        for (Company company : list) {
            XSSFSheet sheet = workbook.createSheet(Integer.toString(sheetCount));

            ExcelCell cellHeader = new ExcelCell("Anwesenheitsliste",0,0, workbook);
            cellHeader.applyFontSize((short) 16);
            cellHeader.applyToSheet(sheet);

            ExcelCell cellCompanyName = new ExcelCell(company.getName(),1,0, workbook);
            cellCompanyName.applyBold();
            cellCompanyName.applyFontSize((short) 16);
            cellCompanyName.applyToSheet(sheet);

            int rowCounter = 3;

            rowCounter = printTimeSlots("8:45 – 9:30", company.getSlotA(), rowCounter, sheet);
            rowCounter = printTimeSlots("9:50 - 10:35", company.getSlotB(), rowCounter, sheet);
            rowCounter = printTimeSlots("10:35 – 11:20", company.getSlotC(), rowCounter, sheet);
            rowCounter = printTimeSlots("11:40 – 12:25", company.getSlotD(), rowCounter, sheet);
            rowCounter = printTimeSlots("12:25 - 13:30", company.getSlotE(), rowCounter, sheet);

            sheetCount++;
        }

        try {
            FileOutputStream out = new FileOutputStream(new File(filePath + "Anwesenheitsliste.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("ANWESENHEITSLISTE geschrieben");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int printTimeSlots (String timeSlot, ArrayList<Student> students ,int rowCounter, XSSFSheet sheet) {
        if( ! students.isEmpty()) {
            rowCounter ++;
            printSlot(timeSlot, rowCounter, sheet);
            printStudentHeader(rowCounter, sheet);
            for (Student student : students) {
                printStudent(student, rowCounter, sheet);
                rowCounter++;
            }
        }
        return rowCounter;
    }

    private void printStudent (Student student, int row, XSSFSheet sheet) {

        String[] header = {student.getKlasse(),student.getLastName(),student.getFirstName(),""};
        for (int i = 0; i < header.length; i++) {
            ExcelCell cell = new ExcelCell(header[i], row, i, workbook);

            cell.applyAllBorder(BorderStyle.THIN);

            cell.applyTextHorizontal(HorizontalAlignment.LEFT);
            cell.applyBold();
            cell.applyToSheet(sheet);
        }
    }

    private void printStudentHeader (int row, XSSFSheet sheet) {

        String[] header = {"Klasse","Name","Vorname","Anwesend?"};
        for (int i = 0; i < header.length; i++) {
            ExcelCell cell = new ExcelCell(header[i], row, i, workbook);

            cell.applyAllBorder(BorderStyle.THIN);

            cell.applyTextHorizontal(HorizontalAlignment.LEFT);
            cell.applyBold();
            cell.applyToSheet(sheet);
        }

    }

    private void printSlot(String slot , int row, XSSFSheet sheet) {
        ExcelCell slotA = new ExcelCell(slot,row,0, workbook);
        slotA.applyBold();
        slotA.applyFontSize((short) 11);
        slotA.applyToSheet(sheet);
    }
}
