package lab05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UVicTest {

    WebDriver browser;

    @BeforeEach
    public void setUp() {
        // Chrome
        System.setProperty("webdriver.chrome.driver", "/Users/arfazhussain/Downloads/chromedriver_mac_arm64/chromedriver");
        browser = new ChromeDriver();

        // Firefox
        // System.setProperty("webdriver.gecko.driver", "*****LOCATION OF YOUR WEBDRIVER*****");
        // browser = new FirefoxDriver();

        // Safari
        // browser = new SafariDriver();

        browser.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        browser.quit();
    }

    // TEST-1: The webpage loads with Home – University of Victoria as its title.
    @Test
    public void uvicPageLoads() {
        browser.get("https://www.uvic.ca");
        assertEquals("Home - University of Victoria", browser.getTitle());
        // System.out.println("["+browser.getTitle()+"]");
    }

    // TEST-2: The webpage contains a search button (the magnifying glass in the upper right).
    @Test
    public void uvicSearchBoxAppears() {
        browser.get("https://www.uvic.ca");
        // Works
        // WebElement searchButton = browser.findElement(By.name("searchMain"));
        // assertTrue(searchButton.isDisplayed());

        WebElement searchButton = browser.findElement(By.xpath("//*[@id='search-btn']"));
        assertTrue(searchButton.isEnabled());
    }

    // TEST-3: When the button is pressed, the search bar appears.
    @Test
    public void uvicSearchButtonAppears() {
        browser.get("https://www.uvic.ca");
        WebElement searchButton = browser.findElement(By.xpath("//*[@id=\"searchUVic\"]"));
        assertTrue(searchButton.isEnabled());
    }

    /*      TEST-4: When the letters csc are typed in that search bar, they appear correctly.
     *      TEST-5: When the search for csc is launched (either by clicking the search button at the end of
     *      the search bar, or by sending Keys.ENTER, a new webpage loads with Search –
     *      University of Victoria as its title.
     */
    @Test
    public void uvicSearchTermAppears() {
        browser.get("https://www.uvic.ca");
        WebElement inputBox = browser.findElement(By.id("searchUVic"));
        WebElement searchButton1 = browser.findElement(By.cssSelector("#search-btn > .fa-solid"));
        WebElement searchButton2 = browser.findElement(By.cssSelector(".btn-light > .fa-solid"));
        searchButton1.click();
        inputBox.sendKeys("csc");
        assertEquals("csc", inputBox.getAttribute("value")); // TEST-4
        searchButton2.click();
        new WebDriverWait(browser, 3).until(ExpectedConditions.titleIs("Search - University of Victoria")); // TEST-5-1
        assertEquals("Search - University of Victoria", browser.getTitle()); // TEST-5-2
    }

    /*
     *      TEST-6
     *      When you load uvic.ca/about-uvic/contact-us/index.php, somewhere on that page, the
     *      phone number 250-721-8121 appears. Note that it is not enough to find the link – your
     *      test must confirm that the phone number is 250-721-8121.
     */
    @Test
    public void uvicContactsNumberExists() {
        browser.get("https://www.uvic.ca/about-uvic/contact-us/index.php");
        WebElement phoneNumberElement = browser.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div/p[2]/a"));
//        WebElement phoneNumberElement = browser.findElement(By.linkText("250-721-8121"));
        String phoneNumber = phoneNumberElement.getText();
        assertEquals("250-721-8121", phoneNumber);
    }

}
