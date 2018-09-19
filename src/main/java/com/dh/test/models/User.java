package com.dh.test.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	
	private String username;
	private String password;
	
	@ManyToOne
    @JoinColumn(name = "FK_Store", nullable=false)
	private Store store;
	
	@ManyToOne
    @JoinColumn(name = "FK_Status", nullable=false)
	private MetaStatus status;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<Role> roleList;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public MetaStatus getStatus() {
		return status;
	}

	public void setStatus(MetaStatus status) {
		this.status = status;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	User(){
		
	}
	
	public User(User user){
		this.setStatus(user.getStatus());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
		this.setStore(user.getStore());
		this.setRoleList(user.getRoleList());
	}
}