package FileWriter;

import DataWrapper.Company;
import FileReader.CompanyReader;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {


        String filename3 = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\2024\\Veranstaltungen.xlsx";
        CompanyReader coReader = new CompanyReader(filename3);
        ArrayList<Company> companies =  coReader.parse();

        FileWriter<Company> writer = new CompanyTimeTableWriter();
        writer.write(companies);
    }
}
