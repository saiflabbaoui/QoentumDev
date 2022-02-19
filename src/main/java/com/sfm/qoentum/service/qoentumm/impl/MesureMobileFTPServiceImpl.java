package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.MesureMobileFTPDao;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileFTP;
import com.sfm.qoentum.service.qoentumm.MesureMobileFTPService;


@Service(value = "mesureMobileFTPService")
public class MesureMobileFTPServiceImpl implements MesureMobileFTPService {
	
	@Autowired
	private MesureMobileFTPDao mesureMobileFTPDao;

	@Override
	public MesureMobileFTP save(MesureMobileFTP mesureMobileFTP) {
		return mesureMobileFTPDao.save(mesureMobileFTP);
	}

	@Override
	public List<MesureMobileFTP> findAll() {
		List<MesureMobileFTP> list = new ArrayList<>();
		mesureMobileFTPDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		mesureMobileFTPDao.deleteById(id);
		
	}

	@Override
	public MesureMobileFTP findById(Long id) {
		return mesureMobileFTPDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return mesureMobileFTPDao.count();
	}
}
