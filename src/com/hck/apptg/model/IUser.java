package com.hck.apptg.model;

import com.hck.apptg.bean.User;
import com.hck.apptg.interfaces.RequestCallBack;

public interface IUser {
	void login(User user, Boolean isAlert, RequestCallBack<User> callBack);

	void prefectUser(User user, Boolean isAlert, RequestCallBack<User> callBack);
}
