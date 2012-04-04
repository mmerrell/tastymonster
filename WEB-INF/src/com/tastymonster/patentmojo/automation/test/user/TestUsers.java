package com.tastymonster.patentmojo.automation.test.user;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tastymonster.patentmojo.automation.director.user.UserDirector;
import com.tastymonster.patentmojo.automation.director.user.UserFields;

public class TestUsers {

	@Test( groups = { "admin", "user" } )
	public void testCreateUser() {
		UserFields fields = new UserFields();
		fields.firstNameField.setValue( "Marcus" );
		fields.firstNameField.setWebValue();
		UserDirector director = new UserDirector();
		director.create( fields );
		
		Assert.assertEquals( fields.firstNameField.getWebValue(), "Marcus", "The first name should be 'Marcus'" );
	}
}
