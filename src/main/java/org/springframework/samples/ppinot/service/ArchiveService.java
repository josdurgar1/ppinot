package org.springframework.samples.ppinot.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Archive;
import org.springframework.samples.ppinot.model.User;
import org.springframework.samples.ppinot.repository.ArchiveRepository;
import org.springframework.stereotype.Service;

@Service
public class ArchiveService {

	@Autowired
	private ArchiveRepository archiveRepository;

	public Set<Archive> myArchives(User user) {
		Set<Archive> myArchives;
//		myArchives = archiveRepository.findMyArchives(user.getId());
//		return myArchives;
		return null;
	}

}
