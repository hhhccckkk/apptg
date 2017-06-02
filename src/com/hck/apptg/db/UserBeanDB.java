package com.hck.apptg.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_msg_user_info")
public class UserBeanDB {
	@DatabaseField(generatedId = true)
	private long id;
	@DatabaseField(columnName = "userName")
	private String userName;
	@DatabaseField(columnName = "nicheng")
	private String nicheng;
	@DatabaseField(columnName = "touxiang")
	private String touxiang;
	@DatabaseField(columnName = "uid")
	private long uid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNicheng() {
		return nicheng;
	}
	public void setNicheng(String nicheng) {
		this.nicheng = nicheng;
	}
	public String getTouxiang() {
		return touxiang;
	}
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
	
	

}
