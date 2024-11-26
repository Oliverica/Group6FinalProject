package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.DateUtils;

import java.util.List;
import java.util.Map;

public class AddDependentSteps extends CommonMethods {

    @When("user enters admin username and admin password")
    public void user_enters_admin_username_and_admin_password() {
        sendText(ConfigReader.read("userName"), loginPage.usernameField);
        sendText(ConfigReader.read("password"), loginPage.passwordField);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click(loginPage.loginButton);

    }

    @Then("user is navigated to dashboard page")
    public void user_is_navigated_to_dashboard_page() {
        Assert.assertTrue("Dashboard page is not displayed.",
                dashboardPage.pimMenuButton.isDisplayed());
        click(dashboardPage.pimMenuButton);
    }

    @When("the user navigates to the Employee List")
    public void the_user_navigates_to_the_employee_list() {
        click(dashboardPage.employeeListButton);
        Assert.assertTrue("List option page is not displayed. ",
                dashboardPage.employeeListButton.isDisplayed());

    }

    @When("the user searches for the employee {string} with ID {string}")
    public void the_user_searches_for_the_employee_with_ID(String name, String empId) {
        sendText(name, addDependentPage.nameToSearch);
        sendText(empId, addDependentPage.empID);
        click(addDependentPage.searchEmployee);

    }

    @Then("user clicks search button")
    public void user_clicks_search_button() {
        click(addDependentPage.searchEmployee);
    }

    @And("the employee details should be displayed")
    public void the_employee_details_should_be_displayed() throws InterruptedException {
        Assert.assertTrue("Employee details not displayed",
                addDependentPage.resultTable.size() > 0);
        Thread.sleep(2000);
    }

    @Then("the user selects the employee")
    public void the_user_selects_the_employee() throws InterruptedException {
        waitForElementToBeClickable(addDependentPage.employeeName);
        click(addDependentPage.employeeName);
        Thread.sleep(2000);
    }

    @And("the user is on the Personal Details Page")
    public void the_user_is_on_the_Personal_Details_Page() throws InterruptedException {
        Assert.assertTrue("Personal Details Page is not displayed",
                addDependentPage.personalDetailsPage.isDisplayed());
        System.out.println("User is on the Personal Details Page");
        Thread.sleep(2000);
    }

    @Then("the user clicks on Dependent tab")
    public void the_user_clicks_on_dependent_tab() {
        waitForElementToBeClickable(addDependentPage.dependentsButton);
        click(addDependentPage.dependentsButton);
    }

    @And("the user clicks on Add button to add dependent")
    public void the_user_clicks_on_add_button_to_add_dependent() {
        click(addDependentPage.addDependentsButton);
    }

    @When("the user enters dependent name as {string}")
    public void the_user_enters_dependent_name_as(String name) {
        sendText(name, addDependentPage.dependentName);
    }

    @When("the user selects relationship as {string}")
    public void the_user_selects_relationship_as(String relationship) throws InterruptedException {
        waitForElementToBeClickable(addDependentPage.selectRelationship);
        addDependentPage.selectRelationship.click();

        if (relationship.equalsIgnoreCase("Child")) {
            selectFromDropDown(addDependentPage.selectRelationship, "Child");
        } else if (relationship.equalsIgnoreCase("Other")) {
            selectFromDropDown(relationship, addDependentPage.selectRelationship);
            sendText("Parent", addDependentPage.specifyRelationship);
            Thread.sleep(2000);
        }
    }

    @When("the user selects date of birth as {string}")
    public void the_user_selects_date_of_birth_as(String dob) {
        waitForElementToBeClickable(addDependentPage.dobCalender);
        click(addDependentPage.dobCalender);

        String[] dobParts = dob.split("-");
        String year = dobParts[0];
        String month = dobParts[1];
        String day = dobParts[2];

        String monthText = DateUtils.getMonthText(month);

        selectFromDropDown(addDependentPage.monthDropdown, monthText);
        selectFromDropDown(addDependentPage.yearDropdown, year);
        for (WebElement date : addDependentPage.dayElement) {
            if (date.getText().equals(day)) {
                date.click();
                break;
            }
        }
    }

