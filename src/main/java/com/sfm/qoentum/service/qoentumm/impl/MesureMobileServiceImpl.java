package com.sfm.qoentum.service.qoentumm.impl;

import com.sfm.qoentum.config.Constants;
import com.sfm.qoentum.dao.qoentumm.MesureMobileDao;
import com.sfm.qoentum.dao.qoentumm.MobileTechnoDao;
import com.sfm.qoentum.dao.qoentumm.SeuilMobileDao;
import com.sfm.qoentum.dto.*;
import com.sfm.qoentum.dto.geo.GeoJSONMobile;
import com.sfm.qoentum.dto.geo.GeoPresenceCouverture;
import com.sfm.qoentum.dto.geo.TestDTO;
import com.sfm.qoentum.enumer.EnumGranularite;
import com.sfm.qoentum.enumer.EnumNatureAppel;
import com.sfm.qoentum.enumer.EnumTypeMesure;
import com.sfm.qoentum.model.Features;
import com.sfm.qoentum.model.qoentumf.FournisseurAcces;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.model.qoentumm.MobileTechno;
import com.sfm.qoentum.model.qoentumm.Operateur;
import com.sfm.qoentum.model.qoentumm.SeuilMobile;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobile;
import com.sfm.qoentum.service.qoentumm.MesureMobileService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "mesureMobileService")
public class MesureMobileServiceImpl implements MesureMobileService {
	
	private static final String DEFAULT_COLOR = "#33001a";
	private static final String DEFAULT_LABEL_SEUIL = "NA";

	@Autowired
	private MesureMobileDao mesureMobileDao;

	@Autowired
	private SeuilMobileDao seuilMobileDao;

	@Autowired
	private MobileTechnoDao mobileTechnoDao;

	@Override
	public MesureMobile save(MesureMobile mesureMobile) {
		return mesureMobileDao.save(mesureMobile);
	}

	@Override
	public List<MesureMobile> findAll() {
		List<MesureMobile> list = new ArrayList<>();
		mesureMobileDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		mesureMobileDao.deleteById(id);

	}

	@Override
	public MesureMobile findById(Long id) {
		return mesureMobileDao.findById(id).get();
	}

	@Override
	public long count() {
		return mesureMobileDao.count();
	}

