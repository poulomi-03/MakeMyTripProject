package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class CabBookingPage {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	public CabBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/* Page Objects */
	
	//Button to disable the Login pop-up
	@FindBy(xpath="//span[@class='commonModal__close']") WebElement closePopUpBtn;
	@FindBy(xpath="//span[text()='Cabs']") WebElement cabsSection;
	@FindBy(xpath="//li[text()='Outstation One-Way']") WebElement tripTypeButton;
	@FindBy(xpath="//label[@for='fromCity']") WebElement fromCityField;
	@FindBy(xpath="//span[text()='Delhi']") WebElement fromCitySuggestion;
	@FindBy(xpath="//label[@for='toCity']") WebElement toCityField;
	@FindBy(xpath="//div[@role='combobox']/input") WebElement destinationInput;
	@FindBy(xpath="//li[@role='option']") WebElement toplaceSuggestion;
	@FindBy(xpath="//label[@for='departure']") WebElement departureDateField;
	@FindBy(xpath="//label[@for='pickupTime']") WebElement pickUpTimeField;
	@FindBy(xpath="//p[contains(@data-cy, 'Search')]/a") WebElement searchButton;
	@FindBy(xpath="//img[@alt='Close'") WebElement suggestedPackagePopup;
	@FindBy(xpath="//span[text()='SUV']") WebElement cabTypeFilter;
	@FindBy(xpath="//div[@class='applyBtn']") WebElement applyTimeButton;
	@FindBy(xpath="//span[contains(@class, 'cabDetailsCard') and contains(text(), '₹')]") List<WebElement> baseAndOtherCharges;
//	@FindBy(xpath="") WebElement ;
	
	
	/* Action methods */
	
	public void disableLoginPopUp() {
		closePopUpBtn.click();
	}

	public void selectSection(String section) {
		cabsSection.click();		
	}
	
	public void selectTripType(String tripType) {
		tripTypeButton.click();
	}
	
	public void clickFromField() {
        fromCityField.click();
	}
	
	public void selectCityFromSuggestion(String city) {
        fromCitySuggestion.click();
	}
	
	public void clickToField() {
        try{
        	toCityField.click();
        } catch (Exception e) {
        }
	}
	
	public void enterDestination(String destination) {
		destinationInput.sendKeys(destination);
	}
	
	public void selectDestinationFromSuggestion(String destination) {	
        By manaliSuggestionLocator = By.xpath("//li[@role='option']"); //locator for 1st suggestion
        // 1. Wait until the element with the specified locator contains the desired text
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(manaliSuggestionLocator, destination));
        // click 1st suggestion
        driver.findElement(manaliSuggestionLocator).click();
	}
	
	public void clickdeparture(){	
        // departure date field
        driver.findElement(By.xpath("//label[@for='departure']")).click();
	}
	
	public void clickPickupTime() {
        // click pickUp-Time
        driver.findElement(By.xpath("//label[@for='pickupTime']")).click();	
	}
	
	public void search() {
        // Click Search
        driver.findElement(By.xpath("//p[contains(@data-cy, 'Search')]/a")).click();	
	}
	
	public void closePackagesPopup() {
        // close popup
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']"))).click();
        } 
        catch (Exception e) {
            System.out.println("No popup detected or popup's close button not clickable within timeout. Proceeding...");
        }	
	}
	
	public void selectCarType(String carType) {
        // Click required car type
        driver.findElement(By.xpath("//div[@role='checkbox']//span[text()='"+carType+"']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CLEAR ALL']")));	
	}
	
	
}
 
 