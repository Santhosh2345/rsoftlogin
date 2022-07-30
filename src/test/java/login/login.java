package login;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class login {
	
	 public static WebDriver driver;
	 
	 public static boolean methodexecutionstatus=false,methodreturnvalue;
	
     public static String url="http://rdot.in/public/login",companyname="TESTCRMONE",finalresult=""; 
	 
	 public static String correctusername="manickatcrm",correctpassword="Rsoft@123",wrongusername="adminrsoft",wrongpassword="RSoft!@125",returnurl="",logoutstatus="";
	 
	 
	 @FindBy(name="companyname")
	 public static WebElement admincompanyname;
	 
	
	 @FindBy(name="email")
	 public static WebElement adminusername;
	 
	 @FindBy(name="password")
	 public static WebElement adminpassword;
	 
	 @FindBy(xpath="//button[contains(text(),'Login')]")
	 public static WebElement adminloginbutton;
	
		 
	 @FindBy(xpath="//body/nav[1]/div[3]/div[1]/div[1]/ul[2]/li[4]/a[1]/span[1]/img[1]")
	 public static WebElement rimagebutton;
	 
	 @FindBy(linkText="Logout")
	 public static WebElement logouttext;
	
	 
	
  @Test
  public void f() throws InterruptedException {
	  
	  outputtextfileclear();
	  
	  finalresult = finalresult + "\n" + "First Test" + "\n";
	  finalresult = finalresult + "----------" + "\n" +"\n";
	  finalresult = finalresult + "Login with correctusername and correct password" + "\n";	  
	  finalresult = finalresult + "-----------------------------------------------" + "\n"+"\n";
	  
	  
	  checklogin(correctusername,correctpassword);
	  Thread.sleep(2000);
	  
	  finalresult = finalresult + "\n" + "Second Test";
	  finalresult = finalresult + "\n"+ "----------" + "\n" +"\n";
	  finalresult = finalresult + "Login with correctusername and wrong password" + "\n";	  
	  finalresult = finalresult + "---------------------------------------------" + "\n"+"\n";
	  
      checklogin(correctusername,wrongpassword);
      Thread.sleep(1000);
	  
      admincompanyname.clear();
      adminusername.clear();
      adminpassword.clear();
	  
      finalresult = finalresult + "\n" + "Third Test";
	  finalresult = finalresult + "\n"+ "----------" + "\n" +"\n";
	  finalresult = finalresult + "Login with wrongusername and correct password" + "\n";	  
	  finalresult = finalresult + "---------------------------------------------" + "\n"+"\n";
	  
      checklogin(wrongusername,correctpassword);
      Thread.sleep(1000);
      
      admincompanyname.clear();
      adminusername.clear();
      adminpassword.clear();
      
            
      finalresult = finalresult + "\n" + "Fourth Test";
	  finalresult = finalresult + "\n"+ "----------" + "\n" +"\n";
	  finalresult = finalresult + "Login with wrongusername and wrong password" + "\n";	  
	  finalresult = finalresult + "-------------------------------------------" + "\n"+"\n";
	  
      checklogin(wrongusername,wrongpassword);
      Thread.sleep(1000);
      
      driver.quit();
      Thread.sleep(1000);
      
      System.out.println(finalresult);
	   
	   
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
	
  
  
    
  
  public void checklogin(String uname,String pwd) throws InterruptedException {
	  
  	  
  boolean logincheckstatus=false;	  
	  
        
	   admincompanyname.sendKeys(companyname);
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
		   
		   rimagebutton.click();
		   Thread.sleep(1000);
		   
		   logouttext.click();
		   Thread.sleep(1000);
		   
		   returnurl = driver.getCurrentUrl();
		   
		   if (returnurl.equals(url))
			   logoutstatus = "Logout Successfully verified";
		   else
			   logoutstatus = "Logout verification failed";
		   
		   
		   System.out.println();
		   
		   
		   finalresult = finalresult + "Login Status" + "\n";
		   finalresult = finalresult + "------------" + "\n";
		   finalresult = finalresult + "Login Username = " + uname + "\n";
		   finalresult = finalresult + "Login Password = " + pwd + "\n";
		   finalresult = finalresult + "Application Log in Succeded" + "\n";
		   finalresult = finalresult + "Login Successfully verified" + "\n";
		   finalresult = finalresult + logoutstatus + "\n" ;
		   
	   }
	   catch(NoSuchElementException e) {
		   
           finalresult = finalresult + "Login Status" + "\n";
		   finalresult = finalresult + "------------" + "\n";
		   finalresult = finalresult + "Login Username = " + uname + "\n";
		   finalresult = finalresult + "Login Password = " + pwd + "\n";
		   finalresult = finalresult + "Application Log in Failed" + "\n";
		   finalresult = finalresult + "Login Successfully verified" + "\n";
	   } 

	   
	
	   
	   
  }
 
  
  public login() {

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
