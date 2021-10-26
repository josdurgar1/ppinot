package org.springframework.samples.ppinot.web;

import java.util.Arrays;
import java.util.List;

import org.h2.util.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewController {

//	@RequestMapping(value = "/news", method = RequestMethod.GET)
//	public ModelAndView signup() {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("/new/news");
//		return modelAndView;
//	}

	@GetMapping(value = "/news")
	public ModelAndView git() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/new/news");
		String uri = "https://api.github.com/repos/josdurgar1/ppinot/commits";
		RestTemplate resTemplate = new RestTemplate();
		Object result = resTemplate.getForObject(uri, Object.class);
		modelAndView.addObject("git", result);
		return modelAndView;

	}

	

}
