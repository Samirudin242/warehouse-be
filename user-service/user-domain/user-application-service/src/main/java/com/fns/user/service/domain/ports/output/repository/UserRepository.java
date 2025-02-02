package com.fns.user.service.domain.ports.output.repository;

import com.fns.user.service.domain.dto.create.EditUserCommand;
import com.fns.user.service.domain.dto.get.GetAllUserResponse;
import com.fns.user.service.domain.dto.get.ProfilePhotoResponse;
import com.fns.user.service.domain.dto.get.RoleResponse;
import com.fns.user.service.domain.entity.Location;
import com.fns.user.service.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface UserRepository {
   User save(User user);

   User edit(EditUserCommand editUserCommand);

   Page<GetAllUserResponse> getAllUsers(Integer page, Integer pageSize, UUID role, String name);

   User getUserById(UUID id);

   User findByUsernameOrEmail(String username, String password);

   ProfilePhotoResponse uploadUserPhoto(MultipartFile file) throws IOException;

   List<RoleResponse> getAllRoles();
}
