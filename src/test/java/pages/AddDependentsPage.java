package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class AddDependentsPage extends CommonMethods {


    @FindBy(linkText = "//a[text()='Dependents']")
    public WebElement dependentsButton;

    @FindBy(id = "btnAddDependent")
    public WebElement addDependentsButton;

    @FindBy(id = "dependent_name")
    public WebElement dependentName;

    @FindBy(id = "dependent_relationshipType")
    public WebElement selectRelationship;

    @FindBy(id = "dependent_relationship")
    public WebElement specifyRelationship;

    @FindBy(id = "dependent_dateOfBirth")
    public WebElement dateOfBirth;

    @FindBy(id = "btnSaveDependent")
    public WebElement saveDependent;

    @FindBy(id = "delDependentBtn")
    public WebElement deleteDependent;

    @FindBy(id = "ui-datepicker-month")
    public WebElement monthDropdown;

    @FindBy(xpath = "//select[@class='ui-datepicker-year']")
    public WebElement yearDropdown;

    @FindBy(xpath = "//a[@class='ui-state-default']")
    public WebElement dayDropdown;

    @FindBy(xpath = "//table[@id='dependent_list']/tbody/tr")
    public List<WebElement> dependentRows;

    public AddDependentsPage() {
        PageFactory.initElements(driver, this);

    }
}