package com.sfm.qoentum.service.qoentumf;

import java.util.Date;
import java.util.List;

import com.sfm.qoentum.model.qoentumf.SondeHistory;
import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumf.Sonde;

public interface SondeService {
	
	Sonde save(Sonde sonde);

    Sonde configure(Sonde sonde);
	
    List<Sonde> findAll();
    
    void delete(long id);
    
    Sonde findById(Long id);
    
    long count(long client_id);
    
    Sonde setDerniereConnexion(long idSonde, String hostname, String ipAddress, Date date);
    
    EntityPage<Sonde> findByEmplacementNomContainingOrFournisseurAccesNomContaining(String recherche, Pageable pageable);



}
