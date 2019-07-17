package com.codepuran.imas.respository;

import com.codepuran.imas.dto.UserDto;
import com.codepuran.imas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT new com.idx.dto.UserDto(u.id,u.name,u.email) FROM User u")
  public List<UserDto> getAllUser();

  public Optional<User> findByEmail(String email);
}
