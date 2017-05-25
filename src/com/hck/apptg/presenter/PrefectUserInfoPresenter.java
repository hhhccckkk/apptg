package com.hck.apptg.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.hck.apptg.bean.User;
import com.hck.apptg.interfaces.RequestCallBack;
import com.hck.apptg.model.UserModel;
import com.hck.apptg.ui.PrefectUserInfoActivity;
import com.hck.apptg.util.MyToast;

public class PrefectUserInfoPresenter {
	private Context mContext;
	private UserModel mUserModel;
	private PrefectUserInfoActivity mActivity;

	public PrefectUserInfoPresenter(Context context) {
		this.mContext = context;
		mUserModel = new UserModel(context);
		mActivity = (PrefectUserInfoActivity) context;
	}

	public boolean isCheckOk(User user) {
		String phoneString = user.getPhone();
		String qqString = user.getQq();
		String weixin = user.getWeixin();
		String address = user.getAddress();
		String jieshao = user.getJieshao();
		if (TextUtils.isEmpty(phoneString)) {
			MyToast.showCustomerToast("手机号码不能为空");
			return false;
		}
		if (phoneString.length() != 11) {
			MyToast.showCustomerToast("请输入正确的手机号码");
			return false;
		}
		if (TextUtils.isEmpty(address)) {
			MyToast.showCustomerToast("详细地址不能为空");
			return false;
		}
		if (address.length() < 5) {
			MyToast.showCustomerToast("请输入正确的地址信息");
			return false;
		}
		if (TextUtils.isEmpty(jieshao)) {
			MyToast.showCustomerToast("请输入正确的介绍信息");
			return false;
		}
		if (TextUtils.isEmpty(qqString) || TextUtils.isEmpty(weixin)) {
			MyToast.showCustomerToast("qq和微信至少需要填写一个");
			return false;
		}
		return true;

	}
    /**
     * 完善用户信息
     * @param user
     */
	public void prefectUser(User user) {
		mUserModel.prefectUser(user, true, new RequestCallBack<User>() {

			@Override
			public void onFailure(int code, String message) {
				MyToast.showCustomerToast("网络异常，请重试");
			}

			@Override
			public void onSuccess(int code, User data) {
				mActivity.prefectSuccess(data);
			}
		});
	}
}
