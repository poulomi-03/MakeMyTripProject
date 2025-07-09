package pages;

import java.util.ArrayList;
import java.util.List;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
public class CabBookingPage {
	WebDriver driver;
	
	public CabBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/* Page Objects */
	
	//Button to disable the Login pop-up
	@FindBy(xpath="//span[@class='commonModal__close']") WebElement disablePopUpBtn;
	@FindBy(xpath="//a[contains(@href,'/hotels/')]") WebElement hotelsTabSelector;
	@FindBy(id="guests") WebElement roomsAndGuestsOptionSelector;
	@FindBy(xpath = "//p[contains(text(), 'Adults')]/following::span[@class='gstSlct__text'][1]") WebElement adultsDropdownSelector;
	@FindBy(xpath = "//ul[@class='gstSlct__list']/li") List<WebElement> adultsCountList;
	
	
	/* Action methods */
	
	public void disableLoginPopUp() {
		disablePopUpBtn.click();
	}
	
	public void clickOnHotelsTab() {
		hotelsTabSelector.click();
	}
	
	public void clickOnRoomsAndGuestsOption() {
		roomsAndGuestsOptionSelector.click();
	}
	
	public void clickOnAdultsDropdown() {
		adultsDropdownSelector.click();
	}
	
	public List<Integer> getListOfCountsInAdultDropdown() {
		List<Integer> listOfAdultCounts = new ArrayList<Integer>();
		for(WebElement countEle: adultsCountList) {
			listOfAdultCounts.add(Integer.parseInt(countEle.getText()));
		}
		
		return listOfAdultCounts;
	}
}
 
 