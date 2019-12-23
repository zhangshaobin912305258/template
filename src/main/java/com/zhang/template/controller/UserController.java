package com.zhang.template.controller;

import com.zhang.template.service.UserServiceImpl;
import com.zhang.template.vo.PageRequest;
import com.zhang.template.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

  @Autowired private UserServiceImpl sysUserService;

  @PostMapping("/findPage")
  public Result findPage(@RequestBody PageRequest pageRequest) {
    return sysUserService.findPage(pageRequest);
  }
}
