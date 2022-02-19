package com.sfm.qoentum.dao.qoentumf;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.model.qoentumf.Sonde;

import java.util.Date;
import java.util.List;

@Repository
public interface SondeDao extends JpaRepository<Sonde, Long> {
	Page<Sonde> findByClientIdAndEmplacementNomContainingOrClientIdAndFournisseurAccesNomContaining(long clientId, String emplacementNom, long clientId2, String fournisseurNom, Pageable pageable);
	Page<Sonde> findByEmplacementNomContainingOrFournisseurAccesNomContaining(String emplacementNom, String fournisseurNom, Pageable pageable);
	long countByClientId(long client_id);
    Integer countSondeByEnabled(boolean s);
    Integer countSondeByEnabledNot(boolean s);
    Integer countSondeByEnabledAndDerniereConnexionIsAfter(boolean s, Date date2);
    Integer countSondeByEnabledAndDerniereConnexionIsBefore(boolean s, Date date);
}
