package com.tastymonster.automation.director.user;

import com.tastymonster.automation.director.base.AbstractFields;
import com.tastymonster.automation.director.base.IDirectorFields;
import com.tastymonster.automation.element.base.IClickable;
import com.tastymonster.automation.element.base.IDiv;
import com.tastymonster.automation.element.base.ITextBox;
import com.tastymonster.automation.page.base.BaseCreateUserPage;

public class UserFields extends AbstractFields implements IDirectorFields {

	protected final BaseCreateUserPage page = newPage( BaseCreateUserPage.class );
	public final IClickable btnSubmit = page.btnSubmit;
	public final ITextBox securityQuestionField = page.usersecurityQuestion;
	public final ITextBox lastNameField = page.userlastName;
	public final IDiv wrapper = page.wrapper;
	public final ITextBox phoneField = page.userphone;
	public final ITextBox emailField = page.emailField;
	public final ITextBox firstNameField = page.userfirstName;
	public final ITextBox securityAnswerField = page.usersecurityAnswer;
	
	public UserFields() {
		super.fields.add( securityAnswerField );
		fields.add( lastNameField );
		fields.add( phoneField );
		fields.add( emailField );
		fields.add( firstNameField );
		fields.add( securityAnswerField );
	};
	
}
