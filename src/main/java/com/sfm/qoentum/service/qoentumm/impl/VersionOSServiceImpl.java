package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.VersionOSDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumm.VersionOS;
import com.sfm.qoentum.service.qoentumm.VersionOSService;

@Service(value = "versionOSService")
public class VersionOSServiceImpl implements VersionOSService {

	@Autowired
	private VersionOSDao versionOSDao;

	@Override
	public VersionOS save(VersionOS versionOS) {
		return versionOSDao.save(versionOS);
	}

	@Override
	public List<VersionOS> findAll() {
		List<VersionOS> list = new ArrayList<>();
		versionOSDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		versionOSDao.deleteById(id);

	}

	@Override
	public VersionOS findById(Long id) {
		return versionOSDao.findById(id).get();
	}

	@Override
	public long count() {
		return versionOSDao.count();
	}

	@Override
	public VersionOS findByVersion(String version) {
		return versionOSDao.findByVersion(version);
	}

	@Override
	public EntityPage<VersionOS> findByNomContaining(String version, Pageable pageable) {
		Page<VersionOS> versionOSsPage = versionOSDao.findByVersionContaining(version, pageable);

		EntityPage<VersionOS> versionOSs = new EntityPage<VersionOS>();

		versionOSs.setList(versionOSsPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(versionOSsPage.getNumberOfElements());
		pageUtil.setNombrePage(versionOSsPage.getTotalPages());
		pageUtil.setNumeroPage(versionOSsPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(versionOSsPage.getTotalElements());

		versionOSs.setPageUtil(pageUtil);

		return versionOSs;
	}

	@Override
	public boolean existsVersionOSByVersion(String version, Long id) {
		if (id == 0) {
			return versionOSDao.existsVersionOSByVersion(version);
		} else {
			return versionOSDao.existsVersionOSByVersionAndIdIsNot(version, id);
		}
	}
}
