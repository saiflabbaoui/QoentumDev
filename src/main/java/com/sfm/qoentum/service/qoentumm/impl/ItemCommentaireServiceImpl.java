package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.ItemCommentaireDao;
import com.sfm.qoentum.model.qoentumm.ItemCommentaire;
import com.sfm.qoentum.service.qoentumm.ItemCommentaireService;

@Service(value = "itemCommentaireService")
public class ItemCommentaireServiceImpl implements ItemCommentaireService {

	@Autowired
	private ItemCommentaireDao itemCommentaireDao;

	@Override
	public ItemCommentaire save(ItemCommentaire itemCommentaire) {
		return itemCommentaireDao.save(itemCommentaire);
	}

	@Override
	public List<ItemCommentaire> findAll() {
		List<ItemCommentaire> list = new ArrayList<>();
		itemCommentaireDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		itemCommentaireDao.deleteById(id);
	}

	@Override
	public ItemCommentaire findById(Long id) {
		return itemCommentaireDao.findById(id).get();
	}

}
