package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
<<<<<<< Updated upstream
        features = "src/test/resources/apiFeatures/",
=======
<<<<<<< HEAD
        features = "src/test/resources/apifeatures/",
=======
        features = "src/test/resources/apiFeatures/",
>>>>>>> Marina
>>>>>>> Stashed changes
        glue = "apiSteps",
        dryRun = false,
        tags = "@generateToken",
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json",
                "rerun:target/failed.txt"}
)

public class APIRunner {

}
