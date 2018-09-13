package com.dh.test;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class TTReport {
	@Id
	@GeneratedValue
	private Long id;
	
	private String reportId;
	
	private Timestamp timeStamp;
	
	@OneToMany(mappedBy = "report")
	private List<TimeTrack> timeTrackList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<TimeTrack> getTimeTrackList() {
		return timeTrackList;
	}

	public void setTimeTrackList(List<TimeTrack> timeTrackList) {
		this.timeTrackList = timeTrackList;
	}

	TTReport(){
		
	}
	
	TTReport(String reportId, Timestamp timeStamp){
		this.setReportId(reportId);
		this.setTimeStamp(timeStamp);
	}
	
	public JSONObject getInfo() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("id", this.getId());
		json.put("reportId", this.getReportId());
		json.put("timestamp", this.getTimeStamp());
		return json;
	}
}