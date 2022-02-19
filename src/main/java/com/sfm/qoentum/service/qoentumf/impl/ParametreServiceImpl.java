package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumf.ParametreDao;
import com.sfm.qoentum.model.qoentumf.Parametre;
import com.sfm.qoentum.service.qoentumf.ParametreService;


@Service(value = "parametreService")
public class ParametreServiceImpl implements ParametreService {
	
	@Autowired
	private ParametreDao parametreDao;

	@Override
	public Parametre save(Parametre parametre) {
		return parametreDao.save(parametre);
	}

	@Override
	public List<Parametre> findAll() {
		List<Parametre> list = new ArrayList<>();
		parametreDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		parametreDao.deleteById(id);
		
	}

	@Override
	public Parametre findById(Long id) {
		return parametreDao.findById(id).get();
	}

}
