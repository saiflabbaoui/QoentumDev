package com.sfm.qoentum.controller.admin;

import com.sfm.qoentum.enumer.EnumTypeLicence;
import com.sfm.qoentum.model.admin.Regulateur;
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
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.service.admin.ClientService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/client")
public class ClientController {

	  @Autowired
	    private ClientService clientService;
	    
	    @RequestMapping(value="/save", method = RequestMethod.POST)
	    public Client saveClient(
	    		@RequestParam(name = "file") MultipartFile file,
				@RequestParam(name = "nom") String nom,
				@RequestParam(name = "adresse") String adresse,
				@RequestParam(name = "idProjet") String idProjet,
				@RequestParam(name = "dateDebutLicence") Date dateDebutLicence,
				@RequestParam(name = "dateFinLicence") Date dateFinLicence,
				@RequestParam(name = "typeLicence") EnumTypeLicence typeLicence,
				@RequestParam(name = "regulateurs") List<Regulateur> regulateurs ) throws IOException {
	    	return clientService.save(file,nom,adresse,idProjet,dateDebutLicence,dateFinLicence,typeLicence,regulateurs);
	    }

	    @RequestMapping(value="/list", method = RequestMethod.GET)
	    public EntityPage<Client> listClient(
	    		@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
	    		@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
	    		@RequestParam(name = "recherche", defaultValue="", required = false) String recherche){
	    	
	    	Pageable pageable = PageRequest.of(numPage-1, nombreElement, Sort.by("nom").ascending());
	    	
	        return clientService.findByNomContaining(recherche, pageable);
	    }

	    @RequestMapping(value = "/details", method = RequestMethod.GET)
	    public Client getById(@RequestParam(name = "id") Long id){
	        return clientService.findById(id);
	    }

	    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	    public void deleteById(@RequestParam(name = "id") Long id){
	    	clientService.delete(id);
	    }
	    
	    @RequestMapping(value = "/unique/nom/{value}/{id}", method = RequestMethod.GET)
	    public boolean existsByNom(@PathVariable(value = "value") String nom, 
	    		@PathVariable(value = "id") Long id){
	        return clientService.existsClientByNom(nom, id);
	    }

}
