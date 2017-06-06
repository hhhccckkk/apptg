package com.hck.apptg.liaotian;

import android.content.Context;

import com.easemob.chat.EMMessage;
import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.controller.EaseUI.EaseUserProfileProvider;
import com.easemob.easeui.domain.EaseUser;
import com.hck.apptg.bean.User;
import com.hck.apptg.db.UserBeanDB;
import com.hck.apptg.model.UserModel;
import com.hck.apptg.util.LogUtil;

public class MsgHelper {
	private UserModel mUserModel;

	public MsgHelper(Context context) {
		mUserModel = new UserModel(context);
	}

	/**
	 * 聊天时候，获取用户的昵称和头像
	 */
	public void setUserProfileProvider() {
		EaseUI.getInstance().setUserProfileProvider(
				new EaseUserProfileProvider() {

					@Override
					public EaseUser getUser(String username) {
						UserBeanDB userBeanDB = mUserModel
								.getUserByName(username);
						if (userBeanDB != null) {
							EaseUser easeUser = new EaseUser(username);
							easeUser.setNick(userBeanDB.getNicheng());
							easeUser.setAvatar(userBeanDB.getTouxiang());
							easeUser.setEid(userBeanDB.getUid() + "");
							easeUser.setUsername(username);
							return easeUser;
						} else {
							return null;
						}

					}
				});
	}

	
	public void saveUser(EMMessage message){
		try {
			String nicheng = message.getStringAttribute("nicheng");
			String touxiang = message.getStringAttribute("touxiang");
			long uid = message.getLongAttribute("uid");
			String name = message.getFrom();
			User user = new User();
			user.setName(name);
			user.setNicheng(nicheng);
			user.setId(uid);
			user.setTouxiang(touxiang);
			mUserModel.saveUserToDb(user);
		} catch (Exception e) {
			
		}
		
	}

}
