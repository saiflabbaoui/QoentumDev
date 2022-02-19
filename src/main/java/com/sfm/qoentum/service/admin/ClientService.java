package com.sfm.qoentum.service.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.sfm.qoentum.enumer.EnumTypeLicence;
import com.sfm.qoentum.model.admin.Regulateur;
import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.Client;
import org.springframework.web.multipart.MultipartFile;

public interface ClientService {
	
	Client save(MultipartFile file, String nom, String adresse, String idProjet,
                Date dateDebutLicence, Date dateFinLicence,
                EnumTypeLicence typeLicence, List<Regulateur> regulateurs) throws IOException;
	
    List<Client> findAll();
    
    void delete(long id);
    
    Client findById(Long id);
    
    long count();
    
    EntityPage<Client> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsClientByNom(String nom, Long id);
}
