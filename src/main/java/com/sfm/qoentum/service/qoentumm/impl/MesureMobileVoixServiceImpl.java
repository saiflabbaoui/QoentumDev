package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.MesureMobileVoixDao;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileVoix;
import com.sfm.qoentum.service.qoentumm.MesureMobileVoixService;


@Service(value = "mesureMobileVoixService")
public class MesureMobileVoixServiceImpl implements MesureMobileVoixService {
	
	@Autowired
	private MesureMobileVoixDao mesureMobileVoixDao;

	@Override
	public MesureMobileVoix save(MesureMobileVoix mesureMobileVoix) {
		return mesureMobileVoixDao.save(mesureMobileVoix);
	}

	@Override
	public List<MesureMobileVoix> findAll() {
		List<MesureMobileVoix> list = new ArrayList<>();
		mesureMobileVoixDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		mesureMobileVoixDao.deleteById(id);
		
	}

	@Override
	public MesureMobileVoix findById(Long id) {
		return mesureMobileVoixDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return mesureMobileVoixDao.count();
	}
}
