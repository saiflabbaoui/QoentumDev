package com.sfm.qoentum.service.admin;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.Region;

public interface RegionService {
	
	Region save(Region region);
	
    List<Region> findAll();
    
    void delete(long id);
    
    Region findById(Long id);
    
    long count();
    
    EntityPage<Region> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsRegionByNom(String nom, Long id);
    
}
