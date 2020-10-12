package com.zhang.template.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.constants.ApiConstants;
import com.zhang.template.constants.ResultState;
import com.zhang.template.entity.Article;
import com.zhang.template.mapper.ArticleMapper;
import com.zhang.template.service.ArticleService;
import com.zhang.template.util.AssertUtils;
import com.zhang.template.vo.page.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhang
 * @since 2020-09-29
 */
@SuppressWarnings("DuplicatedCode")
@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public void add(Article article) {
        AssertUtils.isNotNull(article, ResultState.PARAM_ERROR);
        String title = article.getTitle();
        AssertUtils.isNotEmpty(title, ResultState.PARAM_ERROR, ApiConstants.Message.TITLE_NOT_EMPTY);
        Article articleDb = getByTitle(title);
        AssertUtils.isNull(articleDb, ResultState.PARAM_ERROR, ApiConstants.Message.TITLE_REPEAT);
        baseMapper.insert(article);
    }

    @Override
    public Article getByTitle(String title) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getTitle, title);
        return getOne(queryWrapper);
    }

    @Override
    public void update(Article article) {
        AssertUtils.isNotNull(article, ResultState.PARAM_ERROR);
        String title = article.getTitle();
        AssertUtils.isNotEmpty(title, ResultState.PARAM_ERROR, ApiConstants.Message.TITLE_NOT_EMPTY);
        Integer id = article.getId();
        AssertUtils.isNotEqualZero(id, ResultState.PARAM_ERROR);
        Article articleDb = getById(id);
        AssertUtils.isNotNull(articleDb, ResultState.PARAM_ERROR, ApiConstants.Message.ARTICLE_NOT_EXIST);
        String titleDb = articleDb.getTitle();
        if (!title.equals(titleDb)) {
            //修改了标题,需要判断是否存在重复的article
            Article repeatArticle = getByTitle(title);
            AssertUtils.isNull(repeatArticle, ResultState.PARAM_ERROR, ApiConstants.Message.TITLE_REPEAT);
        }
        BeanUtil.copyProperties(article, articleDb, "id", "createTime");
        saveOrUpdate(articleDb);
    }

    @Override
    public void delete(Integer articleId) {
        removeById(articleId);
    }

    @Override
    public Page<Article> listByNavId(Integer navId, PageRequest pageRequest) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getNavId, navId);
        queryWrapper.eq(Article::getStatus, ApiConstants.ZERO);
        queryWrapper.eq(Article::getDraft, ApiConstants.ZERO);
        String key = pageRequest.getKey();
        queryWrapper.like(StrUtil.isNotBlank(key), Article::getTitle, key);
        /*List<PageRequest.Order> orders = pageRequest.getOrders();
        if (CollUtil.isNotEmpty(orders)) {
            for (PageRequest.Order order : orders) {
                String column = order.getColumn();
                queryWrapper.orderByAsc("123", "234");
                queryWrapper.orderByAsc(Article::getTitle);
            }

        }*/
        queryWrapper.orderByDesc(Article::getOrderNum);
        queryWrapper.orderByDesc(Article::getCreateTime);
        Page<Article> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        return page(page, queryWrapper);
    }

    @Override
    public Page<Article> listByLabelId(Integer labelId, PageRequest pageRequest) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getLabelId, labelId);
        queryWrapper.eq(Article::getStatus, ApiConstants.ZERO);
        queryWrapper.eq(Article::getDraft, ApiConstants.ZERO);
        String key = pageRequest.getKey();
        queryWrapper.like(StrUtil.isNotBlank(key), Article::getTitle, key);
        queryWrapper.orderByDesc(Article::getOrderNum);
        queryWrapper.orderByDesc(Article::getCreateTime);
        Page<Article> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        return page(page, queryWrapper);
    }
}
