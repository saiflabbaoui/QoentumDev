package com.sfm.qoentum.dao.qoentumm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.model.qoentumm.Operateur;

@Repository
public interface OperateurDao extends JpaRepository<Operateur, Long> {
	Page<Operateur> findByNomContainingOrMccContainingOrMncContaining(String nom, String mcc,String mnc, Pageable pageable);
	Page<Operateur> findByRegulateurClientsAndNomContainingOrMccContainingOrMncContaining(Client client, String nom, String mcc,String mnc, Pageable pageable);
	Page<Operateur> findByRegulateur(Regulateur regulateur, Pageable pageable);
	Operateur findByMccAndMnc(String mcc,String mnc);
	boolean existsOperateurByNom(String nom);
	boolean existsOperateurByNomAndIdIsNot(String nom, Long id);
	boolean existsOperateurByMccAndMnc(String mcc, String mnc);
	boolean existsOperateurByMccAndMncAndIdIsNot(String mcc, String mnc, Long id);
}
