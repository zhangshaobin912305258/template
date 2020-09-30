package com.zhang.template.service;

import com.zhang.template.entity.Navigation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.template.vo.nav.NavVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2020-09-29
 */
public interface NavigationService extends IService<Navigation> {

    List<Navigation> rootNavs();

    List<Navigation> childNavs(List<Integer> navIds);

    void addNav(NavVo nav);

    Navigation getByTitle(String title);

    void updateNav(NavVo nav);

    void deleteNav(int navId);

    void deleteChildren(int parentId);
}
