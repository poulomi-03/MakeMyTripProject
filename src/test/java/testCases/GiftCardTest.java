package testCases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GiftCardTest {
    WebDriver driver;
    WebDriverWait wait;

    public GiftCardTest(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Explicit wait
        driver.manage().window().maximize();
    }

    public void openWebsite() throws InterruptedException {
        driver.get("https://www.makemytrip.com/");
        System.out.println("Website opened.");
        Thread.sleep(1000);
    }

    public void closePopup() throws InterruptedException {
        try {
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("commonModal__close")
            ));
            closeButton.click();
            System.out.println("Popup closed.");
        } catch (Exception e) {
            System.out.println("Popup not present or already closed.");
        }
        Thread.sleep(1000);
    }

    public void goToGiftCards() throws InterruptedException {
        WebElement giftCardIcon = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//img[@alt='Gift Cards_image']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", giftCardIcon);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", giftCardIcon);
        System.out.println("Navigated to Gift Cards.");
        Thread.sleep(1000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            System.out.println("Switched to new tab.");
        }
        Thread.sleep(1000);
    }

    public void chooseThankYouCard() throws InterruptedException {
        try {
            WebElement thankYouCard = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h3[normalize-space()='Thank You Gift Card']")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thankYouCard);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", thankYouCard);
            System.out.println("Thank You Gift Card selected.");
        } catch (TimeoutException e) {
            System.out.println("Thank You Gift Card element not found or not clickable.");
        }
        Thread.sleep(1000);
    }

    public void setQuantityAndToggle() throws InterruptedException {
        WebElement quantity = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[contains(text(),'╋')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", quantity);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", quantity);
        System.out.println("Quantity increased.");
        Thread.sleep(1000);

        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[@class='slider round']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", toggle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
        System.out.println("Toggle clicked.");
        Thread.sleep(1000);
    }

    public void fillRecipientDetails() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Recipient 1']")))
            .sendKeys("Ravi - 9876543210");
        driver.findElement(By.xpath("//input[@name='Recipient 2']")).sendKeys("Divya - 9123456789");
        System.out.println("Recipient details filled.");
        Thread.sleep(1000);
    }

    public void fillSenderDetailsInvalidEmail() throws InterruptedException {
        driver.findElement(By.name("senderName")).sendKeys("Reshmitha");
        driver.findElement(By.name("senderMobileNo")).sendKeys("9876543210");
        driver.findElement(By.name("senderEmailId")).sendKeys("invalidemail.com");
        System.out.println("Sender details with invalid email filled.");
        Thread.sleep(1000);
    }

    public void clickSubmitButton() throws InterruptedException {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[3]/button[1]")));
        submitButton.click();
        System.out.println("Submit button clicked.");
        Thread.sleep(1000);

        try {
            WebElement errorMsg = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'email')]")
            ));
            String errorText = errorMsg.getText();
            System.out.println("Error message captured: " + errorText);
        } catch (Exception e) {
            System.out.println("Error message not found: " + e.getMessage());
        }

        // Always take screenshot
        try {
            File screenshotDir = new File("OneWayCabAutomationMajorProject/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
                System.out.println("Screenshot directory created.");
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotDir, "error_screenshot.png");
            Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
        } catch (IOException io) {
            System.out.println("Screenshot saving failed: " + io.getMessage());
        }

        Thread.sleep(1000);
    }
}
