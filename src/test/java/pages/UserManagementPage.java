import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class UserManagementPage extends CommonMethods{

    @FindBy(id="menu_admin_UserManagement")
    public WebElement userManagement;

    @FindBy(id="btnAdd")
    public void addButton;

    @FindBy (id="btnDelete")
    public void deleteButton;

    @FindBy(id="id=searchSystemUser_userName")
    public  WebElement usernameField;

    @FindBy(id="systemUser_userType")
    public WebElement userRoleDropdown;

    @FindBy (id="searchSystemUser_employeeName_empName")
    public void WebElement employeeNameField;

    @FindBy (id="systemUser_userName")
    public void WebElement systemUsername;

    @FindBy (id="systemUser_status")
    public void WebElement statusDropdown;

    @FindBy (id="systemUser_password")
    public void WebElement systemPassword;

    @FindBy (id="systemUser_confirmPassword")
    public void WebElement confirmPassword;

    @FindBy (id="btnSave")
    public void WebElement btnSave;


    @FindBy (id="searchBtn")
    public void WebElement searchButton;

    @FindBy (id="searchSystemUser_userName")
    public void WebElement searchUsername;

    @FindBy (id="dialogDeleteBtn")
    public void WebElement confirmedDeletionBtn;

}