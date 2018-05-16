package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{

  private String id;
  private String name;
  private String password;
  private String mobile;
  private Boolean locked = Boolean.FALSE;
  private Boolean expired = Boolean.FALSE;
  private String fullName;
  private String email;
}
