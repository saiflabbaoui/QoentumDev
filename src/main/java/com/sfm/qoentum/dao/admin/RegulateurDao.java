package com.sfm.qoentum.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.Regulateur;

@Repository
public interface RegulateurDao extends JpaRepository<Regulateur, Long> {
	Page<Regulateur> findByNomContaining(String nom, Pageable pageable);
	Page<Regulateur> findByClients(Client client, Pageable pageable);
}
