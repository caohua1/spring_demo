package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageRequest implements Serializable {

  private int page;
  private int size = 20;

  public int getOffset(){
    return page * size;
  }

  public PageRequest(){

  }

  public PageRequest(int page, int size){
    this.page = page;
    this.size = size;
  }

}