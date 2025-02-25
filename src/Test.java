import DataWrapper.Student;
import FileReader.StudentReader;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        //String filename = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\IMPORT BOT2_Wahl.xlsx";
        String filename = "U:\\Documents\\java_workspace\\Eventplanner\\ressources\\import\\IMPORT_BOT2_Wahl.csv";

        StudentReader reader = new StudentReader(filename, ";");
        ArrayList<Student> students =  reader.parse();

        int p = 0;

    }

}
