package hw3.ex2glue;

import hw3.pageElements.CommonElements;
import hw3.pageElements.DifferentElementsPageElements;
import hw3.pageElements.IndexPageElements;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Ex2Glue {
    private WebDriver webDriver;
    private IndexPageElements indexPage;
    private CommonElements commonElements;
    private DifferentElementsPageElements elementsPage;
    private Properties properties;
    private SoftAssert softAssert;

    @Before
    public void setup() throws IOException {
        Path resourceDirectory = Paths.get("src", "test", "resources", "test.properties");
        properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(resourceDirectory.toFile()), StandardCharsets.UTF_8));
        softAssert = new SoftAssert();
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(properties.getProperty("URL"));
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void quit() {
        webDriver.quit();
    }

    @When("I open test site by URL")
    public void openSite() {
        webDriver.get(properties.getProperty("URL"));
        indexPage = new IndexPageElements(webDriver);
        commonElements = new CommonElements(webDriver);
        elementsPage = new DifferentElementsPageElements(webDriver);
    }

    @Then("Home page is opened")
    public void siteURLTest() {
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty("URL"));
    }

    @When("I log in as {string} - {string}")
    public void login(String userName, String password) {
        indexPage.login(userName, password);
    }


    @Then("I logged in as {string}")
    public void userLoginTest(String expectedUserName) {
        Assert.assertEquals(indexPage.getUsername(), expectedUserName);
    }

    @Then("Browser title is {string}")
    public void browserTitleTest(String expectedTitle) {
        Assert.assertEquals(webDriver.getTitle(), expectedTitle);
    }

    @When("I click on Service subcategory in the Header")
    public void clickOnServiceSubcategoryInTheHeader() {
        commonElements.getDropdown().click();
    }

    @Then("There are {int} elements with proper texts in the Header")
    public void serviceSubcategoryElementsTest(int amount) {
        List<String> serviceElements = commonElements.getHeaderDropdownElements();
        Assert.assertEquals(serviceElements.size(), amount);
        List<String> expectedTitles = Arrays.asList(properties.getProperty("fullServiceMenu").split(","));
        for (int i = 0; i < serviceElements.size(); i++) {
            softAssert.assertEquals(serviceElements.get(i), expectedTitles.get(i));
        }
        softAssert.assertAll();
    }

    @When("I click on Service subcategory in the Left Section")
    public void clickOnServiceSubcategoryInTheLeftSection() {
        commonElements.getLeftSectionDropdown().click();
    }

    @Then("There are {int} elements with proper texts int the Left Section")
    public void thereAreElementsWithProperTextsIntTheLeftSection(int amount) {
        List<String> serviceElements = commonElements.getLeftSectionDropdownElements();
        Assert.assertEquals(serviceElements.size(), amount);
        List<String> expectedTitles = Arrays.asList(properties.getProperty("fullServiceMenuLowerCase").split(","));
        for (int i = 0; i < serviceElements.size(); i++) {
            softAssert.assertEquals(serviceElements.get(i), expectedTitles.get(i));
        }
        softAssert.assertAll();
    }

    @When("I click on Different Elements page in Service subcategory")
    public void clickOnDifferentElementsPageInServiceSubcategory() {
        WebElement service = commonElements.getDropdown();
        service.click();
        WebElement toggle = commonElements.getDifferentElementsPage();
        toggle.click();
    }

    @Then("{string} page is opened")
    public void differentElementsPageOpenTest(String title) {
        Assert.assertEquals(webDriver.getTitle(), title);
    }

    @Then("There are {int} checkboxes")
    public void checkboxesAmountTest(int amount) {
        Assert.assertEquals(elementsPage.getCheckboxList().size(), amount);
        for(WebElement we : elementsPage.getCheckboxList())
        {
            softAssert.assertTrue(we.isDisplayed(),"");
        }
        softAssert.assertAll();
    }

    @And("There are {int} radiobuttons")
    public void radiobuttonsAmountTest(int amount) {
        Assert.assertEquals(elementsPage.getRadioList().size(), amount);
        for(WebElement we : elementsPage.getRadioList())
        {
            softAssert.assertTrue(we.isDisplayed(), "");
        }
        softAssert.assertAll();
    }

    @And("There is a dropdown")
    public void dropdownTest() {
        Assert.assertTrue(elementsPage.getDropdown().isDisplayed());
    }

    @And("There are {int} buttons")
    public void buttonsAmountTest(int amount) {
        Assert.assertEquals(elementsPage.getButtonsList().size(), amount);
        for(WebElement we : elementsPage.getButtonsList())
        {
            softAssert.assertTrue(we.isDisplayed(),"");
        }
        softAssert.assertAll();
    }

    @And("There is the Right Section")
    public void rightSectionTest() {
        Assert.assertTrue(elementsPage.getLogSideBar().isDisplayed());
    }

    @And("There is the Left Section")
    public void leftSectionTest() {
        Assert.assertTrue(commonElements.getLeftSection().isDisplayed());
    }

    @When("I select Water and Wind checkboxes")
    public void selectWaterAndWindCheckboxes() {
        List<WebElement> checkboxes = elementsPage.getCheckboxList();
        WebElement water = checkboxes.get(0);
        WebElement wind = checkboxes.get(2);
        water.click();
        wind.click();
    }

    @Then("Checkboxes are selected")
    public void checkboxesSelectionTest() {
        List<WebElement> checkboxList = elementsPage.getCheckboxList();
        softAssert.assertTrue(checkboxList.get(0).isSelected(), "");
        softAssert.assertTrue(checkboxList.get(2).isSelected(), "");
        softAssert.assertAll();
    }

    @And("For each checkbox there is an individual log row and value is corresponded to the status of checkbox")
    public void checkboxesLogTest() {
        List<WebElement> logs = elementsPage.getLogs();
        softAssert.assertTrue(logs.get(0).isDisplayed(), "");
        softAssert.assertTrue(logs.get(1).isDisplayed(), "");
        String waterLog = logs.get(1).getText();
        String windLog = logs.get(0).getText();
        softAssert.assertTrue(waterLog.contains("Water") && (waterLog.contains("true")), "");
        softAssert.assertTrue(windLog.contains("Wind") && (windLog.contains("true")), "");
        softAssert.assertAll();
    }

    @When("I select Selen radiobutton")
    public void selenRadiobuttonSelection() {
        List<WebElement> radioList = elementsPage.getRadioList();
        WebElement selen = radioList.get(3);
        selen.click();
    }

    @Then("There is a log row and value is corresponded to the status of radiobutton")
    public void radiobuttonLogTest() {
        String radiobuttonLog = elementsPage.getLogs().get(0).getText();
        Assert.assertTrue(radiobuttonLog.contains("metal") && radiobuttonLog.contains("Selen"));
    }

    @When("I select Yellow in dropdown")
    public void dropdownOptionSelection() {
        elementsPage.getYellow().click();
    }

    @Then("There is a log row and value is corresponded to the selected value")
    public void dropdownLogTest() {
        String dropdownLog = elementsPage.getLogs().get(0).getText();
        Assert.assertTrue(dropdownLog.contains("Colors") && dropdownLog.contains("Yellow"));
    }

    @When("I unselect checkboxes")
    public void checkboxesUnselection() {
        List<WebElement> checkboxes = elementsPage.getCheckboxList();
        WebElement water = checkboxes.get(0);
        WebElement wind = checkboxes.get(2);
        water.click();
        wind.click();
    }

    @Then("Checkboxes are unselected")
    public void checkboxesUnselectionTest() {
        List<WebElement> checkboxList = elementsPage.getCheckboxList();
        softAssert.assertFalse(checkboxList.get(0).isSelected());
        softAssert.assertFalse(checkboxList.get(2).isSelected());
        softAssert.assertAll();
    }


    @And("For each checkbox there is an individual log row and value is corresponded to the unselected status of checkbox")
    public void checkboxesUnselectedLogTest() {
        List<WebElement> logs = elementsPage.getLogs();
        softAssert.assertTrue(logs.get(0).isDisplayed(), "");
        softAssert.assertTrue(logs.get(1).isDisplayed(), "");
        String waterLog = logs.get(1).getText();
        String windLog = logs.get(0).getText();
        softAssert.assertTrue(waterLog.contains("Water") && (waterLog.contains("false")), "");
        softAssert.assertTrue(windLog.contains("Wind") && (windLog.contains("false")), "");
        softAssert.assertAll();
    }
}
