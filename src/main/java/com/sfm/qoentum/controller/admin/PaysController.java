package com.sfm.qoentum.controller.admin;

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
import com.sfm.qoentum.model.admin.Pays;
import com.sfm.qoentum.service.admin.PaysService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pays")
public class PaysController {

	@Autowired
	private PaysService paysService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Pays savePays(@RequestBody Pays pays) {
		return paysService.save(pays);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<Pays> listPays(@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("nom").ascending());

		return paysService.findByNomContaining(recherche, pageable);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Pays getById(@RequestParam(name = "id") Long id) {
		return paysService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		paysService.delete(id);
	}
	
	@RequestMapping(value = "/unique/nom/{value}/{id}", method = RequestMethod.GET)
    public boolean existsByNom(@PathVariable(value = "value") String nom, 
    		@PathVariable(value = "id") Long id){
        return paysService.existsPaysByNom(nom, id);
    }

}
