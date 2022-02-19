package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.BrutMesureMobileDao;
import com.sfm.qoentum.model.qoentumm.BrutMesureMobile;
import com.sfm.qoentum.service.qoentumm.BrutMesureMobileService;


@Service(value = "brutMesureMobileService")
public class BrutMesureMobileServiceImpl implements BrutMesureMobileService {
	
	@Autowired
	private BrutMesureMobileDao brutMesureMobileDao;

	@Override
	public BrutMesureMobile save(BrutMesureMobile brutMesureMobile) {
		return brutMesureMobileDao.save(brutMesureMobile);
	}

	@Override
	public List<BrutMesureMobile> findAll() {
		List<BrutMesureMobile> list = new ArrayList<>();
		brutMesureMobileDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public List<BrutMesureMobile> findAll(int nombreElement) {
		
		Page<BrutMesureMobile> brutMesureMobilePage = brutMesureMobileDao.findAll(
				PageRequest.of(0, nombreElement, Sort.by(Sort.Direction.ASC, "id"))
				);
		
		return brutMesureMobilePage.getContent();
	}

	@Override
	public void delete(long id) {
		brutMesureMobileDao.deleteById(id);
		
	}

	@Override
	public BrutMesureMobile findById(Long id) {
		return brutMesureMobileDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return brutMesureMobileDao.count();
	}

	@Override
	public String generateToken() {
		String token = UUID.randomUUID().toString();
		System.out.println(token);
		return token;
	}
}
