package com.sfm.qoentum.controller.qoentumm;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import com.sfm.qoentum.dto.RequestStatWifi;
import com.sfm.qoentum.model.qoentumf.FournisseurAcces;
import com.sfm.qoentum.service.qoentumf.FournisseurAccesService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfm.qoentum.dto.PageMeta;
import com.sfm.qoentum.dto.RequestStat;
import com.sfm.qoentum.dto.Retour;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.model.qoentumm.Operateur;
import com.sfm.qoentum.service.StorageService;
import com.sfm.qoentum.service.qoentumm.GenerationTechnoService;
import com.sfm.qoentum.service.qoentumm.MesureMobileService;
import com.sfm.qoentum.service.qoentumm.OperateurService;
import com.sfm.qoentum.service.qoentumm.SeuilMobileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/geo")
public class GeoController {

	@Autowired
	private MesureMobileService mesureMobileService;

	@Autowired
	private OperateurService operateurService;

	@Autowired
	private FournisseurAccesService fournisseurAccesService;

	@Autowired
	private GenerationTechnoService generationTechnoService;

	@Autowired
	private SeuilMobileService seuilMobileService;
	
	@Autowired
	private StorageService storageService;
	
	@ModelAttribute
	public void setResponseHeader(HttpServletResponse response) {
		response.setHeader("Content-Type", "application/vnd.geo+json");
	}

//	@RequestMapping(value = "/geoNew", method = RequestMethod.GET)
//	public String geoPlage(
//			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
//			@RequestParam(name = "nombreElement", defaultValue = "1000", required = false) int nombreElement,
//			@RequestParam(name = "operateur_id", required = true) Long operateur_id,
//			@RequestParam(name = "generation_id", required = true) Long generation_id,
//			@RequestParam(name = "indicateur_mobile_id", required = true) Long indicateur_id,
//			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start_date,
//			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end_date) {
//
//		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("date").descending());
//		
//		Operateur operateur = operateurService.findById(operateur_id);
//		GenerationTechno generationTechno = generationTechnoService.findById(generation_id);
//
//		return storageService.uploadResult(
//				mesureMobileService.geoNew(operateur, generationTechno, start_date, end_date, indicateur_id, pageable).toString()
//				, operateur.getNom()+"_"+generationTechno.getGeneration());
//	}
	
