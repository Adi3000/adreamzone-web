package com.adreamzone.web.beans.profile;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.annotation.MultipartConfig;

import com.adreamzone.common.database.data.file.File;

@ManagedBean
@RequestScoped
public class FileBean {
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;

	private List<File> fileList;
	private java.io.File file;
	

	public String uploadFiles(){
		return "editUploadedFiles";
	}
	
	public List<File> getFileList() {
		if(fileList == null){
			fileList = new ArrayList<File>();
			fileList.add(new File());
		}
		return fileList;
	}
	
	public java.io.File getFile(int index) {
		return fileList.get(index).getFile();
	}
	
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}
	
	public UserBean getUserBean() {
		return userBean;
	}
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
    public java.io.File getFile() {
        return file;
    }
    
	public void setFile(java.io.File file) {
		this.file = file;
		File newFile = new File();
		if(fileList == null){
			fileList = new ArrayList<File>();
		}
		newFile.setFile(file);
		fileList.add(newFile);
	}


	public void addFileToUpload(){
		fileList.add(new File());
	}
	
}