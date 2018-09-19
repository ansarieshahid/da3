package com.dh.test.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Store {
	@Id
	@GeneratedValue
	private long id;
	
	private String title = "-";
	private String address = "-";
	private String phone = "-";
	
	private String fileName = "-";
	private String filePath = "-";
	private String fileUrl = "-";
	
	@ManyToOne
    @JoinColumn(name = "FK_Currency", nullable=false)
	private MetaCurrency currency;
	
	@ManyToOne
    @JoinColumn(name = "FK_Status", nullable=false)
	private MetaStatus status;
	
	@OneToMany(mappedBy = "store")
	private List<User> userList;
	
	@OneToMany(mappedBy = "store")
	private List<Menu> menuList;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public MetaCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(MetaCurrency currency) {
		this.currency = currency;
	}

	Store(){
		
	}
}