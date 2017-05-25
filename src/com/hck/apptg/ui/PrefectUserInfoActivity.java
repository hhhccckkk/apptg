package com.hck.apptg.ui;

import android.os.Bundle;

import com.hck.apptg.R;
import com.hck.apptg.bean.User;

public class PrefectUserInfoActivity extends BaseActivity {
	private User mUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mUser = (User) getIntent().getSerializableExtra("user");
		setContentView(R.layout.activity_prefect_user_info);
		initTitle("完善信息");

	}

	@Override
	public void clickBackBtn() {
		finish();
	}

}
