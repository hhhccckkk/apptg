package com.hck.apptg.presenter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.d;
import cn.sharesdk.tencent.qq.QQ;

import com.easemob.EMCallBack;
import com.easemob.EMError;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.exceptions.EaseMobException;
import com.google.android.gms.internal.el;
import com.hck.apptg.bean.User;
import com.hck.apptg.data.Constant;
import com.hck.apptg.interfaces.RequestCallBack;
import com.hck.apptg.model.UserModel;
import com.hck.apptg.ui.LoginActivity;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.MyPreferences;
import com.hck.apptg.util.MyToast;

public class LoginPresenter {
	private static final int LOGIN_ERROR = 0;
	private static final int LOGIN_SUCCESS = 1;
	private static final int LOGIN_CANCEL = 2;
	private UserModel mUserModel;
	private LoginActivity mLoginActivity;
	private static final int NET_WORK_BAD = 1;
	private static final int USER_NAME_IS_EXIT = 2;
	private static final int UNKNOWN_ERROR = 3;
	private static final int REGIST_OK = 0; // 注册成功
	private static final int LOGIN_IM_ERROR = -100; // 注册到环信失败

	public LoginPresenter(Context context) {
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
				String saveUsernameString = MyPreferences.getString("userName",
						null);
				String newUserString = data.getName() + data.getId();
				if (TextUtils.isEmpty(saveUsernameString)) {
					regsterToMsgServer(data);
				} else if (!saveUsernameString.equals(newUserString)) {
					regsterToMsgServer(data);
				} else {
					loginToMsgServer(data);
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
				Iterator ite = arg2.entrySet().iterator();
				while (ite.hasNext()) {
					Map.Entry entry = (Map.Entry) ite.next();
					Object key = entry.getKey();
					Object value = entry.getValue();
					LogUtil.D("key: " + key + "： " + value);
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

			}
		});
		qq.showUser(null);

	}

	// 加载本地聊天信息
	public void loadMsg() {
		new Thread(new Runnable() {
			public void run() {
				if (EMChat.getInstance().isLoggedIn()) {
					EMGroupManager.getInstance().loadAllGroups();
					EMChatManager.getInstance().loadAllConversations();
				}
			}
		}).start();

	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case USER_NAME_IS_EXIT: // 存在说明已注册，表示成功
			case REGIST_OK:
				loginToMsgServer((User) msg.obj);
				break;
			case UNKNOWN_ERROR:
			case NET_WORK_BAD:
			case LOGIN_IM_ERROR:
				MyToast.showCustomerToast("初始化聊天模块失败");
			default:
				break;
			}
		};
	};

	/**
	 * 注册账号到环信
	 * 
	 * @param user
	 */
	private void regsterToMsgServer(final User user) {
		new Thread(new Runnable() {
			public void run() {
				try {
					// 调用sdk注册方法
					String username = user.getName();
					EMChatManager.getInstance().createAccountOnServer(
							username + user.getId(), Constant.PASSWORD);
					MyPreferences.saveString("userName",
							user.getName() + user.getId());
					Message message = new Message();
					message.what = REGIST_OK;
					message.obj = user;
					handler.sendMessage(message);
				} catch (final EaseMobException e) {
					int errorCode = e.getErrorCode();
					LogUtil.D("errorCodeerrorCode: " + errorCode);
					if (errorCode == EMError.NONETWORK_ERROR) {
						LogUtil.D("网络异常");
						Message message = new Message();
						message.what = NET_WORK_BAD;
						message.obj = user;
						handler.sendMessage(message);
					} else if (errorCode == EMError.USER_ALREADY_EXISTS) {
						LogUtil.D("用户已存在");
						Message message = new Message();
						message.what = USER_NAME_IS_EXIT;
						message.obj = user;
						handler.sendMessage(message);
					} else if (errorCode == EMError.UNAUTHORIZED) {

						LogUtil.D("无权限: " + EMError.UNAUTHORIZED);
						Message message = new Message();
						message.what = UNKNOWN_ERROR;
						message.obj = user;
						handler.sendMessage(message);

					} else {
						LogUtil.D("无权限222");
						Message message = new Message();
						message.what = UNKNOWN_ERROR;
						message.obj = user;
						handler.sendMessage(message);
					}
				}
			}
		}).start();

	}

	public void loginToMsgServer(final User user) {
		String userIMName = user.getName() + user.getId();
		EMChatManager.getInstance().login(userIMName, Constant.PASSWORD,
				new EMCallBack() {// 回调
					@Override
					public void onSuccess() {
						mLoginActivity.runOnUiThread(new Runnable() {
							public void run() {
								EMGroupManager.getInstance().loadAllGroups();
								EMChatManager.getInstance()
										.loadAllConversations();
								LogUtil.D("登录okokok");
							}
						});
					}

					@Override
					public void onProgress(int progress, String status) {

					}

					@Override
					public void onError(int code, String message) {
						LogUtil.D("messagemessage: " + message + code);
						handler.sendEmptyMessage(LOGIN_IM_ERROR);

					}
				});

	}

}
