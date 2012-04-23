package com.tastymonster.automation.user;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tastymonster.automation.director.user.UserDirector;
import com.tastymonster.automation.director.user.UserFields;

public class TestUsers {

	@Test( groups = { "admin", "user" } )
	public void testCreateUser() {
		//Set the fields on the user object
		UserFields fields = new UserFields();
		fields.firstNameField.setValue( "Charlie" );
		fields.lastNameField.setValue( "Brown" );
		fields.emailField.setValue( "cbrown@clown.com" );
		fields.phoneField.setValue( "212-555-1212" );
		
		//Create the user however the director needs to
		UserDirector director = new UserDirector();
		director.create( fields );
		director.navigate( fields );
		
		//Make sure the user exists in the UI
		Assert.assertTrue( director.exists( fields ), "The User should exist in the interface" );
	}
	
//	@Test( groups = { "admin", "user" } )
//	public void testLoginAsGuest() {
//		//Create the user however the director needs to
//		UserDirector director = new UserDirector();
//		director.loginAsGuest();
//	}

}
