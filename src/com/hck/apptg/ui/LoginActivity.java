package com.hck.apptg.ui;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

import com.baidu.location.BDLocation;
import com.hck.apptg.R;
import com.hck.apptg.downapp.UpdateUtil;
import com.hck.apptg.downapp.UpdateUtil.UpdateAppCallBack;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.MyToast;

public class LoginActivity extends Activity implements UpdateAppCallBack {
	private static final int LOGIN_ERROR = 0;
	private static final int LOGIN_SUCCESS = 1;
	private static final int LOGIN_CANCEL = 2;
	private Button loginBtn; // 登录按钮
	private View pBar; // 圈圈
	private BDLocation bdLocation;
	private String userName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		ShareSDK.initSDK(this);
		setContentView(R.layout.activity_splash);
		initView();
		setListener();
		new UpdateUtil().isUpdate(this); // 监测是否有新版本
		userLogin();
	}

	private void initView() {
		loginBtn = (Button) findViewById(R.id.loginBtn);
		pBar = findViewById(R.id.pb);
	}

	private void userLogin() {
		Platform qq = ShareSDK.getPlatform(QQ.NAME);
		// UserBean userBean = MyData.getData().getUserBean();
		// if (qq != null && qq.isAuthValid() && userBean != null) { //
		// QQ登录过没有过去，直接登录
		// getUserData(userBean.getUid());
		// } else {
		loginBtn.setVisibility(View.VISIBLE);
		// }
	}

	private void setListener() {
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loginBtn.setFocusable(false);
				loginBtn.setVisibility(View.GONE);
				loginQQ();
			}
		});
	}

	private void loginQQ() {
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
				LogUtil.D("onError: "+arg1+": "+arg2);
			}

			@Override
			public void onComplete(Platform arg0, int arg1,
					HashMap<String, Object> arg2) {

				Message message = new Message();
				message.what = LOGIN_SUCCESS;

				PlatformDb platDB = arg0.getDb();
				handler.sendMessage(message);
				LogUtil.D("onCompleteonCompleteonComplete: "
						+ platDB.getUserName());

			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				Message message = new Message();
				message.what = LOGIN_CANCEL;
				handler.sendMessage(message);
				LogUtil.D("onCancel: "+arg1+": ");

			}
		});
		qq.showUser(null);

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
				pBar.setVisibility(View.VISIBLE);
				loginBtn.setVisibility(View.VISIBLE);
				MyToast.showCustomerToast("登录成功");

			}

		};
	};
}