	@Override
	public EntityPage<MesureMobile> findAllByOperateurAndMobileTechnoGenerationTechno(Operateur operateur,
			GenerationTechno generationTechno, Date start, Date end, Pageable pageable, Long indicateurId) {

		Page<MesureMobile> mesureMobilesPage = null;

		// Force du signal
		if (indicateurId == Constants.Force_Signal_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		/****************************** FTP *****************************/

		// Latence
		if (indicateurId == Constants.Latence_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// Jitter
		if (indicateurId == Constants.Jitter_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// FTP Download
		if (indicateurId == Constants.FTP_Download_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// FTP Upload
		if (indicateurId == Constants.FTP_Upload_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// FTP MOS
		if (indicateurId == Constants.MOS_TELECHARGEMENT) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		/****************************** HTTP *****************************/

		// HTTP Temps de chargement
		if (indicateurId == Constants.HTTP_Temps_Chargement) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}
		
		// DNS LookUp
				if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
					mesureMobilesPage = mesureMobileDao
							.findByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
									operateur, generationTechno, start, end, pageable);
				}

		// HTTP Débit
		if (indicateurId == Constants.HTTP_Debit) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// HTTP Mos
		if (indicateurId == Constants.MOS_NAVIGATION) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		/****************************** Video *****************************/

		// Video Buffering Time
		if (indicateurId == Constants.Video_Buffering_Time) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// Video Durée
		if (indicateurId == Constants.Video_Duree) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// Video Débit
		if (indicateurId == Constants.Video_Debit) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// Video Débit
		if (indicateurId == Constants.MOS_VIDEO) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		/****************************** Voix *****************************/

		// Voix Durée Appel
		if (indicateurId == Constants.Voix_Duree_Appel) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// Voix Setup Time
		if (indicateurId == Constants.Voix_Setup_Time) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// Voix Mos
		if (indicateurId == Constants.Voix_Mos) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		/****************************** SMS *****************************/

		// SMS Mos
		if (indicateurId == Constants.MOS_SMS) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		// SMS Delais
		if (indicateurId == Constants.DELAIS_SMS) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							operateur, generationTechno, start, end, pageable);
		}

		EntityPage<MesureMobile> mesureMobiles = new EntityPage<MesureMobile>();

		mesureMobiles.setList(mesureMobilesPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(mesureMobilesPage.getNumberOfElements());
		pageUtil.setNombrePage(mesureMobilesPage.getTotalPages());
		pageUtil.setNumeroPage(mesureMobilesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(mesureMobilesPage.getTotalElements());

		mesureMobiles.setPageUtil(pageUtil);

		return mesureMobiles;
	}

	@Override
	public EntityPage<MesureMobile> findAllmesureWifi(FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, Date start, Date end, Pageable pageable, Long indicateurId) {
		Page<MesureMobile> mesureMobilesPage = null;

		// Force du signal
		if (indicateurId == Constants.Force_Signal_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByDbmIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		/****************************** FTP *****************************/

		// Latence
		if (indicateurId == Constants.Latence_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPPingIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// Jitter
		if (indicateurId == Constants.Jitter_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPJitterIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// FTP Download
		if (indicateurId == Constants.FTP_Download_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPDownloadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// FTP Upload
		if (indicateurId == Constants.FTP_Upload_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPUploadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// FTP MOS
		if (indicateurId == Constants.MOS_TELECHARGEMENT) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		/****************************** HTTP *****************************/

		// HTTP Temps de chargement
		if (indicateurId == Constants.HTTP_Temps_Chargement) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPTempsChargementIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// DNS LookUp
		if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPDnsLookupIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// HTTP Débit
		if (indicateurId == Constants.HTTP_Debit) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// HTTP Mos
		if (indicateurId == Constants.MOS_NAVIGATION) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		/****************************** Video *****************************/

		// Video Buffering Time
		if (indicateurId == Constants.Video_Buffering_Time) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoBufferingTimeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// Video Durée
		if (indicateurId == Constants.Video_Duree) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoDureeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// Video Débit
		if (indicateurId == Constants.Video_Debit) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// Video Débit
		if (indicateurId == Constants.MOS_VIDEO) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		/****************************** Voix *****************************/

		// Voix Durée Appel
		if (indicateurId == Constants.Voix_Duree_Appel) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVoixDureeAppelIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// Voix Setup Time
		if (indicateurId == Constants.Voix_Setup_Time) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVoixSetupTimeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// Voix Mos
		if (indicateurId == Constants.Voix_Mos) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVoixMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		/****************************** SMS *****************************/

		// SMS Mos
		if (indicateurId == Constants.MOS_SMS) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileSMSMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		// SMS Delais
		if (indicateurId == Constants.DELAIS_SMS) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileSMSDelaisSMSIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							fournisseurAcces, generationTechno, start, end, pageable);
		}

		EntityPage<MesureMobile> mesureMobiles = new EntityPage<MesureMobile>();

		mesureMobiles.setList(mesureMobilesPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(mesureMobilesPage.getNumberOfElements());
		pageUtil.setNombrePage(mesureMobilesPage.getTotalPages());
		pageUtil.setNumeroPage(mesureMobilesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(mesureMobilesPage.getTotalElements());

		mesureMobiles.setPageUtil(pageUtil);

		return mesureMobiles;
	}

	@Override
	public EntityPage<MesureMobile> findNoAffectedmesureWifi(GenerationTechno generationTechno, Date start, Date end, Pageable pageable, Long indicateurId) {

		Page<MesureMobile> mesureMobilesPage = null;
// Force du signal
		if (indicateurId == Constants.Force_Signal_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByDbmIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		/****************************** FTP *****************************/

		// Latence
		if (indicateurId == Constants.Latence_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPPingIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// Jitter
		if (indicateurId == Constants.Jitter_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPJitterIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// FTP Download
		if (indicateurId == Constants.FTP_Download_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPDownloadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// FTP Upload
		if (indicateurId == Constants.FTP_Upload_Id) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPUploadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// FTP MOS
		if (indicateurId == Constants.MOS_TELECHARGEMENT) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileFTPMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		/****************************** HTTP *****************************/

		// HTTP Temps de chargement
		if (indicateurId == Constants.HTTP_Temps_Chargement) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPTempsChargementIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// DNS LookUp
		if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPDnsLookupIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// HTTP Débit
		if (indicateurId == Constants.HTTP_Debit) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// HTTP Mos
		if (indicateurId == Constants.MOS_NAVIGATION) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileHTTPMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		/****************************** Video *****************************/

		// Video Buffering Time
		if (indicateurId == Constants.Video_Buffering_Time) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoBufferingTimeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// Video Durée
		if (indicateurId == Constants.Video_Duree) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoDureeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// Video Débit
		if (indicateurId == Constants.Video_Debit) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// Video Débit
		if (indicateurId == Constants.MOS_VIDEO) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVideoMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		/****************************** Voix *****************************/

		// Voix Durée Appel
		if (indicateurId == Constants.Voix_Duree_Appel) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVoixDureeAppelIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// Voix Setup Time
		if (indicateurId == Constants.Voix_Setup_Time) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVoixSetupTimeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// Voix Mos
		if (indicateurId == Constants.Voix_Mos) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileVoixMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		/****************************** SMS *****************************/

		// SMS Mos
		if (indicateurId == Constants.MOS_SMS) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileSMSMosIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		// SMS Delais
		if (indicateurId == Constants.DELAIS_SMS) {
			mesureMobilesPage = mesureMobileDao
					.findByMesureMobileSMSDelaisSMSIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndDateBetweenOrderByDateDesc(
							null, generationTechno, start, end, pageable);
		}

		EntityPage<MesureMobile> mesureMobiles = new EntityPage<MesureMobile>();

		mesureMobiles.setList(mesureMobilesPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(mesureMobilesPage.getNumberOfElements());
		pageUtil.setNombrePage(mesureMobilesPage.getTotalPages());
		pageUtil.setNumeroPage(mesureMobilesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(mesureMobilesPage.getTotalElements());

		mesureMobiles.setPageUtil(pageUtil);

		return mesureMobiles;	}

	@Override
	public StatParSeuil statPercent(List<SeuilMobile> plages, Operateur operateur, GenerationTechno generationTechno,
			Date start, Date end, Long indicateurId) {

		List<LabelRecordsPercent> labelRecordsPercents = new ArrayList<>();

		int totalRecords = 0;

		// Force du signal
		if (indicateurId == Constants.Force_Signal_Id) {
			totalRecords = mesureMobileDao.countByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
					operateur, generationTechno, start, end);
		}

		/****************************** FTP *****************************/

		// Latence
		if (indicateurId == Constants.Latence_Id) {
			totalRecords = mesureMobileDao
					.countByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// Jitter
		if (indicateurId == Constants.Jitter_Id) {
			totalRecords = mesureMobileDao
					.countByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// FTP Download
		if (indicateurId == Constants.FTP_Download_Id) {
			totalRecords = mesureMobileDao
					.countByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// FTP Upload
		if (indicateurId == Constants.FTP_Upload_Id) {
			totalRecords = mesureMobileDao
					.countByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// FTP MOS
		if (indicateurId == Constants.MOS_TELECHARGEMENT) {
			totalRecords = mesureMobileDao
					.countByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		/****************************** HTTP *****************************/

		// HTTP Temps de chargement
		if (indicateurId == Constants.HTTP_Temps_Chargement) {
			totalRecords = mesureMobileDao
					.countByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}
		
		// DNS LookUp
				if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
					totalRecords = mesureMobileDao
							.countByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
									operateur, generationTechno, start, end);
				}

		// HTTP Débit
		if (indicateurId == Constants.HTTP_Debit) {
			totalRecords = mesureMobileDao
					.countByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// HTTP Mos
		if (indicateurId == Constants.MOS_NAVIGATION) {
			totalRecords = mesureMobileDao
					.countByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		/****************************** Video *****************************/

		// Video Buffering Time
		if (indicateurId == Constants.Video_Buffering_Time) {
			totalRecords = mesureMobileDao
					.countByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// Video Durée
		if (indicateurId == Constants.Video_Duree) {
			totalRecords = mesureMobileDao
					.countByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// Video Débit
		if (indicateurId == Constants.Video_Debit) {
			totalRecords = mesureMobileDao
					.countByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// Video Débit
		if (indicateurId == Constants.MOS_VIDEO) {
			totalRecords = mesureMobileDao
					.countByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		/****************************** Voix *****************************/

		// Voix Durée Appel
		if (indicateurId == Constants.Voix_Duree_Appel) {
			totalRecords = mesureMobileDao
					.countByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// Voix Setup Time
		if (indicateurId == Constants.Voix_Setup_Time) {
			totalRecords = mesureMobileDao
					.countByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// Voix Mos
		if (indicateurId == Constants.Voix_Mos) {
			totalRecords = mesureMobileDao
					.countByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		/****************************** SMS *****************************/

		// SMS Mos
		if (indicateurId == Constants.MOS_SMS) {
			totalRecords = mesureMobileDao
					.countByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		// SMS Delais
		if (indicateurId == Constants.DELAIS_SMS) {
			totalRecords = mesureMobileDao
					.countByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDateBetween(
							operateur, generationTechno, start, end);
		}

		System.out.println("----------------> totalRecords" + totalRecords);

		if (totalRecords == 0) {
			return new StatParSeuil(totalRecords, labelRecordsPercents);
		}

		for (SeuilMobile plage : plages) {

			System.out.println("----------------> Seuil : " + plage.getLibelle());

			int records = 0;

			if (plage.getBorneSup() == null) {
				// On a uniquement la borne inférieure
				Double borneInfTest;
				if (plage.getIncluseInf() == true) {
					// La borne est Incluse
					borneInfTest = plage.getBorneInf();
				} else {
					// La borne n'est pas incluse
					borneInfTest = plage.getBorneInf() + 1;
				}

				// Force du signal
				if (indicateurId == Constants.Force_Signal_Id) {
					records = mesureMobileDao
							.countByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDbmGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				/****************************** FTP *****************************/

				// Latence
				if (indicateurId == Constants.Latence_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPPingGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest, start, end);
				}

				// Jitter
				if (indicateurId == Constants.Jitter_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPJitterGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest, start, end);
				}

				// FTP Download
				if (indicateurId == Constants.FTP_Download_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPDownloadGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest, start, end);
				}

				// FTP Upload
				if (indicateurId == Constants.FTP_Upload_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPUploadGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest, start, end);
				}

				// FTP Mos
				if (indicateurId == Constants.MOS_TELECHARGEMENT) {
					records = mesureMobileDao
							.countByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPMosGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				/****************************** HTTP *****************************/

				// HTTP Temps de chargement
				if (indicateurId == Constants.HTTP_Temps_Chargement) {
					records = mesureMobileDao
							.countByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPTempsChargementGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}
				
				// HTTP DNS Lookup
				if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
					records = mesureMobileDao
							.countByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDnsLookupGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				// HTTP Débit
				if (indicateurId == Constants.HTTP_Debit) {
					records = mesureMobileDao
							.countByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDebitGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest, start, end);
				}

				// HTTP MOS
				if (indicateurId == Constants.MOS_NAVIGATION) {
					records = mesureMobileDao
							.countByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPMosGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				/****************************** Video *****************************/

				// Video Buffering Time
				if (indicateurId == Constants.Video_Buffering_Time) {
					records = mesureMobileDao
							.countByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoBufferingTimeGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				// Video Durée
				if (indicateurId == Constants.Video_Duree) {
					records = mesureMobileDao
							.countByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDureeGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				// Video Débit
				if (indicateurId == Constants.Video_Debit) {
					records = mesureMobileDao
							.countByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDebitGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest, start, end);
				}

				// Video Débit
				if (indicateurId == Constants.MOS_VIDEO) {
					records = mesureMobileDao
							.countByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoMosGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				/****************************** Voix *****************************/

				// Voix Durée Appel
				if (indicateurId == Constants.Voix_Duree_Appel) {
					records = mesureMobileDao
							.countByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixDureeAppelGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				// Voix Setup Time
				if (indicateurId == Constants.Voix_Setup_Time) {
					records = mesureMobileDao
							.countByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixSetupTimeGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest, start, end);
				}

				// Voix Mos
				if (indicateurId == Constants.Voix_Mos) {
					records = mesureMobileDao
							.countByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixMosGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				/****************************** SMS *****************************/

				// SMS Mos
				if (indicateurId == Constants.MOS_SMS) {
					records = mesureMobileDao
							.countByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSMosGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

				// SMS Délais
				if (indicateurId == Constants.DELAIS_SMS) {
					records = mesureMobileDao
							.countByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSDelaisSMSGreaterThanEqualAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), start, end);
				}

			} else if (plage.getBorneInf() == null) {
				// On a uniquement la borne supérieure
				Double borneSupTest;
				if (plage.getIncluseSup() == true) {
					// La borne est Incluse
					borneSupTest = plage.getBorneSup();
				} else {
					// La borne n'est pas incluse
					borneSupTest = plage.getBorneSup() - 1;
				}

				// Force du signal
				if (indicateurId == Constants.Force_Signal_Id) {
					records = mesureMobileDao
							.countByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDbmLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

				/****************************** FTP *****************************/

				// Latence
				if (indicateurId == Constants.Latence_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPPingLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest, start, end);
				}

				// Jitter
				if (indicateurId == Constants.Jitter_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPJitterLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest, start, end);
				}

				// FTP Download
				if (indicateurId == Constants.FTP_Download_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPDownloadLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest, start, end);
				}

				// FTP Upload
				if (indicateurId == Constants.FTP_Upload_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPUploadLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest, start, end);
				}

				// FTP MOS
				if (indicateurId == Constants.MOS_TELECHARGEMENT) {
					records = mesureMobileDao
							.countByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPMosLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

				/****************************** HTTP *****************************/

				// HTTP Temps de chargement
				if (indicateurId == Constants.HTTP_Temps_Chargement) {
					records = mesureMobileDao
							.countByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPTempsChargementLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}
				
				// HTTP DNS Lookup
				if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
					records = mesureMobileDao
							.countByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDnsLookupLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

				// HTTP Débit
				if (indicateurId == Constants.HTTP_Debit) {
					records = mesureMobileDao
							.countByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDebitLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest, start, end);
				}

				// HTTP MOS
				if (indicateurId == Constants.MOS_NAVIGATION) {
					records = mesureMobileDao
							.countByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPMosLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

				/****************************** Video *****************************/

				// Video Buffering Time
				if (indicateurId == Constants.Video_Buffering_Time) {
					records = mesureMobileDao
							.countByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoBufferingTimeLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

				// Video Durée
				if (indicateurId == Constants.Video_Duree) {
					records = mesureMobileDao
							.countByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDureeLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest, start, end);
				}

				// Video Débit
				if (indicateurId == Constants.Video_Debit) {
					records = mesureMobileDao
							.countByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDebitLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest, start, end);
				}

				// Video Mos
				if (indicateurId == Constants.MOS_VIDEO) {
					records = mesureMobileDao
							.countByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoMosLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

				/****************************** Voix *****************************/

				// Voix Durée Appel
				if (indicateurId == Constants.Voix_Duree_Appel) {
					records = mesureMobileDao
							.countByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixDureeAppelLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

				// Voix Setup Time
				if (indicateurId == Constants.Voix_Setup_Time) {
					records = mesureMobileDao
							.countByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixSetupTimeLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest, start, end);
				}

				// Voix Mos
				if (indicateurId == Constants.Voix_Mos) {
					records = mesureMobileDao
							.countByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixMosLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

				/****************************** SMS *****************************/

				// SMS Mos
				if (indicateurId == Constants.MOS_SMS) {
					records = mesureMobileDao
							.countByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSMosLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

				// SMS Delais
				if (indicateurId == Constants.DELAIS_SMS) {
					records = mesureMobileDao
							.countByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSDelaisSMSLessThanEqualAndDateBetween(
									operateur, generationTechno, borneSupTest.intValue(), start, end);
				}

			} else {
				// On a les deux bornes
				Double borneInfTest;
				if (plage.getIncluseInf() == true) {
					// La borne est Incluse
					borneInfTest = plage.getBorneInf();
				} else {
					// La borne n'est pas incluse
					borneInfTest = plage.getBorneInf() + 1;
				}

				Double borneSupTest;
				if (plage.getIncluseSup() == true) {
					// La borne est Incluse
					borneSupTest = plage.getBorneSup();
				} else {
					// La borne n'est pas incluse
					borneSupTest = plage.getBorneSup() - 1;
				}

				// Force du signal
				if (indicateurId == Constants.Force_Signal_Id) {
					records = mesureMobileDao
							.countByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndDbmBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				/****************************** FTP *****************************/

				// Latence
				if (indicateurId == Constants.Latence_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPPingBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest, borneSupTest, start, end);
				}

				// Jitter
				if (indicateurId == Constants.Jitter_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPJitterBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest, borneSupTest, start, end);
				}

				// FTP Download
				if (indicateurId == Constants.FTP_Download_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPDownloadBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest, borneSupTest, start, end);
				}

				// FTP Upload
				if (indicateurId == Constants.FTP_Upload_Id) {
					records = mesureMobileDao
							.countByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPUploadBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest, borneSupTest, start, end);
				}

				// FTP MOS
				if (indicateurId == Constants.MOS_TELECHARGEMENT) {
					records = mesureMobileDao
							.countByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileFTPMosBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				/****************************** HTTP *****************************/

				// HTTP Temps de chargement
				if (indicateurId == Constants.HTTP_Temps_Chargement) {
					records = mesureMobileDao
							.countByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPTempsChargementBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}
				
				// HTTP DNS Lookup
				if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
					records = mesureMobileDao
							.countByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDnsLookupBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				// HTTP Débit
				if (indicateurId == Constants.HTTP_Debit) {
					records = mesureMobileDao
							.countByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPDebitBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest, borneSupTest, start, end);
				}

				// HTTP Mos
				if (indicateurId == Constants.MOS_NAVIGATION) {
					records = mesureMobileDao
							.countByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileHTTPMosBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				/****************************** Video *****************************/

				// Video Buffering Time
				if (indicateurId == Constants.Video_Buffering_Time) {
					records = mesureMobileDao
							.countByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoBufferingTimeBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				// Video Durée
				if (indicateurId == Constants.Video_Duree) {
					records = mesureMobileDao
							.countByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDureeBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				// Video Débit
				if (indicateurId == Constants.Video_Debit) {
					records = mesureMobileDao
							.countByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoDebitBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest, borneSupTest, start, end);
				}

				// Video Mos
				if (indicateurId == Constants.MOS_VIDEO) {
					records = mesureMobileDao
							.countByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVideoMosBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				/****************************** Voix *****************************/

				// Voix Durée Appel
				if (indicateurId == Constants.Voix_Duree_Appel) {
					records = mesureMobileDao
							.countByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixDureeAppelBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				// Voix Setup Time
				if (indicateurId == Constants.Voix_Setup_Time) {
					records = mesureMobileDao
							.countByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixSetupTimeBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest, borneSupTest, start, end);
				}

				// Voix Mos
				if (indicateurId == Constants.Voix_Mos) {
					records = mesureMobileDao
							.countByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileVoixMosBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				/****************************** SMS *****************************/
				// SMS Mos
				if (indicateurId == Constants.MOS_SMS) {
					records = mesureMobileDao
							.countByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSMosBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

				// SMS Delais
				if (indicateurId == Constants.DELAIS_SMS) {
					records = mesureMobileDao
							.countByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndMesureMobileSMSDelaisSMSBetweenAndDateBetween(
									operateur, generationTechno, borneInfTest.intValue(), borneSupTest.intValue(),
									start, end);
				}

			}

			System.out.println("----------------> records" + records);

			double percent = ((double) records / totalRecords) * 100;

			labelRecordsPercents.add(new LabelRecordsPercent(plage.getLibelle(), percent, records, plage.getColor()));
		}

		return new StatParSeuil(totalRecords, labelRecordsPercents);
	}

	@Override
	public JSONObject geoPlage(Operateur operateur, GenerationTechno generationTechno, Date start, Date end,
			Long indicateurId) {

		JSONObject featureCollection = new JSONObject();

		String TexteGeo = "";

		try {

			featureCollection.put("type", "FeatureCollection");

			JSONArray features = new JSONArray();

			List<MesureMobile> records = new ArrayList<>();

			// Force du signal
			if (indicateurId == Constants.Force_Signal_Id) {
				records = mesureMobileDao
						.findAllByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDbmAsc(
								operateur, generationTechno, EnumTypeMesure.Couverture, start, end);
				TexteGeo = "Signal";
			}

			/****************************** FTP *****************************/

			// Latence
			if (indicateurId == Constants.Latence_Id) {
				records = mesureMobileDao
						.findAllByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, start, end);
				TexteGeo = "Latence";
			}

			// Jitter
			if (indicateurId == Constants.Jitter_Id) {
				records = mesureMobileDao
						.findAllByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPJitterAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, start, end);
				TexteGeo = "Jitter";
			}

			// FTP Download
			if (indicateurId == Constants.FTP_Download_Id) {
				records = mesureMobileDao
						.findAllByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPDownloadAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, start, end);
				TexteGeo = "DL";
			}

			// FTP Upload
			if (indicateurId == Constants.FTP_Upload_Id) {
				records = mesureMobileDao
						.findAllByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPUploadAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, start, end);
				TexteGeo = "UL";
			}

			// FTP Mos
			if (indicateurId == Constants.MOS_TELECHARGEMENT) {
				records = mesureMobileDao
						.findAllByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPMosAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, start, end);
				TexteGeo = "Score";
			}

			/****************************** HTTP *****************************/

			// HTTP Temps de chargement
			if (indicateurId == Constants.HTTP_Temps_Chargement) {
				records = mesureMobileDao
						.findAllByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPTempsChargementAsc(
								operateur, generationTechno, EnumTypeMesure.HTTP, start, end);
				TexteGeo = "Temps";
			}
			
			// HTTP DNS Lookup
						if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
							records = mesureMobileDao
									.findAllByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDnsLookupAsc(
											operateur, generationTechno, EnumTypeMesure.HTTP, start, end);
							TexteGeo = "DNS Lookup";
						}

			// HTTP Débit
			if (indicateurId == Constants.HTTP_Debit) {
				records = mesureMobileDao
						.findAllByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDebitAsc(
								operateur, generationTechno, EnumTypeMesure.HTTP, start, end);
				TexteGeo = "Debit";
			}

			// HTTP Mos
			if (indicateurId == Constants.MOS_NAVIGATION) {
				records = mesureMobileDao
						.findAllByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPMosAsc(
								operateur, generationTechno, EnumTypeMesure.HTTP, start, end);
				TexteGeo = "Score";
			}

			/****************************** Video *****************************/

			// Video Buffering Time
			if (indicateurId == Constants.Video_Buffering_Time) {
				records = mesureMobileDao
						.findAllByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoBufferingTimeAsc(
								operateur, generationTechno, EnumTypeMesure.Video, start, end);
				TexteGeo = "Temps";
			}

			// Video Durée
			if (indicateurId == Constants.Video_Duree) {
				records = mesureMobileDao
						.findAllByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDureeAsc(
								operateur, generationTechno, EnumTypeMesure.Video, start, end);
				TexteGeo = "Duree";
			}

			// Video Débit
			if (indicateurId == Constants.Video_Debit) {
				records = mesureMobileDao
						.findAllByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDebitAsc(
								operateur, generationTechno, EnumTypeMesure.Video, start, end);
				TexteGeo = "Debit";
			}

			// Video Débit
			if (indicateurId == Constants.MOS_VIDEO) {
				records = mesureMobileDao
						.findAllByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoMosAsc(
								operateur, generationTechno, EnumTypeMesure.Video, start, end);
				TexteGeo = "Score";
			}

			/****************************** Voix *****************************/

			// Voix Durée Appel
			if (indicateurId == Constants.Voix_Duree_Appel) {
				records = mesureMobileDao
						.findAllByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixDureeAppelAsc(
								operateur, generationTechno, EnumTypeMesure.Voix, start, end);
				TexteGeo = "Duree";
			}

			// Voix Setup Time
			if (indicateurId == Constants.Voix_Setup_Time) {
				records = mesureMobileDao
						.findAllByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixSetupTimeAsc(
								operateur, generationTechno, EnumTypeMesure.Voix, start, end);
				TexteGeo = "Temps";
			}

			// Voix MOS
			if (indicateurId == Constants.Voix_Mos) {
				records = mesureMobileDao
						.findAllByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixMosAsc(
								operateur, generationTechno, EnumTypeMesure.Voix, start, end);
				TexteGeo = "Score";
			}

			/****************************** SMS *****************************/
			// SMS Mos
			if (indicateurId == Constants.MOS_SMS) {
				records = mesureMobileDao
						.findAllByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSMosAsc(
								operateur, generationTechno, EnumTypeMesure.SMS, start, end);
				TexteGeo = "Score";
			}

			if (indicateurId == Constants.DELAIS_SMS) {
				records = mesureMobileDao
						.findAllByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSDelaisSMSAsc(
								operateur, generationTechno, EnumTypeMesure.SMS, start, end);
				TexteGeo = "delais";
			}

			System.out.println("----------------> records : " + records.size());

			if (!records.isEmpty()) {
				for (MesureMobile mesureMobile : records) {

					JSONObject feature = new JSONObject();
					feature.put("type", "Feature");

					JSONObject geometry = new JSONObject();
					geometry.put("type", "Point");

					JSONArray JSONArrayCoord = new JSONArray(
							"[" + mesureMobile.getLatitude() + "," + mesureMobile.getLongitude() + "]");
					geometry.put("coordinates", JSONArrayCoord);

					JSONObject properties = new JSONObject();
					properties.put("Operateur", mesureMobile.getOperateur().getId());

					// Force du signal
					if (indicateurId == Constants.Force_Signal_Id) {
						properties.put(TexteGeo, mesureMobile.getDbm());
					}

					/****************************** FTP *****************************/

					// Latence
					if (indicateurId == Constants.Latence_Id) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getPing());
					}

					// Jitter
					if (indicateurId == Constants.Jitter_Id) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getJitter());
					}

					// FTP Download
					if (indicateurId == Constants.FTP_Download_Id) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getDownload());
					}

					// FTP Upload
					if (indicateurId == Constants.FTP_Upload_Id) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getUpload());
					}

					// FTP Mos
					if (indicateurId == Constants.MOS_TELECHARGEMENT) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getMos());
					}

					/****************************** HTTP *****************************/

					// HTTP Temps de chargement
					if (indicateurId == Constants.HTTP_Temps_Chargement) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileHTTP().getTempsChargement());
					}
					
					// HTTP DNS Lookup
					if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileHTTP().getDnsLookup());
					}

					// HTTP Débit
					if (indicateurId == Constants.HTTP_Debit) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileHTTP().getDebit());
					}

					// HTTP Mos
					if (indicateurId == Constants.MOS_NAVIGATION) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileHTTP().getMos());
					}

					/****************************** Video *****************************/

					// Video Buffering Time
					if (indicateurId == Constants.Video_Buffering_Time) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVideo().getBufferingTime());
					}

					// Video Durée
					if (indicateurId == Constants.Video_Duree) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVideo().getDuree());
					}

					// Video Débit
					if (indicateurId == Constants.Video_Debit) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVideo().getDebit());
					}

					// Video Mos
					if (indicateurId == Constants.MOS_VIDEO) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVideo().getMos());
					}

					/****************************** Voix *****************************/

					// Voix Durée Appel
					if (indicateurId == Constants.Voix_Duree_Appel) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVoix().getDureeAppel());
					}

					// Voix Setup Time
					if (indicateurId == Constants.Voix_Setup_Time) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVoix().getSetupTime());
					}

					// Voix Mos
					if (indicateurId == Constants.Voix_Mos) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVoix().getMos());
					}

					/****************************** SMS *****************************/

					// SMS Mos
					if (indicateurId == Constants.MOS_SMS) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileSMS().getMos());
					}

					// SMS Delais
					if (indicateurId == Constants.DELAIS_SMS) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileSMS().getDelaisSMS());
					}

					properties.put("Mobile", mesureMobile.getMobilePhone().getModele());

					feature.put("geometry", geometry);
					feature.put("properties", properties);

					features.put(feature);

				}
			}

			featureCollection.put("features", features);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return featureCollection;
	}
	private String filterIndicator(Long indicateurId,Long indicateurIdComparable,String key){
		if (indicateurId==indicateurIdComparable)
			return key;
		return  "";
	}

	@Override
	public JSONObject geoNew(Operateur operateur, GenerationTechno generationTechno, Date start, Date end,
							 Long indicateurId, List<Long> seuilIds, Pageable pageable) {

		System.out.println("-----> indicateurId : " + indicateurId);
		Features features = new Features();
		List<Features.Feature> features1 = new ArrayList<>();
//		JSONObject featureCollection = new JSONObject();
//		String TexteGeo = "";

		try {
			Page<MesureMobile> mesureMobiles = mesureMobileDao.findAll(pageable);
			List<MobileTechno> mobileTechno = mobileTechnoDao.findByGenerationTechno(generationTechno);

			Page<MesureMobile> recordsPage = null;

			// Force du signal
			//TexteGeo = this.filterIndicator(indicateurId,Constants.Force_Signal_Id,"Signal");
//			if (indicateurId == Constants.Force_Signal_Id) {
//				List<MesureMobile> tempListMesureMobiles = mesureMobiles.stream()
//						.filter(x -> x.getDbm() != null)
//						.filter(x -> x.getLongitude() != 0)
//						.filter(x -> x.getLatitude() != 0)
//						.filter(x -> x.getOperateur().equals(operateur))
//						.filter(x -> mobileTechno.contains(x.getMobileTechno()))
//						.filter(x -> x.getTypeMesure().equals(EnumTypeMesure.Couverture))
//						.filter(x -> x.getDate().before(end))
//						.filter(x -> x.getDate().after(start))
//						.collect(Collectors.toList());
//				recordsPage = new PageImpl(tempListMesureMobiles, pageable, tempListMesureMobiles.size());
//
//				TexteGeo = "Signal";
//			}
			if (indicateurId == Constants.Force_Signal_Id) {
				recordsPage = mesureMobileDao
						.findAllByDbmIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDbmAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.Couverture, start, end, pageable);
//							TexteGeo = "Signal";
			}

			/****************************** FTP *****************************/

			// Latence
			if (indicateurId == Constants.Latence_Id) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileFTPPingIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.FTP, start, end, pageable);
