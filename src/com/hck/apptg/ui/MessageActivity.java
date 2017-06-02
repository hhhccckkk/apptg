package com.hck.apptg.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.hck.apptg.R;
import com.hck.apptg.liaotian.MyConversationListFragment;

public class MessageActivity extends BaseActivity implements OnClickListener {
	private static final int LIAOTIAN_MSG = 1;
	private static final int HUIFU_MSG = 2;
	private static final int XITONG_MSG = 3;
	private MyConversationListFragment liaoTianFragment;
	private HuiFuMsgFragment huiFuMsgFragment;
	private TongZhiMsgFragment xiTongMsgFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiaoxi);
		hiddenBackBtn();
		initTitle();
		initFragment();
		changeFragment(liaoTianFragment);
		setListener();
	}

	private void changeFragment(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.xiaoxi_content, fragment).commit();
	}

	private void initFragment() {
		liaoTianFragment = new MyConversationListFragment();
		// Bundle bundle = new Bundle();
		// User user = UserCacheData.getUser();
		// bundle.putString(EaseConstant.EXTRA_USER_ID,
		// user.getName() + user.getId());
		// liaoTianFragment.setArguments(bundle);
		huiFuMsgFragment = new HuiFuMsgFragment();
		xiTongMsgFragment = new TongZhiMsgFragment();
	}

	private void initTitle() {
		leftTextView.setText(R.string.xiaoxi_liaotian);
		centerTextView.setText(R.string.xiaoxi_huifu);
		rightTextView.setText(R.string.xiaoxi_xitong);
		leftTextView.setBackgroundResource(R.drawable.home_title_bt_shap);
		leftTextView.setTextColor(getResources().getColor(
				R.color.red_anniu_bt_color));
	}

	private void setListener() {
		leftTextView.setOnClickListener(this);
		centerTextView.setOnClickListener(this);
		rightTextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_title_left:
			changeBg(LIAOTIAN_MSG);
			break;
		case R.id.home_title_center:
			changeBg(HUIFU_MSG);
			break;
		case R.id.home_title_right:
			changeBg(XITONG_MSG);
			break;
		default:
			break;
		}
	}

	private void changeBg(int flag) {
		switch (flag) {
		case LIAOTIAN_MSG:
			leftTextView.setBackgroundResource(R.drawable.home_title_bt_shap);
			leftTextView.setTextColor(getResources().getColor(
					R.color.red_anniu_bt_color));
			centerTextView.setTextColor(getResources().getColor(R.color.whilt));
			rightTextView.setTextColor(getResources().getColor(R.color.whilt));
			centerTextView.setBackgroundResource(R.color.transparent);
			rightTextView.setBackgroundResource(R.color.transparent);
			changeFragment(liaoTianFragment);
			break;
		case HUIFU_MSG:
			leftTextView.setBackgroundResource(R.color.transparent);
			leftTextView.setTextColor(getResources().getColor(R.color.whilt));
			centerTextView.setTextColor(getResources().getColor(
					R.color.red_anniu_bt_color));
			rightTextView.setTextColor(getResources().getColor(R.color.whilt));
			centerTextView.setBackgroundResource(R.drawable.home_title_bt_shap);
			rightTextView.setBackgroundResource(R.color.transparent);
			changeFragment(huiFuMsgFragment);
			break;
		case XITONG_MSG:
			leftTextView.setBackgroundResource(R.color.transparent);
			leftTextView.setTextColor(getResources().getColor(R.color.whilt));
			centerTextView.setTextColor(getResources().getColor(R.color.whilt));
			rightTextView.setTextColor(getResources().getColor(
					R.color.red_anniu_bt_color));
			centerTextView.setBackgroundResource(R.color.transparent);
			rightTextView.setBackgroundResource(R.drawable.home_title_bt_shap);
			changeFragment(xiTongMsgFragment);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		alertExitD();

		return false;
	}
}
