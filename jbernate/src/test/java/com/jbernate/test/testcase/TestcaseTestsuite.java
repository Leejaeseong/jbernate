package com.jbernate.test.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jbernate.test.temp.StringTest1;

@RunWith(Suite.class)
@SuiteClasses(
	{ 
		CommonCrudTest.class
		, ViewSelectTest.class
		, ManualQuerySelectTest.class
	}
)
public class TestcaseTestsuite {

}
