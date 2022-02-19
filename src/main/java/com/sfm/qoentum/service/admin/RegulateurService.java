package com.sfm.qoentum.service.admin;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.Regulateur;

public interface RegulateurService {
	
	Regulateur save(Regulateur regulateur);
	
    List<Regulateur> findAll();
    
    void delete(long id);
    
    Regulateur findById(Long id);
    
    long count();
    
    EntityPage<Regulateur> findByNomContaining(String nom, Pageable pageable);
    
    EntityPage<Regulateur> findByClient(Client client, Pageable pageable);
}
