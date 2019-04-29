package poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import java.io.*;

/**
 * @author Misaki
 * @date 2019/4/29/029 16:09
 **/
public class DemoOne {

    public static void main(String[] args) {
        DemoOne demoOne = new DemoOne();
        //demoOne.readTwo("C:\\Users\\Administrator\\Desktop\\善融P2P系统第七期工作量统计及价格计划_v180416.xls");
        demoOne.createNewExcel();
    }

    public void readTwo(String fileName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            Workbook sheets = WorkbookFactory.create(fileInputStream);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet1 = sheets.getSheetAt(0);
            for (Row row : sheet1) {
                for (Cell cell : row) {
                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                    System.out.print(cellRef.formatAsString());
                    System.out.print(" - ");

                    String text = formatter.formatCellValue(cell);
                    //System.out.println("formatter.formatCellValue(cell)"+text);

                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getRichStringCellValue().getString() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.println(cell.getDateCellValue() + "\t");
                            } else {
                                System.out.println(cell.getNumericCellValue() + "\t");
                            }
                            break;
                        case BOOLEAN:
                            System.out.println(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.println(cell.getCellFormula() + "\t");
                            break;
                        case BLANK:
                            System.out.println("\t");
                            break;
                        default:
                            System.out.println("\t");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert fileInputStream != null;
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createNewExcel(){
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(2);
        cell.setCellValue("Use \n with word wrap on to create a new line");

        //to enable newlines you need set a cell styles with wrap=true
        CellStyle cs = wb.createCellStyle();
        cs.setWrapText(true);
        cell.setCellStyle(cs);

        //increase row height to accommodate two lines of text
        row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

        //adjust column width to fit the content
        sheet.autoSizeColumn(2);

        try (OutputStream fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\ooxml-newlines.xlsx")) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
