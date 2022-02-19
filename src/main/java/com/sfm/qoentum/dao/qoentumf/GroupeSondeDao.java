package com.sfm.qoentum.dao.qoentumf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumf.GroupeSonde;

@Repository
public interface GroupeSondeDao extends JpaRepository<GroupeSonde, Long> {
	Page<GroupeSonde> findByNomContaining(String nom, Pageable pageable);
	Page<GroupeSonde> findByUser(User user, Pageable pageable);
	boolean existsGroupeSondeByNom(String nom);
	boolean existsGroupeSondeByNomAndIdIsNot(String nom, Long id);
	Integer countGroupeSondeByEnabled(boolean g);

}
