package com.sfm.qoentum.service.admin;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.UserDto;
import com.sfm.qoentum.enumer.EnumRole;
import com.sfm.qoentum.model.admin.User;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);
    
    User changePassword(UserDto user);

    User findById(Long id);
    List<User> findUserByRole(EnumRole role);
    long countByRole(EnumRole role);
    
    long count(long client_id);
    
    EntityPage<User> findByNomContaining(User user, String nom, Pageable pageable);
    
    boolean existsUserByUsername(String username,Long id);
}
