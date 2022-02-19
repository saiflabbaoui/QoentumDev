package com.sfm.qoentum.dao.qoentumf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumf.ServiceFixe;

@Repository
public interface ServiceFixeDao extends JpaRepository<ServiceFixe, Long> {
	Page<ServiceFixe> findByServiceContaining(String service, Pageable pageable);
	boolean existsServiceFixeByService(String service);
	boolean existsServiceFixeByServiceAndIdIsNot(String service, Long id);
}
