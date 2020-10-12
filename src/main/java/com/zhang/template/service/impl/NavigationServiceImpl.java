package com.zhang.template.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.constants.ApiConstants;
import com.zhang.template.constants.ResultState;
import com.zhang.template.converter.nav.NavConverter;
import com.zhang.template.entity.Navigation;
import com.zhang.template.mapper.NavigationMapper;
import com.zhang.template.service.NavigationService;
import com.zhang.template.util.AssertUtils;
import com.zhang.template.vo.nav.NavVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhang
 * @since 2020-09-29
 */
@Service
@RequiredArgsConstructor
@Transactional
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
        AssertUtils.isNotNull(nav, ResultState.PARAM_ERROR);
        String title = nav.getTitle();
        AssertUtils.isNotEmpty(title, ResultState.PARAM_ERROR, ApiConstants.Message.TITLE_NOT_EMPTY);
        Navigation navigation = navConverter.toEntity(nav);
        Navigation navigationDb = getByTitle(title);
        AssertUtils.isNull(navigationDb, ResultState.PARAM_ERROR, ApiConstants.Message.TITLE_REPEAT);
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
        AssertUtils.isNotNull(nav, ResultState.PARAM_ERROR);
        Navigation navigation = navConverter.toEntity(nav);
        Integer navId = navigation.getId();
        AssertUtils.isNotEqualZero(navId, ResultState.PARAM_ERROR);
        Navigation navDb = baseMapper.selectById(navId);
        AssertUtils.isNotNull(navDb, ResultState.PARAM_ERROR);
        BeanUtil.copyProperties(navigation, navDb);
        baseMapper.updateById(navDb);
    }

    @Override
    public void deleteNav(int navId) {
        AssertUtils.isNotEqualZero(navId, ResultState.PARAM_ERROR);
        Navigation navigation = baseMapper.selectById(navId);
        AssertUtils.isNotNull(navigation, ResultState.PARAM_ERROR);
        Integer parentId = navigation.getParentId();
        if (parentId == ApiConstants.ZERO) {
            //可能有子导航
            deleteChildren(parentId);
        }
        baseMapper.deleteById(navId);
    }

    @Override
    public void deleteChildren(int parentId) {
        LambdaQueryWrapper<Navigation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Navigation::getParentId, parentId);
        baseMapper.delete(queryWrapper);
    }
}
