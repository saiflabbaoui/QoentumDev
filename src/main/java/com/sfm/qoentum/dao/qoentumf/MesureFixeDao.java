package com.sfm.qoentum.dao.qoentumf;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.model.qoentumf.MesureFixe;

@Repository
public interface MesureFixeDao extends JpaRepository<MesureFixe, Long> {
	Page<MesureFixe> findBySondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetween(long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id,
			long emplacement_id, Date start, Date end, Pageable pageable);
	
	Integer countByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetween(long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id,
			long emplacement_id, Date start, Date end);
	
	Integer countByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetweenAndMesureBetween(
			long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id,long emplacement_id, Date start, Date end,
			Double mesure_Start, Double mesure_End);
	
	Integer countByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetweenAndMesureLessThanEqual(
			long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id,long emplacement_id, Date start, Date end,
			Double borneSup);
	
	Integer countByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetweenAndMesureGreaterThanEqual(
			long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id,long emplacement_id, Date start, Date end,
			Double borneInf);
	
	List<MesureFixe> findAllByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetweenOrderByMesureAsc(
			long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id,long emplacement_id, Date start, Date end);

	
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" 
			+ "AVG(mesure) as avg, " 
			+ "MIN(mesure) as min, "
			+ "MAX(mesure) as max, " 
			+ "COUNT(mesure) as records) " 
			+ "FROM MesureFixe " 
			+ "WHERE mesure is not NULL "
			+ "And sonde.id=:sonde_id " 
			+ "And fournisseurAcces.id=:fournisseur_acces_id "
			+ "And indicateurFixe.id=:indicateur_fixe_id " 
			+ "And emplacement.id=:emplacement_id "
			+ "And date between :start AND :end")
	public AvgMinMaxPerDate findMesureAvgMinMaxPerDate(
			@Param("sonde_id") long sonde_id, 
			@Param("fournisseur_acces_id") long fournisseur_acces_id, 
			@Param("indicateur_fixe_id") long indicateur_fixe_id,
			@Param("emplacement_id") long emplacement_id, 
			@Param("start") Date start, 
			@Param("end") Date end);
}
