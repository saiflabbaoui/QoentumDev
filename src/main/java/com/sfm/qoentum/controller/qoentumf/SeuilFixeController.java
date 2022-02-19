package com.sfm.qoentum.controller.qoentumf;

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
import com.sfm.qoentum.model.qoentumf.IndicateurFixe;
import com.sfm.qoentum.model.qoentumf.SeuilFixe;
import com.sfm.qoentum.service.admin.UserService;
import com.sfm.qoentum.service.qoentumf.IndicateurFixeService;
import com.sfm.qoentum.service.qoentumf.SeuilFixeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/seuilFixe")
public class SeuilFixeController {

	@Autowired
	private SeuilFixeService seuilFixeService;

	@Autowired
	private IndicateurFixeService indicateurFixeService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public SeuilFixe savePlageSeuilFixe(@RequestBody SeuilFixe seuilFixe) {
		return seuilFixeService.save(seuilFixe);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<SeuilFixe> listPlageSeuilFixe(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche,
			@RequestParam(name = "user_id", required = true) long user_id,
			@RequestParam(name = "indicateur_fixe_id", required = false) Long indicateur_fixe_id) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("libelle").ascending());

		return seuilFixeService.findByLibelleContaining(recherche, user_id, indicateur_fixe_id, pageable);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public SeuilFixe getById(@RequestParam(name = "id") Long id) {
		return seuilFixeService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		seuilFixeService.delete(id);
	}

	@RequestMapping(value = "/unique/libelle_indicateur_user/{id}", method = RequestMethod.GET)
	public boolean existsByLibelleIndicateurFixeUser(
			@PathVariable(value = "id") Long id,
			@RequestParam(name = "libelle", required = true) String libelle,
			@RequestParam(name = "indicateurFixe_id", required = true) Long indicateurFixe_id,
			@RequestParam(name = "user_id", required = true) Long user_id) {

		IndicateurFixe indicateurFixe = indicateurFixeService.findById(indicateurFixe_id);
		User user = userService.findById(user_id);

		return seuilFixeService.existsPlageSeuilFixeByLibelleAndIndicateurFixeAndUser(libelle, indicateurFixe, user, id);
	}
}
