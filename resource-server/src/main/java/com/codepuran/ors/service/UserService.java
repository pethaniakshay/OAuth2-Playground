package com.codepuran.ors.service;

import com.codepuran.ors.dto.UserDto;
import com.codepuran.ors.entity.Role;
import com.codepuran.ors.entity.User;
import com.codepuran.ors.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public UserDto createUser(UserDto userDto) {
    // TODO add validations 1. user with email present or not, 2. field name and length

    // TODO cross check weather given role ids present in db or not otherwise it will throw an
    // error.
    Set<Role> roles = new HashSet<>();
    for (Long roleId : userDto.getRoleIds()) {
      roles.add(Role.builder().id(roleId).build());
    }

    User user =
        User.builder()
            .name(userDto.getName())
            .email(userDto.getEmail())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .roles(roles)
            .build();

    user = userRepository.save(user);
    log.info("User created");
    userDto.setId(user.getId());
    return userDto;
  }

  public List<UserDto> getAllUser() {

    List<User> allUsers = userRepository.findAll();

    List<UserDto> resultList =
        allUsers
            .stream()
            .map(
                u ->
                    UserDto.builder()
                        .id(u.getId())
                        .email(u.getEmail())
                        .name(u.getName())
                        .roleIds(
                            u.getRoles() != null
                                ? u.getRoles()
                                    .stream()
                                    .map(Role::getId)
                                    .collect(Collectors.toList())
                                : Collections.EMPTY_LIST)
                        .build())
            .collect(Collectors.toList());

    return resultList;
    // return userRepository.getAllUser();
  }
}
