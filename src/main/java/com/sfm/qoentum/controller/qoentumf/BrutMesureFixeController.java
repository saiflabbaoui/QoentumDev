package com.sfm.qoentum.controller.qoentumf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.model.qoentumf.BrutMesureFixe;
import com.sfm.qoentum.service.qoentumf.BrutMesureFixeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/brutMesureFixe")
public class BrutMesureFixeController {

    @Autowired
    private BrutMesureFixeService brutMesureFixeService;
    
    @RequestMapping(value = "/saveOne", method = RequestMethod.POST)
	public void saveContinent(@RequestBody BrutMesureFixe brutMesureFixe) {
    	System.out.println("------------------------> brutMesureFixe :  " + brutMesureFixe);
		brutMesureFixeService.save(brutMesureFixe);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void saveContinent(@RequestBody List<BrutMesureFixe> brutMesureFixes) {
		for (BrutMesureFixe brutMesureFixe : brutMesureFixes) {
			brutMesureFixeService.save(brutMesureFixe);
		}
	}

}