	@RequestMapping(value = "/geoNew", method = RequestMethod.POST)
	public Retour geoPlage(@RequestBody RequestStat requestStat) {

		Pageable pageable = PageRequest.of(requestStat.getNumPage() - 1, requestStat.getNombreElement(), Sort.by("date").descending());
		
		Operateur operateur = operateurService.findById(requestStat.getOperateur_id());
		GenerationTechno generationTechno = generationTechnoService.findById(requestStat.getGeneration_id());
		
		JSONObject geo = mesureMobileService.geoNew(operateur, generationTechno, requestStat.getStart_date(), requestStat.getEnd_date(), requestStat.getIndicateur_id(), requestStat.getSeuils_id(),pageable);

		PageMeta metadata = null;
		
		try {
			metadata = new ObjectMapper().readValue(geo.getJSONObject("metadata").toString(), PageMeta.class);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

		return new Retour(
				storageService.uploadResult(geo.toString(),operateur.getNom()+"_"+generationTechno.getGeneration()),
				metadata
				);
	}

	@RequestMapping(value = "/geoNew_Wifi", method = RequestMethod.POST)
	public Retour geoPlageWifi(@RequestBody RequestStatWifi requestStat) {

		Pageable pageable = PageRequest.of(requestStat.getNumPage() - 1, requestStat.getNombreElement(), Sort.by("date").descending());
        if(requestStat.getFournisseur_acces_id() != -1) {
            FournisseurAcces fournisseurAcces = fournisseurAccesService.findById(requestStat.getFournisseur_acces_id());
            GenerationTechno generationTechno = generationTechnoService.findById(requestStat.getGeneration_id());

            JSONObject geo = mesureMobileService.geoNewWifi(fournisseurAcces, generationTechno, requestStat.getStart_date(), requestStat.getEnd_date(), requestStat.getIndicateur_id(), pageable);

            PageMeta metadata = null;

            try {
                metadata = new ObjectMapper().readValue(geo.getJSONObject("metadata").toString(), PageMeta.class);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return new Retour(
                    storageService.uploadResult(geo.toString(), fournisseurAcces.getNom() + "_" + generationTechno.getGeneration()),
                    metadata
            );
        } else {
            GenerationTechno generationTechno = generationTechnoService.findById(requestStat.getGeneration_id());

            JSONObject geo = mesureMobileService.geoNewWifi(null, generationTechno, requestStat.getStart_date(), requestStat.getEnd_date(), requestStat.getIndicateur_id(), pageable);

            PageMeta metadata = null;

            try {
                metadata = new ObjectMapper().readValue(geo.getJSONObject("metadata").toString(), PageMeta.class);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return new Retour(
                    storageService.uploadResult(geo.toString(), "Anonyme_Fournisseur_Access" + "_" + generationTechno.getGeneration()),
                    metadata
            );
        }
	}
	
//	@PostConstruct
//	@Scheduled(fixedDelay = 10 * 60 * 60 * 1000) // 10 Heures
//	public void genCouvertureFile() {
//		
//		System.out.println(" ******************* Création des fichiers ****************** ");
//		
//		long couvertureId = Constants.Force_Signal_Id;
//		
//		GenerationTechno gen2G = generationTechnoService.findByGeneration("2G");
//		GenerationTechno gen3G = generationTechnoService.findByGeneration("3G");
//		GenerationTechno gen4G = generationTechnoService.findByGeneration("4G");
//		
//		Pageable pageable = PageRequest.of(0, 100000, Sort.by("date").descending());
//		
//    	Calendar cal = Calendar.getInstance();
//    	cal.set(Calendar.DAY_OF_MONTH, 1);
//    	cal.set(Calendar.MONTH, Calendar.JANUARY);
//    	cal.set(Calendar.YEAR, 2020);
//    	cal.set(Calendar.HOUR_OF_DAY, 00);
//    	cal.set(Calendar.MINUTE, 00);
//    	cal.set(Calendar.SECOND, 00);
//		Date dateDebut = cal.getTime();
//		Date dateFin = new Date();
//		
//		List<Long> seuils_id_2G = seuilMobileService.findDefaultSeuilByIndicateur(gen2G.getId(), couvertureId);
//		List<Long> seuils_id_3G = seuilMobileService.findDefaultSeuilByIndicateur(gen3G.getId(), couvertureId);
//		List<Long> seuils_id_4G = seuilMobileService.findDefaultSeuilByIndicateur(gen4G.getId(), couvertureId);
//		
//		
//		/************* Orange :	605-01 *************/
//		Operateur orange = operateurService.findByMccAndMnc("605", "01");
//
//		// 2G
//		JSONObject geoOrange2G = mesureMobileService.geoNew(orange, gen2G, dateDebut, dateFin, couvertureId, seuils_id_2G, pageable);
//		storageService.saveMapFile(geoOrange2G.toString(),"Orange2G.geojson");
//		
//		// 3G
//		JSONObject geoOrange3G = mesureMobileService.geoNew(orange, gen3G, dateDebut, dateFin, couvertureId, seuils_id_3G, pageable);
//		storageService.saveMapFile(geoOrange3G.toString(),"Orange3G.geojson");
//		
//		// 4G
//		JSONObject geoOrange4G = mesureMobileService.geoNew(orange, gen4G, dateDebut, dateFin, couvertureId, seuils_id_4G, pageable);
//		storageService.saveMapFile(geoOrange4G.toString(),"Orange4G.geojson");
//		
//		
//		
//		/************* Tunisie Telecom  : 605-02  *************/
//		Operateur tTelecom = operateurService.findByMccAndMnc("605", "02");
//
//		// 2G
//		JSONObject geoTTelecom2G = mesureMobileService.geoNew(tTelecom, gen2G, dateDebut, dateFin, couvertureId, seuils_id_2G, pageable);
//		storageService.saveMapFile(geoTTelecom2G.toString(),"TTelecom2G.geojson");
//		
//		// 3G
//		JSONObject geoTTelecom3G = mesureMobileService.geoNew(tTelecom, gen3G, dateDebut, dateFin, couvertureId, seuils_id_3G, pageable);
//		storageService.saveMapFile(geoTTelecom3G.toString(),"TTelecom3G.geojson");
//		
//		// 4G
//		JSONObject geoTTelecom4G = mesureMobileService.geoNew(tTelecom, gen4G, dateDebut, dateFin, couvertureId, seuils_id_4G, pageable);
//		storageService.saveMapFile(geoTTelecom4G.toString(),"TTelecom4G.geojson");
//		
//		
//		/************* Ooredoo	: 605-03  *************/
//		Operateur ooredoo = operateurService.findByMccAndMnc("605", "03");
//
//		// 2G
//		JSONObject geoOoredoo2G = mesureMobileService.geoNew(ooredoo, gen2G, dateDebut, dateFin, couvertureId, seuils_id_2G, pageable);
//		storageService.saveMapFile(geoOoredoo2G.toString(),"Ooredoo2G.geojson");
//		
//		// 3G
//		JSONObject geoOoredoo3G = mesureMobileService.geoNew(ooredoo, gen3G, dateDebut, dateFin, couvertureId, seuils_id_3G, pageable);
//		storageService.saveMapFile(geoOoredoo3G.toString(),"Ooredoo3G.geojson");
//		
//		// 4G
//		JSONObject geoOoredoo4G = mesureMobileService.geoNew(ooredoo, gen4G, dateDebut, dateFin, couvertureId, seuils_id_4G, pageable);
//		storageService.saveMapFile(geoOoredoo4G.toString(),"Ooredoo4G.geojson");
//	}
	
	

}
