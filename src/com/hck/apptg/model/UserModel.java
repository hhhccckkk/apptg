package com.hck.apptg.model;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONObject;

import android.content.Context;

import com.hck.apptg.bean.User;
import com.hck.apptg.data.Constant;
import com.hck.apptg.data.UserCacheData;
import com.hck.apptg.db.UserBeanDB;
import com.hck.apptg.interfaces.RequestCallBack;
import com.hck.apptg.util.JsonUtils;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.RequestUtil;
import com.hck.httpserver.HCKHttpResponseHandler;

public class UserModel implements IUser {
	private String ADDUSER = "addUserP";
	private String PREFECTUSER = "prefectUserP";
	private String UPDATEUSERPUSHID = "updateUserPushidP";
	private Context mContext;

	public UserModel(Context context) {
		mContext = context;
	}

	@Override
	public void login(User user, Boolean isAlert,
			final RequestCallBack<User> callBack) {
		RequestUtil.requestPost(mContext, ADDUSER, false, isAlert,
				Params.getUserLoginParams(user), new HCKHttpResponseHandler() {
					public void onFailure(Throwable error, String content) {
						if (callBack != null) {
							callBack.onFailure(Constant.ERROR, "网络异常");
						}
					};

					public void onSuccess(String content, String requestUrl) {
						JSONObject object;
						try {
							object = new JSONObject(content);
							int code = object.getInt("code");
							String userData = object.getString("user");
							if (callBack != null) {
								User user = JsonUtils.parse(userData,
										User.class);
								UserCacheData.setUser(user, userData);
								callBack.onSuccess(code, user);
							}
						} catch (Exception e) {
							LogUtil.D("login Exception: " + e);
						}

					};

				});

	}

	@Override
	public void prefectUser(User user, Boolean isAlert,
			final RequestCallBack<User> callBack) {
		RequestUtil.requestPost(mContext, PREFECTUSER, true, isAlert,
				Params.getPrefectUser(user), new HCKHttpResponseHandler() {
					@Override
					public void onFailure(Throwable error, String content) {
						super.onFailure(error, content);
						if (callBack != null) {
							callBack.onFailure(Constant.ERROR, "");
						}
					}

					@Override
					public void onSuccess(String content, String requestUrl) {
						super.onSuccess(content, requestUrl);
						try {
							if (callBack != null) {
								JSONObject object = new JSONObject(content);
								User user;
								String userData = object.getString("user");
								try {
									user = JsonUtils
											.parse(userData, User.class);
									UserCacheData.setUser(user, userData);
									callBack.onSuccess(Constant.SUCCESS, user);
								} catch (Exception e) {
									e.printStackTrace();
									LogUtil.D("prefectUser Exception: " + e);
									callBack.onFailure(Constant.ERROR, "");
								}

							}
						} catch (Exception e) {
						}

					}
				});
	}

	@Override
	public void updateUserPushId(User user, boolean isAlert,
			RequestCallBack<User> callBack) {
		RequestUtil.requestPost(mContext, UPDATEUSERPUSHID, true, false,
				Params.getUserPushParams(user), new HCKHttpResponseHandler() {
					@Override
					public void onFailure(Throwable error, String content) {
						super.onFailure(error, content);
						LogUtil.D("onFailure: " + error + content);

					}

					@Override
					public void onSuccess(String content, String requestUrl) {
						super.onSuccess(content, requestUrl);
						LogUtil.D("onSuccess: " + content);
					}
				});

	}

	@Override
	public void saveUserToDb(User user) {
		UserBeanDB userBeanDB = new UserBeanDB();
		userBeanDB.setUid(user.getId());
		userBeanDB.setNicheng(user.getNicheng());
		userBeanDB.setUserName(user.getName());
		userBeanDB.setTouxiang(user.getTouxiang());
	}
}
