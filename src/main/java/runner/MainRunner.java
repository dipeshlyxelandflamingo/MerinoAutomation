package runner;

import java.util.Collections;

import org.testng.TestNG;

public class MainRunner {

	public static void main(String[] args) {
		  TestNG testng = new TestNG();
	        testng.setTestSuites(Collections.singletonList("NewMerino.xml"));
	        testng.run();
	    }

	}


