package com.sfm.qoentum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.dto.DashboardStats;
import com.sfm.qoentum.service.admin.ClientService;
import com.sfm.qoentum.service.admin.RegulateurService;
import com.sfm.qoentum.service.admin.UserService;
import com.sfm.qoentum.service.qoentumf.FournisseurAccesService;
import com.sfm.qoentum.service.qoentumf.SondeService;
import com.sfm.qoentum.service.qoentumm.OperateurService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private RegulateurService regulateurService;
	
	@Autowired
	private OperateurService operateurService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SondeService sondeService;
	
	@Autowired
	private FournisseurAccesService fournisseurAccesService;
	

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public DashboardStats getStat(@RequestParam(name = "client_id", defaultValue = "-1", required = false) long client_id) {
		
		return new DashboardStats(
				userService.count(client_id),
				
				regulateurService.count(), 
				operateurService.count(), 
				fournisseurAccesService.count(), 
				clientService.count(),
				
				sondeService.count(client_id)
				);
	}

}
