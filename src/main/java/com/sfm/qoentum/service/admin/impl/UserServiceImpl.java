package com.sfm.qoentum.service.admin.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.admin.UserDao;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.dto.UserDto;
import com.sfm.qoentum.enumer.EnumRole;
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.service.admin.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public List<User> findUserByRole(EnumRole role) {
		List<User> list = new ArrayList<>();
		userDao.findUserByRole(role).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
	public User save(UserDto user) {
		User newUser = new User();
		newUser.setId(user.getId());

		newUser.setUsername(user.getUsername());
		
		if(user.getId() != 0) {
			User u = userDao.findById(user.getId()).get();
			newUser.setPassword(u.getPassword());
		} else {
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		}
		
		newUser.setRole(user.getRole());

		newUser.setNom(user.getNom());
		newUser.setPrenom(user.getPrenom());
		newUser.setEmail(user.getEmail());
		newUser.setTel(user.getTel());
		newUser.setEnable(user.isEnable());
		newUser.setSfm(user.isSfm());
		newUser.setClient(user.getClient());

		return userDao.save(newUser);
	}
	
	@Override
	public User changePassword(UserDto user) {
		
		User userChanged = userDao.findById(user.getId()).get();		
		String newPassword = user.getPassword();
		userChanged.setPassword(bcryptEncoder.encode(newPassword));
		
		return userDao.save(userChanged);
	}

	@Override
	public long countByRole(EnumRole role) {
		return userDao.countByRole(role);
	}

	@Override
	public EntityPage<User> findByNomContaining(User user, String nom, Pageable pageable) {
		
		Page<User> usersPage;
		
		if(user.isSfm()) {
			usersPage = userDao.findByNomContainingOrPrenomContaining(nom, nom, pageable);
		} else {
			if(user.getRole()==EnumRole.ROLE_ADMIN) {
				Client client = user.getClient();
				usersPage = userDao.findByClientAndNomContainingOrClientAndPrenomContaining(client, nom, client, nom, pageable);
			} else {
				EntityPage<User> users = new EntityPage<User>();
				users.setList(new ArrayList<>());
				users.getList().add(user);
				
				PageUtil pageUtil = new PageUtil();
				pageUtil.setNombreElementParPage(1);
				pageUtil.setNombrePage(1);
				pageUtil.setNumeroPage(1);
				pageUtil.setNombreTotalElement(1);
				
				users.setPageUtil(pageUtil);
				
				return users; 
			}
			
		}
		
		
		EntityPage<User> users = new EntityPage<User>();

		users.setList(usersPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(usersPage.getNumberOfElements());
		pageUtil.setNombrePage(usersPage.getTotalPages());
		pageUtil.setNumeroPage(usersPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(usersPage.getTotalElements());

		users.setPageUtil(pageUtil);

		return users;
	}
	
	@Override
	public boolean existsUserByUsername(String username, Long id) {
		if(id==0) {
			return userDao.existsUserByUsername(username);
		} else {
			return userDao.existsUserByUsernameAndIdIsNot(username, id);
		}
		
	}
	
	@Override
	public long count(long client_id) {
		if (client_id == -1) 
			return userDao.count();
		else
			return userDao.countByClientId(client_id);
	}
}
