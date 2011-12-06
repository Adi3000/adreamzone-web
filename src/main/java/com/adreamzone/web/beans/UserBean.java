package com.adreamzone.web.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import com.adreamzone.common.database.IDatabaseConstants;
import com.adreamzone.common.database.session.DatabaseSession;
import com.adreamzone.common.model.users.User;

@ManagedBean
@SessionScoped
public class UserBean extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 173422903879328102L;
	private String passwordConfirm;
	private String mailConfirm;
	//TODO move to DataObject User
	private String mail;

	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	/**
	 * @return the mailConfirm
	 */
	public String getMailConfirm() {
		return mailConfirm;
	}
	/**
	 * @param mailConfirm the mailConfirm to set
	 */
	public void setMailConfirm(String mailConfirm) {
		this.mailConfirm = mailConfirm;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void validateAddressMail(ComponentSystemEvent event){

		FacesContext fc = FacesContext.getCurrentInstance();

		UIComponent components = event.getComponent();

		//get textbox1 value
		UIInput uiText1 = (UIInput)components.findComponent("addressMailField");
		

		//get textbox2 value
		UIInput uiText2 = (UIInput)components.findComponent("addressMailFieldConfirmation");
		if(uiText1 != null && uiText2 != null && uiText2.getLocalValue() != null && uiText1.getLocalValue() != null){
			String text1 = uiText1.getLocalValue().toString();
			String text2 = uiText2.getLocalValue().toString();
	
			if(!text1.equals(text2)){
	
				FacesMessage msg = new FacesMessage("Email check failed", 
						"Address mail do not match with previous given");
	
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	
				fc.addMessage(components.getClientId(), msg);
	
				//passed to the Render Response phase
				fc.renderResponse();
			}
		}
	}
	public void validatePassword(ComponentSystemEvent event){

		FacesContext fc = FacesContext.getCurrentInstance();

		UIComponent components = event.getComponent();

		//get textbox1 value
		UIInput uiText1 = (UIInput)components.findComponent("passwordField");
		

		//get textbox2 value
		UIInput uiText2 = (UIInput)components.findComponent("passwordFieldConfirmation");
		if(uiText1 != null && uiText2 != null && uiText2.getLocalValue() != null && uiText1.getLocalValue() != null){
			String text2 = uiText2.getLocalValue().toString();
			String text1 = uiText1.getLocalValue().toString();
			if(!text1.equals(text2)){
	
				FacesMessage msg = new FacesMessage("Password check failed", 
						"Password do not match with previous given");
	
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	
				fc.addMessage(components.getClientId(), msg);
	
				//passed to the Render Response phase
				fc.renderResponse();
			}
		}
	}
	public String registerUser()
	{
		User newUser = new User();
		newUser.setLogin(getLogin());
		newUser.setPassword(getPassword());
		DatabaseSession db =  new DatabaseSession();
		newUser.setDatabaseOperation(IDatabaseConstants.INSERT);
		db.persist(newUser);
		db.commit();
		return "test";

	}
}
