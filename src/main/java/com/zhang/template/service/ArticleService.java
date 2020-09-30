package com.zhang.template.service;

import com.zhang.template.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
