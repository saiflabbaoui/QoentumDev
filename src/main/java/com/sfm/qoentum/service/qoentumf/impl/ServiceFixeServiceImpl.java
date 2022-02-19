package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumf.ServiceFixeDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumf.ServiceFixe;
import com.sfm.qoentum.service.qoentumf.ServiceFixeService;


@Service(value = "serviceFixeService")
public class ServiceFixeServiceImpl implements ServiceFixeService {
	
	@Autowired
	private ServiceFixeDao serviceFixeDao;

	@Override
	public ServiceFixe save(ServiceFixe serviceFixe) {
		return serviceFixeDao.save(serviceFixe);
	}

	@Override
	public List<ServiceFixe> findAll() {
		List<ServiceFixe> list = new ArrayList<>();
		serviceFixeDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		serviceFixeDao.deleteById(id);
		
	}

	@Override
	public ServiceFixe findById(Long id) {
		return serviceFixeDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return serviceFixeDao.count();
	}
	
	@Override
	public EntityPage<ServiceFixe> findByNomContaining(String nom, Pageable pageable) {
		
		Page<ServiceFixe> serviceFixesPage = serviceFixeDao.findByServiceContaining(nom, pageable);
		
		EntityPage<ServiceFixe> serviceFixes = new EntityPage<ServiceFixe>();
		
		serviceFixes.setList(serviceFixesPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(serviceFixesPage.getNumberOfElements());
		pageUtil.setNombrePage(serviceFixesPage.getTotalPages());
		pageUtil.setNumeroPage(serviceFixesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(serviceFixesPage.getTotalElements());
		
		serviceFixes.setPageUtil(pageUtil);
		
		return serviceFixes;
	}
	
	@Override
	public boolean existsServiceFixeByService(String service, Long id) {
		if (id==0) {
			return serviceFixeDao.existsServiceFixeByService(service);
		} else {
			return serviceFixeDao.existsServiceFixeByServiceAndIdIsNot(service, id);
		}
	}
}
