package com.zhang.template.controller;


import com.zhang.template.entity.Label;
import com.zhang.template.service.LabelService;
import com.zhang.template.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author zhang
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/api/label")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    /**
     * 查询所有label
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        List<Label> labels = labelService.list();
        return Result.ok(labels);
    }

    /**
     * 添加label
     * @param label
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return Result.ok();
    }




}
