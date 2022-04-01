package org.springframework.samples.ppinot.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.samples.ppinot.domain.MeasureRedefined;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.service.LogService;
import org.springframework.samples.ppinot.service.MetricService;
import org.springframework.samples.ppinot.util.NuevoTipo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import es.us.isa.ppinot.evaluation.Measure;

@Controller
@RequestMapping("/measure")
public class MeasureController {

	@Autowired
	private MetricService metricService;
	@Autowired
	private LogService logService;
	
	@RequestMapping(value = "/prueba", method = RequestMethod.GET)
	public String detail(Model model) {
		Log ll= logService.findByName("prueba");
		List<Metric> ms= metricService.findByLogId(ll.getId());
		Metric m= ms.get(0);
		List<MeasureRedefined> lm= m.getMeasure();
		List<NuevoTipo> lnt=new ArrayList<NuevoTipo>();
		for(MeasureRedefined mr:lm) {
			lnt.add(new NuevoTipo(mr.getMeasureScope().getProcessId(),mr.getValue()));
		}
		
	
		 model.addAttribute("datos", lnt);

		System.out.println(lnt);
		return "measure/detail";
	}
}
