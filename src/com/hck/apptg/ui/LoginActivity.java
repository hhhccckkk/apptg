package com.hck.apptg.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

import com.hck.apptg.R;
import com.hck.apptg.bean.User;
import com.hck.apptg.data.Constant;
import com.hck.apptg.data.MyData;
import com.hck.apptg.data.UserCacheData;
import com.hck.apptg.downapp.UpdateUtil;
import com.hck.apptg.downapp.UpdateUtil.UpdateAppCallBack;
import com.hck.apptg.presenter.LoginPresenter;
import com.hck.apptg.util.AppManager;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.MyToast;
import com.hck.apptg.util.MyTools;

public class LoginActivity extends Activity implements UpdateAppCallBack {
	private static final int LOGIN_ERROR = 0;
	private static final int LOGIN_SUCCESS = 1;
	private static final int LOGIN_CANCEL = 2;
	private Button loginBtn; // 登录按钮
	private View pBar; // 圈圈
	private LoginPresenter mPresenter;
	private User mUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		ShareSDK.initSDK(this);
		mPresenter = new LoginPresenter(this);
		mUser = UserCacheData.getUser();
		setContentView(R.layout.activity_login);
		initView();
		setListener();
		new UpdateUtil().isUpdate(this); // 监测是否有新版本
		userLogin();
	}

	private void initView() {
		loginBtn = (Button) findViewById(R.id.loginBtn);
		pBar = findViewById(R.id.pb);
	}

	/**
	 * 去登录或者注册用户
	 */
	private void userLogin() {
		Platform qq = ShareSDK.getPlatform(QQ.NAME);
		if (qq != null && qq.isAuthValid() && mUser != null) {
			if (MyData.bdLocation != null) {
				mUser.setJingdu(MyData.bdLocation.getLongitude());
				mUser.setWeidu(MyData.bdLocation.getLatitude());
			}
			mPresenter.login(mUser, false);
		} else {
			loginBtn.setVisibility(View.VISIBLE);
		}
	}

	private void setListener() {
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loginBtn.setFocusable(false);
				loginBtn.setVisibility(View.GONE);
				mPresenter.loginQQ(handler);
			}
		});
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (isFinishing()) {
				return;
			}
			loginBtn.setFocusable(true);
			if (msg == null || msg.what == LOGIN_ERROR
					|| msg.what == LOGIN_CANCEL) {
				MyToast.showCustomerToast("登录失败 您可以重新登录");
				loginBtn.setVisibility(View.VISIBLE);
			} else if (msg.what == LOGIN_SUCCESS) {
				loginBtn.setVisibility(View.GONE);
				pBar.setVisibility(View.GONE);
				loginBtn.setVisibility(View.VISIBLE);
				PlatformDb platformDb = (PlatformDb) msg.obj;
				registerUser(platformDb);

			}

		};
	};

	/**
	 * qq登录成功后，注册用户
	 * 
	 * @param platformDb
	 */
	private void registerUser(PlatformDb platformDb) {
		if (platformDb != null) {
			mUser = new User();
			mUser.setQqid(platformDb.getUserId());
			String xingbie = platformDb.getUserGender();
			if (Constant.MAN.equals(xingbie)) {
				mUser.setSex(Constant.NAN);
			} else {
				mUser.setSex(Constant.NV);
			}
			if (MyData.bdLocation != null) {
				mUser.setJingdu(MyData.bdLocation.getLongitude());
				mUser.setWeidu(MyData.bdLocation.getLatitude());
			}
			String touxiang=platformDb.getUserIcon();
			mUser.setTouxiang(touxiang);
			mUser.setImei(MyTools.getImei(this));
			mUser.setPhonetype(MyTools.getModel());
			mUser.setNicheng(platformDb.getUserName());
			mPresenter.login(mUser, true);
		} else {
			LogUtil.D(" registerUser PlatformDb is null");
		}

	}

	/**
	 * 登录成功
	 * 
	 * @param user
	 */
	public void loginSuccess(User user) {
		if (user == null) {
			loginError();
		} else if (TextUtils.isEmpty(user.getPhone())) { // 说明用户还未完善信息
			Intent intent = new Intent();
			intent.putExtra("user", user);
			intent.setClass(this, PrefectUserInfoActivity.class);
			AppManager.getAppManager().startActivity(this, intent);
			finish();
		} else {
			AppManager.getAppManager().startActivity(this, MainActivity.class);
			finish();
		}
	}

	public void loginError() {
		MyToast.showCustomerToast("网络异常 登录失败");
		loginBtn.setVisibility(View.VISIBLE);
	}
}
