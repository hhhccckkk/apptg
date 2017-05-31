package com.hck.apptg.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.hck.apptg.R;
import com.hck.apptg.bean.User;
import com.hck.apptg.bean.Ziyuan;
import com.hck.apptg.data.UserCacheData;
import com.hck.apptg.presenter.FaTiePresenter;
import com.hck.apptg.util.MyToast;

public class FaTieActivity extends BaseActivity {
	private int type = 1;
	private TextView mTitleView, mAppNameView, mAppJiageView, mAppContentView,
			mUserPhoneView, mUserQQView, mUserWeiXinView;
	private RadioGroup mXiTongGroup, mZhouQiGroup;
	private String xitong = "安卓";
	private String zhouqi = "日结";
	private Ziyuan mZiyuan;
	private User mUser;
	private FaTiePresenter mFaTiePresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actiivty_fati);
		initTitle("请输入资源详细信息");
		type = getIntent().getIntExtra("type", type);
		initView();
		initData();
		setListener();
	}

	private void initData() {
		mUser = UserCacheData.getUser();
		mZiyuan = new Ziyuan();
		mFaTiePresenter = new FaTiePresenter(this);
		if (!TextUtils.isEmpty(mUser.getPhone())) {
			mUserPhoneView.setText(mUser.getPhone());
		}
		if (!TextUtils.isEmpty(mUser.getQq())) {
			mUserQQView.setText(mUser.getQq());
		}
		if (!TextUtils.isEmpty(mUser.getWeixin())) {
			mUserQQView.setText(mUser.getWeixin());
		}
	}

	private void initView() {
		mTitleView = getViewById(R.id.tiezi_title);
		mAppNameView = getViewById(R.id.tiezi_appName);
		mAppJiageView = getViewById(R.id.tiezi_appjiage);
		mAppContentView = getViewById(R.id.tiezi_content);
		mUserPhoneView = getViewById(R.id.tiezi_phone);
		mUserQQView = getViewById(R.id.tiezi_qq);
		mUserWeiXinView = getViewById(R.id.tiezi_weixin);
		mXiTongGroup = getViewById(R.id.tiezi_radioGroupXT);
		mZhouQiGroup = getViewById(R.id.tiezi_zhouqi);

	}

	private void setListener() {
		mXiTongGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.tiezi_ios) {
					xitong = "IOS";
				} else if (checkedId == R.id.tiezi_pc) {
					xitong = "PC";
				} else if (checkedId == R.id.tiezi_az) {
					xitong = "安卓";
				}
			}
		});
		mZhouQiGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.tiezi_rijie) {
					xitong = "日结";
				} else if (checkedId == R.id.tiezi_zhoujie) {
					xitong = "周结";
				} else if (checkedId == R.id.tiezi_yuejie) {
					xitong = "月结";
				}
			}
		});

	}

	public void submit(View view) {
		getData();
		if (mFaTiePresenter.isCheckedData(mZiyuan)) {
			mFaTiePresenter.addZiYuanInfo(mZiyuan);
		}
	}

	private void getData() {
		String titleString = mTitleView.getText().toString();
		mZiyuan.setContent(mAppContentView.getText().toString());
		mZiyuan.setHuifunum(0l);
		mZiyuan.setImage(mUser.getTouxiang());
		mZiyuan.setJiage(Integer.parseInt(mAppJiageView.getText().toString()));
		mZiyuan.setJiesuantime(zhouqi);
		mZiyuan.setPhone(mUserPhoneView.getText().toString());
		mZiyuan.setQq(mUserQQView.getText().toString());
		mZiyuan.setWeixin(mUserWeiXinView.getText().toString());
		mZiyuan.setXitong(xitong);
		mZiyuan.setTitle(titleString);
		mZiyuan.setAppName(mAppNameView.getText().toString());
	}

	public void addZiYuanSuccess() {
		MyToast.showCustomerToast("发布成功");
		finish();
	}

	public void addZiYuanFail() {
		MyToast.showCustomerToast("发布失败 请重试");
	}
}