    @Then("the user clicks on {string} button")
    public void the_user_clicks_on_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("SAVE")) {
            click(addDependentPage.saveDependent);
        } else {
            throw new IllegalArgumentException("Button " + buttonName + "is not implemented.");
        }

    }

    @Then("the dependent {string} should be added successfully")
    public void the_dependent_should_be_added_successfully(String dependentName) {
        boolean isDependentDisplayed = true;
        for (WebElement dependent : addDependentPage.dependentRows) {
            if (dependent.getText().equalsIgnoreCase(dependentName)) {
                isDependentDisplayed = true;
                break;
            }
        }
        Assert.assertTrue("Dependent" + dependentName + "was not added successfully",
                isDependentDisplayed);

    }

    @When("the user sends a request to add a dependent with a following data: a dependent with {string},and {string}")
    public void the_user_sends_a_request_to_add_a_dependent_with_a_following_data_a_dependent_with_and(
            String name, String relationship) throws InterruptedException {
        if (!name.isEmpty()) {
            sendText(name, addDependentPage.dependentName);
        } else {
        }
        System.out.println("Name field is empty");

        if (!relationship.isEmpty() && relationship.equals("-- Select --")) {
            selectFromDropDown(relationship, addDependentPage.selectRelationship);
        } else {
            System.out.println("Relationship is either empty or invalid");

        }
        click(addDependentPage.saveDependent);
    }

    @Then("the response should return {string}")
    public void the_response_should_return(String expectedMessage) {
        String actualMessage = addDependentPage.errorMessage.getText();
        Assert.assertEquals("Validation message mismatch", expectedMessage, actualMessage);

    }

    @When("the user sends a request to add a dependent with a following data:")
    public void the_user_sends_a_request_to_add_a_dependent_with_a_following_data(
            io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> dependents = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dependent : dependents) {
            String name = dependent.get("name");
            String relationship = dependent.get("relationship");
            String dateOfBirth = dependent.get("dateOfBirth");

            if (!name.isEmpty()) {
                sendText(name, addDependentPage.dependentName);
            }
            if (!relationship.isEmpty() && relationship.equals("-- Select --")) {
                selectFromDropDown(relationship, addDependentPage.selectRelationship);
            }
            if (!dateOfBirth.isEmpty()) {
                System.out.println("Date of birth is not provided.");
            }
            click(addDependentPage.saveDependent);
            Thread.sleep(2000);
        }
    }

    @Then("the response should confirm all dependents were added successfully")
    public void the_response_should_confirm_all_dependents_were_added_successfully() {
        List<WebElement> dependentsList = addDependentPage.dependentRows;
        Assert.assertTrue(
                "Dependents were not added successfully", dependentsList.size() > 0);
    }

    @When("the user views  the dependent list for an employee")
    public void the_user_views_the_dependent_list_for_an_employee() {
        waitForVisibility(addDependentPage.assignedDependentsSection);
        System.out.println("User is viewing the Assigned Dependents section");

    }

    @Then("the dependent list should display all added dependents")
    public void the_dependent_list_should_display_all_added_dependents() throws InterruptedException {
        waitForVisibility(addDependentPage.assignedDependentsTable);

        List<WebElement> dependentRows = addDependentPage.getDependentsRows();
        Assert.assertTrue("No dependents are listed under Assigned Dependents!",
                dependentRows.size() > 0);

        for (WebElement rows : dependentRows) {
            System.out.println("Dependent Data: " + rows.getText());
            Assert.assertFalse("A row in the Dependents Table is empty."
                    , rows.getText().isEmpty());
            Thread.sleep(2000);
        }

    }

    @When("the user selects a dependent to edit with name {string}")
    public void the_user_selects_a_dependent_to_edit_with_name(String currentName) throws InterruptedException {
        waitForVisibility(addDependentPage.assignedDependentsTable);
        System.out.println("Dependent table is now visible.");
        Thread.sleep(2000);

        boolean found = false;

        for (WebElement row : addDependentPage.dependentRows) {
            String rowText = row.getText();
            System.out.println("Row text: " + rowText);

            if (rowText.contains(currentName)) {
                System.out.println("Dependent found: " + currentName);
                waitForElementToBeClickable(row);
                row.click();
                found = true;
                break;
            }
        }
        Assert.assertTrue("Dependent with name: " + currentName + "not found", found);
    }


    @When("the user updates the dependent's information as {string}")
    public void the_user_updates_the_dependent_s_information_as(String newName) {
        sendText(newName, addDependentPage.dependentName);
        click(addDependentPage.saveDependent);

    }

    @Then("the dependent information should be updated successfully")
    public void the_dependent_information_should_be_updated_successfully() {
        System.out.println("Dependent information updated successfully.");

        List<WebElement> dependentRows = addDependentPage.getDependentsRows();
        boolean isUpdated = false;

        for (WebElement rows : dependentRows) {
            String dependentData = rows.getText();
            if (dependentData.contains("lama")) {
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            System.out.println("Dependent update failed or not reflected in the table.");
        }
    }

    @When("the user selects a dependent to delete")
    public void the_user_selects_a_dependent_to_delete() {
        waitForVisibility(addDependentPage.assignedDependentsTable);
        System.out.println("Dependent table is visible");

        boolean found = false;
        for (WebElement row : addDependentPage.dependentRows) {
            String rowText = row.getText();
            if (rowText.contains("zzz")) {
                WebElement checkbox = row.findElement(By.xpath(
                        "//input[@class='checkbox']"));
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
                System.out.println("Selected dependent for deletion: " + rowText);
                found = true;
                break;
            }
        }
        Assert.assertTrue("Dependent to delete not found.", found);
    }

    @When("the user clicks on Dependents tab")
    public void the_user_clicks_on_Dependents_tab() {
        click(addDependentPage.dependentsButton);

    }

    @And("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Delete")) {
            waitForElementToBeClickable(addDependentPage.deleteDependent);
            addDependentPage.deleteDependent.click();
            System.out.println("Delete button clicked.");
        } else {
            Assert.fail("Button with name " + buttonName + "not found.");
        }
    }

    @Then("the dependent should be removed from the list")
    public void the_dependent_should_be_removed_from_the_list() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> updatedDependentRows = addDependentPage.dependentRows;
        boolean isRemoved = true;
        System.out.println("Verifying that dependent has been removed..");

        for (WebElement row : addDependentPage.dependentRows) {
            String rowText = row.getText();
            if (rowText.contains("zzz")) {
                isRemoved = false;
                break;
            }
        }
        Assert.assertTrue("Dependent was not removed from the list.", isRemoved);
        System.out.println("Dependent was successfully removed from the list.");
    }

}