package com.bism.roadplan.modules.dp.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bism.roadplan.modules.dp.entity.User;
import com.bism.roadplan.modules.dp.service.UserService;
import com.bism.roadplan.modules.dp.utils.JsonMapper;

@Controller
@RequestMapping(value = "/user")
public class DPController {

	/** 日志实例 */  
	protected org.slf4j.Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/find/{name:\\w+}", method = RequestMethod.GET)
	@ResponseBody
	public String findUser(@PathVariable("name") String name) {
		logger.info("获取人员信息=" + name); 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		List<Map<String, Object>> list = userService.findUser(map);
		String result = JsonMapper.getInstance().toJson(list);
		
		return result;
	}
	
	@RequestMapping(value = "/delete/{name:\\w+}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable("name") String name) {
		logger.info("获取人员信息=" + name); 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		int re = userService.deleteUser(map);
		map.clear();
		String msg = re > 0 ? "delete user " + name + " successed" : "delete user " + name + " unsuccess";
		map.put("status", msg);
		String result = JsonMapper.getInstance().toJson(map);
		
		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public String listUser(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		List<User> list = userService.listUser();
		String result = JsonMapper.getInstance().toJson(list);
		
		return result;
	}
	
	
}
