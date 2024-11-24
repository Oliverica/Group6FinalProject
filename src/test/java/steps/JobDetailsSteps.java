package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JobDetailsSteps extends CommonMethods {
    @Given("user is able to access HRMS application")
    public void user_is_able_to_access_hrms_application() {
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
        click(loginPage.loginButton);
    }
    @When("user clicks PIM option and Employee List")
    public void user_clicks_pim_option_and_employee_list() {
        click(dashboardPage.btnPIM);
        click(employeeSearchPage.employeeListBT);
    }

    @When("user clicks any Employee id from the list")
    public void user_clicks_any_employee_id_from_the_list() {
      click(employeeSearchPage.RandomIdClick);
    }
    @When("user clicks Job Button")
    public void user_clicks_job_button() {
        click(jobDetailsPage.jobClick);

    }
    @Then("all the required fields are displayed and editable and validated")
    public void all_the_required_fields_are_displayed_and_editable_and_validated(io.cucumber.datatable.DataTable dataTable) {

        click(jobDetailsPage.saveEditDetails);

        //extract expected labels from DataTable
        List<String> expectedDetails = dataTable.asList();



     //extract actual labels from WebElements
        List<WebElement> actualDetails = jobDetailsPage.labelDetails;

      //Declare a new List of String to store actualDetails
        List<String> actualTexts = new ArrayList<>();




        for (WebElement element : actualDetails) {
            if(element.isDisplayed() && element.isEnabled()){
                actualTexts.add(element.getText().trim());


            }

        }

        System.out.println("This is actual text " + actualTexts);


        for (String expected : expectedDetails) {
            // Assert that each expected label name is in the actualTexts list

            boolean isPresent=actualTexts.contains(expected.trim());

            System.out.println(expected);
            System.out.println(actualTexts);
            System.out.println(isPresent);

            Assert.assertTrue(expected,isPresent);

        }
    }
    @When("user clicks saveEdit button")
    public void user_clicks_save_edit_button() {
        click(jobDetailsPage.saveEditDetails);
    }
    @When("the Admin fills in the job details for the employee:")
    public void the_admin_fills_in_the_job_details_for_the_employee(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> details=dataTable.asMap();

        String key=details.values().toString();



        String jobTitle= details.get(0);
        String empStatus= details.get(1);
        String joinedDate= details.get(2);
        String subunit= details.get(3);
        String location= details.get(4);




        selectFromDropDown(jobDetailsPage.jobTitleField,jobTitle);
        selectFromDropDown(jobDetailsPage.empStatusField,empStatus);
        selectFromDropDown(jobDetailsPage.joinedDate,joinedDate);
        selectFromDropDown(jobDetailsPage.subUnit,subunit);
        selectFromDropDown(jobDetailsPage.empLocation,location);





    }
    @Then("the system should validate that all mandatory fields are filled")
    public void the_system_should_validate_that_all_mandatory_fields_are_filled() {
        Assert.assertNotNull(jobDetailsPage.jobTitleField.getText());
        Assert.assertNotNull(jobDetailsPage.empStatusField.getText());
        Assert.assertNotNull(jobDetailsPage.joinedDate.getText());
        Assert.assertNotNull(jobDetailsPage.subUnit.getText());
        Assert.assertNotNull(jobDetailsPage.empLocation.getText());






    }
    @Then("the system should save the job details in the employee's profile")
    public void the_system_should_save_the_job_details_in_the_employee_s_profile() {
        Assert.assertTrue(jobDetailsPage.successMessage.isDisplayed());
    }
    @Then("the changes should be reflected in the database")
    public void the_changes_should_be_reflected_in_the_database() {





    }















}
