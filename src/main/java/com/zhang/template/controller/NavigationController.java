package com.zhang.template.controller;


import com.zhang.template.entity.Navigation;
import com.zhang.template.service.NavigationService;
import com.zhang.template.vo.Result;
import com.zhang.template.vo.nav.NavVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author zhang
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/api/nav")
@RequiredArgsConstructor
public class NavigationController {
    private final NavigationService navigationService;

    /**
     * 一级导航列表
     * @return
     */
    @GetMapping("/rootNavs")
    public Result rootNavs() {
        List<Navigation> navList = navigationService.rootNavs();
        return Result.ok(navList);
    }

    /**
     * 添加导航
     * @param nav
     * @return
     */
    @PostMapping("/addNav")
    public Result addNav(@RequestBody NavVo nav) {
        navigationService.addNav(nav);
        return Result.ok();
    }

    /**
     * 修改导航
     * @param nav
     * @return
     */
    @PostMapping("/updateNav")
    public Result updateNav(@RequestBody NavVo nav) {
        navigationService.updateNav(nav);
        return Result.ok();
    }




}
