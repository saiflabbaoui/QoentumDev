package com.sfm.qoentum.dao.qoentumm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.ServiceMobile;

@Repository
public interface ServiceMobileDao extends JpaRepository<ServiceMobile, Long> {
	Page<ServiceMobile> findByServiceContaining(String service, Pageable pageable);
	boolean existsServiceMobileByService(String service);
	boolean existsServiceMobileByServiceAndIdIsNot(String service, Long id);
}
