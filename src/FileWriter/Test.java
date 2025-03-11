package FileWriter;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        FileWriter writer = new CompanyTimeTableWriter();
        writer.write();
    }
}
