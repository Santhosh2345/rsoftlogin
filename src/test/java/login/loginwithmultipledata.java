package login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class loginwithmultipledata {
	
	 public static WebDriver driver;
	 
	 public static boolean methodexecutionstatus=false,methodreturnvalue;
	
     public static String url="",companyname="TESTCRMONE",consolidatedoutput=""; 
	 
	 public static String correctusername="manickatcrm",correctpassword="Rsoft@123",wrongusername="adminrsoft",wrongpassword="RSoft!@125",returnurl="",logoutstatus="";
	 
	 
	 public static String readingexcelfile="C:\\RSoft\\0.0 LoginandLogout\\Login Data.xlsx";
	 
	 public static int row;
	 
	 
	 
	 @FindBy(name="companyname")
	 public static WebElement admincompanyname;
	 
	
	 @FindBy(name="email")
	 public static WebElement adminusername;
	 
	 @FindBy(name="password")
	 public static WebElement adminpassword;
	 
	 @FindBy(xpath="//button[contains(text(),'Login')]")
	 public static WebElement adminloginbutton;
	
	 public static WebElement rimagebutton;
	 
	 @FindBy(linkText="Logout")
	 public static WebElement logouttext;
	
	 
	
  @Test
  public void f() throws InterruptedException, IOException {
	  
	  String companyname="",username="",password="";
	  
	  boolean fileupdatingstatus;
	  
	  
	  clearexcelsheet();
	  
	  
	  outputtextfileclear();
	  
	  
	  FileInputStream fs = new FileInputStream(readingexcelfile);
	  
	  XSSFWorkbook workbook = new XSSFWorkbook(fs);
	  XSSFSheet sheet = workbook.getSheetAt(0);
		   
	  row = sheet.getLastRowNum();
	  
	  consolidatedoutput = consolidatedoutput + "\n";
      consolidatedoutput = consolidatedoutput + "Final Ouput" + "\n";
      consolidatedoutput = consolidatedoutput + "-----------" + "\n" + "\n";
      consolidatedoutput = consolidatedoutput + "Number of Records in Excel sheet = " + row  + "\n" + "\n";
      
      
      for(int i=1;i<=row;i++) {
			 
    	  companyname="";
    	  username="";
    	  password="";
    	  
 		 //System.out.println("i value = " + i);	 
 			 
 			 try {
 				 XSSFRow rowvalue = sheet.getRow(i);
 				 
 				 for(int j=1;j<rowvalue.getLastCellNum();j++) {
 					 
 						 try {
 							 XSSFCell cell = rowvalue.getCell(j);
 							 if (j==1)
 							 companyname = cell.toString();
 							 else if (j==2)
 							 username = cell.toString();
 							else if (j==3)
 	 							 password = cell.toString();
 						
 						 }	 
 						 catch(java.lang.NullPointerException e) {
							  //System.out.println(e.toString());
							  continue;
						 }	 
 				 }
 				 
 				 
 				fileupdatingstatus= checklogin(companyname,username,password,i);
 				
 				if (fileupdatingstatus==true)
 					consolidatedoutput = consolidatedoutput + "Login Record " + i + " updated successfully in excelsheet" + "\n"; 	
 				else
 					consolidatedoutput = consolidatedoutput + "Login Record " + i + " updation failed in excelsheet" + "\n";
 			 }
 			 catch(java.lang.NullPointerException e) {
				  //System.out.println(e.toString());
				  continue;
			 }
 	
      }
      
      System.out.println(consolidatedoutput);
      
  }

  
  
  public void clearexcelsheet() throws IOException {
		 
		 FileInputStream fs = new FileInputStream(readingexcelfile);
		 
			//Creating a workbook
			 XSSFWorkbook workbook = new XSSFWorkbook(fs);
			 XSSFSheet sheet = workbook.getSheetAt(0);
			   
			 row = sheet.getLastRowNum();

			 for(int i=1;i<=row;i++) {
				 
			 //System.out.println("i value = " + i);	 
				 
				 try {
					 XSSFRow rowvalue = sheet.getRow(i);
					 
					 for(int j=1;j<rowvalue.getLastCellNum();j++) {
						 
						 if (j==4) {
							 
							 try {
								 XSSFCell cell = rowvalue.getCell(j);
								 if (cell.toString().isBlank()==false) {
								    cell.setCellValue("");
								 	FileOutputStream fos = new FileOutputStream(readingexcelfile);
								 	workbook.write(fos);
								 	fos.close();
								 }
								 else
									 continue;
							 }
							 catch(Exception e) {}
						 }
					 }	 
				 }
				 catch(Exception e) {}
			 }	 
		 
	 }
  
  
  
  
  
  
  
  
  
  
  public void outputtextfileclear() {
		
		 try{

			    FileWriter fw = new FileWriter("C:\\RSoft\\0.0 Login\\Loginandlogout.txt", false);

			    PrintWriter pw = new PrintWriter(fw, false);

			    pw.flush();

			    pw.close();

			    fw.close();

			    }catch(Exception exception){

			        System.out.println("Exception have been caught");

			    }
		
		
	}
	
  
  
    
  
  public boolean checklogin(String cname,String uname,String pwd,int currentrow) throws InterruptedException {
	  
  	  
  boolean filewritingstatus=false;
  
  String finalresult="";
	  
      
        	   
  
       if (admincompanyname.getAttribute("value").isBlank()) {}
       else {
             
    	   admincompanyname.clear();
    	   Thread.sleep(1000);
       }   
  	  
  	   
	   admincompanyname.sendKeys(cname);
	   Thread.sleep(1000);
	   
	   //System.out.println("time called");
	   
	   	   
	   adminusername.sendKeys(uname);
	   Thread.sleep(1000);
	   
	      
	   adminpassword.sendKeys(pwd);
	   Thread.sleep(1000);
	   	
	   adminloginbutton.click();
	   Thread.sleep(1000);  
	   
	   try {
		   
		   driver.findElement(By.linkText("All")).isDisplayed();
		   
		   rimagebutton = driver.findElement(By.xpath("//a[@class='dropdown-toggle nav-link dropdown-user-link']"));
		   rimagebutton.click();
		   Thread.sleep(1000);
		   
		   logouttext.click();
		   Thread.sleep(1000);
		   
		   returnurl = driver.getCurrentUrl();
		   
		   
		   if (returnurl.equals(url))
			   finalresult = finalresult + "Application Log in and Log out Succeded";
		   else
			   finalresult = finalresult + "Application Log in and Log out Failed";
		   	   
		   
	   }
	   catch(NoSuchElementException e) {
		   
		   finalresult = finalresult + "Application Log in and Log out Failed"; 
	   } 
	   
	 
	   
	 
		  
		  try {
			  
		    	FileInputStream fs = new FileInputStream(readingexcelfile);
			 
		    	//Creating a workbook
		    	XSSFWorkbook workbook = new XSSFWorkbook(fs);
		    	XSSFSheet sheet = workbook.getSheetAt(0);
		    	
		    	//System.out.println("Current row value = " + currentrow);
			 
		    	XSSFRow rowvalue = sheet.getRow(currentrow);
		  
		    	XSSFCell cell = rowvalue.getCell(4);
		     	
		    	cell.setCellValue(finalresult);
		    	
		    	FileOutputStream fos = new FileOutputStream(readingexcelfile);
			    workbook.write(fos);
			    fos.close();

			    filewritingstatus=true;
			    
		    }
		    catch(Exception e) {
		    	filewritingstatus=false;
		    }
	   
          return filewritingstatus;
	   
  }
 
  
  public loginwithmultipledata() {

	  url = geturl();	 
	  
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  PageFactory.initElements(driver, this);
	  
	  driver.get(url);
	  driver.manage().window().maximize();
	  
	   
  }	 
 
  
 public String geturl() {
	 
	  String url;
	  
	  Properties prop = new Properties();
	  
	  InputStream input = null;
	  
	  input = getClass().getClassLoader().getResourceAsStream("config.properties");
	 
	  try {
		  
		  FileInputStream fis = new FileInputStream("C:\\RSoft\\0.0 LoginandLogout\\src\\test\\java\\login\\config.properties");
		  prop.load(fis);
	  }
	  catch(IOException e) {
		  e.printStackTrace();
	  }
	  
	  url = prop.getProperty("url");
	  
	  return url;
	  
  }
  

  
}
