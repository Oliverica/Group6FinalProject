package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;


public class JobDetailsSteps extends CommonMethods {
    @Given("user is able to access HRMS application")
    public void user_is_able_to_access_hrms_application() {
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
        click(loginPage.loginButton);
    }








}
