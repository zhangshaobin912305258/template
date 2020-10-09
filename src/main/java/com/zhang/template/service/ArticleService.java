package com.zhang.template.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.template.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.template.vo.page.PageRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2020-09-29
 */
public interface ArticleService extends IService<Article> {

    void add(Article article);

    Article getByTitle(String title);

    void update(Article article);

    void delete(Integer articleId);

    Page<Article> listByNavId(Integer navId, PageRequest pageRequest);

    Page<Article> listByLabelId(Integer labelId, PageRequest pageRequest);
}
