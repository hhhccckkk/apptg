package com.hck.apptg.model;

import org.json.JSONObject;

import android.content.Context;

import com.hck.apptg.bean.User;
import com.hck.apptg.data.Constant;
import com.hck.apptg.data.UserCacheData;
import com.hck.apptg.interfaces.RequestCallBack;
import com.hck.apptg.util.JsonUtils;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.RequestUtil;
import com.hck.httpserver.HCKHttpResponseHandler;

public class UserModel implements IUser {
	private String ADDUSER = "addUserP";
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
							callBack.onFailure(Constant.LOGIN_ERROR, "网络异常");
						}
					};

					public void onSuccess(String content, String requestUrl) {
						JSONObject object;
						try {
							object = new JSONObject(content);
							int code = object.getInt("code");
							if (callBack != null) {
								User user = JsonUtils
										.parse(content, User.class);
								UserCacheData.setUser(user, content);
								callBack.onSuccess(code, user);
							}
						} catch (Exception e) {
							LogUtil.D("login Exception: " + e);
						}

					};

				});

	}
}
