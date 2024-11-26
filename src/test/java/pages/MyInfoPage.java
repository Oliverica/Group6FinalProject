package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class MyInfoPage extends CommonMethods {

    @FindBy(id = "menu_pim_viewMyDetails")
    public WebElement myInfoButton;

    @FindBy(id = "profile-pic")
    public WebElement profilePicture;

    @FindBy(xpath = "//a[text()='Qualifications']")
    public WebElement qualificationsButton;

    @FindBy(id = "addLanguage")
    public WebElement addLanguageButton;

    @FindBy(id = "frmLanguage")
    public WebElement dropDownLanguagesField;

    @FindBy(id = "language_code")
    public WebElement languageDropDown;

    @FindBy(id = "language_lang_type")
    public WebElement fluencyDropDown;

    @FindBy(id = "language_competency")
    public WebElement competencyDropDown;

    @FindBy(id = "language_comments")
    public WebElement commentsField;

    @FindBy(id = "btnLanguageSave")
    public WebElement languageSaveButton;

    @FindBy(xpath = "//span[@for='language_code' and contains(@class, 'validation-error') and text()='Required']")
    public WebElement languageValidationError;

    @FindBy(xpath = "//span[@for='language_lang_type' and contains(@class, 'validation-error') and text()='Required']")
    public WebElement fluencyValidationError;

    @FindBy(xpath = "//span[@for='language_competency' and contains(@class, 'validation-error') and text()='Required']")
    public WebElement competencyValidationError;

    @FindBy(xpath = "//span[contains(text(),'100 characters')]")
    public WebElement commentValidationError;

    public MyInfoPage() {
        PageFactory.initElements(driver, this);
    }
}