package com.tokopedia.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (features = "src/test/resources/features",
					glue="com.tokopedia.search",
					plugin = {"pretty",
							"html:target/tokopedia-reports/search-scenario.html",
							})

public class RunnerFile extends AbstractTestNGCucumberTests {

}
