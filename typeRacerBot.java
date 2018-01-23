package typeRacer;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class typeRacerBot {

	static WebDriver driver;
	JavascriptExecutor jse;

	Wait<WebDriver> waitLoad = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
			.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
	
	public void invokeBrowser(String usr, String pwd) {

		try {
			
			driver.get("http://play.typeracer.com/");
			signIn(usr, pwd);
			practice();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void signIn(String usr, String pwd) {
		
		try {
			WebElement signin = driver.findElement(By.cssSelector("table[class=\"MainUserInfoEditor\"] a[class]"));
			waitLoad.until(ExpectedConditions.visibilityOf(signin));
			signin.click();
			
			WebElement username = driver.findElement(By.cssSelector("input[name=\"username\"]"));
			waitLoad.until(ExpectedConditions.visibilityOf(username));
			username.click();
			username.clear();
			username.sendKeys(usr);
			
			WebElement password = driver.findElement(By.cssSelector("input[name=\"password\"]"));
			password.click();
			password.clear();
			password.sendKeys(pwd);
			password.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public void practice() {
		
		try {
			
			
			WebElement practice = driver.findElement(By.xpath("//a[text()='Practice']"));
			practice.click();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) throws Exception {

		File src = new File("P:\\Documents\\Excel_Data\\Selenium_Data.xlsx");		// apache POI on windows
//		File src = new File("");		// apache POI on mac
		
		FileInputStream fis = new FileInputStream(src);								// pass src into fis 
		XSSFWorkbook wb = new XSSFWorkbook(fis);									// this line loads the workbook (XML SpreadSheet Format)
		XSSFSheet sheet = wb.getSheetAt(1);											// accesses sheet 2 on excel
//		XSSFSheet sheet = wb.getSheetAt(0);											// accesses sheet 1 on excel
		
		String username = sheet.getRow(0).getCell(0).getStringCellValue();			//	typetypercer
		String password = sheet.getRow(0).getCell(1).getStringCellValue();
		
//		System.out.println(username + ' ' + password);

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\huangti1\\selenium\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", "");			// mac chromedriver
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window(); // maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // synchronize the lines of code + page --
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); // wait for the page to load

		typeRacerBot myObj = new typeRacerBot();

		myObj.invokeBrowser(username, password);
		
		wb.close();
		fis.close();
	}
}
