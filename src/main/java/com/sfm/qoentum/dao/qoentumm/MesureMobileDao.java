package com.sfm.qoentum.dao.qoentumm;

import java.util.Date;
import java.util.List;

import com.sfm.qoentum.model.qoentumf.FournisseurAcces;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.dto.EventDateDescription;
import com.sfm.qoentum.dto.EventNombrePercent;
import com.sfm.qoentum.dto.geo.GeoPresenceCouverture;
import com.sfm.qoentum.dto.geo.TestDTO;
import com.sfm.qoentum.enumer.EnumNatureAppel;
import com.sfm.qoentum.enumer.EnumTypeMesure;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.model.qoentumm.Operateur;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobile;

@Repository
public interface MesureMobileDao extends JpaRepository<MesureMobile, Long> {

	
	
	/************************** Couverture *********************/
	// Niveau de signal
	Page<MesureMobile> findByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Niveau de signal Wifi
	Page<MesureMobile> findByDbmIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																														 GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	
	/************************** FTP *************************/
	// Ping
	Page<MesureMobile> findByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Ping Wifi
	Page<MesureMobile> findByMesureMobileFTPPingIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																  GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	

	// Jitter
	Page<MesureMobile> findByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Jitter Wifi
	Page<MesureMobile> findByMesureMobileFTPJitterIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																	GenerationTechno generationTechno, Date start, Date end, Pageable pageable);


	// Download
	Page<MesureMobile> findByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Download Wifi
	Page<MesureMobile> findByMesureMobileFTPDownloadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																	  GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	

	// Upload
	Page<MesureMobile> findByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Upload Wifi
	Page<MesureMobile> findByMesureMobileFTPUploadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																	GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	

	// MOS Téléchargement

	Page<MesureMobile> findByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// MOS Téléchargement Wifi

	Page<MesureMobile> findByMesureMobileFTPMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																 GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	/************************** HTTP *************************/

	// Temps de chargement
	Page<MesureMobile> findByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Temps de chargement Wifi
	Page<MesureMobile> findByMesureMobileHTTPTempsChargementIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																			  GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	
	// DDNS LookUp
		Page<MesureMobile> findByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
				GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// DDNS LookUp Wifi
	Page<MesureMobile> findByMesureMobileHTTPDnsLookupIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																		GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	
	// Débit
	Page<MesureMobile> findByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Débit Wifi
	Page<MesureMobile> findByMesureMobileHTTPDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																	GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	
	// Mos HTTP
	Page<MesureMobile> findByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Mos HTTP Wifi
	Page<MesureMobile> findByMesureMobileHTTPMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																  GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	
	/************************** Video *************************/

	// BufferingTime
	Page<MesureMobile> findByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// BufferingTime Wifi
	Page<MesureMobile> findByMesureMobileVideoBufferingTimeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																			 GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	
	// Duree
	Page<MesureMobile> findByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Duree Wifi
	Page<MesureMobile> findByMesureMobileVideoDureeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																	 GenerationTechno generationTechno, Date start, Date end, Pageable pageable);



	// Débit
	Page<MesureMobile> findByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Débit wifi
	Page<MesureMobile> findByMesureMobileVideoDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																	 GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	

	// Mos Video
	Page<MesureMobile> findByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Mos Video wifi
	Page<MesureMobile> findByMesureMobileVideoMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																   GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	

	/************************** Voix *************************/

	// Durée Appel
	Page<MesureMobile> findByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Durée Appel Wifi
	Page<MesureMobile> findByMesureMobileVoixDureeAppelIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																		 GenerationTechno generationTechno, Date start, Date end, Pageable pageable);

	

	// Setup Time
	Page<MesureMobile> findByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Setup Time Wifi
	Page<MesureMobile> findByMesureMobileVoixSetupTimeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																		GenerationTechno generationTechno, Date start, Date end, Pageable pageable);



	// MOS
	Page<MesureMobile> findByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// MOS Wifi
	Page<MesureMobile> findByMesureMobileVoixMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																  GenerationTechno generationTechno, Date start, Date end, Pageable pageable);



	/************************** SMS *************************/

	// MOS
	Page<MesureMobile> findByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// MOS Wifi
	Page<MesureMobile> findByMesureMobileSMSMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																 GenerationTechno generationTechno, Date start, Date end, Pageable pageable);



	// Délais SMS
	Page<MesureMobile> findByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable);
	// Délais SMS Wifi
	Page<MesureMobile> findByMesureMobileSMSDelaisSMSIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(FournisseurAcces fournisseurAcces,
																																	   GenerationTechno generationTechno, Date start, Date end, Pageable pageable);


	
	/************************** Couverture *************************/
	// Niveau de signal
	Integer countByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end);

	Integer countByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDbmBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startDbm, Integer endDbm, Date start,
			Date end);

	Integer countByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDbmLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDbmGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	/************************** FTP *************************/

	// Ping
	Integer countByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPPingBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double startMesureMobileFTPPing,
			Double endMesureMobileFTPPing, Date start, Date end);

	Integer countByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPPingLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneSup, Date start, Date end);

	Integer countByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPPingGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneInf, Date start, Date end);

	// Jitter
	Integer countByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPJitterBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double startMesureMobileFTPJitter,
			Double endMesureMobileFTPJitter, Date start, Date end);

	Integer countByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPJitterLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneSup, Date start, Date end);

	Integer countByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPJitterGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneInf, Date start, Date end);

	// Download
	Integer countByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPDownloadBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double startMesureMobileFTPDownload,
			Double endMesureMobileFTPDownload, Date start, Date end);

	Integer countByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPDownloadLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneSup, Date start, Date end);

	Integer countByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPDownloadGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneInf, Date start, Date end);

	// Upload

	Integer countByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPUploadBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double startMesureMobileFTPUpload,
			Double endMesureMobileFTPUpload, Date start, Date end);

	Integer countByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPUploadLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneSup, Date start, Date end);

	Integer countByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPUploadGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneInf, Date start, Date end);

	// MOS Téléchargement

	Integer countByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPMosBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileFTPMos,
			Integer endMesureMobileFTPMos, Date start, Date end);

	Integer countByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPMosLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPMosGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	/************************** HTTP *************************/

	// Temps de chargement

	Integer countByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPTempsChargementBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileHTTPTempsChargement,
			Integer endMesureMobileHTTPTempsChargement, Date start, Date end);

	Integer countByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPTempsChargementLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPTempsChargementGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);
	
	// DNS LookUp

		Integer countByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
				Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

		Integer countByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDnsLookupBetweenAndDateBetween(
				Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileHTTPDnsLookup,
				Integer endMesureMobileHTTPDnsLookup, Date start, Date end);

		Integer countByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDnsLookupLessThanEqualAndDateBetween(
				Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

		Integer countByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDnsLookupGreaterThanEqualAndDateBetween(
				Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	// Débit
	Integer countByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDebitBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double startMesureMobileHTTPDebit,
			Double endMesureMobileHTTPDebit, Date start, Date end);

	Integer countByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDebitLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneSup, Date start, Date end);

	Integer countByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDebitGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneInf, Date start, Date end);

	// Mos HTTP
	Integer countByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPMosBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileHTTPMos,
			Integer endMesureMobileHTTPMos, Date start, Date end);

	Integer countByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPMosLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPMosGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	/************************** Video *************************/

	// BufferingTime
	Integer countByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoBufferingTimeBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileVideoBufferingTime,
			Integer endMesureMobileVideoBufferingTime, Date start, Date end);

	Integer countByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoBufferingTimeLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoBufferingTimeGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	// Duree

	Integer countByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDureeBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileVideoDuree,
			Integer endMesureMobileVideoDuree, Date start, Date end);

	Integer countByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDureeLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneSup, Date start, Date end);

	Integer countByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDureeGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	// Débit

	Integer countByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDebitBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double startMesureMobileVideoDebit,
			Double endMesureMobileVideoDebit, Date start, Date end);

	Integer countByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDebitLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneSup, Date start, Date end);

	Integer countByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDebitGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneInf, Date start, Date end);

	// Mos Video

	Integer countByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoMosBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileVideoMos,
			Integer endMesureMobileVideoMos, Date start, Date end);

	Integer countByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoMosLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoMosGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	/************************** Voix *************************/

	// Durée Appel
	Integer countByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixDureeAppelBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileVoixDureeAppel,
			Integer endMesureMobileVoixDureeAppel, Date start, Date end);

	Integer countByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixDureeAppelLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixDureeAppelGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	// Setup Time

	Integer countByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixSetupTimeBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double startMesureMobileVoixSetupTime,
			Double endMesureMobileVoixSetupTime, Date start, Date end);

	Integer countByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixSetupTimeLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneSup, Date start, Date end);

	Integer countByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixSetupTimeGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Double borneInf, Date start, Date end);

	// MOS

	Integer countByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixMosBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileVoixMos,
			Integer endMesureMobileVoixMos, Date start, Date end);

	Integer countByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixMosLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixMosGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	/************************** SMS *************************/

	// MOS

	Integer countByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSMosBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileSMSMos,
			Integer endMesureMobileSMSMos, Date start, Date end);

	Integer countByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSMosLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSMosGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	// Délais SMS

	Integer countByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Date start, Date end);

	Integer countByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSDelaisSMSBetweenAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer startMesureMobileSMSMos,
			Integer endMesureMobileSMSMos, Date start, Date end);

	Integer countByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSDelaisSMSLessThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneSup, Date start, Date end);

	Integer countByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSDelaisSMSGreaterThanEqualAndDateBetween(
			Operateur operateur, GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

//	List<MesureMobile> findByOperateurAndMobileTechnoGenerationTechnoAndDbmBetweenAndDateBetweenOrderByDateAsc(Operateur operateur,
//			GenerationTechno generationTechno, Integer startDbm, Integer endDbm, Date start, Date end);
//	
//	List<MesureMobile> findByOperateurAndMobileTechnoGenerationTechnoAndDbmLessThanEqualAndDateBetweenOrderByDateAsc(Operateur operateur,
//			GenerationTechno generationTechno, Integer borneSup, Date start, Date end);
//
//	List<MesureMobile> findByOperateurAndMobileTechnoGenerationTechnoAndDbmGreaterThanEqualAndDateBetweenOrderByDateAsc(Operateur operateur,
//			GenerationTechno generationTechno, Integer borneInf, Date start, Date end);

	/************************** Couverture *************************/
	// Niveau de signal
	List<MesureMobile> findAllByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	/****************************** FTP *****************************/

	// Latence
	List<MesureMobile> findAllByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// Jitter
	List<MesureMobile> findAllByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// FTP Download
	List<MesureMobile> findAllByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// FTP Upload
	List<MesureMobile> findAllByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// FTP Mos
	List<MesureMobile> findAllByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	/****************************** HTTP *****************************/

	// HTTP Temps de chargement
	List<MesureMobile> findAllByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	// HTTP DNS Lookup
		List<MesureMobile> findAllByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
				Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// HTTP Débit
	List<MesureMobile> findAllByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// HTTP Mos
	List<MesureMobile> findAllByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	/****************************** Video *****************************/

	// Video Buffering Time
	List<MesureMobile> findAllByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// Video Durée
	List<MesureMobile> findAllByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// Video Débit
	List<MesureMobile> findAllByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// Video Mos
	List<MesureMobile> findAllByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	/****************************** Voix *****************************/

	// Voix Durée Appel
	List<MesureMobile> findAllByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// Voix Setup Time
	List<MesureMobile> findAllByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// Voix Mos
	List<MesureMobile> findAllByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	/****************************** SMS *****************************/

	// SMS Mos
	List<MesureMobile> findAllByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	// SMS Delais
	List<MesureMobile> findAllByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	/******************** Stat Couverture ********************/
	List<MesureMobile> findAllByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDbmAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDbmAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAll(Pageable pageable);
	Page<MesureMobile> findAllByDbmIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDbmAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(dbm) as avg, " + "MIN(dbm) as min, "
			+ "MAX(dbm) as max, " + "COUNT(dbm) as records) " + "FROM MesureMobile " + "WHERE dbm is not NULL "
			+ "And operateur=:operateur " + "And mobileTechno.generationTechno=:generationTechno "
			+ "And typeMesure=:typeMesure " + "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findDBMAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/******************** Stat Ping ********************/
	List<MesureMobile> findAllByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	////////wiiifiiii
	List<MesureMobile> findAllByMesureMobileFTPPingIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	
	Page<MesureMobile> findAllByMesureMobileFTPPingIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileFTP.ping) as avg, "
			+ "MIN(mesureMobileFTP.ping) as min, " + "MAX(mesureMobileFTP.ping) as max, "
			+ "COUNT(mesureMobileFTP.ping) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileFTP.ping is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findLatenceAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileFTP.ping) as avg, "
			+ "MIN(mesureMobileFTP.ping) as min, " + "MAX(mesureMobileFTP.ping) as max, "
			+ "COUNT(mesureMobileFTP.ping) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileFTP.ping is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findLatenceAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
														@Param("generationTechno") GenerationTechno generationTechno,
														@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
														@Param("endDate") Date endDate);

	/******************** Stat Jitter ********************/
	List<MesureMobile> findAllByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPJitterAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	////wiiifiiii
	List<MesureMobile> findAllByMesureMobileFTPJitterIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPJitterAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileFTPJitterIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPJitterAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileFTP.jitter) as avg, "
			+ "MIN(mesureMobileFTP.jitter) as min, " + "MAX(mesureMobileFTP.jitter) as max, "
			+ "COUNT(mesureMobileFTP.jitter) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileFTP.jitter is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findJitterAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
