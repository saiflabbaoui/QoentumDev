package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.MesureMobileSMSDao;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileSMS;
import com.sfm.qoentum.service.qoentumm.MesureMobileSMSService;


@Service(value = "mesureMobileSMSService")
public class MesureMobileSMSServiceImpl implements MesureMobileSMSService {
	
	@Autowired
	private MesureMobileSMSDao mesureMobileSMSDao;

	@Override
	public MesureMobileSMS save(MesureMobileSMS mesureMobileSMS) {
		return mesureMobileSMSDao.save(mesureMobileSMS);
	}

	@Override
	public List<MesureMobileSMS> findAll() {
		List<MesureMobileSMS> list = new ArrayList<>();
		mesureMobileSMSDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		mesureMobileSMSDao.deleteById(id);
		
	}

	@Override
	public MesureMobileSMS findById(Long id) {
		return mesureMobileSMSDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return mesureMobileSMSDao.count();
	}
}
