package com.adreamzone.web.beans.profile;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.adreamzone.common.database.data.file.File;

@ManagedBean
@RequestScoped
public class FileBean {
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	private List<File> fileList;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7277771912736160425L;
	
	public String uploadFiles(){
		return "editUploadedFile";
	}
	
	public List<File> getFileList() {
		return fileList;
	}
	
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}
	
	
	
	
	
}