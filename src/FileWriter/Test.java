package FileWriter;

import Algorithm.TimeTable;
import Assigner.Assigner;
import Assigner.EventAssigner;
import Assigner.Tuple;
import DataWrapper.ClassRoom;
import DataWrapper.Company;
import DataWrapper.Student;
import FileReader.ClassRoomReader;
import FileReader.CompanyReader;
import FileReader.StudentReader;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {


        String filename3 = "ressources/import/2024/Veranstaltungen.xlsx";
        CompanyReader coReader = new CompanyReader(filename3);
        ArrayList<Company> companies =  coReader.parse();

        String filename4 = "ressources/import/2024/Wahl BOT 23_24.xlsx";
        StudentReader studentReader = new StudentReader(filename4);
        ArrayList<Student> students = studentReader.parse();

        String filename2 = "ressources/import/2024/Raeume.xlsx";
        ClassRoomReader cReader = new ClassRoomReader(filename2);
        ArrayList<ClassRoom> classRooms =  cReader.parse();


        Assigner assigner = new Assigner(students, companies, classRooms);
        assigner.assign();

        TimeTable algo = new TimeTable(companies, classRooms);
        algo.assign();

        EventAssigner eventAssigner = new EventAssigner();
        eventAssigner.assignPupil(companies);

        for(Student student : students){
            ArrayList<Integer> wishes = new ArrayList<>(student.getChoices());
            for(Tuple tuple : student.getZuweisungsListe()){
                for(int i = 0; i < student.getChoices().size(); i++){
                    if(Integer.parseInt(tuple.getWunsch()) == student.getChoices().get(i)){
                        wishes.remove(student.getChoices().get(i));
                    }
                }
            }
            student.setNotFulFilled(wishes);
        }
        eventAssigner.assignRestWishes(students, companies, 0);
        eventAssigner.assignRestWishes(students, companies, 1);

        FileWriter<Student> studentFileWriter = new StudentScheduleWriter();
        studentFileWriter.write(students);

        FileWriter<Company> writer = new CompanyTimeTableWriter();
        writer.write(companies);

        FileWriter<Company> writer2 = new AttendanceListWriter();
        writer2.write(companies);
    }
}
