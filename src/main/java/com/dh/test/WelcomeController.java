package com.dh.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

@Controller
public class WelcomeController {
	
	@Value("${welcome.message:test}")
	private String message = "Hello World";
	
	@Autowired
	TTReportRepository tTReportRepository;
	@Autowired
	TimeTrackRepository timeTrackRepository;
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", "Hello World");
		return "welcome";
	}
	
	@RequestMapping("/reports")
	public String reports(Map<String, Object> model) {
		List<TTReport> reports = tTReportRepository.findAll();
		
		model.put("reports", reports);
		model.put("message", "Hello World");
		
		return "reports";
	}
	
	@RequestMapping("/payroll")
	public String payroll(Map<String, Object> model) {				
		List<Object> reports = timeTrackRepository.getReports();
		
		model.put("reports", reports);
		model.put("message", this.message);
		return "payroll";
	}
	
	@PostMapping("/uploadReport")
    public String uploadReport(@RequestParam("file") MultipartFile file,
    		Map<String, Object> model) throws IllegalStateException, IOException, NumberFormatException, ParseException {

        if (file.isEmpty()) {
        	this.message = "Please select a file to upload";
            return "redirect:payroll";
        }
        
        CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));
        
        List<String[]> lines = reader.readAll();
        List<TimeTrack> ttList = new ArrayList<TimeTrack>();
        String reportId = "";
        
        if(!lines.isEmpty()) {
        	lines.remove(0);
        }
        
        if(!lines.isEmpty()) {
        	String[] line = lines.remove(lines.size()-1);
        	reportId = line[1];
        }
        
        if(!lines.isEmpty() && !reportId.isEmpty()) {  
        	
        	if(tTReportRepository.existsByReportId(reportId)) {
        		this.message = "Duplicate Report Id";
        		return "redirect:payroll";
        	}

        	TTReport report = new TTReport(reportId, timeTrackRepository.getCurrentTime());
        	tTReportRepository.save(report);
	        
        	for(String[] line : lines) {
        		ttList.add(new TimeTrack(line[0],
	            Double.parseDouble(line[1]),
	            Double.parseDouble(line[2]),
	            line[3],
	            report));
	        }
        	
        	timeTrackRepository.saveAll(ttList);
        	
        	this.message = "Upload Success";
	        
        } else {
        	this.message = "Missing Header or Record(s) or Report Id";
        }
		return "redirect:payroll";
	}
}