package com.smallbell.teambition.qa.controller;

import com.smallbell.teambition.common.entity.PageResult;
import com.smallbell.teambition.common.entity.Result;
import com.smallbell.teambition.common.entity.StatusCode;
import com.smallbell.teambition.qa.client.BaseClient;
import com.smallbell.teambition.qa.pojo.Problem;
import com.smallbell.teambition.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 提问
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;

	@Autowired
	private BaseClient baseClient;

	@GetMapping("/label/{labelId}")
	public Result findByLabelId(@PathVariable String labelId){
		Result result = baseClient.findById(labelId);
		return result;
	}

	@GetMapping("/newlist/{labelId}/{page}/{size}")
	public Result findNewList(@PathVariable String labelId, @PathVariable Integer page, @PathVariable Integer size){
		Page<Problem> pageData = problemService.findNewList(labelId, page, size);
		return new Result(true, StatusCode.OK,"查询成功", new PageResult<Problem>(pageData.getTotalElements(),pageData.getContent()));
	}

	@GetMapping("/hotlist/{labelId}/{page}/{size}")
	public Result findHotList(@PathVariable String labelId, @PathVariable Integer page, @PathVariable Integer size){
		Page<Problem> pageData = problemService.findHotList(labelId, page, size);
		return new Result(true,StatusCode.OK,"查询成功", new PageResult<Problem>(pageData.getTotalElements(),pageData.getContent()));
	}

	@GetMapping("/waitlist/{labelId}/{page}/{size}")
	public Result findWaitList(@PathVariable String labelId, @PathVariable Integer page, @PathVariable Integer size){
		Page<Problem> pageData = problemService.findWaitList(labelId, page, size);
		return new Result(true,StatusCode.OK,"查询成功", new PageResult<Problem>(pageData.getTotalElements(),pageData.getContent()));
	}
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