//							TexteGeo = "Latence";
			}

			// Jitter
			if (indicateurId == Constants.Jitter_Id) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileFTPJitterIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPJitterAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.FTP, start, end, pageable);
//							TexteGeo = "Jitter";
			}

			// FTP Download
			if (indicateurId == Constants.FTP_Download_Id) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileFTPDownloadIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPDownloadAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.FTP, start, end, pageable);
//							TexteGeo = "DL";
			}

			// FTP Upload
			if (indicateurId == Constants.FTP_Upload_Id) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileFTPUploadIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPUploadAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.FTP, start, end, pageable);
//							TexteGeo = "UL";
			}

			// FTP Mos
			if (indicateurId == Constants.MOS_TELECHARGEMENT) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileFTPMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPMosAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.FTP, start, end, pageable);
//							TexteGeo = "Score";
			}

			/****************************** HTTP *****************************/

			// HTTP Temps de chargement
			if (indicateurId == Constants.HTTP_Temps_Chargement) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileHTTPTempsChargementIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPTempsChargementAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.HTTP, start, end, pageable);
//							TexteGeo = "Temps";
			}

			// HTTP DNS Lookup
			if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileHTTPDnsLookupIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDnsLookupAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.HTTP, start, end, pageable);
//										TexteGeo = "DNSLookup";
			}

			// HTTP Débit
			if (indicateurId == Constants.HTTP_Debit) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileHTTPDebitIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDebitAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.HTTP, start, end, pageable);
