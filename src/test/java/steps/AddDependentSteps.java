package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.DateUtils;

import java.time.Duration;
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
        sendText(name, employeeSearchPage.emloyeeNameSearchField);
        sendText(empId, employeeSearchPage.employeeIDSearchField);
        click(employeeSearchPage.searchButton);
    }

    @Then("user clicks search button")
    public void user_clicks_search_button() {
        click(addDependentPage.searchEmployee);
    }

    @And("the employee details should be displayed")
    public void the_employee_details_should_be_displayed() throws InterruptedException {
        Assert.assertTrue("Employee details not displayed",
                addDependentPage.resultTable.size() > 0);
    }

    @Then("the user selects the employee {string}")
    public void the_user_selects_the_employee(String empID) throws InterruptedException {
        List<WebElement> allEmployeeId = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
        for (WebElement id : allEmployeeId) {
            if (id.getText().equals(empID)) {
                click(id);
            }
        }
    }

    @And("the user is on the Personal Details Page")
    public void the_user_is_on_the_Personal_Details_Page() throws InterruptedException {
        Assert.assertTrue("Personal Details Page is not displayed",
                addDependentPage.personalDetailsPage.isDisplayed());
        System.out.println("User is on the Personal Details Page");
    }

    @Then("the user clicks on Dependent tab")
    public void the_user_clicks_on_dependent_tab() {
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
        //click(addDependentPage.selectRelationship);

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
        List<WebElement> rows = addDependentPage.dependentRows;
        boolean isDependentDisplayed =
                rows.stream().anyMatch((WebElement row) -> row.getText().contains(dependentName));
        Assert.assertTrue(
                "Dependent '" + dependentName + "'was not found in the table.", isDependentDisplayed);
        System.out.println("Dependent '" + dependentName + "' is displayed.");


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

            sendText(name, addDependentPage.dependentName);
            System.out.println("Dependent name entered :" + name);

            waitForElementToBeClickable(addDependentPage.selectRelationship);
            if (relationship.equalsIgnoreCase("Child")) {
                selectFromDropDown(addDependentPage.selectRelationship, "Child");
            } else {
                selectFromDropDown(relationship, addDependentPage.selectRelationship);
            }
            click(addDependentPage.saveDependent);
            System.out.println("Dependent '" + name + "' with relationship '" + relationship + "' is added.");

            waitForVisibility(addDependentPage.assignedDependentsTable);
            click(addDependentPage.addDependentsButton);
            System.out.println("Clicked add button");
        }
    }

    @Then("the response should confirm all dependents were added successfully:")
    public void the_response_should_confirm_all_dependents_were_added_successfully(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> dependents = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> dependent : dependents) {
            String name = dependent.get("name");

            List<WebElement> rows = addDependentPage.dependentRows;
            boolean isDependentDisplayed =
                    rows.stream().anyMatch(row -> row.getText().contains(name));
            Assert.assertTrue(
                    "Dependent '" + name + "'was not found in the table.", isDependentDisplayed);
            System.out.println("Dependent '" + name + "' is displayed.");
        }

    }

    @When("the user views  the dependent list for an employee")
    public void the_user_views_the_dependent_list_for_an_employee() {
        click(addDependentPage.dependentsButton);
        waitForVisibility(addDependentPage.assignedDependentsSection);
        System.out.println("User is viewing the Assigned Dependents section");

    }

    @Then("the dependent list should display all added dependents")
    public void the_dependent_list_should_display_all_added_dependents() throws InterruptedException {
        waitForVisibility(addDependentPage.assignedDependentsTable);

        List<WebElement> dependentRows = addDependentPage.getDependentsRows();
        Assert.assertTrue("No dependents are listed under Assigned Dependents!",
                dependentRows.size() > 0);

        System.out.println("Dependent list is displayed with " + dependentRows.size() + " dependents.");

    }

    @When("the user selects a dependent to edit with name {string}")
    public void the_user_selects_a_dependent_to_edit_with_name(String currentName) {
        List<WebElement> allDependents = driver.findElements(By.xpath("//table[@id='dependent_list']/tbody/tr/td[@class='dependentName']"));
        System.out.println("Total Dependents Found: " + allDependents.size());
        boolean dependentFound = false;
        for (WebElement dependent : allDependents) {
            System.out.println("Dependent Name: " + dependent.getText());
            if (dependent.getText().equals(currentName)) {
                dependentFound = true;
                System.out.println("Match Found: " + currentName);
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dependent);

                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement clickableDependent = wait.until(ExpectedConditions.elementToBeClickable(dependent));
                    clickableDependent.click();

                    System.out.println("Clicked on: " + currentName);
                } catch (Exception e) {
                    System.err.println("Click failed. Attempting JavaScript click. Error: " + e.getMessage());
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dependent);
                }
                break;
            }
        }
        Assert.assertTrue("Dependent '" + currentName + "' was not found!", dependentFound);
    }

    @When("the user updates the dependent's information as {string}")
    public void the_user_updates_the_dependent_s_information_as(String newName) {
        if (addDependentPage.dependentName.isEnabled()){
            sendText(newName, addDependentPage.dependentName);
        } else {
            System.out.println("Textbox interaction failed: Element not interactable.");
        }
    }

    @Then("the dependent information should be updated successfully")
    public void the_dependent_information_should_be_updated_successfully() {
        Assert.assertTrue(addDependentPage.savedMessage.isDisplayed());
    }


    @When("the user clicks on Dependents tab")
    public void the_user_clicks_on_Dependents_tab() {
        click(addDependentPage.dependentsButton);
        waitForVisibility(addDependentPage.assignedDependentsTable);

    }

    @When("the user selects a dependent to delete {string}")
    public void the_user_selects_a_dependent_to_delete(String name) {
        List<WebElement>rows=addDependentPage.dependentRows;
        boolean found = false;

        for (WebElement row : rows) {
            String dependentName=row.findElement(By.xpath(".//td[@class='dependentName']/a")).getText();
            if (dependentName.equals("Jack Miller")){
                row.findElement(By.xpath(".//td[@class='check']/input[@type='checkbox']")).click();
                break;
            }
        }
        Assert.assertTrue("Dependent to delete not found.", found);
    }

    @And("the user clicks on the Delete button")
    public void the_user_clicks_on_delete_the_button() {
        click(addDependentPage.deleteDependent);
    }

    @Then("the dependent {string} should be removed from the list")
    public void the_dependent_should_be_removed_from_the_list(String name) throws InterruptedException {
        List<WebElement> allDependents = driver.findElements(By.xpath("//table[@id='dependent_list']/tbody/tr/td[2]"));
        boolean isDeleted = false;
        for (WebElement dependent : allDependents) {
            String currentName = dependent.getText();
            if (!currentName.equalsIgnoreCase(name)){
                isDeleted = true;
                System.out.println("The selected dependent was successfully deleted");
            }
        }
    }
}