///////wiiiifiii
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileFTP.jitter) as avg, "
			+ "MIN(mesureMobileFTP.jitter) as min, " + "MAX(mesureMobileFTP.jitter) as max, "
			+ "COUNT(mesureMobileFTP.jitter) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileFTP.jitter is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findJitterAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
													   @Param("generationTechno") GenerationTechno generationTechno,
													   @Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
													   @Param("endDate") Date endDate);

	/******************** Stat FTP DL ********************/
	List<MesureMobile> findAllByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPDownloadAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	/////wiiifii
	List<MesureMobile> findAllByMesureMobileFTPDownloadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPDownloadAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileFTPDownloadIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPDownloadAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileFTP.download) as avg, "
			+ "MIN(mesureMobileFTP.download) as min, " + "MAX(mesureMobileFTP.download) as max, "
			+ "COUNT(mesureMobileFTP.download) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileFTP.download is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findFTPDownloadAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/////wiiifiii
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileFTP.download) as avg, "
			+ "MIN(mesureMobileFTP.download) as min, " + "MAX(mesureMobileFTP.download) as max, "
			+ "COUNT(mesureMobileFTP.download) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileFTP.download is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findFTPDownloadAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
															@Param("generationTechno") GenerationTechno generationTechno,
															@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
															@Param("endDate") Date endDate);

	/******************** Stat FTP UL ********************/
	List<MesureMobile> findAllByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPUploadAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	////wiiifii
	List<MesureMobile> findAllByMesureMobileFTPUploadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPUploadAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileFTPUploadIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPUploadAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileFTP.upload) as avg, "
			+ "MIN(mesureMobileFTP.upload) as min, " + "MAX(mesureMobileFTP.upload) as max, "
			+ "COUNT(mesureMobileFTP.upload) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileFTP.upload is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findFTPUploadAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	///wiiifiiii
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileFTP.upload) as avg, "
			+ "MIN(mesureMobileFTP.upload) as min, " + "MAX(mesureMobileFTP.upload) as max, "
			+ "COUNT(mesureMobileFTP.upload) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileFTP.upload is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findFTPUploadAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
														  @Param("generationTechno") GenerationTechno generationTechno,
														  @Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
														  @Param("endDate") Date endDate);

	/******************** Stat FTP Mos ********************/
	List<MesureMobile> findAllByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPMosAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileFTPMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPMosAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileFTP.mos) as avg, "
			+ "MIN(mesureMobileFTP.mos) as min, " + "MAX(mesureMobileFTP.mos) as max, "
			+ "COUNT(mesureMobileFTP.mos) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileFTP.mos is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findFTPMosAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/******************** Stat HTTP Temps Chargement ********************/
	List<MesureMobile> findAllByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPTempsChargementAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	///wiiifii
	List<MesureMobile> findAllByMesureMobileHTTPTempsChargementIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPTempsChargementAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileHTTPTempsChargementIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPTempsChargementAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileHTTP.tempsChargement) as avg, "
			+ "MIN(mesureMobileHTTP.tempsChargement) as min, " + "MAX(mesureMobileHTTP.tempsChargement) as max, "
			+ "COUNT(mesureMobileHTTP.tempsChargement) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileHTTP.tempsChargement is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findHTTPTempsChargementAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	////wiiiiffiii
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileHTTP.tempsChargement) as avg, "
			+ "MIN(mesureMobileHTTP.tempsChargement) as min, " + "MAX(mesureMobileHTTP.tempsChargement) as max, "
			+ "COUNT(mesureMobileHTTP.tempsChargement) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileHTTP.tempsChargement is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findHTTPTempsChargementAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
																	@Param("generationTechno") GenerationTechno generationTechno,
																	@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
																	@Param("endDate") Date endDate);
	
	/******************** Stat HTTP DNS Lookup ********************/
	List<MesureMobile> findAllByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDnsLookupAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);

	////wiiifiii
	List<MesureMobile> findAllByMesureMobileHTTPDnsLookupIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDnsLookupAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileHTTPDnsLookupIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDnsLookupAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileHTTP.dnsLookup) as avg, "
			+ "MIN(mesureMobileHTTP.dnsLookup) as min, " + "MAX(mesureMobileHTTP.dnsLookup) as max, "
			+ "COUNT(mesureMobileHTTP.dnsLookup) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileHTTP.dnsLookup is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findHTTPDnsLookupAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/////wiiifiii
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileHTTP.dnsLookup) as avg, "
			+ "MIN(mesureMobileHTTP.dnsLookup) as min, " + "MAX(mesureMobileHTTP.dnsLookup) as max, "
			+ "COUNT(mesureMobileHTTP.dnsLookup) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileHTTP.dnsLookup is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findHTTPDnsLookupAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
															  @Param("generationTechno") GenerationTechno generationTechno,
															  @Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
															  @Param("endDate") Date endDate);

	/******************** Stat HTTP Débit ********************/
	List<MesureMobile> findAllByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDebitAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	////wiiifiii
	List<MesureMobile> findAllByMesureMobileHTTPDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDebitAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileHTTPDebitIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDebitAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileHTTP.debit) as avg, "
			+ "MIN(mesureMobileHTTP.debit) as min, " + "MAX(mesureMobileHTTP.debit) as max, "
			+ "COUNT(mesureMobileHTTP.debit) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileHTTP.debit is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findHTTPDebitAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	////wiiifiii
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileHTTP.debit) as avg, "
			+ "MIN(mesureMobileHTTP.debit) as min, " + "MAX(mesureMobileHTTP.debit) as max, "
			+ "COUNT(mesureMobileHTTP.debit) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileHTTP.debit is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findHTTPDebitAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
														  @Param("generationTechno") GenerationTechno generationTechno,
														  @Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
														  @Param("endDate") Date endDate);

	/******************** Stat HTTP Mos ********************/
	List<MesureMobile> findAllByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPMosAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileHTTPMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPMosAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileHTTP.mos) as avg, "
			+ "MIN(mesureMobileHTTP.mos) as min, " + "MAX(mesureMobileHTTP.mos) as max, "
			+ "COUNT(mesureMobileHTTP.mos) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileHTTP.mos is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findHTTPMosAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/******************** Stat Video BufferingTime ********************/
	List<MesureMobile> findAllByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoBufferingTimeAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	////wiiifiii
	List<MesureMobile> findAllByMesureMobileVideoBufferingTimeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoBufferingTimeAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileVideoBufferingTimeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoBufferingTimeAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVideo.bufferingTime) as avg, "
			+ "MIN(mesureMobileVideo.bufferingTime) as min, " + "MAX(mesureMobileVideo.bufferingTime) as max, "
			+ "COUNT(mesureMobileVideo.bufferingTime) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVideo.bufferingTime is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVideoBufferingTimeAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/////wiiiifiiii
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVideo.bufferingTime) as avg, "
			+ "MIN(mesureMobileVideo.bufferingTime) as min, " + "MAX(mesureMobileVideo.bufferingTime) as max, "
			+ "COUNT(mesureMobileVideo.bufferingTime) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVideo.bufferingTime is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVideoBufferingTimeAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
																   @Param("generationTechno") GenerationTechno generationTechno,
																   @Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
																   @Param("endDate") Date endDate);

	/******************** Stat Video Duree ********************/
	List<MesureMobile> findAllByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDureeAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	///wiiifii
	List<MesureMobile> findAllByMesureMobileVideoDureeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDureeAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileVideoDureeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDureeAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVideo.duree) as avg, "
			+ "MIN(mesureMobileVideo.duree) as min, " + "MAX(mesureMobileVideo.duree) as max, "
			+ "COUNT(mesureMobileVideo.duree) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVideo.duree is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVideoDureeAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	///wiiifiii
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVideo.duree) as avg, "
			+ "MIN(mesureMobileVideo.duree) as min, " + "MAX(mesureMobileVideo.duree) as max, "
			+ "COUNT(mesureMobileVideo.duree) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVideo.duree is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVideoDureeAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
														   @Param("generationTechno") GenerationTechno generationTechno,
														   @Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
														   @Param("endDate") Date endDate);

	/******************** Stat Video Débit ********************/
	List<MesureMobile> findAllByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDebitAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	///wiiifiii
	List<MesureMobile> findAllByMesureMobileVideoDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDebitAsc(
			FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileVideoDebitIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDebitAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVideo.debit) as avg, "
			+ "MIN(mesureMobileVideo.debit) as min, " + "MAX(mesureMobileVideo.debit) as max, "
			+ "COUNT(mesureMobileVideo.debit) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVideo.debit is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVideoDebitAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
	////wiiifiii
	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVideo.debit) as avg, "
			+ "MIN(mesureMobileVideo.debit) as min, " + "MAX(mesureMobileVideo.debit) as max, "
			+ "COUNT(mesureMobileVideo.debit) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVideo.debit is not NULL " + "And fournisseurAcces=:fournisseurAcces "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVideoDebitAvgMinMaxPerDateWifi(@Param("fournisseurAcces") FournisseurAcces fournisseurAcces,
														   @Param("generationTechno") GenerationTechno generationTechno,
														   @Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
														   @Param("endDate") Date endDate);

	/******************** Stat Video Mos ********************/
	List<MesureMobile> findAllByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoMosAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileVideoMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoMosAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVideo.mos) as avg, "
			+ "MIN(mesureMobileVideo.mos) as min, " + "MAX(mesureMobileVideo.mos) as max, "
			+ "COUNT(mesureMobileVideo.mos) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVideo.mos is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVideoMosAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/******************** Stat Voix Durée Appel ********************/
	List<MesureMobile> findAllByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixDureeAppelAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileVoixDureeAppelIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixDureeAppelAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVoix.dureeAppel) as avg, "
			+ "MIN(mesureMobileVoix.dureeAppel) as min, " + "MAX(mesureMobileVoix.dureeAppel) as max, "
			+ "COUNT(mesureMobileVoix.dureeAppel) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVoix.dureeAppel is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVoixDureeAppelAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/******************** Stat Voix Setup Time ********************/
	List<MesureMobile> findAllByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixSetupTimeAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileVoixSetupTimeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixSetupTimeAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVoix.setupTime) as avg, "
			+ "MIN(mesureMobileVoix.setupTime) as min, " + "MAX(mesureMobileVoix.setupTime) as max, "
			+ "COUNT(mesureMobileVoix.setupTime) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVoix.setupTime is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVoixSetupTimeAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/******************** Stat Voix MOS ********************/
	List<MesureMobile> findAllByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixMosAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileVoixMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixMosAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileVoix.mos) as avg, "
			+ "MIN(mesureMobileVoix.mos) as min, " + "MAX(mesureMobileVoix.mos) as max, "
			+ "COUNT(mesureMobileVoix.mos) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileVoix.mos is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findVoixMosAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/******************** Stat SMS MOS ********************/
	List<MesureMobile> findAllByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSMosAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileSMSMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSMosAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileSMS.mos) as avg, "
			+ "MIN(mesureMobileSMS.mos) as min, " + "MAX(mesureMobileSMS.mos) as max, "
			+ "COUNT(mesureMobileSMS.mos) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileSMS.mos is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findSMSMosAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/******************** Stat SMS Delais ********************/
	List<MesureMobile> findAllByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSDelaisSMSAsc(
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end);
	
	Page<MesureMobile> findAllByMesureMobileSMSDelaisSMSIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSDelaisSMSAsc(double latitude, double longitude,
			Operateur operateur, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);

	@Query("SELECT new com.sfm.qoentum.dto.AvgMinMaxPerDate(" + "AVG(mesureMobileSMS.delaisSMS) as avg, "
			+ "MIN(mesureMobileSMS.delaisSMS) as min, " + "MAX(mesureMobileSMS.delaisSMS) as max, "
			+ "COUNT(mesureMobileSMS.delaisSMS) as records) " + "FROM MesureMobile "
			+ "WHERE mesureMobileSMS.delaisSMS is not NULL " + "And operateur=:operateur "
			+ "And mobileTechno.generationTechno=:generationTechno " + "And typeMesure=:typeMesure "
			+ "And date between :startDate AND :endDate")
	public AvgMinMaxPerDate findSMSDelaisSMSAvgMinMaxPerDate(@Param("operateur") Operateur operateur,
			@Param("generationTechno") GenerationTechno generationTechno,
			@Param("typeMesure") EnumTypeMesure typeMesure, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	/***************************
	 * Mobiles
	 *******************************************/

	List<MesureMobile> findByDbmIsNotNullAndLongitudeIsNotAndLatitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndDbmGreaterThanEqual(
			double longitude, double latitude, Operateur operateur, GenerationTechno generationTechno, Integer dbm);

	List<MesureMobile> findByDbmIsNotNullAndLongitudeIsNotAndLatitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndDbmLessThanEqual(
			double longitude, double latitude, Operateur operateur, GenerationTechno generationTechno, Integer dbm);

	List<MesureMobile> findByDbmIsNotNullAndLongitudeIsNotAndLatitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndDbmBetween(
			double longitude, double latitude, Operateur operateur, GenerationTechno generationTechno, Integer dbmStart,
			Integer dbmEnd);

	/*****************************
	 * Fin Mobiles
	 **************************************/

	@Query("SELECT new com.sfm.qoentum.dto.geo.GeoPresenceCouverture(" + "longitude as longitude, "
			+ "latitude as latitude, " + "case when asu = 99 then false else true end as presence ) "
			+ "FROM MesureMobile " + "WHERE asu is not NULL " + "And longitude != 0 " + "And latitude != 0 "
			+ "And operateur=:operateur " + "And date between :startDate AND :endDate")
	public List<GeoPresenceCouverture> findPresenceCouverturePerDate(@Param("operateur") Operateur operateur,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	Integer countByTypeMesureAndOperateurAndDateBetween(EnumTypeMesure typeMesure, Operateur operateur, Date start,
			Date end);
	
	Integer countByTypeMesureAndMesureMobileVoixNatureAppelAndOperateurAndDateBetween(EnumTypeMesure typeMesure, EnumNatureAppel natureAppel, Operateur operateur, Date start,
			Date end);

	@Query("SELECT new com.sfm.qoentum.dto.EventNombrePercent(" + "mesureMobileVoix.code as event, "
			+ "mesureMobileVoix.reason as description, " + "count(*) as nombre) " + "FROM MesureMobile "
			+ "WHERE typeMesure = 'Voix' " + "And operateur=:operateur " + "And date between :startDate AND :endDate "
			+ "AND mesureMobileVoix.natureAppel = 'SORTANT' "
			+ "GROUP BY mesureMobileVoix.code")
	public List<EventNombrePercent> findStatVoixEventNombrePercentPerDate(@Param("operateur") Operateur operateur,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query("SELECT new com.sfm.qoentum.dto.EventNombrePercent(" + "mesureMobileSMS.result as event, "
			+ "count(*) as nombre) " + "FROM MesureMobile " + "WHERE typeMesure = 'SMS' " + "And operateur=:operateur "
			+ "And date between :startDate AND :endDate " + "GROUP BY mesureMobileSMS.result")
	public List<EventNombrePercent> findStatSMSEventNombrePercentPerDate(@Param("operateur") Operateur operateur,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	
	@Query("SELECT new com.sfm.qoentum.dto.EventDateDescription(" 
			+ "date as date, "
			+ "mesureMobileVoix.code as event, "
			+ "mesureMobileVoix.description as description, "
			+ "longitude as longitude, "
			+ "latitude  as latitude) " 
			+ "FROM MesureMobile " 
			+ "WHERE typeMesure = 'Voix' "
			+ "And longitude != 0 "
			+ "And latitude != 0 " 
			+ "And operateur=:operateur "
			+ "And date between :startDate AND :endDate "
			+ "Order By mesureMobileVoix.code")
	public List<EventDateDescription> findVoixEventPerDate(
			@Param("operateur") Operateur operateur,
			@Param("startDate") Date startDate, 
			@Param("endDate") Date endDate);
	
	
	@Query("SELECT new com.sfm.qoentum.dto.EventDateDescription(" 
			+ "date as date, "
			+ "mesureMobileSMS.result as resultSMS, "
			+ "longitude as longitude, "
			+ "latitude  as latitude) " 
			+ "FROM MesureMobile " 
			+ "WHERE typeMesure = 'SMS' " 
			+ "And operateur=:operateur "
			+ "And date between :startDate AND :endDate "
			+ "Order By mesureMobileSMS.result")
	public List<EventDateDescription> findSMSEventPerDate(
			@Param("operateur") Operateur operateur,
			@Param("startDate") Date startDate, 
			@Param("endDate") Date endDate);
	
	
	/**************************************		Test	 *********************************/
	
	@Query("SELECT COUNT(*) FROM MesureMobile")
	public List<TestDTO> test();


/**************************************		Map mesures WIIFIIIIII	 *********************************/
Page<MesureMobile> findAllByDbmIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDbmAsc(double latitude, double longitude,
																																							 FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileFTPPingIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(double latitude, double longitude,
																																																 FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileFTPJitterIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPJitterAsc(double latitude, double longitude,
																																																	 FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileFTPDownloadIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPDownloadAsc(double latitude, double longitude,
																																																		 FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileFTPUploadIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPUploadAsc(double latitude, double longitude,
																																																	 FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileHTTPTempsChargementIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPTempsChargementAsc(double latitude, double longitude,
																																																						 FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileHTTPDnsLookupIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDnsLookupAsc(double latitude, double longitude,
																																																			 FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileHTTPDebitIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDebitAsc(double latitude, double longitude,
																																																	 FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileVideoBufferingTimeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoBufferingTimeAsc(double latitude, double longitude,
																																																							  FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileVideoDureeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDureeAsc(double latitude, double longitude,
																																																			  FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
	Page<MesureMobile> findAllByMesureMobileVideoDebitIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDebitAsc(double latitude, double longitude,
																																																			  FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, EnumTypeMesure typeMesure, Date start, Date end, Pageable pageable);
}