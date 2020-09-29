package com.zhang.template.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.template.constants.ApiConstants;
import com.zhang.template.constants.ResultState;
import com.zhang.template.converter.nav.NavConverter;
import com.zhang.template.entity.Navigation;
import com.zhang.template.exception.BusinessException;
import com.zhang.template.mapper.NavigationMapper;
import com.zhang.template.service.NavigationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.vo.nav.NavVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author zhang
 * @since 2020-09-29
 */
@Service
@RequiredArgsConstructor
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper, Navigation> implements NavigationService {
    private final NavConverter navConverter;


    @Override
    public List<Navigation> rootNavs() {
        LambdaQueryWrapper<Navigation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Navigation::getStatus, ApiConstants.StatusConstant.NORMAL);
        queryWrapper.eq(Navigation::getParentId, ApiConstants.ZERO);
        queryWrapper.orderByAsc(Navigation::getOrderNum);
        List<Navigation> rootNavs = list(queryWrapper);
        if (CollUtil.isNotEmpty(rootNavs)) {
            List<Integer> navIds = rootNavs.stream()
                    .map(Navigation::getId)
                    .collect(Collectors.toList());
            List<Navigation> childNavs = childNavs(navIds);
            if (CollUtil.isNotEmpty(childNavs)) {
                Map<Integer, List<Navigation>> childNavMap = childNavs.stream().collect(Collectors.groupingBy(Navigation::getParentId));
                for (Navigation navigation : rootNavs) {
                    Integer id = navigation.getId();
                    List<Navigation> children = childNavMap.get(id);
                    if (CollUtil.isNotEmpty(children)) {
                        navigation.setChildren(children);
                    }
                }
            }
        }
        return rootNavs;
    }

    @Override
    public List<Navigation> childNavs(List<Integer> navIds) {
        LambdaQueryWrapper<Navigation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Navigation::getStatus, ApiConstants.StatusConstant.NORMAL);
        queryWrapper.in(Navigation::getParentId, navIds);
        return list(queryWrapper);
    }

    @Override
    public void addNav(NavVo nav) {
        if (nav == null) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR);
        }
        String title = nav.getTitle();
        if (StrUtil.isBlank(title)) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR, ApiConstants.Message.TITLE_NOT_EMPTY);
        }
        Navigation navigation = navConverter.toEntity(nav);
        Navigation navigationDb = getByTitle(title);
        if (navigationDb != null) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR, ApiConstants.Message.TITLE_REPEAT);
        }
        save(navigation);
    }

    @Override
    public Navigation getByTitle(String title) {
        LambdaQueryWrapper<Navigation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Navigation::getTitle, title);
        return getOne(queryWrapper);
    }

    @Override
    public void updateNav(NavVo nav) {

    }


}
