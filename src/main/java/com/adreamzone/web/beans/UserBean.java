package com.adreamzone.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
