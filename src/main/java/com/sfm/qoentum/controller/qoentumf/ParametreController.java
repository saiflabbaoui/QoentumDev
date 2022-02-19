package com.sfm.qoentum.controller.qoentumf;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.model.qoentumf.Parametre;
import com.sfm.qoentum.model.qoentumf.Sonde;
import com.sfm.qoentum.service.qoentumf.ParametreService;
import com.sfm.qoentum.service.qoentumf.SondeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/parametre")
public class ParametreController {
	
	@Autowired 
    private SondeService sondeService;

	@Autowired
	private ParametreService parametreService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Parametre saveParametre(@RequestBody Parametre parametre) {
		return parametreService.save(parametre);
	}

	@RequestMapping(value = "/list/{idSonde}", method = RequestMethod.GET)
	public List<Parametre> listParametre(
			@PathVariable(value = "idSonde") long idSonde,
			@RequestParam(name = "hostname", defaultValue = "", required = false) String hostname,
			@RequestParam(name = "ipAddress", defaultValue = "", required = false) String ipAddress) {
		
		sondeService.setDerniereConnexion(idSonde, hostname, ipAddress, new Date());
		
		Sonde sonde = sondeService.findById(idSonde);
		
		List<Parametre> params = parametreService.findAll();
		
		// Ajouter l'emplacement actuel de la sonde
		params.add(new Parametre("EMPLACEMENT", sonde.getEmplacement().getId() + ""));
		
		//Ajouter le FAI actuel de la sonde
		params.add(new Parametre("FAI", sonde.getFournisseurAcces().getId() + ""));
		
		return params;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Parametre getById(@RequestParam(name = "id") Long id) {
		return parametreService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		parametreService.delete(id);
	}

}
