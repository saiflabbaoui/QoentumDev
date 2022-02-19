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
import com.sfm.qoentum.model.qoentumm.IndicateurMobile;
import com.sfm.qoentum.service.qoentumm.IndicateurMobileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/indicateurMobile")
public class IndicateurMobileController {

    @Autowired
    private IndicateurMobileService indicateurMobileService;
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public IndicateurMobile saveIndicateurMobile(@RequestBody IndicateurMobile indicateurMobile){
    	return indicateurMobileService.save(indicateurMobile);
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public EntityPage<IndicateurMobile> listIndicateurMobile(
    		@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
    		@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
    		@RequestParam(name = "recherche", defaultValue="", required = false) String recherche){
    	
    	Pageable pageable = PageRequest.of(numPage-1, nombreElement, Sort.by("id").ascending());
    	
        return indicateurMobileService.findByNomContaining(recherche, pageable);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public IndicateurMobile getById(@RequestParam(name = "id") Long id){
        return indicateurMobileService.findById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteById(@RequestParam(name = "id") Long id){
    	indicateurMobileService.delete(id);
    }
    
    @RequestMapping(value = "/unique/libelle/{value}/{id}", method = RequestMethod.GET)
    public boolean existsByLibelle(@PathVariable(value = "value") String libelle, @PathVariable(value = "id") Long id){
        return indicateurMobileService.existsIndicateurMobileByLibelle(libelle, id);
    }
}
