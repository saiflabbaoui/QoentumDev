package com.sfm.qoentum.controller.qoentumm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.model.qoentumm.ItemCommentaire;
import com.sfm.qoentum.service.qoentumm.ItemCommentaireService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/itemCommentaire")
public class ItemCommentaireController {

	@Autowired
	private ItemCommentaireService itemCommentaireService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ItemCommentaire saveItemCommentaire(@RequestBody ItemCommentaire itemCommentaire) {
		return itemCommentaireService.save(itemCommentaire);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<ItemCommentaire> listItemCommentaire() {
		return itemCommentaireService.findAll();
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ItemCommentaire getById(@RequestParam(name = "id") Long id) {
		return itemCommentaireService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		itemCommentaireService.delete(id);
	}

}
