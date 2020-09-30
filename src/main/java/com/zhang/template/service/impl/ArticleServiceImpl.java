package com.zhang.template.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.constants.ApiConstants;
import com.zhang.template.constants.ResultState;
import com.zhang.template.entity.Article;
import com.zhang.template.exception.BusinessException;
import com.zhang.template.mapper.ArticleMapper;
import com.zhang.template.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 *
 * @author zhang
 * @since 2020-09-29
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public void add(Article article) {
        if (article == null) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR);
        }
        String title = article.getTitle();
        if (StrUtil.isBlank(title)) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR, ApiConstants.Message.TITLE_NOT_EMPTY);
        }
        Article articleDb = getByTitle(title);
        if (articleDb != null) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR, ApiConstants.Message.TITLE_REPEAT);
        }
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
        if (article == null) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR);
        }
        Integer articleId = article.getId();
        String title = article.getTitle();
        Article articleDb = getByTitle(title);
        if (articleDb != null && (!articleDb.getId().equals(articleId))) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR,ApiConstants.Message.TITLE_REPEAT);
        }
        articleDb = baseMapper.selectById(articleId);
        BeanUtil.copyProperties(article, articleDb);
        baseMapper.updateById(articleDb);
    }

    @Override
    public void delete(Integer articleId) {
        if (articleId == null || articleId <= 0) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR);
        }
        baseMapper.deleteById(articleId);
    }
}
