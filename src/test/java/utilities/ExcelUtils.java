package utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import stepdefinition.UpcomingBikes;
import testclasses.Tc1_UpcomingBikesTest;

public class ExcelUtils 
{
	public static FileOutputStream file_path;
	
	public static void excelOutput(List<String> getmodelNames, List<String> priceValues, List<String> launchDates) throws IOException
	{
		file_path=new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\ExcelOutput.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet1=workbook.createSheet("New Bike Details");	
		XSSFSheet sheet2 = workbook.createSheet("PopularCars");
		int row =0;
		for(int i=0;i<getmodelNames.size();i++) 
		{
			if(Tc1_UpcomingBikesTest.index_values.contains(i)) 
			{
				
				XSSFRow rows=sheet1.createRow(row);			
				rows.createCell(0).setCellValue(getmodelNames.get(i));
				rows.createCell(1).setCellValue(priceValues.get(i));
				rows.createCell(2).setCellValue(launchDates.get(i));
				row++;
				System.out.println(getmodelNames.get(i)+" "+priceValues.get(i)+" "+launchDates.get(i));
			}
			else if(UpcomingBikes.index_values.contains(i))
			{
				XSSFRow rows=sheet1.createRow(row);			
				rows.createCell(0).setCellValue(getmodelNames.get(i));
			    rows.createCell(1).setCellValue(priceValues.get(i));
				rows.createCell(2).setCellValue(launchDates.get(i));
				row++;
				System.out.println(getmodelNames.get(i)+" "+priceValues.get(i)+" "+launchDates.get(i));
			}
			else
			{
				
			}
		}
		workbook.write(file_path);
    	workbook.close();
    	file_path.close();
	}
	
	
	public static void excelOutput(List<String> popularcars) throws IOException
	{
		String path=System.getProperty("user.dir")+"\\testdata\\ExcelOutput.xlsx";
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("PopularCars");
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Popular Cars Used in Chennai are listed below : ");
		int size = popularcars.size();
		for(int i =0; i<size;i++)
    	{
    		row = sheet.createRow(1+i);
    		row.createCell(0).setCellValue(popularcars.get(i));
    		System.out.println(popularcars.get(i));
    	}	
		file_path=new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\ExcelOutput.xlsx");
		workbook.write(file_path);
    	workbook.close();
    	file_path.close();
	}
	
}
