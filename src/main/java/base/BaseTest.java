package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.beust.jcommander.Parameter;


public class BaseTest {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	
//	@Parameters("url")
	@BeforeClass
	public void DriverSetup() {
		driver = WebDriverSetUp.setupDriver("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
	}
	
	@BeforeMethod
	public static void closePopup() {
		String baseUrl = "https://www.makemytrip.com/";
		driver.get(baseUrl); // Open MakemyTrip Home page.
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals(baseUrl)) {
            System.out.println("Remarks: Failed to navigate to "+baseUrl+"Current URL is: "+currentUrl);
        }
        else {
            System.out.println("Remarks: Successfully navigated to "+ baseUrl);        	
        }
        driver.findElement(By.xpath("//span[@data-cy='closeModal']")).click(); // Close popup
	}
	

	
	//code added by Poulomi
	//added by Poulomi again
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
