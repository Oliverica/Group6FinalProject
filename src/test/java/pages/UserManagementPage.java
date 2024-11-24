import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class UserManagementPage extends CommonMethods{

    @FindBy(id="menu_admin_UserManagement")
    public WebElement userManagement;

    @FindBy(id="btnAdd")
    public WebElement addButton;

    @FindBy (id="btnDelete")
    public WebElement deleteButton;

    @FindBy(id="id=searchSystemUser_userName")
    public  WebElement usernameField;

    @FindBy(id="systemUser_userType")
    public WebElement userRoleDropdown;

    @FindBy (id="searchSystemUser_employeeName_empName")
    public WebElement employeeNameField;

    @FindBy (id="systemUser_userName")
    public WebElement systemUsername;

    @FindBy (id="systemUser_status")
    public WebElement statusDropdown;

    @FindBy (id="systemUser_password")
    public WebElement systemPassword;

    @FindBy (id="systemUser_confirmPassword")
    public WebElement confirmPassword;

    @FindBy (id="btnSave")
    public WebElement btnSave;


    @FindBy (id="searchBtn")
    public WebElement searchButton;

    @FindBy (id="searchSystemUser_userName")
    public WebElement searchUsername;

    @FindBy (id="dialogDeleteBtn")
    public WebElement confirmedDeletionBtn;

    public UserManagementPage(){
        PageFactory.initElements(driver,this);

    }

}