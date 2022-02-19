package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.MobilePhone;

public interface MobilePhoneService {
	
	MobilePhone save(MobilePhone mobilePhone);
	
    List<MobilePhone> findAll();
    
    void delete(long id);
    
    MobilePhone findById(Long id);
    
    long count();

    void update(long id, MobilePhone mobilePhone);

    MobilePhone getMobilePhoneByUuid(String uuid);

    EntityPage<MobilePhone> findByModeleContaining(String modele, Pageable pageable);
    
}
