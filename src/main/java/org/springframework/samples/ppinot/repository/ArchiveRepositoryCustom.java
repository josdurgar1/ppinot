package org.springframework.samples.ppinot.repository;

import java.util.Set;

import org.springframework.samples.ppinot.domain.Log;

public interface ArchiveRepositoryCustom {
	
	Set<Log> query(DynamicQuery dynamicQuery);


}
