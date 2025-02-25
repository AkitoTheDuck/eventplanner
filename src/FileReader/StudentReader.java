package FileReader;

import DataWrapper.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StudentReader {

    private String filename;

    private String delimiter;


    public StudentReader(String filename, String delimiter) {
        this.filename = filename;
        this.delimiter = delimiter;
    }

    public ArrayList<Student> parse() {
        BufferedReader reader = null;
        ArrayList<Student> studentArrayList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(this.filename));
            String line;
            if ((line = reader.readLine()) != null) {

                String[] headers = line.split(this.delimiter);

                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(this.delimiter);

                    Map<String, String> lineMap = new HashMap<>();

                    for (int i = 0; i < headers.length; i++) {
                        if (i < values.length) {
                            lineMap.put(headers[i].trim(), values[i].trim());
                        } else {
                            lineMap.put(headers[i].trim(), "");
                        }
                    }

                    if(Objects.equals(lineMap.get("Wahl 1"), "")) {

                    }

                    try {
                        Student student = new Student(
                                lineMap.get("\uFEFFKlasse"),
                                lineMap.get("Name"),
                                lineMap.get("Vorname"),
                                Integer.parseInt(lineMap.get("Wahl 1")),
                                Integer.parseInt(lineMap.get("Wahl 2")),
                                Integer.parseInt(lineMap.get("Wahl 3")),
                                Integer.parseInt(lineMap.get("Wahl 4")),
                                Integer.parseInt(lineMap.get("Wahl 5")),
                                Integer.parseInt(lineMap.get("Wahl 6"))
                            );

                        studentArrayList.add(student);
                    } catch (NumberFormatException e) {}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return studentArrayList;
    }

}
