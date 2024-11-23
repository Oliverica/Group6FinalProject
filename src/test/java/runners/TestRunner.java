package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "steps",
        dryRun = false,
<<<<<<< Updated upstream
        tags = "@profilePhotoUpload",
=======
<<<<<<< HEAD
        tags = "@Jobdetails",
=======
        tags = "@profilePhotoUpload",
>>>>>>> Marina
>>>>>>> Stashed changes
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json",
                "rerun:target/failed.txt"}
)

public class TestRunner {

}
