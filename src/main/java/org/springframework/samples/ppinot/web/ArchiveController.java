package org.springframework.samples.ppinot.web;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Archive;
import org.springframework.samples.ppinot.service.ArchiveService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/archives")
public class ArchiveController {
	
	@Autowired
	private ArchiveService archiveService;

	@RequestMapping(value = "/myArchives", method = RequestMethod.GET)
	public ModelAndView myArchives() {
		List<Archive> myArchives = archiveService.myArchives();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("myArchives",myArchives);
		modelAndView.setViewName("/archive/list");
		return modelAndView;
	  }
	
	@RequestMapping(value = "/newArchive", method = RequestMethod.GET)
	public ModelAndView newArchive() {
		Archive archive=archiveService.create();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("archive",archive);
		modelAndView.setViewName("/archive/add");
		return modelAndView;
	  }
	@RequestMapping(value = "/newArchive", method = RequestMethod.POST)
	public ModelAndView saveNewArchive(@Valid Archive archive, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
			archiveService.save(archive);
			modelAndView.addObject("successMessage", "Archive has been add successfully");
			List<Archive> myArchives = archiveService.myArchives();
			modelAndView.addObject("myArchives",myArchives);
			modelAndView.setViewName("myArchives");

		return modelAndView;
	}

	
}
