package com.xhs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.xhs.entity.User;

 
public interface UserMapper {

	public int save(User user);

	public int update(User user);

	public int delete(int id);

	public User findById(int id);

	public List<User> findAll();

	public User getUserByUsername(String username);

	/**
	 * 鍒嗛〉+妯＄硦鏌ヨ
	 * @param startRecord 浠庣鍑犳潯寮�
	 * @param pageSize 鏄剧ず澶氬皯鏉�
	 * @param userName 鏍规嵁鐢ㄦ埛鍚嶆煡璇�
	 * @param age 鏍规嵁骞撮緞鏌ヨ
	 * @return
	 */
	public List<User> getAllUserByPage(
			@Param(value = "startRecord") Integer startRecord,
			@Param(value = "pageSize") Integer pageSize,
			@Param(value = "username") String username,
			@Param(value = "password") String password);

	/**
	 * 鏍规嵁鏉′欢鏌ヨ鎬绘潯鏁�
	 * @param username
	 * @param age
	 * @return
	 */
	public int userCount(@Param(value = "username") String username, 
			@Param(value = "password") String password);

}
