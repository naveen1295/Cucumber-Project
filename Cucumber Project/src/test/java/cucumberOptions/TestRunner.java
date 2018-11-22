package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//feature file

//step def
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
					glue="stepDefination")
public class TestRunner {

}
