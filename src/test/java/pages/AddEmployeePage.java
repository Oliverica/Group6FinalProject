package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

    public class AddEmployeePage extends CommonMethods {

        @FindBy(xpath = "//span[@id='user_password_help_text' and @class='validation-error']")
        public WebElement passwordError;

        @FindBy(xpath = "//input[@class='formInputText valid']")
        public WebElement dependent_Name;

        @FindBy(xpath = "//h1[@id='heading']")
        public WebElement editDependent;

        @FindBy(xpath = ".//td[@class='check']/input[@type='checkbox']")
        public WebElement dependentCheckbox;

        @FindBy(xpath = ".//td[@class='dependentName']/a")
        public WebElement dependent_name;

        @FindBy(xpath = "//span[@for='re_password' and contains(text(),'Passwords do not match')]")
        public WebElement confirmPasswordError;

        @FindBy(id = "firstName")
        public WebElement firstNameField;

        @FindBy(id = "middleName")
        public WebElement middleNameField;

        @FindBy(id = "lastName")
        public WebElement lastNameField;

        @FindBy(id = "employeeId")
        public WebElement employeeIDField;

        @FindBy(id = "btnSave")
        public WebElement saveButton;

        @FindBy(id = "frmEmpPersonalDetails")
        public WebElement personalDetails;

        @FindBy(id = "empPic")
        public WebElement photoField;

        @FindBy (id = "photofile")
        public WebElement choosePhotoFile;

        @FindBy(xpath = "//input[@id='firstName']/following-sibling::span[@for='firstName' and text()='Required']")
        public WebElement empFirstNameErrorMsg;

        @FindBy(xpath = "//input[@id='lastName']/following-sibling::span[@for='lastName' and text()='Required']")
        public WebElement empLastNameErrorMsg;

        @FindBy(xpath = "//div[@class='message warning fadable']")
        public WebElement employeeIdErrorMessage;

        @FindBy (id="user_name")
        public WebElement usernameField;

        @FindBy (id="user_password")
        public WebElement passwordField;

        @FindBy (id="re_password")
        public WebElement confirmPasswordField;

        @FindBy (id="status")
        public WebElement statusDropdown;

        @FindBy(xpath = "//h1[text()='Personal Details']")
        public WebElement profilePage;

        @FindBy(id="chkLogin")
        public WebElement createLoginCheckbox;

        @FindBy(id="menu_pim_addEmployee")
        public WebElement addEmployee;

        @FindBy(xpath = "//span[text()='Passwords do not match']")
        public WebElement passwordMismatchError;

    public AddEmployeePage() {
        PageFactory.initElements(driver, this);
    }
}