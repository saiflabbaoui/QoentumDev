package com.sfm.qoentum.service.admin.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import com.sfm.qoentum.enumer.EnumTypeLicence;
import com.sfm.qoentum.model.admin.Regulateur;
import com.sfm.qoentum.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.admin.ClientDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.service.admin.ClientService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service(value = "clientService")
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDao clientDao;

	@Override
	public Client save(MultipartFile file, String nom, String adresse, String idProjet,
					   Date dateDebutLicence, Date dateFinLicence, EnumTypeLicence typeLicence, List<Regulateur> regulateurs) throws IOException {
		Client c = new Client();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");

		}else {

			c.setLogo("client_logo/"+fileName);
			c.setNom(nom);
			c.setAdresse(adresse);
			c.setIdProjet(idProjet);
			c.setTypeLicence(typeLicence);
			c.setDateDebutLicence(dateDebutLicence);
			c.setDateFinLicence(dateFinLicence);
			String uploadDir = "/webapp/client_logo/";
			FileUploadUtil.saveFile(uploadDir, fileName, file);
		}
		return clientDao.save(c);
	}

	@Override
	public List<Client> findAll() {
		List<Client> list = new ArrayList<>();
		clientDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		clientDao.deleteById(id);
		
	}

	@Override
	public Client findById(Long id) {
		return clientDao.findById(id).get();
	}
	
	@Override
	public long count() {
		return clientDao.count();
	}
	
	@Override
	public EntityPage<Client> findByNomContaining(String nom, Pageable pageable) {
		
		Page<Client> clientsPage = clientDao.findByNomContaining(nom, pageable);
		
		EntityPage<Client> clients = new EntityPage<Client>();
		
		clients.setList(clientsPage.getContent());
		
		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(clientsPage.getNumberOfElements());
		pageUtil.setNombrePage(clientsPage.getTotalPages());
		pageUtil.setNumeroPage(clientsPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(clientsPage.getTotalElements());
		
		clients.setPageUtil(pageUtil);
		
		return clients;
	}
	
	@Override
	public boolean existsClientByNom(String nom, Long id) {
		if(id == 0) {
			return clientDao.existsClientByNom(nom);
		} else {
			return clientDao.existsClientByNomAndIdIsNot(nom, id);
		}
		
	}
}
