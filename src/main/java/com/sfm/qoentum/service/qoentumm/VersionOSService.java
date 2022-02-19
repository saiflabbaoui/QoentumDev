package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.VersionOS;

public interface VersionOSService {
	
	VersionOS save(VersionOS versionOS);
	
    List<VersionOS> findAll();
    
    void delete(long id);
    
    VersionOS findById(Long id);
    
    long count();
    
    VersionOS findByVersion(String version);
    
    EntityPage<VersionOS> findByNomContaining(String version, Pageable pageable);
    
    boolean existsVersionOSByVersion(String version, Long id);
    
}
