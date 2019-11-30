package com.xhs.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.xhs.entity.User;

 
public interface UserService {
	
	public boolean save(User user);
	
	public boolean update(User user);
	
	public boolean delete(int id);
	
	public User findById(int id);
	
	public List<User> findAll();
	
	public User checkLogin(String username,String password);
	
	public List<User> getAllUserByPage(Integer startRecord,Integer pageSize,String username,String password);
	
	public int userCount(String username,String password);
}
