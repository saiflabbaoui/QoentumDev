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
import com.sfm.qoentum.model.qoentumm.VersionOS;
import com.sfm.qoentum.service.qoentumm.VersionOSService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/versionOS")
public class VersionOSController {

	@Autowired
	private VersionOSService versionOSService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public VersionOS saveVersionOS(@RequestBody VersionOS versionOS) {
		return versionOSService.save(versionOS);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<VersionOS> listVersionOS(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("nom").ascending());

		return versionOSService.findByNomContaining(recherche, pageable);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public VersionOS getById(@RequestParam(name = "id") Long id) {
		return versionOSService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		versionOSService.delete(id);
	}

	@RequestMapping(value = "/unique/version/{value}/{id}", method = RequestMethod.GET)
	public boolean existsByVersion(@PathVariable(value = "value") String nom, 
			@PathVariable(value = "id") Long id) {
		return versionOSService.existsVersionOSByVersion(nom, id);
	}

}
