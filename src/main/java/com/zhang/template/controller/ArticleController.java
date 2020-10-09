package com.zhang.template.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.template.entity.Article;
import com.zhang.template.service.ArticleService;
import com.zhang.template.vo.Result;
import com.zhang.template.vo.page.PageRequest;
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
     * 修改article
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

    /**
     * 获取article
     * @param articleId
     * @return
     */
    @PostMapping("/getById/{articleId}")
    public Result getById(@PathVariable Integer articleId) {
        Article article = articleService.getById(articleId);
        return Result.ok(article);
    }

    /**
     * 分页查询
     * @param navId
     * @param pageRequest
     * @return
     */
    @PostMapping("/listByNavId/{navId}")
    public Result listByNavId(@PathVariable Integer navId, @RequestBody PageRequest pageRequest) {
        Page<Article> articlePage = articleService.listByNavId(navId, pageRequest);
        return Result.ok(articlePage);
    }

    /**
     * 分页查询
     * @param labelId
     * @param pageRequest
     * @return
     */
    @PostMapping("/listByLabelId/{labelId}")
    public Result listByLabelId(@PathVariable Integer labelId, @RequestBody PageRequest pageRequest) {
        Page<Article> articlePage = articleService.listByLabelId(labelId, pageRequest);
        return Result.ok(articlePage);
    }

}