//							TexteGeo = "Debit";
			}

			// HTTP Mos
			if (indicateurId == Constants.MOS_NAVIGATION) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileHTTPMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPMosAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.HTTP, start, end, pageable);
//							TexteGeo = "Score";
			}

			/****************************** Video *****************************/

			// Video Buffering Time
			if (indicateurId == Constants.Video_Buffering_Time) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVideoBufferingTimeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoBufferingTimeAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.Video, start, end, pageable);
//							TexteGeo = "Temps";
			}

			// Video Durée
			if (indicateurId == Constants.Video_Duree) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVideoDureeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDureeAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.Video, start, end, pageable);
//							TexteGeo = "Duree";
			}

			// Video Débit
			if (indicateurId == Constants.Video_Debit) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVideoDebitIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDebitAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.Video, start, end, pageable);
//							TexteGeo = "Debit";
			}

			// Video Débit
			if (indicateurId == Constants.MOS_VIDEO) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVideoMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoMosAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.Video, start, end, pageable);
//							TexteGeo = "Score";
			}

			/****************************** Voix *****************************/

			// Voix Durée Appel
			if (indicateurId == Constants.Voix_Duree_Appel) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVoixDureeAppelIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixDureeAppelAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.Voix, start, end, pageable);
//							TexteGeo = "Duree";
			}

			// Voix Setup Time
			if (indicateurId == Constants.Voix_Setup_Time) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVoixSetupTimeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixSetupTimeAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.Voix, start, end, pageable);
//							TexteGeo = "Temps";
			}

			// Voix MOS
			if (indicateurId == Constants.Voix_Mos) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVoixMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixMosAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.Voix, start, end, pageable);
//							TexteGeo = "Score";
			}

			/****************************** SMS *****************************/
			// SMS Mos
			if (indicateurId == Constants.MOS_SMS) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileSMSMosIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSMosAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.SMS, start, end, pageable);
//							TexteGeo = "Score";
			}
			// SMS Delais
			if (indicateurId == Constants.DELAIS_SMS) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileSMSDelaisSMSIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSDelaisSMSAsc(
								0, 0, operateur, generationTechno, EnumTypeMesure.SMS, start, end, pageable);
//							TexteGeo = "Delais";
			}

			List<MesureMobile> records = recordsPage.getContent();
			Features.Metadata metadata = new Features.Metadata();
			//MetaData
//			JSONObject metadata = new JSONObject();
			metadata.setCount(recordsPage.getNumberOfElements());
			metadata.setNumeroPage(recordsPage.getNumber() + 1);
			metadata.setTotalElement(recordsPage.getTotalElements());
			metadata.setNombrePage(recordsPage.getTotalPages());
//			metadata.setTitle( TexteGeo);
			//Features feature = new Features();

			features.setMetadata(metadata);

//			featureCollection.put("metadata", metadata);


			// Features
