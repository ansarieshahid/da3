package com.dh.test.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MenuSchedule {
	@Id
	@GeneratedValue
	private long id;
	
	private Date startDate;
	private Date endDate;

	private Double price = 0.0;
	
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	MenuSchedule(){
		
	}
}