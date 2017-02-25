package org.rec.studentdetails.reader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.NumberToTextConverter;

public class XLSReader {
	public List<List<String>> read(String fileName) throws Exception {
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row;
		Iterator<?> rows = sheet.rowIterator();
		List<List<String>> students = new ArrayList<>();
		while (rows.hasNext()) {
			row = (HSSFRow) rows.next();
			Iterator<?> cells = row.cellIterator();
			List<String> student = readRow(cells);
			students.add(student);
		}
		return students;
	}
	
	public List<String> readRow(Iterator<?> cells) {
		ArrayList<String> vals = new ArrayList<>();
		HSSFCell cell;
		while (cells.hasNext()) {
			cell = (HSSFCell) cells.next();
			if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING || cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				vals.add(getString(cell));
			}
		}
		return vals;
	}
	
	public static String getString(HSSFCell cell) {
		if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
			return cell.getStringCellValue();
		else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			return NumberToTextConverter.toText(cell.getNumericCellValue());
		else
			return null;
	}
}