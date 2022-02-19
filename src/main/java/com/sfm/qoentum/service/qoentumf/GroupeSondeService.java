package com.sfm.qoentum.service.qoentumf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumf.GroupeSonde;

public interface GroupeSondeService {
	
	GroupeSonde save(GroupeSonde groupeGroupeSonde);
	
    List<GroupeSonde> findAll();
    
    void delete(long id);
    
    GroupeSonde findById(Long id);
    
    long count();
    
    EntityPage<GroupeSonde> findByNomContaining(String nom, Pageable pageable);
    
    EntityPage<GroupeSonde> findByUser(User user, Pageable pageable);
    
    boolean existsGroupeSondeByNom(String nom, Long id);
    
}
