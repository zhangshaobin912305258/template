package com.zhang.template.service.impl;

import com.zhang.template.entity.Menu;
import com.zhang.template.mapper.MenuMapper;
import com.zhang.template.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-03-30
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> listByName(String userName) {
        return baseMapper.listByName(userName);
    }
}
