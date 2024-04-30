package cucumber.options;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features", plugin="json:target/jsonReports/cucumber-report.json",  glue= {"stepDefinations"},tags= "@DeletePlace")

public class TestRunner {
	
	

}

