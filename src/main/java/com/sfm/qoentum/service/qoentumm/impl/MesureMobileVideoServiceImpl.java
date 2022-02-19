package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.MesureMobileVideoDao;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobileVideo;
import com.sfm.qoentum.service.qoentumm.MesureMobileVideoService;


@Service(value = "mesureMobileVideoService")
public class MesureMobileVideoServiceImpl implements MesureMobileVideoService {
	
	@Autowired
	private MesureMobileVideoDao mesureMobileVideoDao;

	@Override
	public MesureMobileVideo save(MesureMobileVideo mesureMobileVideo) {
		return mesureMobileVideoDao.save(mesureMobileVideo);
	}

	@Override
	public List<MesureMobileVideo> findAll() {
		List<MesureMobileVideo> list = new ArrayList<>();
		mesureMobileVideoDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		mesureMobileVideoDao.deleteById(id);
		
	}

	@Override
	public MesureMobileVideo findById(Long id) {
		return mesureMobileVideoDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return mesureMobileVideoDao.count();
	}
}
