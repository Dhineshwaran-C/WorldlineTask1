package Task1.Task1;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	XSSFWorkbook wb;
	XSSFSheet sheet;
	
	public ReadExcelFile(String excelPath) {
		try {
			File src = new File(excelPath);
			FileInputStream inp = new FileInputStream(src);
			wb = new XSSFWorkbook(inp);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public String getData(int sheetnumber, int row, int column) {
		sheet = wb.getSheetAt(sheetnumber);
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	public int getRowCount(int sheetnumber) {
		int rows = wb.getSheetAt(sheetnumber).getLastRowNum();
		return rows;
	}
	
	public int getColCount(int sheetnumber) {
		Row row = wb.getSheetAt(sheetnumber).getRow(0);
		int columns = 0;

		if (row != null) {
		    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
		    for (int i = 0; i < physicalNumberOfCells; i++) {
		        Cell cell = row.getCell(i);
		        if (cell != null && cell.getCellType() != CellType.BLANK) {
		            columns++;
		        }
		    }
		}
		return columns;
	}
}

