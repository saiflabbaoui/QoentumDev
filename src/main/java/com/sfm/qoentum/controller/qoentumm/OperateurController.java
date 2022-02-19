package com.sfm.qoentum.controller.qoentumm;

import java.util.List;

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
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumm.Operateur;
import com.sfm.qoentum.service.admin.RegulateurService;
import com.sfm.qoentum.service.admin.UserService;
import com.sfm.qoentum.service.qoentumm.OperateurService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/operateur")
public class OperateurController {

	@Autowired
	private OperateurService operateurService;
	
	@Autowired
	private RegulateurService regulateurService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Operateur saveOperateur(@RequestBody Operateur operateur) {
		return operateurService.save(operateur);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<Operateur> listOperateur(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche,
			@RequestParam(name = "user_id", required = false) Long user_id) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("nom").ascending());
		
		if(user_id != null) {
			User user = userService.findById(user_id);
			
			System.out.println("-----------> client : " + user.getClient());
			
			return operateurService.findByNomContainingAndClient(user.getClient(), recherche, pageable);
			
		} else {
			return operateurService.findByNomContaining(recherche, pageable);
		}
		
	}
	
	@RequestMapping(value = "/listMobile", method = RequestMethod.GET)
	public List<Operateur> mobileListOperateur(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "50", required = false) int nombreElement,
			@RequestParam(name = "mcc", required = true) String mcc,
			@RequestParam(name = "mnc", required = true) String mnc) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("nom").ascending());
		
		
		Operateur operateur = operateurService.findByMccAndMnc(mcc, mnc);
		return operateurService.findByRegulateur(operateur.getRegulateur(), pageable).getList();
		
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Operateur getById(@RequestParam(name = "id") Long id) {
		return operateurService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		operateurService.delete(id);
	}
	
	@RequestMapping(value = "/listOperateurByRegulateur", method = RequestMethod.GET)
	public EntityPage<Operateur> listOperateurByRegulateur(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "regulateur_id", required = true) long regulateur_id) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("nom").ascending());
		
		Regulateur regulateur = regulateurService.findById(regulateur_id);

		return operateurService.findByRegulateur(regulateur, pageable);
	}
	
	@RequestMapping(value = "/unique/nom/{value}/{id}", method = RequestMethod.GET)
    public boolean existsByNom(@PathVariable(value = "value") String nom, 
    		@PathVariable(value = "id") Long id){
        return operateurService.existsOperateurByNom(nom,id);
    }
	
	@RequestMapping(value = "/unique/mcc_mnc/{id}", method = RequestMethod.GET)
    public boolean existsByMccAndMnc(
    		@PathVariable(value = "id") Long id,
    		@RequestParam(name = "mcc", required = true) String mcc,
    		@RequestParam(name = "mnc", required = true) String mnc){
        return operateurService.existsOperateurByMccAndMnc(mcc, mnc, id);
    }

}
