package com.zhang.template.controller;

import com.zhang.template.service.MenuService;
import com.zhang.template.vo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/menu")
@Slf4j
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /**
     * 获取用户具有的权限列表
     *
     * @param username
     * @return
     */
    @PostMapping("/listByUsername/{username}")
    public Result<Set<String>> listByUsername(@PathVariable String username) {
        Set<String> menus = menuService.listByUsername(username);
        return Result.ok(menus);
    }

}
