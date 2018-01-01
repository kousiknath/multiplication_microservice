package com.microservices.book;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber", "junit:target/junit-report.xml" },
		features = "src/test/resources/Multiplication.feature")
public class MultiplicationFeatureTest {

	@Test
	public void contextLoads() {
	}

}
