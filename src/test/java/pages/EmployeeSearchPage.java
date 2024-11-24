package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeSearchPage extends CommonMethods {


    @FindBy(id="menu_pim_viewEmployeeList")
    public WebElement employeeListBT;

    @FindBy(xpath ="//div[@id='tableWrapper']//table[@id='resultTable']//a[contains(@href, 'empNumber/100624')]")
    public WebElement RandomIdClick;





    public EmployeeSearchPage() {

        PageFactory.initElements(driver, this);
    }
}
