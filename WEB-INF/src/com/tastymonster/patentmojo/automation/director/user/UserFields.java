package com.tastymonster.patentmojo.automation.director.user;

import com.tastymonster.patentmojo.automation.director.base.AbstractFields;
import com.tastymonster.patentmojo.automation.director.base.IDirectorFields;
import com.tastymonster.patentmojo.automation.element.base.IClickable;
import com.tastymonster.patentmojo.automation.element.base.IDiv;
import com.tastymonster.patentmojo.automation.element.base.ITextBox;
import com.tastymonster.patentmojo.automation.page.base.BaseCreateUserPage;

public class UserFields extends AbstractFields implements IDirectorFields {

	protected final BaseCreateUserPage page = newPage( BaseCreateUserPage.class );
	public final IClickable btnSubmit = page.btnSubmit;
	public final ITextBox securityQuestionField = page.securityQuestionField;
	public final ITextBox lastNameField = page.lastNameField;
	public final IDiv wrapper = page.wrapper;
	public final ITextBox phoneField = page.phoneField;
	public final ITextBox emailField = page.emailField;
	public final ITextBox firstNameField = page.firstNameField;
	public final IDiv standard_form = page.standard_form;
	public final ITextBox securityAnswerField = page.securityAnswerField;
}
