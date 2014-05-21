package com.jbernate.tt.testcase.crud;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jbernate.tt.temp.StringTest1;

@RunWith(Suite.class)
@SuiteClasses(
	{ 
		CrudOneTableTest.class
		, ViewSelectTest.class
		, ManualQuerySelectTest.class
		, Crud11Test.class
		, Crud1nTest.class
	}
)
public class CrudTestsuite {

}