//			JSONArray features = new JSONArray();
			if (!records.isEmpty()) {
				for (MesureMobile mesureMobile : records) {
					// Feature
//					JSONObject feature = new JSONObject();
//					features.put("type", "Feature");

					// Geometrie
//					JSONObject geometry = new JSONObject();
//					geometry.put("type", "Point");
						Features.Feature feature = new Features.Feature();

						Features.Feature.Geometry geometry = new Features.Feature.Geometry();
					Double[] longlat = new Double[2];
					longlat[0] = mesureMobile.getLongitude();
					longlat[1] = mesureMobile.getLatitude();
					geometry.setCoordinates(longlat);
//JSONArray JSONArrayCoord = new JSONArray(
//							"[" + mesureMobile.getLongitude() + "," + mesureMobile.getLatitude() + "]");
//					geometry.put("coordinates", JSONArrayCoord);
//
//					feature.put("geometry", geometry);

					feature.setGeometry(geometry);

					// Properties
					Features.Feature.Properties properties = new Features.Feature.Properties();
					Double val = 0.0;
//JSONObject properties = new JSONObject();
					properties.setOperateur(mesureMobile.getOperateur().getId());
//
					if (mesureMobile.getMobilePhone() != null) {
						properties.setMobile(mesureMobile.getMobilePhone().getModele());

//
					}
					// Force du signal
					if (indicateurId == Constants.Force_Signal_Id) {
						metadata.setTitle("Signal");

						properties.setSignal(mesureMobile.getDbm());
						val = Double.parseDouble(mesureMobile.getDbm().toString());
//
					}

					/****************************** FTP *****************************/

					// Latence
					if (indicateurId == Constants.Latence_Id) {
						metadata.setTitle("Latence");
						properties.setLatence(mesureMobile.getMesureMobileFTP().getPing());
						val = Double.parseDouble(mesureMobile.getMesureMobileFTP().getPing().toString());

//
					}

					// Jitter
					if (indicateurId == Constants.Jitter_Id) {
						metadata.setTitle("Jitter");

						properties.setJitter(mesureMobile.getMesureMobileFTP().getJitter());
						val = Double.parseDouble(mesureMobile.getMesureMobileFTP().getJitter().toString());

//
					}

					// FTP Download
					if (indicateurId == Constants.FTP_Download_Id) {
						metadata.setTitle("DL");
						properties.setDl(mesureMobile.getMesureMobileFTP().getDownload());
						val = Double.parseDouble(mesureMobile.getMesureMobileFTP().getDownload().toString());

//
					}

					// FTP Upload
					if (indicateurId == Constants.FTP_Upload_Id) {
						metadata.setTitle("UL");
						properties.setUl(mesureMobile.getMesureMobileFTP().getUpload());
						val = Double.parseDouble(mesureMobile.getMesureMobileFTP().getUpload().toString());

//
					}

					// FTP Mos
					if (indicateurId == Constants.MOS_TELECHARGEMENT) {
						metadata.setTitle("Score");
						properties.setScore(mesureMobile.getMesureMobileFTP().getMos());
						val = Double.parseDouble(mesureMobile.getMesureMobileFTP().getMos().toString());

						//
					}

					/****************************** HTTP *****************************/

					// HTTP Temps de chargement
					if (indicateurId == Constants.HTTP_Temps_Chargement) {
						metadata.setTitle("Temps");
						properties.setTemps(mesureMobile.getMesureMobileHTTP().getTempsChargement());
						val = Double.parseDouble(mesureMobile.getMesureMobileHTTP().getTempsChargement().toString());

//
					}

					// HTTP DNS Lookup
					if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
						metadata.setTitle("DNSLookup");

						properties.setDnslookup(mesureMobile.getMesureMobileHTTP().getDnsLookup());
						val = Double.parseDouble(mesureMobile.getMesureMobileHTTP().getDnsLookup().toString());

//
					}

					// HTTP Débit
					if (indicateurId == Constants.HTTP_Debit) {
						metadata.setTitle("Debit");
						properties.setDebit(mesureMobile.getMesureMobileHTTP().getDebit());
						val = Double.parseDouble(mesureMobile.getMesureMobileHTTP().getDebit().toString());

//
					}

					// HTTP Mos
					if (indicateurId == Constants.MOS_NAVIGATION) {
						metadata.setTitle("Score");
						properties.setScore(mesureMobile.getMesureMobileHTTP().getMos());
						val = Double.parseDouble(mesureMobile.getMesureMobileHTTP().getMos().toString());

//
					}

					/****************************** Video *****************************/

					// Video Buffering Time
					if (indicateurId == Constants.Video_Buffering_Time) {
						metadata.setTitle("Temps");

						properties.setTemps(mesureMobile.getMesureMobileVideo().getBufferingTime());
						val = Double.parseDouble(mesureMobile.getMesureMobileVideo().getBufferingTime().toString());

//
					}

					// Video Durée
					if (indicateurId == Constants.Video_Duree) {
						metadata.setTitle("Duree");
						properties.setDuree(mesureMobile.getMesureMobileVideo().getDuree());
						val = Double.parseDouble(mesureMobile.getMesureMobileVideo().getDuree().toString());

//
					}

					// Video Débit
					if (indicateurId == Constants.Video_Debit) {
						metadata.setTitle("Debit");
						properties.setDebit(mesureMobile.getMesureMobileVideo().getDebit());
						val = Double.parseDouble(mesureMobile.getMesureMobileVideo().getDebit().toString());

//
					}

					// Video Mos
					if (indicateurId == Constants.MOS_VIDEO) {
						metadata.setTitle("Score");
						properties.setScore(mesureMobile.getMesureMobileVideo().getMos());
						val = Double.parseDouble(mesureMobile.getMesureMobileVideo().getMos().toString());

//
					}

					/****************************** Voix *****************************/

					// Voix Durée Appel
					if (indicateurId == Constants.Voix_Duree_Appel) {
						metadata.setTitle("Duree");
						properties.setDuree(mesureMobile.getMesureMobileVoix().getDureeAppel());
						val = Double.parseDouble(mesureMobile.getMesureMobileVoix().getDureeAppel().toString());

//
					}

					// Voix Setup Time
					if (indicateurId == Constants.Voix_Setup_Time) {
						metadata.setTitle("Temps");
						properties.setTemps(mesureMobile.getMesureMobileVoix().getSetupTime());
						val = Double.parseDouble(mesureMobile.getMesureMobileVoix().getSetupTime().toString());

//
					}

					// Voix Mos
					if (indicateurId == Constants.Voix_Mos) {
						metadata.setTitle("Score");
						properties.setScore(mesureMobile.getMesureMobileVoix().getMos());
						val = Double.parseDouble(mesureMobile.getMesureMobileVoix().getMos().toString());

//
					}

					/****************************** SMS *****************************/

					// SMS Mos
					if (indicateurId == Constants.MOS_SMS) {
						metadata.setTitle("Score");
						properties.setScore(mesureMobile.getMesureMobileSMS().getMos());
						val = Double.parseDouble(mesureMobile.getMesureMobileSMS().getMos().toString());

					}

					// SMS Delais
					if (indicateurId == Constants.DELAIS_SMS) {
						metadata.setTitle("Delais");
						properties.setDelais(mesureMobile.getMesureMobileSMS().getDelaisSMS());
						val = Double.parseDouble(mesureMobile.getMesureMobileSMS().getDelaisSMS().toString());
					}
					properties.setDate(mesureMobile.getDate());
					/***********Couleur en fonction des seuils ********************/

					List<SeuilMobile> plages = getSeuilMobileList(seuilIds);

					if (!plages.isEmpty()) {
						boolean affectationDone = false;
						for (SeuilMobile plage : plages) {
							if (plage.getBorneSup() == null) {
								// On a uniquement la borne inférieure
								Double borneInfTest = plage.getBorneInf();
								if (plage.getIncluseInf()) {
									if (borneInfTest <= val) {
										properties.setColor(plage.getColor());
										properties.setSeuil(plage.getLibelle());
										affectationDone = true;
									}
								} else {
									if (borneInfTest < val) {
										properties.setColor(plage.getColor());
										properties.setSeuil(plage.getLibelle());
										affectationDone = true;
									}
								}
							} else if (plage.getBorneInf() == null) {
								// On a uniquement la borne supérieure
								Double borneSupTest = plage.getBorneSup();
								if (plage.getIncluseSup()) {
									if (val <= borneSupTest) {
										properties.setColor(plage.getColor());
										properties.setSeuil(plage.getLibelle());
										affectationDone = true;
									}
								} else {
									if (val < borneSupTest) {
										properties.setColor(plage.getColor());
										properties.setSeuil(plage.getLibelle());
										affectationDone = true;
									}
								}
							} else {
								// On a les deux bornes
								Double borneInfTest = plage.getBorneInf();
								Double borneSupTest = plage.getBorneSup();
								if ((plage.getIncluseInf()) && (plage.getIncluseSup())) {
									if ((borneInfTest <= val) && (val <= borneSupTest)) {
										properties.setColor(plage.getColor());
										properties.setSeuil(plage.getLibelle());
										affectationDone = true;
									}
								} else if ((!plage.getIncluseInf()) && (plage.getIncluseSup())) {
									if ((borneInfTest < val) && (val <= borneSupTest)) {
										properties.setColor(plage.getColor());
										properties.setSeuil(plage.getLibelle());
										affectationDone = true;
									}
								} else if ((plage.getIncluseInf()) && (!plage.getIncluseSup())) {
									if ((borneInfTest <= val) && (val < borneSupTest)) {
										properties.setColor(plage.getColor());
										properties.setSeuil(plage.getLibelle());
										affectationDone = true;
									}
								} else {
									if ((borneInfTest < val) && (val < borneSupTest)) {
										properties.setColor(plage.getColor());
										properties.setSeuil(plage.getLibelle());
										affectationDone = true;
									}
								}
							}
						}
						if (!affectationDone) {
							properties.setColor(DEFAULT_COLOR);
							properties.setSeuil(DEFAULT_LABEL_SEUIL);
						}
					} else {
						properties.setColor(DEFAULT_COLOR);
						properties.setSeuil(DEFAULT_LABEL_SEUIL);
					}
					/*************************************************************/
					feature.setProperties(properties);
					feature.setId(mesureMobile.getId());
					features1.add(feature);

				}
				System.out.println("features  :"+features1);
				features.setFeatures(features1);
				features.setType("FeatureCollection");
				System.out.println("features class  :"+features);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject(features);
	}

	@Override
	public JSONObject geoNewWifi(FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, Date start, Date end, Long indicateurId, Pageable pageable) {
		System.out.println("-----> indicateurId : " + indicateurId);


		JSONObject featureCollection = new JSONObject();
		String TexteGeo = "";

		try {
			featureCollection.put("type", "FeatureCollection");

			Page<MesureMobile> recordsPage = null;

			if (indicateurId == Constants.Force_Signal_Id) {
				recordsPage = mesureMobileDao
						.findAllByDbmIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDbmAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.Couverture, start, end, pageable);
							TexteGeo = "Signal";
			}

			/****************************** FTP *****************************/

			// Latence
			if (indicateurId == Constants.Latence_Id) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileFTPPingIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.FTP, start, end, pageable);
							TexteGeo = "Latence";
			}

			// Jitter
			if (indicateurId == Constants.Jitter_Id) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileFTPJitterIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPJitterAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.FTP, start, end, pageable);
							TexteGeo = "Jitter";
			}

			// FTP Download
			if (indicateurId == Constants.FTP_Download_Id) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileFTPDownloadIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPDownloadAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.FTP, start, end, pageable);
							TexteGeo = "DL";
			}

			// FTP Upload
			if (indicateurId == Constants.FTP_Upload_Id) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileFTPUploadIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPUploadAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.FTP, start, end, pageable);
							TexteGeo = "UL";
			}

			/****************************** HTTP *****************************/

			// HTTP Temps de chargement
			if (indicateurId == Constants.HTTP_Temps_Chargement) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileHTTPTempsChargementIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPTempsChargementAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.HTTP, start, end, pageable);
							TexteGeo = "Temps";
			}

			// HTTP DNS Lookup
			if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileHTTPDnsLookupIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDnsLookupAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.HTTP, start, end, pageable);
										TexteGeo = "DNSLookup";
			}

			// HTTP Débit
			if (indicateurId == Constants.HTTP_Debit) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileHTTPDebitIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDebitAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.HTTP, start, end, pageable);
							TexteGeo = "Debit";
			}

			/****************************** Video *****************************/

			// Video Buffering Time
			if (indicateurId == Constants.Video_Buffering_Time) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVideoBufferingTimeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoBufferingTimeAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.Video, start, end, pageable);
							TexteGeo = "Temps";
			}

			// Video Durée
			if (indicateurId == Constants.Video_Duree) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVideoDureeIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDureeAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.Video, start, end, pageable);
							TexteGeo = "Duree";
			}

			// Video Débit
			if (indicateurId == Constants.Video_Debit) {
				recordsPage = mesureMobileDao
						.findAllByMesureMobileVideoDebitIsNotNullAndLatitudeIsNotAndLongitudeIsNotAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDebitAsc(
								0, 0, fournisseurAcces, generationTechno, EnumTypeMesure.Video, start, end, pageable);
							TexteGeo = "Debit";
			}



			List<MesureMobile> records = recordsPage.getContent();
			//MetaData
			JSONObject metadata = new JSONObject();
			metadata.put("count", recordsPage.getNumberOfElements());
			metadata.put("numeroPage", recordsPage.getNumber()+1);
			metadata.put("totalElement", recordsPage.getTotalElements());
			metadata.put("nombrePage", recordsPage.getTotalPages());
			metadata.put("title", TexteGeo);

			featureCollection.put("metadata", metadata);


			// Features
			JSONArray features = new JSONArray();
			if (!records.isEmpty()) {
				for (MesureMobile mesureMobile : records) {

					// Feature
					JSONObject feature = new JSONObject();
					feature.put("type", "Feature");

					// Geometrie
					JSONObject geometry = new JSONObject();
					geometry.put("type", "Point");
					JSONArray JSONArrayCoord = new JSONArray(
							"[" + mesureMobile.getLongitude() + "," + mesureMobile.getLatitude() + "]");
					geometry.put("coordinates", JSONArrayCoord);

					feature.put("geometry", geometry);

					// Properties
					JSONObject properties = new JSONObject();
					if(fournisseurAcces != null) {
						properties.put("FournisseurAccess", mesureMobile.getFournisseurAcces().getId());
					} else {
						properties.put("FournisseurAccess", "Anonyme");
					}
					properties.put("Mobile", mesureMobile.getMobilePhone().getModele());

					// Force du signal
					if (indicateurId == Constants.Force_Signal_Id) {
						properties.put(TexteGeo, mesureMobile.getDbm());
					}

					/****************************** FTP *****************************/

					// Latence
					if (indicateurId == Constants.Latence_Id) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getPing());
					}

					// Jitter
					if (indicateurId == Constants.Jitter_Id) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getJitter());
					}

					// FTP Download
					if (indicateurId == Constants.FTP_Download_Id) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getDownload());
					}

					// FTP Upload
					if (indicateurId == Constants.FTP_Upload_Id) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getUpload());
					}

					// FTP Mos
					if (indicateurId == Constants.MOS_TELECHARGEMENT) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileFTP().getMos());
					}

					/****************************** HTTP *****************************/

					// HTTP Temps de chargement
					if (indicateurId == Constants.HTTP_Temps_Chargement) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileHTTP().getTempsChargement());
					}

					// HTTP DNS Lookup
					if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileHTTP().getDnsLookup());
					}

					// HTTP Débit
					if (indicateurId == Constants.HTTP_Debit) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileHTTP().getDebit());
					}

					// HTTP Mos
					if (indicateurId == Constants.MOS_NAVIGATION) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileHTTP().getMos());
					}

					/****************************** Video *****************************/

					// Video Buffering Time
					if (indicateurId == Constants.Video_Buffering_Time) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVideo().getBufferingTime());
					}

					// Video Durée
					if (indicateurId == Constants.Video_Duree) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVideo().getDuree());
					}

					// Video Débit
					if (indicateurId == Constants.Video_Debit) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVideo().getDebit());
					}

					// Video Mos
					if (indicateurId == Constants.MOS_VIDEO) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVideo().getMos());
					}

					/****************************** Voix *****************************/

					// Voix Durée Appel
					if (indicateurId == Constants.Voix_Duree_Appel) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVoix().getDureeAppel());
					}

					// Voix Setup Time
					if (indicateurId == Constants.Voix_Setup_Time) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVoix().getSetupTime());
					}

					// Voix Mos
					if (indicateurId == Constants.Voix_Mos) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileVoix().getMos());
					}

					/****************************** SMS *****************************/

					// SMS Mos
					if (indicateurId == Constants.MOS_SMS) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileSMS().getMos());
					}

					// SMS Delais
					if (indicateurId == Constants.DELAIS_SMS) {
						properties.put(TexteGeo, mesureMobile.getMesureMobileSMS().getDelaisSMS());
					}


					properties.put("date", mesureMobile.getDate());

					/***********Couleur en fonction des seuils ********************/

					// Rcéupérer la valeur

					Double val = Double.parseDouble(properties.get(TexteGeo).toString());
					//Double val = properties.getDouble(TexteGeo);

					/*************************************************************/

					feature.put("properties", properties);
					feature.put("id", mesureMobile.getId());

					features.put(feature);

				}

			}

			featureCollection.put("features", features);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return featureCollection;
	}

	@Override
	public List<EvolutionIndicateur> evolutionIndicateur(Operateur operateur, GenerationTechno generationTechno,
			Date start, Date end, Long indicateurId) {

		List<EvolutionIndicateur> evolutionIndicateurs = new ArrayList<>();

		List<MesureMobile> mesureMobiles = new ArrayList<>();

		// Force du signal
		if (indicateurId == Constants.Force_Signal_Id) {
			mesureMobiles = mesureMobileDao
					.findAllByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.Couverture, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(
							mesureMobile.getDate().getTime(), 
							"Signal", 
							mesureMobile.getDbm().doubleValue(), 
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()
							));
				}

			return evolutionIndicateurs;
		}

		/****************************** FTP *****************************/

		// Latence
		if (indicateurId == Constants.Latence_Id) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.FTP, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Latence",
							mesureMobile.getMesureMobileFTP().getPing(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// Jitter
		if (indicateurId == Constants.Jitter_Id) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.FTP, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Jitter",
							mesureMobile.getMesureMobileFTP().getJitter(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// FTP Download
		if (indicateurId == Constants.FTP_Download_Id) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.FTP, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "DL",
							mesureMobile.getMesureMobileFTP().getDownload(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// FTP Upload
		if (indicateurId == Constants.FTP_Upload_Id) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.FTP, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "UL",
							mesureMobile.getMesureMobileFTP().getUpload(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// FTP Mos
		if (indicateurId == Constants.MOS_TELECHARGEMENT) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.FTP, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Score",
							mesureMobile.getMesureMobileFTP().getMos().doubleValue(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		/****************************** HTTP *****************************/

		// HTTP Temps de chargement
		if (indicateurId == Constants.HTTP_Temps_Chargement) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.HTTP, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Temps",
							mesureMobile.getMesureMobileHTTP().getTempsChargement().doubleValue(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}
		
		// HTTP DNS Lookup
				if (indicateurId == Constants.HTTP_DNS_LOOKUP) {
					mesureMobiles = mesureMobileDao
							.findAllByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
									operateur, generationTechno, EnumTypeMesure.HTTP, start, end);

					if (!mesureMobiles.isEmpty())
						for (MesureMobile mesureMobile : mesureMobiles) {
							evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "DNS Lookup",
									mesureMobile.getMesureMobileHTTP().getDnsLookup().doubleValue(),
									//mesureMobile.getMobilePhone().getModele(),
									mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
									mesureMobile.getVersionOS().getVersion(), 
									mesureMobile.getMobileTechno().getTechnologie(), 
									mesureMobile.getCi(), 
									mesureMobile.getPci(), 
									mesureMobile.getTac(), 
									mesureMobile.getEarfcn(), 
									mesureMobile.getBandwidth(), 
									mesureMobile.getPsc(), 
									mesureMobile.getUarfcn(), 
									mesureMobile.getCid(), 
									mesureMobile.getLac(), 
									mesureMobile.getBsic(), 
									mesureMobile.getArfcn()));

						}

					return evolutionIndicateurs;
				}

		// HTTP Débit
		if (indicateurId == Constants.HTTP_Debit) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.HTTP, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Débit",
							mesureMobile.getMesureMobileHTTP().getDebit(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// HTTP MOS
		if (indicateurId == Constants.MOS_NAVIGATION) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.HTTP, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Score",
							mesureMobile.getMesureMobileHTTP().getMos().doubleValue(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(),
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		/****************************** Video *****************************/

		// Video Buffering Time
		if (indicateurId == Constants.Video_Buffering_Time) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.Video, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Temps",
							mesureMobile.getMesureMobileVideo().getBufferingTime().doubleValue(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// Video Durée
		if (indicateurId == Constants.Video_Duree) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.Video, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Durée",
							mesureMobile.getMesureMobileVideo().getDuree().doubleValue(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// Video Débit
		if (indicateurId == Constants.Video_Debit) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.Video, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Débit",
							mesureMobile.getMesureMobileVideo().getDebit(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// Video Mos
		if (indicateurId == Constants.MOS_VIDEO) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.Video, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Score",
							mesureMobile.getMesureMobileVideo().getMos().doubleValue(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		/****************************** Voix *****************************/

		// Voix Durée Appel
		if (indicateurId == Constants.Voix_Duree_Appel) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.Voix, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Durée",
							mesureMobile.getMesureMobileVoix().getDureeAppel().doubleValue(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// Voix Setup Time
		if (indicateurId == Constants.Voix_Setup_Time) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.Voix, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Temps",
							mesureMobile.getMesureMobileVoix().getSetupTime(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// Voix MOS
		if (indicateurId == Constants.Voix_Mos) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.Voix, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Score",
							mesureMobile.getMesureMobileVoix().getMos().doubleValue(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		/****************************** SMS *****************************/

		// SMS MOS
		if (indicateurId == Constants.MOS_SMS) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.SMS, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Score",
							mesureMobile.getMesureMobileSMS().getMos().doubleValue(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		// SMS Delais
		if (indicateurId == Constants.DELAIS_SMS) {
			mesureMobiles = mesureMobileDao
					.findAllByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDateAsc(
							operateur, generationTechno, EnumTypeMesure.SMS, start, end);

			if (!mesureMobiles.isEmpty())
				for (MesureMobile mesureMobile : mesureMobiles) {
					evolutionIndicateurs.add(new EvolutionIndicateur(mesureMobile.getDate().getTime(), "Delais",
							mesureMobile.getMesureMobileSMS().getDelaisSMS(),
							//mesureMobile.getMobilePhone().getModele(),
							mesureMobile.getVersionOS().getSystemeExploitation().getNom(), 
							mesureMobile.getVersionOS().getVersion(), 
							mesureMobile.getMobileTechno().getTechnologie(), 
							mesureMobile.getCi(), 
							mesureMobile.getPci(), 
							mesureMobile.getTac(), 
							mesureMobile.getEarfcn(), 
							mesureMobile.getBandwidth(), 
							mesureMobile.getPsc(), 
							mesureMobile.getUarfcn(), 
							mesureMobile.getCid(), 
							mesureMobile.getLac(), 
							mesureMobile.getBsic(), 
							mesureMobile.getArfcn()));

				}

			return evolutionIndicateurs;
		}

		return evolutionIndicateurs;
	}

	@Override
	public List<AvgMinMaxPerDate> findAvgMinMaxPerDate(Operateur operateur, GenerationTechno generationTechno,
			Date startDate, Date endDate, EnumGranularite granularite, Long indicateurId) {
		List<AvgMinMaxPerDate> resultList = new ArrayList<>();

		// Récupérer la liste des dates en fonction de la granularité entre les deux
		// dates
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		startDate = cal.getTime();

		System.out.println("start date --------->" + startDate);

		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		endDate = cal.getTime();

		System.out.println("end date --------->" + endDate);

		List<Date> datesBetween = getDatesBetween(startDate, endDate);

		System.out.println("dates between --------->" + datesBetween);

		// Générer la liste des dates debut et fin en fonction de la granularité
		List<DateStartEnd> dateStartEnds = generatesDateStartDebut(datesBetween, granularite);

		// Récupérer depuis le DAO tous les résultats un à un par intervalle de la
		// granularité selon l'indicateur mobile

		// Force du signal
		if (indicateurId == Constants.Force_Signal_Id)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findDBMAvgMinMaxPerDate(operateur, generationTechno,
						EnumTypeMesure.Couverture, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByDbmIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByDbmAsc(
								operateur, generationTechno, EnumTypeMesure.Couverture, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate
								.setMedian((ms.get(ms.size() / 2).getDbm() + ms.get((ms.size() / 2) - 1).getDbm()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getDbm());
				}

				resultList.add(avgMinMaxPerDate);
			}

		/****************************** FTP *****************************/

		// Latence
		if (indicateurId == Constants.Latence_Id)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findLatenceAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileFTPPingIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileFTP().getPing()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileFTP().getPing()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileFTP().getPing());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Jitter
		if (indicateurId == Constants.Jitter_Id)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findJitterAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileFTPJitterIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPJitterAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileFTP().getJitter()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileFTP().getJitter()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileFTP().getJitter());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// FTP Download
		if (indicateurId == Constants.FTP_Download_Id)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findFTPDownloadAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileFTPDownloadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPDownloadAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileFTP().getDownload()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileFTP().getDownload()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileFTP().getDownload());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// FTP Upload
		if (indicateurId == Constants.FTP_Upload_Id)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findFTPUploadAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileFTPUploadIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPUploadAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileFTP().getUpload()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileFTP().getUpload()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileFTP().getUpload());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// FTP Mos
		if (indicateurId == Constants.MOS_TELECHARGEMENT)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findFTPMosAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileFTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPMosAsc(
								operateur, generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileFTP().getMos()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileFTP().getMos()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileFTP().getMos());
				}

				resultList.add(avgMinMaxPerDate);
			}

		/****************************** HTTP *****************************/

		// HTTP Temps de chargement
		if (indicateurId == Constants.HTTP_Temps_Chargement)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findHTTPTempsChargementAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileHTTPTempsChargementIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPTempsChargementAsc(
								operateur, generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileHTTP().getTempsChargement()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileHTTP().getTempsChargement()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileHTTP().getTempsChargement());
				}

				resultList.add(avgMinMaxPerDate);
			}
		
		// HTTP DNS Lookup
				if (indicateurId == Constants.HTTP_DNS_LOOKUP)
					for (DateStartEnd dateStartEnd : dateStartEnds) {

						AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findHTTPDnsLookupAvgMinMaxPerDate(operateur,
								generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
						avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

						List<MesureMobile> ms = mesureMobileDao
								.findAllByMesureMobileHTTPDnsLookupIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDnsLookupAsc(
										operateur, generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(),
										dateStartEnd.getDateEnd());

						if (ms.isEmpty())
							avgMinMaxPerDate.setMedian(0);

						else {

							if (ms.size() % 2 == 0)
								avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileHTTP().getDnsLookup()
										+ ms.get((ms.size() / 2) - 1).getMesureMobileHTTP().getDnsLookup()) / 2);
							else
								avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileHTTP().getDnsLookup());
						}

						resultList.add(avgMinMaxPerDate);
					}

		// HTTP Débit
		if (indicateurId == Constants.HTTP_Debit)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findHTTPDebitAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileHTTPDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDebitAsc(
								operateur, generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileHTTP().getDebit()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileHTTP().getDebit()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileHTTP().getDebit());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// HTTP Mos
		if (indicateurId == Constants.MOS_NAVIGATION)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findHTTPMosAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileHTTPMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPMosAsc(
								operateur, generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileHTTP().getMos()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileHTTP().getMos()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileHTTP().getMos());
				}

				resultList.add(avgMinMaxPerDate);
			}

		/****************************** Video *****************************/

		// Video Buffering Time
		if (indicateurId == Constants.Video_Buffering_Time)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVideoBufferingTimeAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVideoBufferingTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoBufferingTimeAsc(
								operateur, generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVideo().getBufferingTime()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVideo().getBufferingTime()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVideo().getBufferingTime());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Video Durée
		if (indicateurId == Constants.Video_Duree)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVideoDureeAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVideoDureeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDureeAsc(
								operateur, generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVideo().getDuree()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVideo().getDuree()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVideo().getDuree());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Video Débit
		if (indicateurId == Constants.Video_Debit)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVideoDebitAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVideoDebitIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDebitAsc(
								operateur, generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVideo().getDebit()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVideo().getDebit()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVideo().getDebit());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Video Mos
		if (indicateurId == Constants.MOS_VIDEO)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVideoMosAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVideoMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoMosAsc(
								operateur, generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVideo().getMos()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVideo().getMos()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVideo().getMos());
				}

				resultList.add(avgMinMaxPerDate);
			}

		/****************************** Voix *****************************/

		// Voix Durée Appel
		if (indicateurId == Constants.Voix_Duree_Appel)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVoixDureeAppelAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.Voix, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVoixDureeAppelIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixDureeAppelAsc(
								operateur, generationTechno, EnumTypeMesure.Voix, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVoix().getDureeAppel()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVoix().getDureeAppel()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVoix().getDureeAppel());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Voix Setup Time
		if (indicateurId == Constants.Voix_Setup_Time)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVoixSetupTimeAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.Voix, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVoixSetupTimeIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixSetupTimeAsc(
								operateur, generationTechno, EnumTypeMesure.Voix, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVoix().getSetupTime()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVoix().getSetupTime()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVoix().getSetupTime());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Mos
		if (indicateurId == Constants.Voix_Mos)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVoixMosAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.Voix, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVoixMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVoixMosAsc(
								operateur, generationTechno, EnumTypeMesure.Voix, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVoix().getMos()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVoix().getMos()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVoix().getMos());
				}

				resultList.add(avgMinMaxPerDate);
			}

		/****************************** SMS *****************************/

		// Mos
		if (indicateurId == Constants.MOS_SMS)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findSMSMosAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.SMS, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileSMSMosIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSMosAsc(
								operateur, generationTechno, EnumTypeMesure.SMS, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileSMS().getMos()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileSMS().getMos()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileSMS().getMos());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Delais
		if (indicateurId == Constants.DELAIS_SMS)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findSMSDelaisSMSAvgMinMaxPerDate(operateur,
						generationTechno, EnumTypeMesure.SMS, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileSMSDelaisSMSIsNotNullAndOperateurAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileSMSDelaisSMSAsc(
								operateur, generationTechno, EnumTypeMesure.SMS, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileSMS().getDelaisSMS()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileSMS().getDelaisSMS()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileSMS().getDelaisSMS());
				}

				resultList.add(avgMinMaxPerDate);
			}

		return resultList;
	}

	@Override
	public List<AvgMinMaxPerDate> findAvgMinMaxPerDateWifi(FournisseurAcces fournisseurAcces, GenerationTechno generationTechno, Date startDate, Date endDate, EnumGranularite granularite, Long indicateurId) {
		List<AvgMinMaxPerDate> resultList = new ArrayList<>();

		// Récupérer la liste des dates en fonction de la granularité entre les deux
		// dates
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		startDate = cal.getTime();

		System.out.println("start date --------->" + startDate);

		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		endDate = cal.getTime();

		System.out.println("end date --------->" + endDate);

		List<Date> datesBetween = getDatesBetween(startDate, endDate);

		System.out.println("dates between --------->" + datesBetween);

		// Générer la liste des dates debut et fin en fonction de la granularité
		List<DateStartEnd> dateStartEnds = generatesDateStartDebut(datesBetween, granularite);

		// Récupérer depuis le DAO tous les résultats un à un par intervalle de la
		// granularité selon l'indicateur mobile

		/****************************** FTP *****************************/

		// Latence
		if (indicateurId == Constants.Latence_Id)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findLatenceAvgMinMaxPerDateWifi(fournisseurAcces,
						generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileFTPPingIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPPingAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileFTP().getPing()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileFTP().getPing()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileFTP().getPing());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Jitter
		if (indicateurId == Constants.Jitter_Id)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findJitterAvgMinMaxPerDateWifi(fournisseurAcces,
						generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileFTPJitterIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPJitterAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileFTP().getJitter()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileFTP().getJitter()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileFTP().getJitter());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// FTP Download
		if (indicateurId == Constants.FTP_Download_Id)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findFTPDownloadAvgMinMaxPerDateWifi(fournisseurAcces,
						generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileFTPDownloadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPDownloadAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileFTP().getDownload()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileFTP().getDownload()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileFTP().getDownload());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// FTP Upload
		if (indicateurId == Constants.FTP_Upload_Id)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findFTPUploadAvgMinMaxPerDateWifi(fournisseurAcces,
						generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileFTPUploadIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileFTPUploadAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.FTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileFTP().getUpload()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileFTP().getUpload()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileFTP().getUpload());
				}

				resultList.add(avgMinMaxPerDate);
			}


		/****************************** HTTP *****************************/

		// HTTP Temps de chargement
		if (indicateurId == Constants.HTTP_Temps_Chargement)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findHTTPTempsChargementAvgMinMaxPerDateWifi(fournisseurAcces,
						generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileHTTPTempsChargementIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPTempsChargementAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileHTTP().getTempsChargement()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileHTTP().getTempsChargement()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileHTTP().getTempsChargement());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// HTTP DNS Lookup
		if (indicateurId == Constants.HTTP_DNS_LOOKUP)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findHTTPDnsLookupAvgMinMaxPerDateWifi(fournisseurAcces,
						generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileHTTPDnsLookupIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDnsLookupAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileHTTP().getDnsLookup()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileHTTP().getDnsLookup()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileHTTP().getDnsLookup());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// HTTP Débit
		if (indicateurId == Constants.HTTP_Debit)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findHTTPDebitAvgMinMaxPerDateWifi( fournisseurAcces,
						generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileHTTPDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileHTTPDebitAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.HTTP, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileHTTP().getDebit()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileHTTP().getDebit()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileHTTP().getDebit());
				}

				resultList.add(avgMinMaxPerDate);
			}



		/****************************** Video *****************************/

		// Video Buffering Time
		if (indicateurId == Constants.Video_Buffering_Time)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVideoBufferingTimeAvgMinMaxPerDateWifi(fournisseurAcces,
						generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVideoBufferingTimeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoBufferingTimeAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVideo().getBufferingTime()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVideo().getBufferingTime()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVideo().getBufferingTime());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Video Durée
		if (indicateurId == Constants.Video_Duree)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVideoDureeAvgMinMaxPerDateWifi(fournisseurAcces,
						generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVideoDureeIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDureeAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVideo().getDuree()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVideo().getDuree()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVideo().getDuree());
				}

				resultList.add(avgMinMaxPerDate);
			}

		// Video Débit
		if (indicateurId == Constants.Video_Debit)
			for (DateStartEnd dateStartEnd : dateStartEnds) {

				AvgMinMaxPerDate avgMinMaxPerDate = mesureMobileDao.findVideoDebitAvgMinMaxPerDateWifi(fournisseurAcces,
						generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());

				List<MesureMobile> ms = mesureMobileDao
						.findAllByMesureMobileVideoDebitIsNotNullAndFournisseurAccesAndMobileTechnoGenerationTechnoAndTypeMesureAndDateBetweenOrderByMesureMobileVideoDebitAsc(
								fournisseurAcces, generationTechno, EnumTypeMesure.Video, dateStartEnd.getDateStart(),
								dateStartEnd.getDateEnd());

				if (ms.isEmpty())
					avgMinMaxPerDate.setMedian(0);

				else {

					if (ms.size() % 2 == 0)
						avgMinMaxPerDate.setMedian((ms.get(ms.size() / 2).getMesureMobileVideo().getDebit()
								+ ms.get((ms.size() / 2) - 1).getMesureMobileVideo().getDebit()) / 2);
					else
						avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesureMobileVideo().getDebit());
				}

				resultList.add(avgMinMaxPerDate);
			}


		return resultList;	}

	@Override
	public List<GeoJSONMobile> geoCouvertureMobile(Operateur operateur, GenerationTechno generationTechno) {

		List<GeoJSONMobile> geoJSONMobiles = new ArrayList<>();

		try {

			List<MesureMobile> records = new ArrayList<>();

			List<SeuilMobile> plages = seuilMobileDao.findByIndicateurMobileIdAndGenerationTechnoIdAndDefaut(Constants.Force_Signal_Id,generationTechno.getId(),
					true);

			if (!plages.isEmpty())
				for (SeuilMobile plage : plages) {

					System.out.println("----------------> Seuil : " + plage.getLibelle());

					if (plage.getBorneSup() == null) {
						// On a uniquement la borne inférieure
						Double borneInfTest;
						if (plage.getIncluseInf() == true) {
							// La borne est Incluse
							borneInfTest = plage.getBorneInf();
						} else {
							// La borne n'est pas incluse
							borneInfTest = plage.getBorneInf() + 1;
						}

						records = mesureMobileDao
								.findByDbmIsNotNullAndLongitudeIsNotAndLatitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndDbmGreaterThanEqual(
										0.0, 0.0, operateur, generationTechno, borneInfTest.intValue());

					} else if (plage.getBorneInf() == null) {
						// On a uniquement la borne supérieure
						Double borneSupTest;
						if (plage.getIncluseSup() == true) {
							// La borne est Incluse
							borneSupTest = plage.getBorneSup();
						} else {
							// La borne n'est pas incluse
							borneSupTest = plage.getBorneSup() - 1;
						}

						records = mesureMobileDao
								.findByDbmIsNotNullAndLongitudeIsNotAndLatitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndDbmLessThanEqual(
										0.0, 0.0, operateur, generationTechno, borneSupTest.intValue());

					} else {
						// On a les deux bornes
						Double borneInfTest;
						if (plage.getIncluseInf() == true) {
							// La borne est Incluse
							borneInfTest = plage.getBorneInf();
						} else {
							// La borne n'est pas incluse
							borneInfTest = plage.getBorneInf() + 1;
						}

						Double borneSupTest;
						if (plage.getIncluseSup() == true) {
							// La borne est Incluse
							borneSupTest = plage.getBorneSup();
						} else {
							// La borne n'est pas incluse
							borneSupTest = plage.getBorneSup() - 1;
						}

						records = mesureMobileDao
								.findByDbmIsNotNullAndLongitudeIsNotAndLatitudeIsNotAndOperateurAndMobileTechnoGenerationTechnoAndDbmBetween(
										0.0, 0.0, operateur, generationTechno, borneInfTest.intValue(),
										borneSupTest.intValue());

					}

					System.out.println("----------------> records : " + records.size());

					if (!records.isEmpty()) {
						for (MesureMobile mesureMobile : records) {
							geoJSONMobiles.add(new GeoJSONMobile(mesureMobile.getLongitude(),
									mesureMobile.getLatitude(), mesureMobile.getDbm(), plage.getColor()));

						}
					}

				}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!geoJSONMobiles.isEmpty() && geoJSONMobiles.size() > 5000) {
			return geoJSONMobiles.stream().distinct().collect(Collectors.toList()).subList(0, 4999);
		}

		return geoJSONMobiles.stream().distinct().collect(Collectors.toList());

	}

	@Override
	public JSONObject geoPresenceCouverture(Operateur operateur, Date start, Date end) {

		List<GeoPresenceCouverture> geoPresenceCouvertures = mesureMobileDao.findPresenceCouverturePerDate(operateur,
				start, end);

		JSONObject featureCollection = new JSONObject();

		try {

			featureCollection.put("type", "FeatureCollection");

			JSONArray features = new JSONArray();

			if (!geoPresenceCouvertures.isEmpty()) {
				for (GeoPresenceCouverture geoPresenceCouverture : geoPresenceCouvertures) {

					JSONObject feature = new JSONObject();
					feature.put("type", "Feature");

					JSONObject geometry = new JSONObject();
					geometry.put("type", "Point");

					JSONArray JSONArrayCoord = new JSONArray("[" + geoPresenceCouverture.getLatitude() + ","
							+ geoPresenceCouverture.getLongitude() + "]");
					geometry.put("coordinates", JSONArrayCoord);

					JSONObject properties = new JSONObject();
					properties.put("presence", geoPresenceCouverture.getPresence());

					feature.put("geometry", geometry);
					feature.put("properties", properties);

					features.put(feature);

				}
			}

			featureCollection.put("features", features);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return featureCollection;
	}

	@Override
	public List<EventNombrePercent> findStatEventPerDate(Operateur operateur, EnumTypeMesure typeMesure, Date start,
			Date end) {
		if (typeMesure == EnumTypeMesure.Voix) {
			Integer total = mesureMobileDao.countByTypeMesureAndMesureMobileVoixNatureAppelAndOperateurAndDateBetween(EnumTypeMesure.Voix, EnumNatureAppel.SORTANT, operateur,
					start, end);
			List<EventNombrePercent> eventNombrePercents = mesureMobileDao
					.findStatVoixEventNombrePercentPerDate(operateur, start, end);
			
			/***** Add Attempts and Success and Drop ******/
			// ATTEMPT
			eventNombrePercents.add(new EventNombrePercent("ATTEMPT", "Call Attempt", total));
			// SUCCESS
			long countSUCCESS = 0;
			for (EventNombrePercent eventNombrePercent : eventNombrePercents) {
				if(eventNombrePercent.getEvent().contains("LOCAL")  
						|| eventNombrePercent.getEvent().contains("REMOTE")  
						|| eventNombrePercent.getEvent().contains("MISSED")  
						|| eventNombrePercent.getEvent().contains("REJECTED")  
						|| eventNombrePercent.getEvent().contains("BUSY")) countSUCCESS += eventNombrePercent.getCount();
			}
			eventNombrePercents.add(new EventNombrePercent("SUCCESS", "Call success", countSUCCESS));
			
			
			if (!eventNombrePercents.isEmpty())
				for (EventNombrePercent eventNombrePercent : eventNombrePercents) {
					eventNombrePercent.calculPercent(total);
				}

			return eventNombrePercents;
		}

		if (typeMesure == EnumTypeMesure.SMS) {
			Integer total = mesureMobileDao.countByTypeMesureAndOperateurAndDateBetween(EnumTypeMesure.SMS, operateur,
					start, end);
			List<EventNombrePercent> eventNombrePercents = mesureMobileDao
					.findStatSMSEventNombrePercentPerDate(operateur, start, end);
			if (!eventNombrePercents.isEmpty())
				for (EventNombrePercent eventNombrePercent : eventNombrePercents) {
					eventNombrePercent.calculPercent(total);
				}

			return eventNombrePercents;
		}

		return null;
	}
	
	
	@Override
	public JSONObject findEventPerDate(Operateur operateur, EnumTypeMesure typeMesure, Date start,
			Date end) {
		
		List<EventDateDescription> eventDateDescriptions = new ArrayList<>();
		
		if (typeMesure == EnumTypeMesure.Voix) {
			eventDateDescriptions = mesureMobileDao.findVoixEventPerDate(operateur, start, end);
		}

		if (typeMesure == EnumTypeMesure.SMS) {
			eventDateDescriptions = mesureMobileDao.findSMSEventPerDate(operateur, start, end);
		}
		
		JSONObject featureCollection = new JSONObject();

		try {

			featureCollection.put("type", "FeatureCollection");

			JSONArray features = new JSONArray();

			if (!eventDateDescriptions.isEmpty()) {
				for (EventDateDescription eventDateDescription : eventDateDescriptions) {

					JSONObject feature = new JSONObject();
					feature.put("type", "Feature");

					JSONObject geometry = new JSONObject();
					geometry.put("type", "Point");

					JSONArray JSONArrayCoord = new JSONArray("[" + eventDateDescription.getLatitude() + ","
							+ eventDateDescription.getLongitude() + "]");
					geometry.put("coordinates", JSONArrayCoord);

					JSONObject properties = new JSONObject();
					properties.put("date", eventDateDescription.getDate());
					properties.put("event", eventDateDescription.getEvent());
					properties.put("description", eventDateDescription.getDescription());

					feature.put("geometry", geometry);
					feature.put("properties", properties);

					features.put(feature);

				}
			}

			featureCollection.put("features", features);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return featureCollection;
	}

	@Override
	public List<DateListEventNombrePercent> findEvolutionEventPerDate(Operateur operateur, EnumTypeMesure typeMesure,
			Date start, Date end, EnumGranularite granularite) {

		List<DateListEventNombrePercent> dateListEventNombrePercents = new ArrayList<>();

		// Récupérer la liste des dates en fonction de la granularité entre les deux
		// dates
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		start = cal.getTime();

		System.out.println("start date --------->" + start);

		cal.setTime(end);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		end = cal.getTime();

		System.out.println("end date --------->" + end);

		List<Date> datesBetween = getDatesBetween(start, end);

		System.out.println("dates between --------->" + datesBetween);

		// Générer la liste des dates debut et fin en fonction de la granularité
		List<DateStartEnd> dateStartEnds = generatesDateStartDebut(datesBetween, granularite);

		// Récupérer depuis le DAO tous les résultats un à un par intervalle de la
		// granularité selon l'indicateur mobile

		for (DateStartEnd dateStartEnd : dateStartEnds) {
			if (typeMesure == EnumTypeMesure.Voix) {
				Integer total = mesureMobileDao.countByTypeMesureAndMesureMobileVoixNatureAppelAndOperateurAndDateBetween(EnumTypeMesure.Voix, EnumNatureAppel.SORTANT,
						operateur, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				List<EventNombrePercent> eventNombrePercents = mesureMobileDao.findStatVoixEventNombrePercentPerDate(
						operateur, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());

				/***** Add Attempts and Success and Drop ******/
				// ATTEMPT
				eventNombrePercents.add(new EventNombrePercent("ATTEMPT", "Call Attempt", total));
				// SUCCESS
				long countSUCCESS = 0;
				for (EventNombrePercent eventNombrePercent : eventNombrePercents) {
					if(eventNombrePercent.getEvent().contains("LOCAL")  
							|| eventNombrePercent.getEvent().contains("REMOTE")  
							|| eventNombrePercent.getEvent().contains("MISSED")  
							|| eventNombrePercent.getEvent().contains("REJECTED")  
							|| eventNombrePercent.getEvent().contains("BUSY")) countSUCCESS += eventNombrePercent.getCount();
				}
				eventNombrePercents.add(new EventNombrePercent("SUCCESS", "Call success", countSUCCESS));
				
				if (!eventNombrePercents.isEmpty())
					for (EventNombrePercent eventNombrePercent : eventNombrePercents) {
						eventNombrePercent.calculPercent(total);
					}

				dateListEventNombrePercents
						.add(new DateListEventNombrePercent(dateStartEnd.getDateStart(), eventNombrePercents));
			}

			if (typeMesure == EnumTypeMesure.SMS) {
				Integer total = mesureMobileDao.countByTypeMesureAndOperateurAndDateBetween(EnumTypeMesure.SMS,
						operateur, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());

				List<EventNombrePercent> eventNombrePercents = mesureMobileDao.findStatSMSEventNombrePercentPerDate(
						operateur, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
				if (!eventNombrePercents.isEmpty())
					for (EventNombrePercent eventNombrePercent : eventNombrePercents) {
						eventNombrePercent.calculPercent(total);
					}

				dateListEventNombrePercents
						.add(new DateListEventNombrePercent(dateStartEnd.getDateStart(), eventNombrePercents));
			}

		}

		return dateListEventNombrePercents;
	}

	public static List<Date> getDatesBetween(Date startDate, Date endDate) {
		List<Date> datesInRange = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);

		while (calendar.before(endCalendar)) {
			Date result = calendar.getTime();
			datesInRange.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return datesInRange;
	}

	public static List<DateStartEnd> generatesDateStartDebut(List<Date> dates, EnumGranularite granularite) {
		List<DateStartEnd> dateStartEnds = new ArrayList<>();

		if (granularite.equals(EnumGranularite.Heure)) {
			for (Date date : dates) {
				for (int i = 0; i < 24; i++) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);

					cal.set(Calendar.HOUR_OF_DAY, i);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					Date start = cal.getTime();

					cal.set(Calendar.HOUR_OF_DAY, i);
					cal.set(Calendar.MINUTE, 59);
					cal.set(Calendar.SECOND, 59);
					Date end = cal.getTime();

					dateStartEnds.add(new DateStartEnd(start, end));

				}
			}
		} else {

			for (Date date : dates) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date start = cal.getTime();

				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				Date end = cal.getTime();

				dateStartEnds.add(new DateStartEnd(start, end));
			}

		}

		System.out.println("--------> les dates : " + dateStartEnds);

		return dateStartEnds;
	}
	
	List<SeuilMobile> getSeuilMobileList (List<Long> ids){
		List<SeuilMobile> retour = new ArrayList<>();
		if(!ids.isEmpty()) {
			for (Long id : ids) {
				retour.add(seuilMobileDao.findById(id).get());
			}
		}
		return retour;
	}

	@Override
	public List<TestDTO> test() {
		return mesureMobileDao.test();
	}

}
