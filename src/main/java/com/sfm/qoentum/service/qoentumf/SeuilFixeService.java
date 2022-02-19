package com.sfm.qoentum.service.qoentumf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumf.IndicateurFixe;
import com.sfm.qoentum.model.qoentumf.SeuilFixe;

public interface SeuilFixeService {
	
	SeuilFixe save(SeuilFixe seuilFixe);
	
    List<SeuilFixe> findAll();
    
    void delete(long id);
    
    SeuilFixe findById(Long id);
    
    long count();
    
    EntityPage<SeuilFixe> findByLibelleContaining(String libelle, long user_id, Long indicateur_fixe_id, Pageable pageable);
    
    boolean existsPlageSeuilFixeByLibelleAndIndicateurFixeAndUser(String libelle,IndicateurFixe indicateurFixe,User user, Long id);
    
}
