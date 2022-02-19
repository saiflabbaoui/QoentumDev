package com.sfm.qoentum.controller.qoentumm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sfm.qoentum.model.qoentumf.FournisseurAcces;
import com.sfm.qoentum.service.qoentumf.FournisseurAccesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.dto.DateListEventNombrePercent;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.EventNombrePercent;
import com.sfm.qoentum.dto.EvolutionIndicateur;
import com.sfm.qoentum.dto.RequestStat;
import com.sfm.qoentum.dto.StatParSeuil;
import com.sfm.qoentum.dto.geo.GeoJSONMobile;
import com.sfm.qoentum.dto.geo.TestDTO;
import com.sfm.qoentum.enumer.EnumGranularite;
import com.sfm.qoentum.enumer.EnumTypeMesure;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.model.qoentumm.Operateur;
import com.sfm.qoentum.model.qoentumm.SeuilMobile;
import com.sfm.qoentum.model.qoentumm.mesure.MesureMobile;
import com.sfm.qoentum.service.qoentumm.GenerationTechnoService;
import com.sfm.qoentum.service.qoentumm.MesureMobileService;
import com.sfm.qoentum.service.qoentumm.OperateurService;
import com.sfm.qoentum.service.qoentumm.SeuilMobileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mesureMobile")
public class MesureMobileController {

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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<MesureMobile> listMesureMobile(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "100", required = false) int nombreElement,
			@RequestParam(name = "operateur_id", required = true) Long operateur_id,
			@RequestParam(name = "generation_id", required = true) Long generation_id,
			@RequestParam(name = "indicateur_mobile_id", required = true) Long indicateur_id,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("date").descending());

		Operateur operateur = operateurService.findById(operateur_id);

		GenerationTechno generationTechno = generationTechnoService.findById(generation_id);

		return mesureMobileService.findAllByOperateurAndMobileTechnoGenerationTechno(operateur, generationTechno, start,
				end, pageable,indicateur_id);
	}
	@RequestMapping(value = "/listMesuresWifi", method = RequestMethod.GET)
	public EntityPage<MesureMobile> listMesureWifi(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "100", required = false) int nombreElement,
			@RequestParam(name = "fournisseur_id", required = true) Long fournisseur_id,
			@RequestParam(name = "generation_id", required = true) Long generation_id,
			@RequestParam(name = "indicateur_mobile_id", required = true) Long indicateur_id,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("date").descending());
		if(fournisseur_id != -1){
			FournisseurAcces fournisseurAcces = fournisseurAccesService.findById(fournisseur_id);
			GenerationTechno generationTechno = generationTechnoService.findById(generation_id);

			return mesureMobileService.findAllmesureWifi(fournisseurAcces, generationTechno, start,
					end, pageable, indicateur_id);
		} else {
			GenerationTechno generationTechno = generationTechnoService.findById(generation_id);
			return mesureMobileService.findNoAffectedmesureWifi(generationTechno, start,
					end, pageable, indicateur_id);
		}
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public MesureMobile getById(@RequestParam(name = "id") Long id) {
		return mesureMobileService.findById(id);
	}

	@RequestMapping(value = "/statPercentPlage", method = RequestMethod.POST)
	public StatParSeuil statPercentPlage(@RequestBody RequestStat requestStat) {

		Operateur operateur = operateurService.findById(requestStat.getOperateur_id());
		GenerationTechno generationTechno = generationTechnoService.findById(requestStat.getGeneration_id());

		List<SeuilMobile> seuilMobiles = new ArrayList<>();
		if (!requestStat.getSeuils_id().isEmpty() && (requestStat.getSeuils_id() != null))
			for (long l : requestStat.getSeuils_id()) {
				seuilMobiles.add(seuilMobileService.findById(l));
			}

		return mesureMobileService.statPercent(seuilMobiles, operateur, generationTechno, requestStat.getStart_date(),
				requestStat.getEnd_date(), requestStat.getIndicateur_id());
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
//		return mesureMobileService.geoNew(operateur, generationTechno, start_date, end_date, indicateur_id, pageable).toString();
//	}

	@RequestMapping(value = "/geoPlage", method = RequestMethod.POST)
	public String geoPlage(@RequestBody RequestStat requestStat) {

		Operateur operateur = operateurService.findById(requestStat.getOperateur_id());
		GenerationTechno generationTechno = generationTechnoService.findById(requestStat.getGeneration_id());

		return mesureMobileService.geoPlage(operateur, generationTechno, requestStat.getStart_date(),
				requestStat.getEnd_date(), requestStat.getIndicateur_id()).toString();
	}
	
	@RequestMapping(value = "/geoCouvertureMobile", method = RequestMethod.GET)
	public List<GeoJSONMobile> geoCouvertureMobile(
			@RequestParam(name = "operateur_id", required = true) Long operateur_id,
			@RequestParam(name = "generation_id", required = true) Long generation_id) {

		Operateur operateur = operateurService.findById(operateur_id);
		GenerationTechno generationTechno = generationTechnoService.findById(generation_id);
		
		return mesureMobileService.geoCouvertureMobile(operateur, generationTechno);
	}

	@RequestMapping(value = "/evolutionIndicateur", method = RequestMethod.GET)
	public List<EvolutionIndicateur> evolutionNiveauSignal(
			@RequestParam(name = "operateur_id", required = true) Long operateur_id,
			@RequestParam(name = "generation_id", required = true) Long generation_id,
			@RequestParam(name = "indicateur_id", required = true) Long indicateur_id,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		Operateur operateur = operateurService.findById(operateur_id);

		GenerationTechno generationTechno = generationTechnoService.findById(generation_id);

		return mesureMobileService.evolutionIndicateur(operateur, generationTechno, start, end, indicateur_id);

	}

	@RequestMapping(value = "/avgMinMaxPerDate", method = RequestMethod.GET)
	public List<AvgMinMaxPerDate> findAvgMinMaxPerDate(
			@RequestParam(name = "operateur_id", required = true) Long operateur_id,
			@RequestParam(name = "generation_id", required = true) Long generation_id,
			@RequestParam(name = "indicateur_id", required = true) Long indicateur_id,
			@RequestParam(name = "granularite", defaultValue = "Heure", required = false) EnumGranularite granularite,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		Operateur operateur = operateurService.findById(operateur_id);

		GenerationTechno generationTechno = generationTechnoService.findById(generation_id);

		return mesureMobileService.findAvgMinMaxPerDate(operateur, generationTechno, start, end, granularite,
				indicateur_id);
	}

	@RequestMapping(value = "/avgMinMaxPerDateWifi", method = RequestMethod.GET)
	public List<AvgMinMaxPerDate> findAvgMinMaxPerDateWifi(
			@RequestParam(name = "fournisseur_id", required = true) Long fournisseur_id,
			@RequestParam(name = "generation_id", required = true) Long generation_id,
			@RequestParam(name = "indicateur_id", required = true) Long indicateur_id,
			@RequestParam(name = "granularite", defaultValue = "Heure", required = false) EnumGranularite granularite,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		FournisseurAcces fournisseurAcces = fournisseurAccesService.findById(fournisseur_id);

		GenerationTechno generationTechno = generationTechnoService.findById(generation_id);

		return mesureMobileService.findAvgMinMaxPerDateWifi(fournisseurAcces, generationTechno, start, end, granularite,
				indicateur_id);
	}
	
	@RequestMapping(value = "/presenceCouverture", method = RequestMethod.GET)
	public String presenceCouverture(
			@RequestParam(name = "operateur_id", required = true) Long operateur_id,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		Operateur operateur = operateurService.findById(operateur_id);

		return mesureMobileService.geoPresenceCouverture(operateur, start, end).toString();
	}
	
	@RequestMapping(value = "/statEvent", method = RequestMethod.GET)
	public List<EventNombrePercent> findStatEventPerDate(
			@RequestParam(name = "operateur_id", required = true) Long operateur_id,
			@RequestParam(name = "typeMesure", defaultValue = "Voix", required = false) EnumTypeMesure typeMesure,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		Operateur operateur = operateurService.findById(operateur_id);

		return mesureMobileService.findStatEventPerDate(operateur, typeMesure, start, end);
	}
	
	@RequestMapping(value = "/evolutionEvent", method = RequestMethod.GET)
	public List<DateListEventNombrePercent> findEvolutionEventPerDate(
			@RequestParam(name = "operateur_id", required = true) Long operateur_id,
			@RequestParam(name = "granularite", defaultValue = "Heure", required = false) EnumGranularite granularite,
			@RequestParam(name = "typeMesure", defaultValue = "Voix", required = false) EnumTypeMesure typeMesure,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		Operateur operateur = operateurService.findById(operateur_id);

		return mesureMobileService.findEvolutionEventPerDate(operateur, typeMesure, start, end, granularite);
	}
	
	@RequestMapping(value = "/geoEvent", method = RequestMethod.GET)
	public String findEventPerDate(
			@RequestParam(name = "operateur_id", required = true) Long operateur_id,
			@RequestParam(name = "typeMesure", defaultValue = "Voix", required = false) EnumTypeMesure typeMesure,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		Operateur operateur = operateurService.findById(operateur_id);

		return mesureMobileService.findEventPerDate(operateur, typeMesure, start, end).toString();
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<TestDTO> test() {
		return mesureMobileService.test();
	}

}
