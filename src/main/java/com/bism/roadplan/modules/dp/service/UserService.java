package com.bism.roadplan.modules.dp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bism.roadplan.modules.dp.dao.UserMapper;
import com.bism.roadplan.modules.dp.entity.User;


@Service
public class UserService{

	@Autowired
	private UserMapper userMapper;
	
	public List<Map<String,Object>> findUser(Map<String, Object> map) {
		List<Map<String, Object>> list = userMapper.findUser(map);
		
		return list;
	}
	
	@Transactional(readOnly=true)
	public int deleteUser(Map<String, Object> map) {
		int re = userMapper.deleteUser(map);
		
		return re;
	}
	
	public List<User> listUser() {
		List<User> list = userMapper.selectAll();
		
		return list;
	}
	
}
