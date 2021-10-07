package org.springframework.samples.ppinot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewController {
	
	
		@RequestMapping(value = "/news", method = RequestMethod.GET)
		public ModelAndView signup() {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("/new/news");
			return modelAndView;
		  }
	

}
