package com.sfm.qoentum.controller.qoentumm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.model.qoentumm.BrutMesureMobile;
import com.sfm.qoentum.service.qoentumm.BrutMesureMobileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/brutMesureMobile")
public class BrutMesureMobileController {

	@Autowired
	private BrutMesureMobileService brutMesureMobileService;
	
	@RequestMapping(value = "/saveOne", method = RequestMethod.POST)
	public void saveContinent(@RequestBody BrutMesureMobile brutMesureMobile) {
		System.out.println("--------> " + brutMesureMobile);
		brutMesureMobileService.save(brutMesureMobile);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void saveContinent(@RequestBody List<BrutMesureMobile> brutMesureMobiles) {
		for (BrutMesureMobile brutMesureMobile : brutMesureMobiles) {
			System.out.println("--------> " + brutMesureMobile);
			brutMesureMobileService.save(brutMesureMobile);
		}
	}
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public String GetToken() {
		String token = brutMesureMobileService.generateToken();
		return token;
	}
}
