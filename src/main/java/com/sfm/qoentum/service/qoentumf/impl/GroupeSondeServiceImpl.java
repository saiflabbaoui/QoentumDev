package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumf.GroupeSondeDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumf.GroupeSonde;
import com.sfm.qoentum.service.qoentumf.GroupeSondeService;

@Service(value = "groupeSondeService")
public class GroupeSondeServiceImpl implements GroupeSondeService {

	@Autowired
	private GroupeSondeDao groupeSondeDao;

	@Override
	public GroupeSonde save(GroupeSonde groupeGroupeSonde) {
		return groupeSondeDao.save(groupeGroupeSonde);
	}

	@Override
	public List<GroupeSonde> findAll() {
		List<GroupeSonde> list = new ArrayList<>();
		groupeSondeDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		groupeSondeDao.deleteById(id);

	}

	@Override
	public GroupeSonde findById(Long id) {
		return groupeSondeDao.findById(id).get();
	}

	@Override
	public long count() {
		return groupeSondeDao.count();
	}

	@Override
	public EntityPage<GroupeSonde> findByNomContaining(String nom, Pageable pageable) {

		Page<GroupeSonde> groupeSondesPage = groupeSondeDao.findByNomContaining(nom, pageable);

		EntityPage<GroupeSonde> groupeSondes = new EntityPage<GroupeSonde>();

		groupeSondes.setList(groupeSondesPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(groupeSondesPage.getNumberOfElements());
		pageUtil.setNombrePage(groupeSondesPage.getTotalPages());
		pageUtil.setNumeroPage(groupeSondesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(groupeSondesPage.getTotalElements());

		groupeSondes.setPageUtil(pageUtil);

		return groupeSondes;
	}

	@Override
	public EntityPage<GroupeSonde> findByUser(User user, Pageable pageable) {

		Page<GroupeSonde> groupeSondesPage = groupeSondeDao.findByUser(user, pageable);

		EntityPage<GroupeSonde> groupeSondes = new EntityPage<GroupeSonde>();

		groupeSondes.setList(groupeSondesPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(groupeSondesPage.getNumberOfElements());
		pageUtil.setNombrePage(groupeSondesPage.getTotalPages());
		pageUtil.setNumeroPage(groupeSondesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(groupeSondesPage.getTotalElements());

		groupeSondes.setPageUtil(pageUtil);

		return groupeSondes;
	}

	@Override
	public boolean existsGroupeSondeByNom(String nom, Long id) {
		if(id ==0) {
			return groupeSondeDao.existsGroupeSondeByNom(nom);
		} else {
			return groupeSondeDao.existsGroupeSondeByNomAndIdIsNot(nom, id);
		}
	}
}
