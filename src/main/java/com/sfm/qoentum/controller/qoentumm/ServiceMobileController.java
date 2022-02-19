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
import com.sfm.qoentum.model.qoentumm.ServiceMobile;
import com.sfm.qoentum.service.qoentumm.ServiceMobileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/serviceMobile")
public class ServiceMobileController {

	 @Autowired
	    private ServiceMobileService serviceMobileService;
	    
	    @RequestMapping(value="/save", method = RequestMethod.POST)
	    public ServiceMobile saveServiceMobile(@RequestBody ServiceMobile serviceMobile){
	    	return serviceMobileService.save(serviceMobile);
	    }

	    @RequestMapping(value="/list", method = RequestMethod.GET)
	    public EntityPage<ServiceMobile> listServiceMobile(
	    		@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
	    		@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
	    		@RequestParam(name = "recherche", defaultValue="", required = false) String recherche){
	    	
	    	Pageable pageable = PageRequest.of(numPage-1, nombreElement, Sort.by("service").ascending());
	    	
	        return serviceMobileService.findByNomContaining(recherche, pageable);
	    }

	    @RequestMapping(value = "/details", method = RequestMethod.GET)
	    public ServiceMobile getById(@RequestParam(name = "id") Long id){
	        return serviceMobileService.findById(id);
	    }

	    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	    public void deleteById(@RequestParam(name = "id") Long id){
	    	serviceMobileService.delete(id);
	    }
	    
	    @RequestMapping(value = "/unique/service/{value}/{id}", method = RequestMethod.GET)
	    public boolean existsByService(@PathVariable(value = "value") String service, @PathVariable(value = "id") Long id ){
	        return serviceMobileService.existsServiceMobileByService(service, id);
	    }

}
