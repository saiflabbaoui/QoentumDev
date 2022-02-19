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
import com.sfm.qoentum.model.qoentumf.ServiceFixe;
import com.sfm.qoentum.service.qoentumf.ServiceFixeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/serviceFixe")
public class ServiceFixeController {

	@Autowired
    private ServiceFixeService serviceFixeService;
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ServiceFixe saveServiceFixe(@RequestBody ServiceFixe serviceFixe){
    	return serviceFixeService.save(serviceFixe);
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public EntityPage<ServiceFixe> listServiceFixe(
    		@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
    		@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
    		@RequestParam(name = "recherche", defaultValue="", required = false) String recherche){
    	
    	Pageable pageable = PageRequest.of(numPage-1, nombreElement, Sort.by("service").ascending());
    	
        return serviceFixeService.findByNomContaining(recherche, pageable);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ServiceFixe getById(@RequestParam(name = "id") Long id){
        return serviceFixeService.findById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteById(@RequestParam(name = "id") Long id){
    	serviceFixeService.delete(id);
    }
    
    @RequestMapping(value = "/unique/service/{value}/{id}", method = RequestMethod.GET)
    public boolean existsByService(@PathVariable(value = "value") String service, @PathVariable(value = "id") Long id){
        return serviceFixeService.existsServiceFixeByService(service, id);
    }

}
