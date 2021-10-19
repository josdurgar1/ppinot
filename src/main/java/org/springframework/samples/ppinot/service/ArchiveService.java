package org.springframework.samples.ppinot.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
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

	public Set<Archive> myArchives(User user) {
		Set<Archive> myArchives;
		myArchives = archiveRepository.findByUserId(user.getId());
		return myArchives;
	}

	public Archive create() {
		Archive result;
		result=new Archive();
		User user = userService.getPrincipal();
//		Date date = new Date();
//		result.setUploadDate(date);
		result.setUserId(user.getId());
		result.setAssignedMetrics(0);
		return result;
	}

	public void save(@Valid Archive archive) {
		//assert.assertNotNull(archive);
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//		LocalDateTime date =  LocalDateTime.now();
		Date date = new Date();
//		formatter.format(date);
        archive.setUploadDate(date);
		archiveRepository.save(archive);
		
	}

	public Set<Archive> myArchives() {
		User user=userService.getPrincipal();
		Set<Archive> myArchives;
		String userId=user.getId();
		myArchives = archiveRepository.findByUserId(userId);
		return myArchives;
	}

	public Archive archiveDetails(String archiveId) {
		Archive result;
		Optional<Archive> archive= archiveRepository.findById(archiveId);
		result=archive.get();
		return result;
	}

	public Archive findByName(String title) {
		Archive result;
		Optional<Archive> archive=archiveRepository.findByTitle(title);
		result= archive.get();
		return result;
	}

}
