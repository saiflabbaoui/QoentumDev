package com.sfm.qoentum.service.qoentumm;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.Commentaire;

public interface CommentaireService {
	
	Commentaire save(Commentaire commentaire);
	
    List<Commentaire> findAll();
    
    void delete(long id);
    
    Commentaire findById(Long id);
    
    EntityPage<Commentaire> findByOperateur(long operateurId, Date start, Date end, Pageable pageable);
    
}
