package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.model.qoentumm.Operateur;

public interface OperateurService {
	
	Operateur save(Operateur operateur);
	
    List<Operateur> findAll();
    
    void delete(long id);
    
    Operateur findById(Long id);
    
    long count();
    
    EntityPage<Operateur> findByNomContaining(String nom, Pageable pageable);
    
    EntityPage<Operateur> findByNomContainingAndClient(Client client, String nom, Pageable pageable);
    
    EntityPage<Operateur> findByRegulateur(Regulateur regulateur, Pageable pageable);
    
    Operateur findByMccAndMnc(String mcc,String mnc);
    
    boolean existsOperateurByNom(String nom, Long id);
    
    boolean existsOperateurByMccAndMnc(String mcc, String mnc, Long id);
    
}
