package org.springframework.samples.ppinot.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.CountMeasureForm;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.samples.ppinot.domain.Scale;
import org.springframework.samples.ppinot.domain.TimeMeasureForm;
import org.springframework.samples.ppinot.domain.UnitOfMeasure;
import org.springframework.samples.ppinot.service.LogService;
import org.springframework.samples.ppinot.service.MetricService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.us.isa.ppinot.model.condition.TimeMeasureType;
import es.us.isa.ppinot.model.state.BPMNState;
import es.us.isa.ppinot.model.state.ComplexState;
import es.us.isa.ppinot.model.state.GenericState;

@Controller
@RequestMapping("/metrics")
public class MetricController {

	@Autowired
	private LogService logService;
	@Autowired
	private MetricService metricService;
	@Autowired
	private LogController logController;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView new_(@RequestParam String logId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("logId", logId);
		modelAndView.setViewName("/metrics/new");
		return modelAndView;
	}

	@RequestMapping(value = "/newCountMeasure", method = RequestMethod.GET)
	public ModelAndView newCountMeasure(@RequestParam String logId) {
		Log log = logService.findById(logId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("log", log);
		CountMeasureForm countMeasure = new CountMeasureForm();
		modelAndView.addObject("countMeasure", countMeasure);
		modelAndView.addObject("scale_", Scale.values());
		modelAndView.addObject("unitOfMeasure_", UnitOfMeasure.values());
//		modelAndView.addObject("when", GenericState.values());
		List<String>when = new ArrayList<>();
		when.add(GenericState.START.toString());
		when.add(GenericState.END.toString());
		modelAndView.addObject("when", when);
		modelAndView.setViewName("/metrics/newCountMeasure");
		return modelAndView;
	}

	@RequestMapping(value = "/newCountMeasure", method = RequestMethod.POST)
	public ModelAndView newCountMeasure(@RequestParam String logId, CountMeasureForm countMeasure,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/metrics/newCountMeasure");
			modelAndView.addObject("scale_", Scale.values());
			modelAndView.addObject("unitOfMeasure_", UnitOfMeasure.values());
			List<String>when = new ArrayList<>();
			when.add(GenericState.START.toString());
			when.add(GenericState.END.toString());
			modelAndView.addObject("when", when);
		} else {
			try {
				metricService.addCountMeasure(logId, countMeasure);
			} catch (Exception e) {
//				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			modelAndView = logController.logDetails(logId);
			modelAndView.addObject("successMessage", "New TimeMeasure has been add successfully");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/newTimeMeasure", method = RequestMethod.GET)
	public ModelAndView newTimeMeasure(@RequestParam String logId) {
		Log log = logService.findById(logId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("log", log);
		TimeMeasureForm timeMeasure = new TimeMeasureForm();
		modelAndView.addObject("timeMeasure", timeMeasure);
		modelAndView.addObject("scale_", Scale.values());
		modelAndView.addObject("unitOfMeasure_", UnitOfMeasure.values());
		modelAndView.addObject("timeMeasureType", TimeMeasureType.values());
		modelAndView.addObject("state", GenericState.values());
		modelAndView.setViewName("/metrics/newTimeMeasure");
		return modelAndView;
	}

	@RequestMapping(value = "/newTimeMeasure", method = RequestMethod.POST)
	public ModelAndView newTimeMeasure(@RequestParam String logId, TimeMeasureForm timeMeasure,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

//		String appliesTo1= timeMeasure.getFrom().toString();
//		String appliesTo2= timeMeasure.getTo().toString();
//		
//		timeMeasure.setFrom(new TimeInstantCondition(appliesTo1, GenericState.START));
//		timeMeasure.setTo(new TimeInstantCondition(appliesTo2, GenericState.END));
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/metrics/newTimeMeasure");
			modelAndView.addObject("scale_", Scale.values());
			modelAndView.addObject("unitOfMeasure_", UnitOfMeasure.values());
			modelAndView.addObject("timeMeasureType", TimeMeasureType.values());
		} else {
			try {
				metricService.addTimeMeasure(logId, timeMeasure);
			} catch (Exception e) {
//				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			Set<Log> myLogs = logService.myLogs();
//			modelAndView.addObject("myLogs", myLogs);
//			modelAndView.setViewName("log/list");
			modelAndView = logController.logDetails(logId);
			modelAndView.addObject("successMessage", "New TimeMeasure has been add successfully");

		}

		return modelAndView;

	}
}
