package com.sfm.qoentum.service.qoentumf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumf.ServiceFixe;

public interface ServiceFixeService {
	
	ServiceFixe save(ServiceFixe serviceFixe);
	
    List<ServiceFixe> findAll();
    
    void delete(long id);
    
    ServiceFixe findById(Long id);
    
    long count();
    
    EntityPage<ServiceFixe> findByNomContaining(String nom, Pageable pageable);
    
    boolean existsServiceFixeByService(String service, Long id);
    
}
