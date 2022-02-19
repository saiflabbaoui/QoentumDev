package com.sfm.qoentum.service.admin;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.Continent;

public interface ContinentService {
	
	Continent save(Continent continent);
	
    List<Continent> findAll();
    
    void delete(long id);
    
    Continent findById(Long id);
    
    long count();
    
    EntityPage<Continent> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsContinentByNom(String nom, Long id);
    
}
