package com.hck.apptg.data;

import android.text.TextUtils;
import android.util.Log;

import com.hck.apptg.bean.User;
import com.hck.apptg.util.JsonUtils;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.MyPreferences;

public class UserCacheData {
	private static User mUser;

	public static User getUser() {
		if (mUser != null) {
			return mUser;
		}
		String userData = MyPreferences.getString(Constant.KEY_USER_DATA, null);
		if (TextUtils.isEmpty(userData)) {
			return null;
		}
		try {
			mUser = JsonUtils.parse(userData, User.class);
			return mUser;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.D("UserCacheData getUser Exception: "
					+ Log.getStackTraceString(e));
			return null;
		}
	}

	public static void setUser(User user, String data) {
		mUser = user;
		MyPreferences.saveString(Constant.KEY_USER_DATA, data);
	}

}
