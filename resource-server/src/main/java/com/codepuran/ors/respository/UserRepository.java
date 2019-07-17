package com.codepuran.ors.respository;

import com.codepuran.ors.dto.UserDto;
import com.codepuran.ors.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT new com.idx.dto.UserDto(u.id,u.name,u.email) FROM User u")
  public List<UserDto> getAllUser();

  public Optional<User> findByEmail(String email);
}
