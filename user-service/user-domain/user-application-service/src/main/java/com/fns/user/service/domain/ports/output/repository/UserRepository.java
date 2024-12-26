package com.fns.user.service.domain.ports.output.repository;

import com.fns.user.service.domain.dto.get.GetAllUserResponse;
import com.fns.user.service.domain.entity.User;

import java.util.List;
import java.util.UUID;


public interface UserRepository {
   User save(User user);

   List<GetAllUserResponse> getAllUsers();

   User getUserById(UUID id);

   User findByUsernameOrEmail(String username, String password);
}
