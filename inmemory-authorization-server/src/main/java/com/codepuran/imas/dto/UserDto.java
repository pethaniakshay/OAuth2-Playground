package com.codepuran.imas.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;

  private String name;

  private String email;

  private String password;

  private List<Long> roleIds;

  public UserDto(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }
}
