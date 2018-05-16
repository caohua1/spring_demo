package com.example.demo.service.impl;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

  @Autowired
  private UserMapper userMapper;

  @Override
  public PageInfo<User> findAll(PageRequest page) {

    PageHelper.startPage(page.getPage(), page.getSize());
    List<User> users = userMapper.findByPage();
    return new PageInfo(users);
  }
}
