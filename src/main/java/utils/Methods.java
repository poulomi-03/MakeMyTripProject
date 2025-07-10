package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class Methods extends BaseTest{
    public static void scrollClick(WebElement ele){
        try{
            ele.click();
        } catch (ElementClickInterceptedException e){
            // sometimes the element is out of view, so scroll till the element and click
            js.executeScript("arguments[0].scrollIntoView()", ele);
            ele.click();
        }
    }

    public static void datePicker(String date){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        while(true){
            try{
                driver.findElement(By.xpath("//div[contains(@aria-label, '"+date+"')]")).click();
                break;
            } catch (Exception e) {
                try{
                driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
                } catch (Exception ex) {
                    System.out.println("Reached end of booking availability. Cannot book for " + date);
                    break;
                }
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void timePicker(String timeString){
        String time [] = timeString.split(" ");
        int hr = Integer.parseInt(time[0]);
        int min = Integer.parseInt(time[1]);
        String mer = time[2];
        // select hours
        // if 12hrs then select 1st element i.e. element from dropdown list at index 0
        if(hr==12){
            scrollClick(driver.findElements(By.className("hrSlotItemParent")).get(0));
        }
        // else nth element i.e. element from dropdown list at index n, for e.g. 3rd element is at index 0 in dropdown list
        else{
            scrollClick(driver.findElements(By.className("hrSlotItemParent")).get(hr));
        }

        // We have time only in multiples of 5, [0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55]
        min = min/5; // we find index by dividing min/5, for e.g. 33/5 = 6 so element at 6th index is 30
        scrollClick(driver.findElements(By.className("minSlotItemParent")).get(min));

        // If meridian is 'am' then click 0th element which is 'AM'
        if(mer.equalsIgnoreCase("am")){
            scrollClick(driver.findElements(By.className("meridianSlotItemParent")).get(0));
        }
        // If meridian is 'pm' then click 1st element which is 'PM'
        else if (mer.equalsIgnoreCase("pm")) {
            scrollClick(driver.findElements(By.className("meridianSlotItemParent")).get(1));
        }

        //click apply
        driver.findElement(By.xpath("//div[@class='applyBtn']")).click();
    }

    public static WebElement findLowestCharges(){
        List<WebElement> baseAndOtherCharges = driver.findElements(By.xpath("//span[contains(@class, 'cabDetailsCard') and contains(text(), '₹')]"));
        int min = Integer.MAX_VALUE;
        int idx = -1;
        for (int i = 0; i< baseAndOtherCharges.size(); i+=2){
            String baseCharges = baseAndOtherCharges.get(i).getText().replaceAll("[^0-9]", "");
            String additionalCharges = baseAndOtherCharges.get(i+1).getText().replaceAll("[^0-9]", "");
            int totalPrice = Integer.parseInt(baseCharges) + Integer.parseInt(additionalCharges);
            if(totalPrice < min){
                min = totalPrice;
                idx = i/2;
            }
        }
        if(idx<0){
            return null;
        }
        return driver.findElements(By.xpath("//div[contains(@data-testid,'CAB_CARD')]")).get(idx);
    }

    public static void takeElementScreenshot(WebElement ele, String screenshotFileName) {
        File sourceFile = ele.getScreenshotAs(OutputType.FILE);
        File targetFile = new File("screenshots/" + screenshotFileName); // Use a variable for filename

        try {
            // Ensure the directory exists
//            if (!targetFile.getParentFile().exists()) {
//                targetFile.getParentFile().mkdirs(); // Creates "screenshots" directory if it doesn't exist
//            }

            // Using Apache Commons IO:
            FileUtils.copyFile(sourceFile, targetFile);
            System.out.println("Screenshot saved to: " + targetFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}