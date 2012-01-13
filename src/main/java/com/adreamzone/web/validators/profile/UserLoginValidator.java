package com.adreamzone.web.validators.profile;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.adreamzone.common.database.data.model.users.User;
import com.adreamzone.common.database.data.model.users.Users;

@FacesValidator("com.adreamzone.web.validators.profile.UserLoginValidator")
public class UserLoginValidator implements Validator {
		
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)	throws ValidatorException {
		Users userSearch = new Users();
		User user = userSearch.getUserByLogin(value.toString());
		if(user != null){
 			FacesMessage msg = new FacesMessage("User already exists","This user already exist");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 		}
	}

}
