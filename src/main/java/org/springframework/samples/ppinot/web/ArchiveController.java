package org.springframework.samples.ppinot.web;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.Set;

import javax.swing.text.AbstractDocument.Content;
import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.ppinot.domain.Archive;
import org.springframework.samples.ppinot.model.User;
import org.springframework.samples.ppinot.service.ArchiveService;
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
@RequestMapping("/archives")
public class ArchiveController {

	@Autowired
	private ArchiveService archiveService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/myArchives", method = RequestMethod.GET)
	public ModelAndView myArchives() {
		Set<Archive> myArchives = archiveService.myArchives();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("myArchives", myArchives);
		modelAndView.setViewName("/archive/list");
		return modelAndView;
	}

	@RequestMapping(value = "/newArchive", method = RequestMethod.GET)
	public ModelAndView newArchive() {
		Archive archive = archiveService.create();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("archive", archive);
		modelAndView.setViewName("/archive/add");
		return modelAndView;
	}

	@RequestMapping(value = "/newArchive", method = RequestMethod.POST)
	public ModelAndView saveNewArchive(@Valid Archive archive, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.getErrorCount() > 1) {
			modelAndView.setViewName("archive/add");
			modelAndView.addObject("archive", archive);
			modelAndView.addObject("message", bindingResult.getAllErrors());
		} else {
			try {
				archive.setFile(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/archives/download/")
					.path(archive.getTitle()).path("/db").toUriString();
			archive.setDownloadUri(fileDownloadUri);
			archiveService.save(archive);
			modelAndView.addObject("successMessage", "Archive has been add successfully");
			Set<Archive> myArchives = archiveService.myArchives();
			modelAndView.addObject("myArchives", myArchives);
			modelAndView.setViewName("archive/list");
//			modelAndView.addObject("fileDownloadUri", fileDownloadUri);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView archiveDetails(@RequestParam String archiveId) {
		Archive archive = archiveService.archiveDetails(archiveId);
//		File file2=(File) archive.getFile();
		User user = userService.getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		if (!user.getId().equals(archive.getUserId())) {
			modelAndView.setViewName("error-403");
			return modelAndView;
		}
		modelAndView.addObject("archive", archive);
//		modelAndView.addObject("file2", file2);
		modelAndView.setViewName("archive/details");
		return modelAndView;
	}

	@GetMapping("/download/{fileName:.+}/db")
	public ResponseEntity downloadFromDB(@PathVariable String fileName) {
		Archive document = archiveService.findByName(fileName);
		User user = userService.getPrincipal();
		if (!user.getId().equals(document.getUserId())) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "/opss");   
			ResponseEntity response= new ResponseEntity<String>(headers,HttpStatus.FOUND);
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/error-403")).build();
		}
		return ResponseEntity.ok().contentType(null)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + ".txt\"")
				.body(document.getFile());
	}

}
