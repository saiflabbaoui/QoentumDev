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
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.model.qoentumf.GroupeSonde;
import com.sfm.qoentum.service.admin.UserService;
import com.sfm.qoentum.service.qoentumf.GroupeSondeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/groupeSonde")
public class GroupeSondeController {

	@Autowired
	private GroupeSondeService groupeSondeService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public GroupeSonde saveGroupeSonde(@RequestBody GroupeSonde groupeSonde) {
		return groupeSondeService.save(groupeSonde);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<GroupeSonde> listGroupeSonde(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("nom").ascending());

		return groupeSondeService.findByNomContaining(recherche, pageable);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public GroupeSonde getById(@RequestParam(name = "id") Long id) {
		return groupeSondeService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		groupeSondeService.delete(id);
	}

	@RequestMapping(value = "/listGroupeSondeByUser", method = RequestMethod.GET)
	public EntityPage<GroupeSonde> listGroupeSondeByUser(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "user_id", required = true) long user_id) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("nom").ascending());
		
		User user = userService.findById(user_id);

		return groupeSondeService.findByUser(user, pageable);
	}
	
	@RequestMapping(value = "/unique/nom/{value}/{id}", method = RequestMethod.GET)
    public boolean existsByNom(@PathVariable(value = "value") String nom, @PathVariable(value = "id") Long id){
        return groupeSondeService.existsGroupeSondeByNom(nom, id);
    }
}
