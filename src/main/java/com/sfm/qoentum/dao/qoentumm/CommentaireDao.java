package com.sfm.qoentum.dao.qoentumm;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumm.Commentaire;

@Repository
public interface CommentaireDao extends JpaRepository<Commentaire, Long> {
	Page<Commentaire> findByOperateurIdAndDateBetweenOrderByDateDesc(long operateurId, Date start, Date end, Pageable pageable);
}
