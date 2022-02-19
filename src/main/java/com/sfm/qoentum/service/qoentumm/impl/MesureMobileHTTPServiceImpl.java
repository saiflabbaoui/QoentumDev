package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.MesureMobileHTTPDao;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileHTTP;
import com.sfm.qoentum.service.qoentumm.MesureMobileHTTPService;


@Service(value = "mesureMobileHTTPService")
public class MesureMobileHTTPServiceImpl implements MesureMobileHTTPService {
	
	@Autowired
	private MesureMobileHTTPDao mesureMobileHTTPDao;

	@Override
	public MesureMobileHTTP save(MesureMobileHTTP mesureMobileHTTP) {
		return mesureMobileHTTPDao.save(mesureMobileHTTP);
	}

	@Override
	public List<MesureMobileHTTP> findAll() {
		List<MesureMobileHTTP> list = new ArrayList<>();
		mesureMobileHTTPDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		mesureMobileHTTPDao.deleteById(id);
		
	}

	@Override
	public MesureMobileHTTP findById(Long id) {
		return mesureMobileHTTPDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return mesureMobileHTTPDao.count();
	}
}
