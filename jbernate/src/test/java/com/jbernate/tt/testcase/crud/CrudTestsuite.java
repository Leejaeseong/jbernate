package com.jbernate.tt.testcase.crud;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{ 
		CrudOneTableTest.class
		, ViewSelectTest.class
		, ManualQuerySelectTest.class
		, NativeQuerySelectTest.class
		, Crud11Test.class
		, Crud1nTest.class		
	}
)
public class CrudTestsuite {

}
