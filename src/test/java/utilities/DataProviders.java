package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//DataProvider
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{  //This String is a 2 dimensional array which fetches data from the excel
		//Get the path of the excel
		String path=".\\testData\\OpenCart_LoginData.xlsx"; //Taking xl file from testData
		//NOTE: ".\\" DENOTES CURRENT PROJECT LOCATION
		
		//Create an Object for ExcelUtility
		ExcelUtility xlutil = new ExcelUtility(path); 
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcols]; //Created for two dimension array which can store
		
		
		for (int i=1;i<=totalrows;i++) { //1 //read the data from xl storing in two dimensional array //i Starting with 1 cause of avoiding the header
			for (int j=0;j<totalcols;j++) { //0 //i is rows j is cols
				logindata[i-1][j] = xlutil.getCellData("Sheet1",i,j); //1, 0		
				//as index starts from 0 we have put [i-1]
			}			
		}
		return logindata; //Returning two dimension array
		
	}
	
	//Data providers can be added in this class itself if it is needed.
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4

}
