package com.zhang.template.controller;


import com.zhang.template.entity.Article;
import com.zhang.template.service.ArticleService;
import com.zhang.template.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author zhang
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 添加article
     * @param article
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return Result.ok();
    }

    /**
     * 更新article
     * @param article
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Article article) {
        articleService.update(article);
        return Result.ok();
    }

    /**
     * 删除article
     * @param articleId
     * @return
     */
    @PostMapping("/delete/{articleId}")
    public Result delete(@PathVariable Integer articleId) {
        articleService.delete(articleId);
        return Result.ok();
    }


}
