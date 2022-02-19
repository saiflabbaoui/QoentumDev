package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.MobilePhoneDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumm.MobilePhone;
import com.sfm.qoentum.service.qoentumm.MobilePhoneService;


@Service(value = "mobilePhoneService")
public class MobilePhoneServiceImpl implements MobilePhoneService {
	
	@Autowired
	private MobilePhoneDao mobilePhoneDao;

	@Override
	public MobilePhone save(MobilePhone mobilePhone) {
		return mobilePhoneDao.save(mobilePhone);
	}

	@Override
	public List<MobilePhone> findAll() {
		List<MobilePhone> list = new ArrayList<>();
		mobilePhoneDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		mobilePhoneDao.deleteById(id);
		
	}

	@Override
	public MobilePhone findById(Long id) {
		return mobilePhoneDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return mobilePhoneDao.count();
	}

	@Override
	public void update(long id, MobilePhone mobilePhone) {
		MobilePhone mobilePhoneFromDb = mobilePhoneDao.findById(id).get();
		System.out.println(mobilePhoneFromDb.toString());
		if(mobilePhoneFromDb != null) {
			mobilePhoneFromDb.setModele(mobilePhone.getModele());
			mobilePhoneFromDb.setSystemeExploitation(mobilePhone.getSystemeExploitation());
			mobilePhoneFromDb.setUuid(mobilePhone.getUuid());
			mobilePhoneDao.save(mobilePhoneFromDb);
		}else{
			mobilePhoneDao.save(mobilePhone);
		}
	}

	@Override
	public MobilePhone getMobilePhoneByUuid(String uuid) {
		return mobilePhoneDao.getMobilePhoneByUuid(uuid);
	}

	@Override
	public EntityPage<MobilePhone> findByModeleContaining(String modele, Pageable pageable) {
		
		Page<MobilePhone> mobilePhonesPage = mobilePhoneDao.findByModeleContaining(modele, pageable);
		
		EntityPage<MobilePhone> mobilePhones = new EntityPage<MobilePhone>();
		
		mobilePhones.setList(mobilePhonesPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(mobilePhonesPage.getNumberOfElements());
		pageUtil.setNombrePage(mobilePhonesPage.getTotalPages());
		pageUtil.setNumeroPage(mobilePhonesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(mobilePhonesPage.getTotalElements());
		
		mobilePhones.setPageUtil(pageUtil);
		
		return mobilePhones;
	}
}
