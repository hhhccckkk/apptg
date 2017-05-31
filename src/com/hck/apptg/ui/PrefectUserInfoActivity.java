package com.hck.apptg.ui;

import android.R.integer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.hck.apptg.R;
import com.hck.apptg.bean.User;
import com.hck.apptg.presenter.PrefectUserInfoPresenter;
import com.hck.apptg.util.AppManager;
import com.hck.apptg.util.LogUtil;

public class PrefectUserInfoActivity extends BaseActivity {
	private User mUser;
	private EditText mPhonEditText, mQQEditText, mWeiXinEditText,
			mGongSiEditText;
	private EditText mAddressEditText, mJieShaoEditText;
	private PrefectUserInfoPresenter mpPresenter;
	private RadioGroup mRadioGroup;
    private int type=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUser = (User) getIntent().getSerializableExtra("user");
		setContentView(R.layout.activity_prefect_user_info);
		initTitle("完善信息");
		initView();
		mpPresenter = new PrefectUserInfoPresenter(this);
		hiddenBackBtn();
		setListener();

	}

	private void initView() {
		mPhonEditText = (EditText) findViewById(R.id.phone);
		mQQEditText = (EditText) findViewById(R.id.qq);
		mWeiXinEditText = (EditText) findViewById(R.id.weixin);
		mGongSiEditText = (EditText) findViewById(R.id.gongsi);
		mAddressEditText = (EditText) findViewById(R.id.dizhi);
		mJieShaoEditText = (EditText) findViewById(R.id.jieshao);
		mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
	}

	private void setListener() {
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.qudaoRB) {
					type=1;
				}
                else if (checkedId==R.id.ziYuanRB) {
                	type=2;
				}
                else {
                	type=3;
				}
			}
		});
	}

	@Override
	public void clickBackBtn() {
		finish();
	}

	public void submit(View view) {
		String phoneString = getText(mPhonEditText);
		String qqString = getText(mQQEditText);
		String weixin = getText(mWeiXinEditText);
		String gongsi = getText(mGongSiEditText);
		String address = getText(mAddressEditText);
		String jieshao = getText(mJieShaoEditText);
		mUser.setPhone(phoneString);
		mUser.setAddress(address);
		mUser.setQq(qqString);
		mUser.setWeixin(weixin);
		mUser.setGongsi(gongsi);
		mUser.setJieshao(jieshao);
		mUser.setUsertype(type);
		if (mpPresenter.isCheckOk(mUser)) {
			mpPresenter.prefectUser(mUser);
		}
	}

	public void prefectSuccess(User user) {
		AppManager.getAppManager().startActivity(this, MainActivity.class);
		finish();
	}

}
