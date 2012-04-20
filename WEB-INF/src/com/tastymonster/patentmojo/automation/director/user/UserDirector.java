package com.tastymonster.patentmojo.automation.director.user;

import com.tastymonster.patentmojo.automation.director.base.AbstractDirector;
import com.tastymonster.patentmojo.automation.director.base.IEntityDirector;
import com.tastymonster.patentmojo.automation.page.base.CreateUserPage;

public class UserDirector extends AbstractDirector implements IEntityDirector<UserFields> {

	private final CreateUserPage createUserPage = newPage( CreateUserPage.class );
	
	@Override
	public void create( UserFields fields ) {
		System.out.println( "Director: Navigating to page" );
		createUserPage.navigate();
		fields.persist();
		fields.btnSubmit.click();
	}

	@Override
	public void navigate( UserFields fields ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update( UserFields oldFields, UserFields newFields ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete( UserFields fields ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists( UserFields fields ) {
		// TODO Auto-generated method stub
		return false;
	}

	public void loginAsGuest() {
		createUserPage.navigate();
		createUserPage.guestLogin.click();
	}

}
