package com.sfm.qoentum.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Long> {
	Page<Client> findByNomContaining(String nom, Pageable pageable);
	boolean existsClientByNom(String nom);
	boolean existsClientByNomAndIdIsNot(String nom, Long id);
}
