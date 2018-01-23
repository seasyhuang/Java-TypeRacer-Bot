package typeRacer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class typeRacerBot {

	static WebDriver driver;
	JavascriptExecutor jse;

	Wait<WebDriver> waitLoad = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
			.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
	
	public void invokeBrowser() {

		try {
			
			driver.get("http://play.typeracer.com/");
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		// System.out.println(data.toString());

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\huangti1\\selenium\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\huangti1\\selenium\\chromedriver.exe");			// mac chromedriver
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window(); // maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // synchronize the lines of code + page --
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); // wait for the page to load

		typeRacerBot myObj = new typeRacerBot();

		myObj.invokeBrowser();
	}
}
