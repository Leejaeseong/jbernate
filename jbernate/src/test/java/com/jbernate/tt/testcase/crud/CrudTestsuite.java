package com.jbernate.tt.testcase.crud;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jbernate.tt.temp.StringTest1;

@RunWith(Suite.class)
@SuiteClasses(
	{ 
		CmCrudTest.class
		, ViewSelectTest.class
		, ManualQuerySelectTest.class
	}
)
public class CrudTestsuite {

}
