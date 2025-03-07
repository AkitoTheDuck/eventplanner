package FileReader;

import DataWrapper.ClassRoom;
import DataWrapper.DataWrapper;
import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;

public abstract class FileReader <E extends DataWrapper> {

    public abstract ArrayList<E> parse();

    protected static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf((int)cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "";
        }
    }
}
