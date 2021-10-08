package org.springframework.samples.ppinot.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Archive;
import org.springframework.samples.ppinot.model.User;
import org.springframework.samples.ppinot.service.ArchiveService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/archives")
public class ArchiveController {
	
	@Autowired
	private ArchiveService archiveService;

	@RequestMapping(value = "/myArchives", method = RequestMethod.GET)
	public ModelAndView myArchives(@AuthenticationPrincipal User user) {
		Set<Archive> myArchives = archiveService.myArchives(user);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/archive/list");
		return modelAndView;
	  }
	
}
