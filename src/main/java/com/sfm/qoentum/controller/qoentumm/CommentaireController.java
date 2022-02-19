package com.sfm.qoentum.controller.qoentumm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sfm.qoentum.dto.CommentaireDTO;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.model.qoentumm.Commentaire;
import com.sfm.qoentum.model.qoentumm.ItemCommentaire;
import com.sfm.qoentum.model.qoentumm.MobilePhone;
import com.sfm.qoentum.model.qoentumm.Operateur;
import com.sfm.qoentum.model.qoentumm.SystemeExploitation;
import com.sfm.qoentum.service.qoentumm.CommentaireService;
import com.sfm.qoentum.service.qoentumm.ItemCommentaireService;
import com.sfm.qoentum.service.qoentumm.MobilePhoneService;
import com.sfm.qoentum.service.qoentumm.OperateurService;
import com.sfm.qoentum.service.qoentumm.SystemeExploitationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/commentaire")
public class CommentaireController {

	@Autowired
	private CommentaireService commentaireService;
	
	@Autowired
	private OperateurService operateurService;
	
	//@Autowired
	//private ImeiService imeiService;
	
	@Autowired
	private MobilePhoneService mobilePhoneService;
	
	@Autowired
	private SystemeExploitationService systemeExploitationService;
	
	@Autowired
	private ItemCommentaireService itemCommentaireService;
	

	//@RequestMapping(value = "/save", method = RequestMethod.POST)
	//public Commentaire saveCommentaire(@RequestBody CommentaireDTO commentaireDTO) {
		//Commentaire commentaire = new Commentaire();

		//commentaire.setDate(commentaireDTO.getDate());
		//commentaire.setCommentaire(commentaireDTO.getCommentaire());
		//commentaire.setNote(commentaireDTO.getNote());
		
		/*********** ItemCommentaire ********/
		//ItemCommentaire itemCommentaire = itemCommentaireService.findById(commentaireDTO.getItemCommentaire());
		//commentaire.setItemCommentaire(itemCommentaire);
		
		/*********** Operateur ***********/
		//Operateur operateur = operateurService.findById(commentaireDTO.getOperateur());
		//commentaire.setOperateur(operateur);
		
		/*********** Imei *************/
		
		//SystemeExploitation systemeExploitation = systemeExploitationService
				//.findByNom(commentaireDTO.getSystemeExploitation());
		
		
		//MobilePhone mobilePhone = null;
		//List<String> imeiList = toImeiList(commentaireDTO.getImeiList());
		
		//List<Imei> imeisToUpdate = new ArrayList<>();
		//if(!imeiList.isEmpty())
		//for (String imeiString : imeiList) {
			//Imei imei = imeiService.findByImei(imeiString);
			//if (imei == null) {
				//imei = new Imei();
				//imei.setImei(imeiString);
				//imeisToUpdate.add(imeiService.save(imei));
			//} else {
				// Récupérer le mobilePhone au cas où il aurait été une fois enregistré
				//mobilePhone = imei.getMobilePhone();
			//}
		//}

		//if (mobilePhone == null) {
			//mobilePhone = new MobilePhone();
			//mobilePhone.setModele(commentaireDTO.getModele());
			//mobilePhone.setSystemeExploitation(systemeExploitation);
			//mobilePhone = mobilePhoneService.save(mobilePhone);
		//}

		//if (!imeisToUpdate.isEmpty()) {
			//for (Imei imei : imeisToUpdate) {
				//imei.setMobilePhone(mobilePhone);
				//imeiService.save(imei);
			//}
		//}

		//Imei imei = imeiService.findByImei(commentaireDTO.getImei());

		//if (imei == null) {
			//imei = new Imei();
			//imei.setImei(commentaireDTO.getImei());
			//imei.setMobilePhone(mobilePhone);

			//imei = imeiService.save(imei);
			//commentaire.setImei(imei);

		//} else {
			//commentaire.setImei(imei);
		//}
		
		//return commentaireService.save(commentaire);
	//}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<Commentaire> listCommentaire(
			@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "operateur_id", required = true) long operateur_id,
			@RequestParam(name = "start_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
			@RequestParam(name = "end_date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {

		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("date").descending());

		return commentaireService.findByOperateur(operateur_id, start, end, pageable);
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Commentaire getById(@RequestParam(name = "id") Long id) {
		return commentaireService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		commentaireService.delete(id);
	}
	
	
	
	public List<String> toImeiList(String imeiListString) {

		if (imeiListString == null) {
			return new ArrayList<>();
		}
		
		imeiListString = imeiListString.replace("[", "").replace("]", "").trim();
		imeiListString = imeiListString.replaceAll("\\s+", "");
		String str[] = imeiListString.split(",");
		List<String> imeiList = new ArrayList<String>();
		imeiList = Arrays.asList(str);

		return imeiList;

	}

}
