package com.sfm.qoentum.service.qoentumm.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumm.CommentaireDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.qoentumm.Commentaire;
import com.sfm.qoentum.service.qoentumm.CommentaireService;

@Service(value = "commentaireService")
public class CommentaireServiceImpl implements CommentaireService {

	@Autowired
	private CommentaireDao commentaireDao;

	@Override
	public Commentaire save(Commentaire commentaire) {
		return commentaireDao.save(commentaire);
	}

	@Override
	public List<Commentaire> findAll() {
		List<Commentaire> list = new ArrayList<>();
		commentaireDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		commentaireDao.deleteById(id);
	}

	@Override
	public Commentaire findById(Long id) {
		return commentaireDao.findById(id).get();
	}

	@Override
	public EntityPage<Commentaire> findByOperateur(long operateurId, Date start, Date end, Pageable pageable) {

		Page<Commentaire> commentairesPage = commentaireDao.findByOperateurIdAndDateBetweenOrderByDateDesc(operateurId,
				start, end, pageable);

		EntityPage<Commentaire> commentaires = new EntityPage<Commentaire>();

		commentaires.setList(commentairesPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(commentairesPage.getNumberOfElements());
		pageUtil.setNombrePage(commentairesPage.getTotalPages());
		pageUtil.setNumeroPage(commentairesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(commentairesPage.getTotalElements());

		commentaires.setPageUtil(pageUtil);

		return commentaires;

	}

}
