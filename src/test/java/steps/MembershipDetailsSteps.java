package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MembershipDetailsSteps extends CommonMethods {
    @When("user clicks on employee id and click membership button")
    public void user_clicks_on_employee_id_and_click_membership_button() {
       click(jobDetailsPage.IdButton);
       click(membershipPage.memberBTClick);
    }
    @When("user clicks on Top Add button")
    public void user_clicks_on_top_add_button() {
        click(membershipPage.AddmemberBT);
    }



    @Then("user is navigated to MembershipAdd and fields are checked")
    public void user_is_navigated_to_membership_add_and_fields_are_checked() {
        List<WebElement>fields=membershipPage.selMembership;
        for (WebElement field:fields){
            Assert.assertTrue(field.isDisplayed() && field.isEnabled());
        }
        List<WebElement>dates=membershipPage.dateMembership;
        for (WebElement date:dates){
            Assert.assertTrue(date.isDisplayed() && date.isEnabled());
        }

    }
    @When("users select all the required details {string}, {string}\"")
    public void users_select_all_the_required_details(String amount, String date) {
    Select select=new Select(membershipPage.typeOfMember);
    select.getFirstSelectedOption();
    Select select1=new Select(membershipPage.typeOfSubs);
    select1.getFirstSelectedOption();
    sendText(amount, membershipPage.memberAmount);
    sendText(date, membershipPage.date);
    }


    @When("user press Save Bt")
    public void user_press_save_bt() {
       click(membershipPage.SvBt);
    }

    @Then("Error message is coming up {string}")
    public void error_message_is_coming_up(String errorMS) {
       Assert.assertEquals(membershipPage.errorMs.getText(),errorMS);
    }
    @Then("Success message is coming up {string} displayed and enabled")
    public void success_message_is_coming_up(String success) {
        Assert.assertTrue(membershipPage.successMes.isDisplayed() && membershipPage.successMes.isEnabled());
        Assert.assertEquals(membershipPage.successMes.getText(),success);

    }










}
