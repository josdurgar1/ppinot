package org.springframework.samples.ppinot.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.samples.ppinot.domain.Scale;

import org.springframework.samples.ppinot.domain.UnitOfMeasure;
import org.springframework.samples.ppinot.model.User;
import org.springframework.samples.ppinot.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.us.isa.ppinot.model.base.CountMeasure;
import es.us.isa.ppinot.model.base.TimeMeasure;
import es.us.isa.ppinot.model.condition.TimeMeasureType;

@Controller
@RequestMapping("/metrics")
public class MetricController {
	
	@Autowired
	private LogService logService;

	
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView new_(@RequestParam String logId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("logId",logId);
		modelAndView.setViewName("/metrics/new");
		return modelAndView;
	  }
	
	@RequestMapping(value = "/newCountMeasure", method = RequestMethod.GET)
	public ModelAndView newCountMeasure(@RequestParam String logId) {
		Log log = logService.findById(logId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("log",log);
		CountMeasure countMeasure=new CountMeasure();
		modelAndView.addObject("countMeasure",countMeasure);
		modelAndView.addObject("scale_",Scale.values());
		modelAndView.addObject("unitOfMeasure_",UnitOfMeasure.values());
		modelAndView.setViewName("/metrics/newCountMeasure");
		return modelAndView;
	  }
	
	@RequestMapping(value = "/newTimeMeasure", method = RequestMethod.GET)
	public ModelAndView newTimeMeasure(@RequestParam String logId) {
		Log log = logService.findById(logId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("log",log);
		TimeMeasure timeMeasure=new TimeMeasure();
		modelAndView.addObject("timeMeasure",timeMeasure);
		modelAndView.addObject("scale_",Scale.values());
		modelAndView.addObject("unitOfMeasure_",UnitOfMeasure.values());
		modelAndView.addObject("timeMeasureType",TimeMeasureType.values());
		modelAndView.setViewName("/metrics/newTimeMeasure");
		return modelAndView;
	  }
	
	@RequestMapping(value = "/newTimeMeasure", method = RequestMethod.POST)
	public ModelAndView newTimeMeasure(@RequestParam String logId,@Valid TimeMeasure timeMeasure, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/metrics/newTimeMeasure");
		} else {
			logService.addTimeMeasure(logId,timeMeasure);
			
			modelAndView.setViewName("/logs/myLogs");
			modelAndView.addObject("successMessage", "New TimeMeasure has been add successfully");
			

		}
		
		return modelAndView;
		
	}

}
