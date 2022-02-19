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
import com.sfm.qoentum.model.qoentumf.IndicateurFixe;
import com.sfm.qoentum.service.qoentumf.IndicateurFixeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/indicateurFixe")
public class IndicateurFixeController {

    @Autowired
    private IndicateurFixeService indicateurFixeService;
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public IndicateurFixe saveIndicateurFixe(@RequestBody IndicateurFixe indicateurFixe){
    	return indicateurFixeService.save(indicateurFixe);
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public EntityPage<IndicateurFixe> listIndicateurFixe(
    		@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
    		@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
    		@RequestParam(name = "recherche", defaultValue="", required = false) String recherche){
    	
    	Pageable pageable = PageRequest.of(numPage-1, nombreElement, Sort.by("libelle").ascending());
    	
        return indicateurFixeService.findByNomContaining(recherche, pageable);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public IndicateurFixe getById(@RequestParam(name = "id") Long id){
        return indicateurFixeService.findById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteById(@RequestParam(name = "id") Long id){
    	indicateurFixeService.delete(id);
    }
    
    @RequestMapping(value = "/unique/libelle/{value}/{id}", method = RequestMethod.GET)
    public boolean existsByLibelle(@PathVariable(value = "value") String libelle, @PathVariable(value = "id") Long id){
        return indicateurFixeService.existsIndicateurFixeByLibelle(libelle, id);
    }

}
