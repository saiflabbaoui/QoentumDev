package com.sfm.qoentum.controller.qoentumf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.RequestStatFixe;
import com.sfm.qoentum.dto.StatParSeuil;
import com.sfm.qoentum.enumer.EnumGranularite;
import com.sfm.qoentum.model.qoentumf.MesureFixe;
import com.sfm.qoentum.model.qoentumf.SeuilFixe;
import com.sfm.qoentum.service.qoentumf.MesureFixeService;
import com.sfm.qoentum.service.qoentumf.SeuilFixeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mesureFixe")
public class MesureFixeController {
	
	@Autowired
	private MesureFixeService mesureFixeService;
	
	@Autowired
	private SeuilFixeService seuilFixeService;
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public MesureFixe getById(@RequestParam(name = "id") Long id) {
		return mesureFixeService.findById(id);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<MesureFixe> listMesureFixe(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "100", required = false) int nombreElement,
			@RequestParam(name = "sonde_id", required = true) long sonde_id,
			@RequestParam(name = "fournisseur_acces_id", required = true) long fournisseur_acces_id,
			@RequestParam(name = "indicateur_fixe_id", required = true) long indicateur_fixe_id,
			@RequestParam(name = "emplacement_id", required = true) long emplacement_id,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {
		
		System.out.println("F----------> les params : " + numPage + " " + nombreElement + " " + sonde_id + " "+ fournisseur_acces_id + " "+ indicateur_fixe_id + " "+ emplacement_id + " "+ start + " " + end );

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("date").descending());

		return mesureFixeService.listMesureFixe(sonde_id, fournisseur_acces_id, indicateur_fixe_id, emplacement_id, start, end, pageable);
	}
	
	@RequestMapping(value = "/statPercentPlage", method = RequestMethod.POST)
	public StatParSeuil statPercentPlage(@RequestBody RequestStatFixe requestStat) {
		
		System.out.println("F----------> les params des stats : debut  ==" + requestStat.getStart_date() + " Fin ==" + requestStat.getStart_date() );


		List<SeuilFixe> seuilFixes = new ArrayList<>();
		if (!requestStat.getSeuils_id().isEmpty() && (requestStat.getSeuils_id() != null))
			for (long l : requestStat.getSeuils_id()) {
				seuilFixes.add(seuilFixeService.findById(l));
			}

		return mesureFixeService.statPercent(seuilFixes, requestStat.getSonde_id(), requestStat.getFournisseur_acces_id(), requestStat.getIndicateur_fixe_id(), requestStat.getEmplacement_id(), requestStat.getStart_date(), requestStat.getEnd_date());
	}




	@RequestMapping(value = "/avgMinMaxPerDate", method = RequestMethod.GET)
	public List<AvgMinMaxPerDate> findAvgMinMaxPerDate(
			@RequestParam(name = "granularite", defaultValue = "Heure", required = false) EnumGranularite granularite,
			@RequestParam(name = "sonde_id", required = true) long sonde_id,
			@RequestParam(name = "fournisseur_acces_id", required = true) long fournisseur_acces_id,
			@RequestParam(name = "indicateur_fixe_id", required = true) long indicateur_fixe_id,
			@RequestParam(name = "emplacement_id", required = true) long emplacement_id,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end) {

		return mesureFixeService.findAvgMinMaxPerDate(sonde_id, fournisseur_acces_id, indicateur_fixe_id, emplacement_id, start, end, granularite);
	}
	
}
