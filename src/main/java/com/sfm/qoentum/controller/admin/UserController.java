package com.sfm.qoentum.controller.admin;

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
import com.sfm.qoentum.dto.UserDto;
import com.sfm.qoentum.model.admin.User;
import com.sfm.qoentum.service.admin.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public User saveUser(@RequestBody UserDto user) {
		return userService.save(user);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public EntityPage<User> listUser(@RequestParam(name = "numPage", defaultValue = "1", required = false) int numPage,
			@RequestParam(name = "nombreElement", defaultValue = "20", required = false) int nombreElement,
			@RequestParam(name = "recherche", defaultValue = "", required = false) String recherche,
			@RequestParam(name = "user_id", required = true) Long idUser) {
		
		User user = userService.findById(idUser);
		
		Pageable pageable = PageRequest.of(numPage - 1, nombreElement, Sort.by("nom").ascending());
		
		return userService.findByNomContaining(user, recherche, pageable);
		
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public User getById(@RequestParam(name = "id") Long id) {
		return userService.findById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteById(@RequestParam(name = "id") Long id) {
		userService.delete(id);
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public User changePassword(@RequestBody UserDto userDto) {
		return userService.changePassword(userDto);
	}
	
	@RequestMapping(value = "/unique/username/{value}/{id}", method = RequestMethod.GET)
    public boolean existsByUsername(@PathVariable(value = "value") String username, 
    		@PathVariable(value = "id") Long id){
        return userService.existsUserByUsername(username,id);
    }
}
