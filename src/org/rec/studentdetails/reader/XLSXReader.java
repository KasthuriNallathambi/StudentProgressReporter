package org.rec.studentdetails.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReader {
	public List<List<String>> read(String fileName) throws Exception {
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		Iterator<?> rows = sheet.rowIterator();
		List<List<String>> students = new ArrayList<>();
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			Iterator<?> cells = row.cellIterator();
			List<String> student = readRow(cells);
			students.add(student);
		}
		return students;
	}
	
	public List<String> readRow(Iterator<?> cells) throws IOException {
		ArrayList<String> vals = new ArrayList<>();
		XSSFCell cell;
		while (cells.hasNext()) {
			cell = (XSSFCell) cells.next();
			if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING || cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				vals.add(getString(cell));
			}
		}
		return vals;
	}
	
	public static String getString(XSSFCell cell) {
		if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING && !cell.getStringCellValue().isEmpty() && cell.getStringCellValue().length() > 0 )
			return cell.getStringCellValue();
		else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
			return String.valueOf(cell.getNumericCellValue());
		else 
			return null;
	}
}