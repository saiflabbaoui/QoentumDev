package com.sfm.qoentum.controller.qoentumm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.MobilePhone;
import com.sfm.qoentum.service.qoentumm.MobilePhoneService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mobilePhone")
public class MobilePhoneController {

	@Autowired
	private MobilePhoneService mobilePhoneService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<MobilePhone> listMobilePhone(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("modele").ascending());

		return mobilePhoneService.findByModeleContaining(recherche, pageable);
	}

}
