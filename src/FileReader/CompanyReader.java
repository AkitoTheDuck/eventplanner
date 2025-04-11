package FileReader;

import DataWrapper.Company;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Christian
 *
 * Diese Klasse liest eine Excel-Datei und extrahiert Informationen über Unternehmen.
 * Sie verwendet Apache POI, um die Excel-Datei zu verarbeiten und eine Liste
 * von Company-Objekten zu erstellen.
 */
public class CompanyReader extends FileReader<Company> {

    private String filename;

    public CompanyReader(String filename) {
        super(filename);
    }

    @Override
    public ArrayList<Company> parse() {
        ArrayList<Company> companyArrayList = new ArrayList<>();
        try (Workbook workbook = openWorkbook()) {
            Sheet sheet = workbook.getSheetAt(0);
            Map<Integer, String> headers = getHeaders(sheet);
            ArrayList<Map<String, String>> rows = extractRows(sheet, headers);

            for (Map<String, String> lineMap : rows) {
                String nr = lineMap.get("Nr.");
                String companyName = lineMap.get("Unternehmen");
                String fieldOfStudy = lineMap.get("Fachrichtung");
                int max = parseInteger(lineMap.get("Max. Teilnehmer"));
                int intMaxE = parseInteger(lineMap.get("Max. Veranstaltungen"));
                int earliest = parseEarliestSlot(lineMap.get("Frühester Zeitpunkt"));

                Company company = new Company(nr, companyName, fieldOfStudy, max, intMaxE, earliest);
                companyArrayList.add(company);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return companyArrayList;
    }

    private int parseInteger(String value) {
        return (value == null) ? 0 : Integer.parseInt(value);
    }

    private int parseEarliestSlot(String slot) {
        if (slot == null) return 1;  // Default value "A"
        int result;
        switch (slot) {
            case "A":
                result = 1;
                break;
            case "B":
                result = 2;
                break;
            case "C":
                result = 3;
                break;
            case "D":
                result = 4;
                break;
            case "E":
                result = 5;
                break;
            default:
                result = 1; // Default value
                break;
        }
        return result;
    }
}
