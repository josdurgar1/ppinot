package org.springframework.samples.ppinot.repository;

import java.util.Set;

import org.springframework.samples.ppinot.domain.Archive;

public interface ArchiveRepositoryCustom {
	
	Set<Archive> query(DynamicQuery dynamicQuery);


}
