package com.example.demo.controller;

import com.example.demo.entity.PageRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.JsonView;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private UserService userService;

  @GetMapping("/all")
  public JsonView findAll(PageRequest page) {
    logger.info("Request comming to find user list...");
    PageInfo<User> pageInfo = userService.findAll(page);
    return JsonView.success(pageInfo);
  }
}
