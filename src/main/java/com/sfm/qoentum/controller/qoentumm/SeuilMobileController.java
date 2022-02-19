package com.sfm.qoentum.controller.qoentumm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.model.qoentumm.IndicateurMobile;
import com.sfm.qoentum.model.qoentumm.SeuilMobile;
import com.sfm.qoentum.service.admin.UserService;
import com.sfm.qoentum.service.qoentumm.GenerationTechnoService;
import com.sfm.qoentum.service.qoentumm.IndicateurMobileService;
import com.sfm.qoentum.service.qoentumm.SeuilMobileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/seuilMobile")
public class SeuilMobileController {

	@Autowired
	private SeuilMobileService seuilMobileService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IndicateurMobileService indicateurMobileService;
	
	@Autowired
	private GenerationTechnoService generationTechnoService;


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public SeuilMobile savePlageSeuilMobile(@RequestBody SeuilMobile seuilMobile) {
		return seuilMobileService.save(seuilMobile);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<SeuilMobile> listPlageSeuilMobile(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche,
			@RequestParam(name = "user_id", required = true) Long user_id,
			@RequestParam(name = "generation_techno_id", required = false) Long generation_id,
			@RequestParam(name = "indicateur_mobile_id", required = false) Long indicateur_mobile_id) {

		User user = userService.findById(user_id);
		IndicateurMobile indicateurMobile = null;
		if (indicateur_mobile_id!=null) {

			try {
				indicateurMobile = indicateurMobileService.findById(indicateur_mobile_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		GenerationTechno generationTechno = null;
		if (generation_id!=null) {

			try {
				generationTechno = generationTechnoService.findById(generation_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("libelle").ascending());

		return seuilMobileService.findByNomContaining(user, indicateurMobile, generationTechno, recherche, pageable);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public SeuilMobile getById(@RequestParam(name = "id") Long id) {
		return seuilMobileService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		seuilMobileService.delete(id);
	}
	
	@RequestMapping(value = "/unique/libelle_indicateur_user_generation/{id}", method = RequestMethod.GET)
	public boolean existsByLibelleIndicateurMobileUser(
			@PathVariable(value = "id") Long id,
			@RequestParam(name = "libelle", required = true) String libelle,
			@RequestParam(name = "indicateurMobile_id", required = true) Long indicateurMobile_id,
			@RequestParam(name = "generation_techno_id", required = true) Long generation_id,
			@RequestParam(name = "user_id", required = true) Long user_id) {

		IndicateurMobile indicateurMobile = indicateurMobileService.findById(indicateurMobile_id);
		User user = userService.findById(user_id);
		GenerationTechno generationTechno = generationTechnoService.findById(generation_id);

		return seuilMobileService.existsPlageSeuilMobileByLibelleAndIndicateurMobileAndUserAndGenerationTechno(libelle,
				indicateurMobile, user, generationTechno, id);
	}

}
