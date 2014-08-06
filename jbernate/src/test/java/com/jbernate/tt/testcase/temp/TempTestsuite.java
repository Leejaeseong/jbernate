package com.jbernate.tt.testcase.temp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jbernate.tt.temp.StringTest1;

@RunWith(Suite.class)
@SuiteClasses(
	{ 
		IdentObjectTest.class
		, StringTest.class
	}
)
public class TempTestsuite {

}
