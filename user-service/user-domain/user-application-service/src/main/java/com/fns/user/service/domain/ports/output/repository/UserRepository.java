package com.fns.user.service.domain.ports.output.repository;

import com.fns.user.service.domain.dto.create.CreateUserResponse;
import com.fns.user.service.domain.dto.create.GetAllUserResponse;
import com.fns.user.service.domain.entity.User;

import java.util.List;


public interface UserRepository {
   User save(User user);

   List<GetAllUserResponse> getAllUsers();
}
