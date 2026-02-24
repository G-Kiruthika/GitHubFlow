@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/androidTest/assets/features",
        glue = {"steps", "hooks"},
        plugin = {"pretty"}
)
public class TestRunner {
    // Configures Cucumber test execution
}