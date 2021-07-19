package com.smallbell.teambition.base.controller;

import com.smallbell.teambition.base.pojo.Label;
import com.smallbell.teambition.common.entity.PageResult;
import com.smallbell.teambition.common.entity.Result;
import com.smallbell.teambition.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.smallbell.teambition.base.service.LabelService;

import java.util.List;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功", labelService.findAll());
    }

    @GetMapping("/{labelId}")
    public Result findById(@PathVariable String labelId){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @PutMapping("/{labelId}")
    public Result updateById(@PathVariable String labelId, @RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @DeleteMapping(("/{labelId}"))
    public Result deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功",list);

    }

    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable Integer page, @PathVariable Integer size){
        Page<Label> pageData = labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
