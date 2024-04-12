package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;

public class dataProvider {
	
	@DataProvider(name = "form_to_data")
	//@Test
	public String[][] excel() throws IOException
	{
		File excelPath = new File("C:\\Users\\2318646\\eclipse-workspace\\Hackathon_Project\\testData\\HackathonData.xlsx");
		FileInputStream fis = new FileInputStream(excelPath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet s = wb.getSheet("Sheet1");
		int total_rows = s.getLastRowNum();
		DataFormatter formatter = new DataFormatter();
		String data[][] = new String[total_rows][9+1];
		for(int r = 1 ;r<=total_rows;r++)
		{
			XSSFRow row = s.getRow(r);
			for(int c = 0; c<9;c++)
			{
				XSSFCell cell = row.getCell(c);
				data[r-1][c]=formatter.formatCellValue(cell);
			}
			data[r-1][9] = String.valueOf(r);
		}
		/*
		for(int i = 0 ;i< data.length;i++)
		{
			for(int j = 0 ;j< data[i].length;j++)
			{
				System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
		*/
		wb.close();
		return data;
	}
}
