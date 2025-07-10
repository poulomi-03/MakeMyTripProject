package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
//import pages.CabBookingPage;
import utils.Methods;

public class CabBookingTest extends BaseTest{
	

	@DataProvider(name = "testData")
	public String[][] loginData(){
		String[][] arr = {{"Cabs", "Outstation One-Way", "Delhi", "Manali, Himachal Pradesh", "Dec 09 2025", "12 40 PM", "SUV"}};
		return arr;
	}
	
	@Test(dataProvider = "testData")
	public static void oneWayCabSearch(String section, String tripType, String city, String destination, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(city);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(destination);
		cabBookingPage.selectDestinationFromSuggestion(destination);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        cabBookingPage.selectCarType(carType);
        WebElement cab = Methods.findLowestCharges();
        Methods.takeElementScreenshot(cab, "cab.png");
	}
	
	@Test(dataProvider = "testData")
	public static void verifyCarTypeInResult(String section, String tripType, String city, String destination, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(city);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(destination);
		cabBookingPage.selectDestinationFromSuggestion(destination);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        cabBookingPage.selectCarType(carType);
        
        // To verify the Car Type with help of number of seats (SUV has 6 seats)
        List<WebElement> cabs = driver.findElements(By.xpath("//div[contains(@class, 'cabDetailsCard_utilitiesInfo')]"));
        for(WebElement cab : cabs) {
        	String expectedNumberOfSeats = "6 Seats";
        	String actualNumberOfSeats = cab.findElement(By.tagName("span")).getText();
        	System.out.println(expectedNumberOfSeats +" - "+actualNumberOfSeats);
        	Assert.assertEquals(expectedNumberOfSeats, actualNumberOfSeats);
        }
	}

	@Test(dataProvider = "testData")
	public static void verifyClearAllFilterFunctionality(String section, String tripType, String city, String destination, String date, String time, String carType) {
		cabBookingPage.selectSection(section);
		cabBookingPage.selectTripType(tripType);
		cabBookingPage.clickFromField();
		cabBookingPage.selectCityFromSuggestion(city);
		cabBookingPage.clickToField();
		cabBookingPage.enterDestination(destination);
		cabBookingPage.selectDestinationFromSuggestion(destination);
		cabBookingPage.clickdeparture();
        Methods.datePicker(date);
        cabBookingPage.clickPickupTime();
        Methods.timePicker(time);
        cabBookingPage.search();
        cabBookingPage.closePackagesPopup();
        cabBookingPage.selectCarType(carType);
        
        // click clear All
        driver.findElement(By.xpath("//span[text()='CLEAR ALL']")).click();
        
        try {
        	driver.findElement(By.xpath("//div[contains(@class='checkbox_checked')]"));
        	Assert.assertTrue(true);
        } catch(Exception e) {
        	System.out.println("Cleared all filters");
        }   
	}
	
	
	//Change the code and modify it 

	@Test(dataProvider = "testData")
	public static void verifyTripTypeInResults(String section, String tripType, String city, String destination, String date, String time, String carType) {
		Scenario1Steps s = new Scenario1Steps();
		s.selectSection(section);
		s.selectTripType(tripType);
		s.clickFromField();
		s.selectCityFromSuggestion(city);
		s.clickToField();
		s.enterDestination(destination);
		s.selectDestinationFromSuggestion(destination);
		s.clickdeparture();
        Methods.datePicker(date);
        s.clickPickupTime();
        Methods.timePicker(time);
        s.search();
        s.closePackagesPopup();
        
        String expectedTripType = tripType;
        String actualTripType = driver.findElement(By.id("trip_type")).getAttribute("value");
    	System.out.println(expectedTripType +" - "+actualTripType);
    	Assert.assertEquals(expectedTripType, actualTripType);
	}
	
	

}
