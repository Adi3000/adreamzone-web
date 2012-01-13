package com.adreamzone.web.beans.profile;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.util.PropertiesHelper;

import com.adreamzone.common.database.IDatabaseConstants;
import com.adreamzone.common.database.session.DatabaseSession;
import com.adreamzone.common.database.data.model.users.User;
import com.adreamzone.common.security.Security;

@ManagedBean
@SessionScoped
public class UserBean extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 173422903879328102L;
	private transient String passwordConfirm;
	private transient String mailConfirm;
	private boolean newUser;

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
	 * @return the newUser
	 */
	public boolean isNewUser() {
		return newUser;
	}
	/**
	 * @param newUser the newUser to set
	 */
	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
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
		this.setLogginUserInfo(newUser);
		DatabaseSession db =  new DatabaseSession();
		newUser.setDatabaseOperation(IDatabaseConstants.INSERT);
		db.persist(newUser);
		db.commit();
		this.newUser = true;
		return "test";

	}
	public String loginUser()
	{
		User user = new User();
		this.setLogginUserInfo(user);
		DatabaseSession db =  new DatabaseSession();
		user.setDatabaseOperation(IDatabaseConstants.UPDATE);
		db.persist(user);
		db.commit();
		this.newUser = false;
		return "test";

	}
	private void setLogginUserInfo(User user){
		Timestamp now = new Timestamp(new java.util.Date().getTime());
		ExternalContext externe = FacesContext.getCurrentInstance().getExternalContext();
		String ip = ((HttpServletRequest)externe.getRequest()).getRemoteAddr();
		String host = ((HttpServletRequest)externe.getRequest()).getRemoteHost();
		this.setLastDateLogin(now);
		this.setLastIpLogin(ip);
		this.setLastHostNameLogin(host);
		try {
			PropertyUtils.copyProperties(user, this);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setToken(Security.generateSessionID(this.hashCode(), user) );
		this.setToken(user.getToken());
	}
}
