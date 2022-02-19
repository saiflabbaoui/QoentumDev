package com.sfm.qoentum.dao.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sfm.qoentum.enumer.EnumRole;
import com.sfm.qoentum.model.admin.Client;
import com.sfm.qoentum.model.admin.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findUserByRole(EnumRole role);
    long countByRole(EnumRole role);
    
	Page<User> findByNomContainingOrPrenomContaining(String nom, String prenom, Pageable pageable);
	
	Page<User> findByClientAndNomContainingOrClientAndPrenomContaining(Client client1, String nom, Client client2, String prenom, Pageable pageable);
	
	boolean existsUserByUsername(String username);
	boolean existsUserByUsernameAndIdIsNot(String username, Long id);
	
	long countByClientId(long client_id);
}
