package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.GiftCardPage;

public class GiftCardTest {
    WebDriver driver;
    GiftCardPage giftCardPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        giftCardPage = new GiftCardPage(driver);
    }

    @Test
    public void testGiftCardWithInvalidEmail() throws InterruptedException {
        giftCardPage.openWebsite();
        giftCardPage.closePopup();
        giftCardPage.goToGiftCards();
        giftCardPage.chooseThankYouCard();
        giftCardPage.setQuantityAndToggle();
        giftCardPage.fillRecipientDetails();
        giftCardPage.fillSenderDetailsInvalidEmail();
        giftCardPage.clickSubmitButton();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
