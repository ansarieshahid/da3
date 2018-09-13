package com.dh.test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class TimeTrack {
	@Id
	@GeneratedValue
	private long id;
	
	private Date date;
	
	private double hoursWorked = 0;
	private double employeeId;
	private String jobGroup;
	
	@ManyToOne
    @JoinColumn(name = "FKReport", nullable=false)
	private TTReport report;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public double getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(double employeeId) {
		this.employeeId = employeeId;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public TTReport getReport() {
		return report;
	}

	public void setReport(TTReport report) {
		this.report = report;
	}

	TimeTrack(){
		
	}
	
	TimeTrack(String date, double hoursWorked, double employeeId, String jobGroup, TTReport report) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        
		this.setDate(new java.sql.Date(format.parse(date).getTime()));
		this.setEmployeeId(employeeId);
		this.setHoursWorked(hoursWorked);
		this.setJobGroup(jobGroup);
		this.setReport(report);
	}
	
	public JSONObject getInfo() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", this.getId());
		json.put("date", this.getDate());
		json.put("employeeId", this.getEmployeeId());
		json.put("hoursWorked", this.getHoursWorked());
		json.put("jobGroup", this.getJobGroup());
		return json;
	}
}