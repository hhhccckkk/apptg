package com.hck.apptg.model;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.hck.apptg.bean.Ziyuan;
import com.hck.apptg.data.Constant;
import com.hck.apptg.data.ZiYuanData;
import com.hck.apptg.interfaces.RequestCallBack;
import com.hck.apptg.util.JsonUtils;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.RequestUtil;
import com.hck.httpserver.JsonHttpResponseHandler;

public class TieZiModel implements ITieZi {
	private Context mContext;
	private static final String METHOD_ADDZIYUAN = "addZiYuanP";
	private static final String METHOD_GETZIYUAN = "getZiYuanP";

	public TieZiModel(Context context) {
		mContext = context;
	}

	@Override
	public void addZiYuan(final Ziyuan ziyuan, Boolean isAlert,
			final RequestCallBack<Ziyuan> callBack) {
		RequestUtil.requestPost(mContext, METHOD_ADDZIYUAN, true, isAlert,
				Params.getZiYuanInfo(ziyuan), new JsonHttpResponseHandler() {
					@Override
					public void onFailure(Throwable error, String content) {
						super.onFailure(error, content);
						callBack.onFailure(Constant.ERROR, error + content);
					}

					@Override
					public void onSuccess(int statusCode, JSONObject response) {
						super.onSuccess(statusCode, response);
						LogUtil.D("onSuccessonSuccessonSuccess");
						try {
							if (response.getInt("code") == Constant.SUCCESS) {
								callBack.onSuccess(Constant.SUCCESS, ziyuan);
							}
						} catch (Exception e) {
							LogUtil.D("addZiYuan Exception: "
									+ Log.getStackTraceString(e));
						}

					}

					@Override
					public void onFinish(String url) {
						super.onFinish(url);
					}
				});
	}

	@Override
	public void getZiYuan(int page, Boolean isAlert,
			final RequestCallBack<ZiYuanData> callBack) {
		RequestUtil.requestGet(mContext, METHOD_GETZIYUAN,
				Params.getZiYuanPage(page), new JsonHttpResponseHandler() {
					public void onFailure(Throwable error, String content) {
						callBack.onFailure(Constant.ERROR, error + content);
					};

					public void onSuccess(int statusCode, JSONObject response) {
						try {
							if (response.getInt("code") == Constant.SUCCESS) {
								callBack.onSuccess(Constant.SUCCESS, JsonUtils
										.parse(response.toString(),
												ZiYuanData.class));
							}
						} catch (Exception e) {
							LogUtil.D(" getZiYuan Exception: "
									+ Log.getStackTraceString(e));
						}
					};
				}, false, isAlert);

	}
}
