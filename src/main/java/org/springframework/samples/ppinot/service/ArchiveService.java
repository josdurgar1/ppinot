package org.springframework.samples.ppinot.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Archive;
import org.springframework.samples.ppinot.model.User;
import org.springframework.samples.ppinot.repository.ArchiveRepository;
import org.springframework.stereotype.Service;

@Service
public class ArchiveService {

	@Autowired
	private ArchiveRepository archiveRepository;
	@Autowired
	private UserService userService;

	public List<Archive> myArchives(User user) {
		List<Archive> myArchives;
		myArchives = archiveRepository.findByUserId(user.getId());
		return myArchives;
	}

	public Archive create() {
		Archive result;
		result=new Archive();
		User user = userService.getPrincipal();
		result.setUserId(user.getId());
		return result;
	}

	public void save(@Valid Archive archive) {
		//assert.assertNotNull(archive);
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date date =  new Date();
//		formatter.format(date);
        archive.setUploadDate(date);
		archiveRepository.save(archive);
		
	}

	public List<Archive> myArchives() {
		User user=userService.getPrincipal();
		List<Archive> myArchives;
		String userId=user.getId();
		myArchives = archiveRepository.findByUserId(userId);
		return myArchives;
	}

}
