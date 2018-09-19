package com.dh.test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class MenuSize {
	@Id
	@GeneratedValue
	private long id;
	
	private Double price = 0.0;
	
	@ManyToOne
    @JoinColumn(name = "FK_Status", nullable=false)
	private MetaStatus status;
	
	@ManyToOne
    @JoinColumn(name = "FK_Menu", nullable=false)
	private Menu menu;
	
	@ManyToOne
    @JoinColumn(name = "FK_Size", nullable=false)
	private Size size;
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
	
	public MetaStatus getStatus() {
		return status;
	}

	public void setStatus(MetaStatus status) {
		this.status = status;
	}
	
	public JSONObject getInfo() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("size", this.getSize().getId());
		json.put("price", this.getPrice());
		
		return json;
	}

	MenuSize(){
		
	}
	
	public MenuSize(double price, Menu menu, Size size, MetaStatus status){
		this.setPrice(price);
		this.setMenu(menu);
		this.setSize(size);
		this.setStatus(status);
	}
}