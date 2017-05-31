package com.hck.apptg.presenter;

import java.util.List;

import com.hck.apptg.data.ZiYuanData;
import com.hck.apptg.interfaces.RequestCallBack;
import com.hck.apptg.model.TieZiModel;
import com.hck.apptg.ui.ZiYuanActivity;
import com.hck.apptg.util.MyToast;

import android.content.Context;

public class ZiYuanPresenter {
	private Context mContext;
	private ZiYuanActivity mZiYuanActivity;
	private TieZiModel mTieZiModel;

	public ZiYuanPresenter(Context context) {
		mContext = context;
		mZiYuanActivity = (ZiYuanActivity) context;
		mTieZiModel = new TieZiModel(mContext);
	}

	public void getZiYuanInfo(int page) {
		mTieZiModel.getZiYuan(page, true, new RequestCallBack<ZiYuanData>() {

			@Override
			public void onFailure(int code, String message) {
				MyToast.showCustomerToast(null);
			}

			@Override
			public void onSuccess(int code, ZiYuanData data) {
				mZiYuanActivity.updateUi(data);
			}
		});
	}

}
