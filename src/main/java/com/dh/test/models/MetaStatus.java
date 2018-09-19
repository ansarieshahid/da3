package com.dh.test.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MetaStatus {
	@Id
	@GeneratedValue
	private long id;
	
	private String code = "-";	
	private String title = "-";
	
	@OneToMany(mappedBy = "status")
	private List<Menu> menuList;
	
	@OneToMany(mappedBy = "status")
	private List<MenuSize> menuSizeList;
	
	@OneToMany(mappedBy = "status")
	private List<User> userList;
	
	@OneToMany(mappedBy = "status")
	private List<Store> storeList;
		
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	MetaStatus(){
		
	}
}