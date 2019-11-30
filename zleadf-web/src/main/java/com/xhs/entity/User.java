package com.xhs.entity;

import java.util.Date;

/**
 * 用户
 * @author liang
 *
 */
public class User {

	private int id;
	private String username;
	private String password;
	private int status;
	private Date createtime;
	private int roleId;


	public User(){
		super();
	}

	public User(int id, String username, String password, int status, Date createtimeDate, int roleId) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
		this.createtime = createtime;
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id +  ", username=" + username +  ", password=" + password +  ", status=" + status + ", createtime=" + createtime +  ", roleId=" + roleId + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


}
