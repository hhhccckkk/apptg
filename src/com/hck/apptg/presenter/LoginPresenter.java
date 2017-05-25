package com.hck.apptg.presenter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

import com.hck.apptg.bean.User;
import com.hck.apptg.interfaces.RequestCallBack;
import com.hck.apptg.model.UserModel;
import com.hck.apptg.ui.LoginActivity;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.MyToast;

public class LoginPresenter {
	private static final int LOGIN_ERROR = 0;
	private static final int LOGIN_SUCCESS = 1;
	private static final int LOGIN_CANCEL = 2;
	private Context mContext;
	private UserModel mUserModel;
	private LoginActivity mLoginActivity;

	public LoginPresenter(Context context) {
		this.mContext = context;
		mUserModel = new UserModel(context);
		mLoginActivity = (LoginActivity) context;
	}

	/**
	 * 用户登录，注册
	 * 
	 * @param user
	 */
	public void login(User user, boolean isAlert) {
		mUserModel.login(user, isAlert, new RequestCallBack<User>() {

			@Override
			public void onSuccess(int code, User data) {
				if (mLoginActivity != null) {
					mLoginActivity.loginSuccess(data);
				}
			}

			@Override
			public void onFailure(int code, String message) {
				mLoginActivity.loginError();
			}
		});
	}

	public void loginQQ(final Handler handler) {
		Platform qq = ShareSDK.getPlatform(QQ.NAME);
		if (qq == null) {
			MyToast.showCustomerToast("登录失败");
			return;
		}
		if (qq != null && !qq.isAuthValid()) {
			MyToast.showCustomerToast("正在启动qq...");
		}
		qq.SSOSetting(false);
		qq.setPlatformActionListener(new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {
				Message message = new Message();
				message.what = LOGIN_ERROR;
				handler.sendMessage(message);
				LogUtil.D("onError: " + arg1 + ": " + arg2);
			}

			@Override
			public void onComplete(Platform arg0, int arg1,
					HashMap<String, Object> arg2) {
				 Iterator ite =arg2.entrySet().iterator();
				    while (ite.hasNext()) {
				        Map.Entry entry = (Map.Entry)ite.next();
				        Object key = entry.getKey();
				        Object value = entry.getValue();
				        LogUtil.D("key: "+key+"： "+value);
				    }

				Message message = new Message();
				message.what = LOGIN_SUCCESS;

				PlatformDb platDB = arg0.getDb();
				message.obj = platDB;
				handler.sendMessage(message);
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				Message message = new Message();
				message.what = LOGIN_CANCEL;
				handler.sendMessage(message);
				LogUtil.D("onCancel: " + arg1 + ": ");

			}
		});
		qq.showUser(null);

	}

}
