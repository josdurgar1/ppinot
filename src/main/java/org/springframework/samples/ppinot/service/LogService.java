package org.springframework.samples.ppinot.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.samples.ppinot.model.User;
import org.springframework.samples.ppinot.repository.LogRepository;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Evaluation;
import es.us.isa.ppinot.evaluation.computers.TimeMeasureComputer;
import es.us.isa.ppinot.model.MeasureDefinition;
import es.us.isa.ppinot.model.base.TimeMeasure;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;
	@Autowired
	private UserService userService;

	public Set<Log> myLogs(User user) {
		Set<Log> myLogs;
		myLogs = logRepository.findByUserId(user.getId());
		return myLogs;
	}

	public Log create() {
		Log result;
		result=new Log();
		User user = userService.getPrincipal();
		List<String> assignedMetrics=new ArrayList<String>();
		result.setAssignedMetrics(assignedMetrics);
//		Date date = new Date();
//		result.setUploadDate(date);
		result.setUserId(user.getId());
//		result.setAssignedMetrics(0);
		return result;
	}

	public void save(@Valid Log log) {
		//assert.assertNotNull(log);
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//		LocalDateTime date =  LocalDateTime.now();
		Date date = new Date();
//		formatter.format(date);
        log.setUploadDate(date);
		logRepository.save(log);
		
	}

	public Set<Log> myLogs() {
		User user=userService.getPrincipal();
		Set<Log> myLogs;
		String userId=user.getId();
		myLogs = logRepository.findByUserId(userId);
		return myLogs;
	}

	public Log logDetails(String logId) {
		Log result;
		Optional<Log> log= logRepository.findById(logId);
		result=log.get();
		return result;
	}

	public Log findByName(String title) {
		Log result;
		Optional<Log> log=logRepository.findByTitle(title);
		result= log.get();
		return result;
	}

	public Log findById(String logId) {
		Log result;
		Optional<Log> log=logRepository.findById(logId);
		result= log.get();
		return result;
	}

	public void delete(Log log) {
		
		logRepository.delete(log);
		
	}

	public void addTimeMeasure(String logId, @Valid TimeMeasure timeMeasure) {
		
	}

}
