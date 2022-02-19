package com.sfm.qoentum.controller.qoentumf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.model.qoentumf.FournisseurAcces;
import com.sfm.qoentum.service.admin.RegulateurService;
import com.sfm.qoentum.service.qoentumf.FournisseurAccesService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/fournisseurAcces")
public class FournisseurAccesController {

	@Autowired
    private FournisseurAccesService fournisseurAccesService;
	
	@Autowired
    private RegulateurService regulateurService;
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public FournisseurAcces saveFournisseurAcces(@RequestBody FournisseurAcces fournisseurAcces){
    	return fournisseurAccesService.save(fournisseurAcces);
    }



    @RequestMapping(value="/list", method = RequestMethod.GET)
    public EntityPage<FournisseurAcces> listFournisseurAcces(
    		@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
    		@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
    		@RequestParam(name = "recherche", defaultValue="", required = false) String recherche){
    	
    	Pageable pageable = PageRequest.of(numPage-1, nombreElement, Sort.by("nom").ascending());
    	
        return fournisseurAccesService.findByNomContaining(recherche, pageable);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public FournisseurAcces getById(@RequestParam(name = "id") Long id){
        return fournisseurAccesService.findById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteById(@RequestParam(name = "id") Long id){
    	fournisseurAccesService.delete(id);
    }
    
    @RequestMapping(value="/listFournisseurAccessByRegulateur", method = RequestMethod.GET)
    public EntityPage<FournisseurAcces> listFournisseurAccesByRegulateur(
    		@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
    		@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
    		@RequestParam(name = "regulateur_id", required = true) long regulateur_id){
    	
    	Pageable pageable = PageRequest.of(numPage-1, nombreElement, Sort.by("nom").ascending());
    	
    	Regulateur regulateur = regulateurService.findById(regulateur_id);
    	
        return fournisseurAccesService.findByRegulateur(regulateur, pageable);
    }
}
