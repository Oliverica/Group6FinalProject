package steps;

import APISteps.APISteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AddDependentsPage;
import utils.APIConstants;
import utils.APIPayloadConstants;
import utils.CommonMethods;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class AddDependentsSteps extends CommonMethods {

    public static String employee_id;
    public static String token;

    @Given("The user is authenticated")
    public void the_user_is_authenticated() {
        String payload = APIPayloadConstants.createEmployeeJsonPayloadDynamic(
                "Oliverica", "Zekoviccc", "Olala",
                "F", "1986-01-14", "Trainee", "QA engineer"
        );
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer" + token)
                .body(payload)
                .when()
                .post(APIConstants.CREATE_EMPLOYEE);

        if (response.getStatusCode() == 201) {
            System.out.println("User authenticated successfully. Employee created.");
        } else {
            System.err.println("Authentication failed. Response Code: " + response.getStatusCode());
            throw new RuntimeException("Failed to authenticate the user.");
        }
        String employeeID = response.jsonPath().getString("Employee.employee_id");
        System.out.println("Authenticated employee ID: " + employeeID);
        employee_id = employeeID;
    }

    @When("the user sends a request  to add a dependent with {string}, {string} and {string}")
    public void addDependentWithValidData(String name,
                                          String relationship, String dateOfBirth) {
        click(addDependents.addDependentsButton);
        sendText(name, addDependents.dependentName);
        selectFromDropDown(addDependents.selectRelationship, relationship);
        sendText(dateOfBirth, addDependents.dateOfBirth);
        click(addDependents.saveDependent);
    }

    @Then("the response should return {string}")
    public void theReturnedResponse(String expectedMessage) {
        System.out.println("The success message appears: " + expectedMessage);
    }

    @When("the user sends a request  to add a dependent with {string}, {string} and {string}")
    public void addDependentWithInvalidData(String name,
                                            String relationship, String invalidDateOfBirth) {
        click(addDependents.addDependentsButton);
        sendText(name, addDependents.dependentName);
        selectFromDropDown(addDependents.selectRelationship, relationship);
        sendText(invalidDateOfBirth, addDependents.dateOfBirth);
        click(addDependents.saveDependent);

    }

    @Then("the response should return {string}")
    public void the_response_should_return(String expectedMessage) {
        System.out.println("The error message appears: " + expectedMessage);
    }

    @When("the user sends a request to add a dependent with {string}, {string} and {string}")
    public void the_user_sends_a_request_to_add_a_dependent_with_missing_or_invalid_fields
            (String name, String relationship, String dateOfBirth) {
        if (!name.isEmpty()) {
            sendText(name, addDependents.dependentName);
        }
        if (!relationship.isEmpty()) {
            selectFromDropDown(relationship, addDependents.selectRelationship);
        }
        if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
            click(addDependents.dateOfBirth);

            String[] dateParts = dateOfBirth.split("-");
            String year = dateParts[0];
            String month = dateParts[1];
            String day = dateParts[2];

            selectFromDropDown(year, addDependents.yearDropdown);

            selectFromDropDown(addDependents.monthDropdown, Integer.parseInt(month) - 1);

            click(addDependents.dayDropdown);
        }
        click(addDependents.saveDependent);
    }

    @Then("the response should return {string}")
    public void the_response_should_return_missing_or_invalid_field_message(String expectedMessage) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement messageElement = wait.until(ExpectedConditions.
                    visibilityOfElementLocated(By.xpath("formInputText validation-error")));
            String actualMessage = messageElement.getText();

            System.out.println("Actual message displayed: " + actualMessage);
            Assert.assertEquals("The expected and actual message do not match!",
                    expectedMessage, actualMessage);
        } catch (Exception e) {
            System.out.println("Error: Could not capture the message on the page. "
                    + e.getMessage());
        }
    }

    @When("the user sends a request to add a dependent with a following data:")
    public void the_user_sends_a_request_to_add_multiple_dependents(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> dependents = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> dependent : dependents) {
            click(addDependents.dependentsButton);
            sendText(dependent.get("name"), addDependents.dependentName);
            selectFromDropDown(dependent.get("relationship"), addDependents.selectRelationship);
            sendText(dependent.get("dateOfBirth"), addDependents.dateOfBirth);
            click(addDependents.saveDependent);

        }
    }

    @Then("the response should confirm all dependents were added successfully")
    public void the_response_should_confirm_all_dependents_were_added_successfully() {
        List<WebElement> dependentsList = addDependents.dependentRows;
        Assert.assertTrue("Not all dependents are added", dependentsList.size() > 1);
    }

    @When("the user views  the dependent list for an employee")
    public void the_user_views_the_dependent_list_for_an_employee() {
        click(addDependents.dependentsButton);
    }

    @Then("the dependent list should display all added dependents")
    public void the_dependent_list_should_display_all_added_dependents() {
        List<WebElement> dependentList = addDependents.dependentRows;
        Assert.assertTrue("Dependent list is empty", dependentList.size() > 0);

    }


}
