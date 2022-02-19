package com.sfm.qoentum.controller.qoentumm;

import java.util.List;

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
import com.sfm.qoentum.model.qoentumm.GenerationTechno;
import com.sfm.qoentum.service.qoentumm.GenerationTechnoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/generationTechno")
public class GenerationTechnoController {

	@Autowired
	private GenerationTechnoService generationTechnoService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public GenerationTechno saveGenerationTechno(@RequestBody GenerationTechno generationTechno) {
		return generationTechnoService.save(generationTechno);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<GenerationTechno> listGenerationTechno(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("generation").ascending());

		return generationTechnoService.findByNomContaining(recherche, pageable);
	}
	
	@RequestMapping(value = "/listMobile", method = RequestMethod.GET)
	public List<GenerationTechno> mobilelistGenerationTechno() {
		return generationTechnoService.findListMobile();
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public GenerationTechno getById(@RequestParam(name = "id") Long id) {
		return generationTechnoService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		generationTechnoService.delete(id);
	}
	
	@RequestMapping(value = "/unique/nom/{value}/{id}", method = RequestMethod.GET)
    public boolean existsByGeneration(@PathVariable(value = "value") String generation, 
    		@PathVariable(value = "id") Long id){
        return generationTechnoService.existsGenerationTechnoByGeneration(generation, id);
    }

}
