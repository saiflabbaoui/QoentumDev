package com.sfm.qoentum.controller.qoentumm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.MobileTechno;
import com.sfm.qoentum.service.qoentumm.MobileTechnoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mobileTechno")
public class MobileTechnoController {

	@Autowired
	private MobileTechnoService mobileTechnoService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public MobileTechno saveMobileTechno(@RequestBody MobileTechno mobileTechno) {
		return mobileTechnoService.save(mobileTechno);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<MobileTechno> listMobileTechno(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("technologie").ascending());

		return mobileTechnoService.findByNomContaining(recherche, pageable);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public MobileTechno getById(@RequestParam(name = "id") Long id) {
		return mobileTechnoService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		mobileTechnoService.delete(id);
	}
}
