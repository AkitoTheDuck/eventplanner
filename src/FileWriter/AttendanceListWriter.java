package FileWriter;

import DataWrapper.Company;
import FileWriterHelper.ExcelCell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class AttendanceListWriter extends FileWriter<Company>{

    @Override
    public void write(ArrayList<Company> list) {

        XSSFWorkbook workbook = new XSSFWorkbook();

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

            //schleife aller Schüler des TimeSlots




            sheetCount++;
        }

        try {
            FileOutputStream out = new FileOutputStream(new File("src/FileWriter/testAttendance.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("test1.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
