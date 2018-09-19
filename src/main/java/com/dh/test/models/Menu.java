package com.dh.test.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String title = "-";
	
	private Date startDate;
	private Date endDate;
	
	long sortOrder;
	
	private String originalFileName = "-";
	private String fileName = "-";
	private String filePath = "-";
	private String fileUrl = "-";
	
	@ManyToOne
    @JoinColumn(name = "FK_Store", nullable=false)
	private Store store;
	
	@ManyToOne
    @JoinColumn(name = "FK_Status", nullable=false)
	private MetaStatus status;
	
	@OneToMany(mappedBy = "menu")
	private List<MenuSize> menuSizeList;
	
	@OneToMany(mappedBy = "menu")
	private List<MenuSchedule> menuScheduleList;
		
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

	public MetaStatus getStatus() {
		return status;
	}

	public void setStatus(MetaStatus status) {
		this.status = status;
	}

	public List<MenuSchedule> getMenuScheduleList() {
		return menuScheduleList;
	}

	public void setMenuScheduleList(List<MenuSchedule> menuScheduleList) {
		this.menuScheduleList = menuScheduleList;
	}

	public long getOrder() {
		return sortOrder;
	}

	public void setOrder(long sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public JSONObject getInfo() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("title", this.getTitle());
		json.put("dateRange", this.startDate.toString() + " to " + this.getEndDate().toString());
		json.put("fileName", this.getOriginalFileName());
		
		return json;
	}
	
	public void setMenu(String title, Date startDate, Date endDate, String originalFileName, String fileName, String filePath, String fileUrl) {
		this.setTitle(title);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setOriginalFileName(originalFileName);
		this.setFileName(fileName);
		this.setFilePath(filePath);
		this.setFileUrl(fileUrl);
	}

	public Menu(){
		
	}
	
	public Menu(String title, Date startDate, Date endDate, String originalFileName, String fileName, String filePath, String fileUrl, Store store, MetaStatus status){
		this.setTitle(title);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setOriginalFileName(originalFileName);
		this.setFileName(fileName);
		this.setFilePath(filePath);
		this.setFileUrl(fileUrl);
		this.setStatus(status);
		this.setStore(store);
	}
}