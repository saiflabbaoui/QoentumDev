package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.ServiceMobileDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumm.ServiceMobile;
import com.sfm.qoentum.service.qoentumm.ServiceMobileService;


@Service(value = "serviceMobileService")
public class ServiceMobileServiceImpl implements ServiceMobileService {
	
	@Autowired
	private ServiceMobileDao serviceMobileDao;

	@Override
	public ServiceMobile save(ServiceMobile serviceMobile) {
		return serviceMobileDao.save(serviceMobile);
	}

	@Override
	public List<ServiceMobile> findAll() {
		List<ServiceMobile> list = new ArrayList<>();
		serviceMobileDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		serviceMobileDao.deleteById(id);
		
	}

	@Override
	public ServiceMobile findById(Long id) {
		return serviceMobileDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return serviceMobileDao.count();
	}
	
	public EntityPage<ServiceMobile> findByNomContaining(String nom, Pageable pageable) {
		
		Page<ServiceMobile> serviceMobilesPage = serviceMobileDao.findByServiceContaining(nom, pageable);
		
		EntityPage<ServiceMobile> serviceMobiles = new EntityPage<ServiceMobile>();
		
		serviceMobiles.setList(serviceMobilesPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(serviceMobilesPage.getNumberOfElements());
		pageUtil.setNombrePage(serviceMobilesPage.getTotalPages());
		pageUtil.setNumeroPage(serviceMobilesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(serviceMobilesPage.getTotalElements());
		
		serviceMobiles.setPageUtil(pageUtil);
		
		return serviceMobiles;
	}
	
	@Override
	public boolean existsServiceMobileByService(String service, Long id) {
		if(id==0) {
			return serviceMobileDao.existsServiceMobileByService(service);
		} else {
			return serviceMobileDao.existsServiceMobileByServiceAndIdIsNot(service, id);
		}
	}
}
