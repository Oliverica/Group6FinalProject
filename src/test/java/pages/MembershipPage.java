package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class MembershipPage extends CommonMethods {
    @FindBy(xpath ="//body/div/div[3]/div/div/ul/li[11]")
    public WebElement memberBTClick;

    @FindBy(xpath="//input[@id='btnAddMembershipDetail']")
    public WebElement AddmemberBT;

    @FindBy(xpath = "//body/div/div[3]/div[1]/div[2]/div[2]/form/fieldset/ol/li/select")
    public List<WebElement> selMembership;

    @FindBy(xpath = "//body/div/div[3]/div[1]/div[2]/div[2]/form/fieldset/ol/li/input")
    public List<WebElement> dateMembership;

    @FindBy(id="membership_membership")
    public WebElement typeOfMember;

    @FindBy(id="membership_subscriptionPaidBy")
    public WebElement typeOfSubs;

    @FindBy(id="membership_subscriptionAmount")
    public WebElement memberAmount;

    @FindBy(id="membership_subscriptionCommenceDate")
    public WebElement date;

    @FindBy(id="btnSaveMembership")
    public WebElement SvBt;

    @FindBy(xpath = "//body/div/div[3]/div[1]/div[2]/div[2]/form/fieldset/ol/li/span")
    public WebElement errorMs;




    public MembershipPage() {
        PageFactory.initElements(driver, this);
    }
}


