package com.springboot.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.springboot.base.model.BaseIdEntity;

@Entity
public class User extends BaseIdEntity {
	
	
	@Column(name="name")
	private String userName;
	
	@Column(name="user_type")
	private String userType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public User(String userName, String userType) {
		super();
		this.userName = userName;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userType=" + userType + "]";
	}
	
	public User() {
	}
	
	

}
