package FileWriterHelper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Christian
 */
public class ExcelCell {

    private String value;

    private CellStyle style;

    private Font font;

    private int row;

    private int col;

    public ExcelCell(String cellValue, int row, int col, XSSFWorkbook workbook) {
        setValue(cellValue);
        setRow(row);
        setCol(col);
        setFont(workbook.createFont());
        setStyle(workbook.createCellStyle());
        applyFont("Calibri");
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    public void applyTextHorizontal(HorizontalAlignment alignment) {
        style.setAlignment(alignment);
    }

    public void applyTextVertical(VerticalAlignment alignment) {
        style.setVerticalAlignment(alignment);
    }

    public void applyTextCenter() {
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    public void applyFontSize(short points) {
        font.setFontHeightInPoints(points);
        style.setFont(font);
    }

    public void applyBold() {
        font.setBold(true);
        style.setFont(font);
    }

    public void applyUnderline() {
        font.setUnderline(Font.U_SINGLE);
        style.setFont(font);
    }

    public void applyFont(String fontName) {
        font.setFontName(fontName);
        style.setFont(this.font);
    }

    public enum BorderPosition {
        TOP, BOTTOM, LEFT, RIGHT
    }

    public void setBorder(BorderPosition pos, BorderStyle bStyle) {
        CellStyle style = this.style;
        switch (pos) {
            case TOP -> {
                style.setBorderTop(bStyle);
            }
            case BOTTOM -> {
                style.setBorderBottom(bStyle);
            }
            case LEFT -> {
                style.setBorderLeft(bStyle);
            }
            case RIGHT -> {
                style.setBorderRight(bStyle);
            }
        }
    }

    public void applyAllBorder(BorderStyle bStyle) {
        style.setBorderTop(bStyle);
        style.setBorderBottom(bStyle);
        style.setBorderRight(bStyle);
        style.setBorderLeft(bStyle);
    }

    public void applyBackgroundColor(IndexedColors color) {
        this.style.setFillForegroundColor(color.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    public void applyToSheet(Sheet sheet) {
        Row row = sheet.getRow(this.row);
        if (row == null) row = sheet.createRow(this.row);

        Cell cell = row.createCell(this.col);
        cell.setCellValue(this.value);
        cell.setCellStyle(this.style);
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

}
