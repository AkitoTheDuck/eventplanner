package FileWriterHelper;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelCell {

    private String value;

    private CellStyle style;

    private Font font;

    private int row;

    private int col;

    private XSSFWorkbook workbook;

    public enum BorderWidth {
        THIN, THICK, MEDIUM
    }

    public ExcelCell(String cellValue, int row, int col, XSSFWorkbook workbook) {
        setValue(cellValue);
        setRow(row);
        setCol(col);
        setWorkbook(workbook);
        setFont(workbook.createFont());
        setStyle(workbook.createCellStyle());
    }

    public void applyFont(String fontName, boolean bold, boolean underline) {
        Font font = this.font;
        font.setBold(bold);
        font.setFontName(fontName);
        if( underline) {
            font.setUnderline(Font.U_SINGLE);
        }
        this.style.setFont(this.font);
    }

    public void applyCellBorder (boolean top, boolean bottom, boolean left, boolean right, BorderWidth wTop, BorderWidth wBottom, BorderWidth wLeft, BorderWidth wRight) {
        CellStyle style = this.style;
        BorderStyle bStyle = BorderStyle.NONE;

        if(top) {
            switch (wTop) {
                case BorderWidth.MEDIUM -> bStyle = BorderStyle.MEDIUM;
                case BorderWidth.THICK -> bStyle = BorderStyle.THICK;
                case BorderWidth.THIN -> bStyle = BorderStyle.THIN;
            }
            style.setBorderTop(bStyle);
        }

        if(bottom) {
            switch (wBottom) {
                case BorderWidth.MEDIUM -> bStyle = BorderStyle.MEDIUM;
                case BorderWidth.THICK -> bStyle = BorderStyle.THICK;
                case BorderWidth.THIN -> bStyle = BorderStyle.THIN;
            }
            style.setBorderBottom(bStyle);
        }

        if(left) {
            switch (wLeft) {
                case BorderWidth.MEDIUM -> bStyle = BorderStyle.MEDIUM;
                case BorderWidth.THICK -> bStyle = BorderStyle.THICK;
                case BorderWidth.THIN -> bStyle = BorderStyle.THIN;
            }
            style.setBorderLeft(bStyle);
        }

        if(right) {
            switch (wRight) {
                case BorderWidth.MEDIUM -> bStyle = BorderStyle.MEDIUM;
                case BorderWidth.THICK -> bStyle = BorderStyle.THICK;
                case BorderWidth.THIN -> bStyle = BorderStyle.THIN;
            }
            style.setBorderRight(bStyle);
        }
    }

    public void applyBackgroundColor() {
        this.style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
    }


    /// GETTER +  SETTER
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CellStyle getStyle() {
        return style;
    }

    public void setStyle(CellStyle style) {
        this.style = style;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }
}
