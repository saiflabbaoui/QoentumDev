package com.sfm.qoentum.controller.admin;

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
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.service.admin.ClientService;
import com.sfm.qoentum.service.admin.RegulateurService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/regulateur")
public class RegulateurController {

	  @Autowired
	    private RegulateurService regulateurService;
	  
	  @Autowired
	    private ClientService clientService;
	    
	    @RequestMapping(value="/save", method = RequestMethod.POST)
	    public Regulateur saveRegulateur(@RequestBody Regulateur regulateur){
	    	return regulateurService.save(regulateur);
	    }

	    @RequestMapping(value="/list", method = RequestMethod.GET)
	    public EntityPage<Regulateur> listRegulateur(
	    		@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
	    		@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
	    		@RequestParam(name = "recherche", defaultValue="", required = false) String recherche){
	    	
	    	Pageable pageable = PageRequest.of(numPage-1, nombreElement, Sort.by("nom").ascending());
	    	
	        return regulateurService.findByNomContaining(recherche, pageable);
	    }

	    @RequestMapping(value = "/details", method = RequestMethod.GET)
	    public Regulateur getById(@RequestParam(name = "id") Long id){
	        return regulateurService.findById(id);
	    }

	    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	    public void deleteById(@RequestParam(name = "id") Long id){
	    	regulateurService.delete(id);
	    }
	    
	    @RequestMapping(value="/listRegulateurByClient", method = RequestMethod.GET)
	    public EntityPage<Regulateur> listRegulateurByClient(
	    		@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
	    		@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
	    		@RequestParam(name = "client_id", required = true) long client_id){
	    	
	    	Pageable pageable = PageRequest.of(numPage-1, nombreElement, Sort.by("nom").ascending());
	    	
	    	Client client = clientService.findById(client_id);
	    	
	        return regulateurService.findByClient(client, pageable);
	    }
}
