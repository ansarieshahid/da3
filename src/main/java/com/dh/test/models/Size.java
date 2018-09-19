package com.dh.test.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Size {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String title = "-";	

	@ManyToOne
    @JoinColumn(name = "FK_Store", nullable=false)
	private Store store;
	
	@ManyToOne
    @JoinColumn(name = "FK_Status", nullable=false)
	private MetaStatus status;
	
	@OneToMany(mappedBy = "size")
	private List<MenuSize> menuSizeList;
		
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<MenuSize> getMenuSizeList() {
		return menuSizeList;
	}

	public void setMenuSizeList(List<MenuSize> menuSizeList) {
		this.menuSizeList = menuSizeList;
	}

	public MetaStatus getStatus() {
		return status;
	}

	public void setStatus(MetaStatus status) {
		this.status = status;
	}

	Size(){
		
	}
	
	public Size(String title, Store store, MetaStatus status){
		this.setTitle(title);
		this.setStore(store);
		this.setStatus(status);
	}
}