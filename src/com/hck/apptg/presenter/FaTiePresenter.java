package com.hck.apptg.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.hck.apptg.bean.Ziyuan;
import com.hck.apptg.interfaces.RequestCallBack;
import com.hck.apptg.model.TieZiModel;
import com.hck.apptg.ui.FaTieActivity;
import com.hck.apptg.util.MyToast;

public class FaTiePresenter {
	private Context mContext;
	private FaTieActivity mFaTieActivity;
	private TieZiModel mTieZiModel;

	public FaTiePresenter(Context context) {
		mContext = context;
		mFaTieActivity = (FaTieActivity) context;
		mTieZiModel = new TieZiModel(mContext);
	}

	public boolean isCheckedData(Ziyuan ziyuan) {
		if (TextUtils.isEmpty(ziyuan.getTitle())) {
			MyToast.showCustomerToast("标题不能为空");
			return false;
		} else if (TextUtils.isEmpty(ziyuan.getContent())) {
			MyToast.showCustomerToast("详细内容不能为空");
			return false;
		} else if (ziyuan.getJiage() <= 0) {
			MyToast.showCustomerToast("推广价格不能为空");
			return false;
		} else if (TextUtils.isEmpty(ziyuan.getAppName())) {
			MyToast.showCustomerToast("应用名字不能为空");
			return false;
		}
		return true;
	}

	public void addZiYuanInfo(Ziyuan ziyuan) {
		mTieZiModel.addZiYuan(ziyuan, true, new RequestCallBack<Ziyuan>() {

			@Override
			public void onFailure(int code, String message) {
				if (mFaTieActivity != null) {
					mFaTieActivity.addZiYuanFail();
				}

			}

			@Override
			public void onSuccess(int code, Ziyuan data) {
				if (mFaTieActivity != null) {
					mFaTieActivity.addZiYuanSuccess();
				}

			}
		});
	}

}
