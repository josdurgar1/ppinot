package org.springframework.samples.ppinot.web;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.swing.text.AbstractDocument.Content;
import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.samples.ppinot.model.User;
import org.springframework.samples.ppinot.service.LogService;
import org.springframework.samples.ppinot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/logs")
public class LogController {

	@Autowired
	private LogService logService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/myLogs", method = RequestMethod.GET)
	public ModelAndView myLogs() {
		Set<Log> myLogs = logService.myLogs();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("myLogs", myLogs);
		modelAndView.setViewName("/log/list");
		return modelAndView;
	}

	@RequestMapping(value = "/newLog", method = RequestMethod.GET)
	public ModelAndView newLog() {
		Log log = logService.create();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("log", log);
		modelAndView.setViewName("/log/add");
		return modelAndView;
	}

	@RequestMapping(value = "/newLog", method = RequestMethod.POST)
	public ModelAndView saveNewLog(@Valid Log log, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.getErrorCount() > 1) {
			modelAndView.setViewName("log/add");
			modelAndView.addObject("log", log);
			modelAndView.addObject("message", bindingResult.getAllErrors());
		} else {
			try {
				log.setFile(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}catch(IllegalStateException f) {
				modelAndView.addObject("messasge", f.getMessage());
			}

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/logs/download/")
					.path(log.getTitle()).path("/db").toUriString();
			log.setDownloadUri(fileDownloadUri);
			logService.save(log);
			modelAndView = myLogs();
			modelAndView.addObject("successMessage", "Log has been add successfully");
//			Set<Log> myLogs = logService.myLogs();
//			modelAndView.addObject("myLogs", myLogs);
//			modelAndView.setViewName("log/list");
//			modelAndView.addObject("fileDownloadUri", fileDownloadUri);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView logDetails(@RequestParam String logId) {
		Log log = logService.logDetails(logId);
//		File file2=(File) log.getFile();
		User user = userService.getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		if (!user.getId().equals(log.getUserId())) {
			modelAndView.setViewName("error-403");
			return modelAndView;
		}
		modelAndView.addObject("log", log);
//		modelAndView.addObject("file2", file2);
		modelAndView.setViewName("log/details");
		return modelAndView;
	}

	@GetMapping("/download/{fileName:.+}/db")
	public ResponseEntity downloadFromDB(@PathVariable String fileName) {
		Log document = logService.findByName(fileName);
		User user = userService.getPrincipal();
		if (!user.getId().equals(document.getUserId())) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "/opss");
			ResponseEntity response = new ResponseEntity<String>(headers, HttpStatus.FOUND);
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/error-403")).build();
		}
		return ResponseEntity.ok().contentType(null)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + ".log\"")
				.body(document.getFile());
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView logDelete(@RequestParam String logId) {
		Log log = logService.logDetails(logId);
		User user = userService.getPrincipal();
		ModelAndView modelAndView = myLogs();
		if (!user.getId().equals(log.getUserId())) {
			modelAndView.setViewName("error-403");
			return modelAndView;
		}
		logService.delete(log);
		modelAndView=myLogs();
		modelAndView.addObject("successMessage", "Log and associated measures has been delete successfully");
		return modelAndView;
	}

}
