package hw3.pageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class MainPageElements {

    @FindBy(xpath = "//*[@name='navigation-sidebar']")
    WebElement leftSection;
    @FindBy(xpath = "//*[starts-with(@class, uui-header)]//li[@class='dropdown']")
    WebElement dropdown;
    @FindBy(xpath = "/html/body/header/div/nav/ul[1]/li[3]/ul/li[8]")
    WebElement differentElementsPage;
    @FindBy(xpath = "//*[starts-with(@class,'uui-header')]/nav/ul[1]/li/a")
    List<WebElement> headerElementsList;

    public MainPageElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLeftSection() {
        return leftSection;
    }

    public WebElement getDropdown() {
        return dropdown;
    }

    public WebElement getDifferentElementsPage() {
        return differentElementsPage;
    }

    public List<WebElement> getHeaderElements() {
        return headerElementsList;
    }

    public ArrayList<String> getHeaderElementsTitles() {
        ArrayList<String> titles = new ArrayList<String>();
        for(WebElement we : headerElementsList)
        {
            titles.add(we.getText());
        }
        return titles;
    }


}
