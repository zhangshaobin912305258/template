package com.zhang.template.controller;

import com.zhang.template.constants.ApiConstants;
import com.zhang.template.entity.Label;
import com.zhang.template.entity.Role;
import com.zhang.template.service.LabelService;
import com.zhang.template.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Transactional
public class TestController {
    private final LabelService labelService;
    private final RoleService roleService;

    @PostMapping("/insertLabel")
    //@Transactional//(rollbackFor = Exception.class)
    //@Transactional(propagation= Propagation.NEVER)
    public void insertLabel() {
        Label label = new Label();
        label.setName("测试");
        label.setOrderNum(0);
        label.setStatus(ApiConstants.ZERO);
        //label.setCreateTime(LocalDateTime.now());
        labelService.save(label);
        insertRole();
    }

    public void insertRole() {
        Role role = new Role();
        role.setRoleKey("1")
                .setRoleName("测试")
                //.setCreateTime(LocalDateTime.now())
                .setRemark("");
        roleService.save(role);
        int i = 1 / 0;
    }
}
