package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumf.BrutMesureFixeDao;
import com.sfm.qoentum.model.qoentumf.BrutMesureFixe;
import com.sfm.qoentum.service.qoentumf.BrutMesureFixeService;


@Service(value = "brutMesureFixeService")
public class BrutMesureFixeServiceImpl implements BrutMesureFixeService {
	
	@Autowired
	private BrutMesureFixeDao brutMesureFixeDao;

	@Override
	public BrutMesureFixe save(BrutMesureFixe brutMesureFixe) {
		return brutMesureFixeDao.save(brutMesureFixe);
	}

	@Override
	public List<BrutMesureFixe> findAll() {
		List<BrutMesureFixe> list = new ArrayList<>();
		brutMesureFixeDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		brutMesureFixeDao.deleteById(id);
		
	}

	@Override
	public BrutMesureFixe findById(Long id) {
		return brutMesureFixeDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return brutMesureFixeDao.count();
	}
	
	@Override
	public List<BrutMesureFixe> findAll(int nombreElement) {
		
		Page<BrutMesureFixe> brutMesureFixePage = brutMesureFixeDao.findAll(
				PageRequest.of(0, nombreElement, Sort.by(Sort.Direction.ASC, "id"))
				);
		
		return brutMesureFixePage.getContent();
	}
}
