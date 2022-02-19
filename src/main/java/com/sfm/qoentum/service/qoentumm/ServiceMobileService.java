package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.ServiceMobile;

public interface ServiceMobileService {
	
	ServiceMobile save(ServiceMobile serviceMobile);
	
    List<ServiceMobile> findAll();
    
    void delete(long id);
    
    ServiceMobile findById(Long id);
    
    long count();
    
    EntityPage<ServiceMobile> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsServiceMobileByService(String service, Long id);
    
}
