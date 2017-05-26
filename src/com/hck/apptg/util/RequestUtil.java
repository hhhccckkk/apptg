package com.hck.apptg.util;

import android.content.Context;

import com.hck.apptg.bean.User;
import com.hck.apptg.data.Constant;
import com.hck.apptg.data.MyData;
import com.hck.apptg.data.UserCacheData;
import com.hck.apptg.view.Pdialog;
import com.hck.httpserver.HCKHttpClient;
import com.hck.httpserver.HCKHttpResponseHandler;
import com.hck.httpserver.RequestParams;

public class RequestUtil {

	private static final int TIME_OUT = 15 * 1000;
	public static final int REQUEST_SUCCESS = 0;
	private static HCKHttpClient client = new HCKHttpClient();
	static {
		client.setTimeout(TIME_OUT);
	}

	private static void post(String method, Boolean isNeedUserId,
			RequestParams params, HCKHttpResponseHandler handler) {
		if (params == null) {
			client.post(Constant.MAINHOST + method, handler);
		} else {
			User user = UserCacheData.getUser();
			if (isNeedUserId) {
				params.put("id", user.getId() + "");
			}
			client.post(Constant.MAINHOST + method, params, handler);
		}
	}

	public static void requestPost(final Context context, String method,
			Boolean isNeedUserId, final Boolean isAlert, RequestParams params,
			final HCKHttpResponseHandler handler) {
		post(method, isNeedUserId, params, new HCKHttpResponseHandler() {
			@Override
			public void onStart(String url) {
				handler.onStart(url);
				super.onStart(url);
				if (isAlert) {
					Pdialog.showDialog(context, "处理中...", false);
				}
			}

			@Override
			public void onFailure(Throwable error, String content) {
				super.onFailure(error, content);
				handler.onFailure(error, content);
				LogUtil.D("RequestUtil requestPost onFailure: " + error + ": "
						+ content);
			}

			@Override
			public void onSuccess(String content, String requestUrl) {
				super.onSuccess(content, requestUrl);
				handler.onSuccess(content, requestUrl);
				LogUtil.D("RequestUtil requestPost onSuccess: " + content);
			}

			@Override
			public void onFinish(String url) {
				super.onFinish(url);
				handler.onFinish(url);
				Pdialog.hiddenDialog();
				LogUtil.D("RequestUtil requestPost onFinish: " + url);
			}
		});
	}

	public static void get(String method, RequestParams params,
			HCKHttpResponseHandler handler, boolean isNeedUserId) {
		LogUtil.D("需要id吗? "+isNeedUserId);
		if (params == null) {
			client.get(Constant.MAINHOST + method, handler);
		} else {
			User user = UserCacheData.getUser();
			if (isNeedUserId) {
				params.put("id", user.getId() + "");
				LogUtil.D("userid : "+ user.getId());
			}
			client.post(Constant.MAINHOST + method, params, handler);
		}
	}

	public static void requestGet(final Context context, String method,
			RequestParams params, final HCKHttpResponseHandler handler,
			boolean isNeedUserId, final Boolean isAlert) {
		get(method, params, new HCKHttpResponseHandler() {
			@Override
			public void onStart(String url) {
				super.onStart(url);
				if (isAlert) {
					Pdialog.showDialog(context, "数据获取中", false);
				}
				handler.onStart(url);
			}

			public void onFailure(Throwable error, String content) {
				handler.onFailure(error, content);
				LogUtil.D("RequestUtil requestGet onFailure: " + error
						+ content);
			};

			public void onSuccess(String content, String requestUrl) {
				handler.onSuccess(content, requestUrl);
				LogUtil.D("RequestUtil requestGet onSuccess: " + content);
			};

			public void onFinish(String url) {
				handler.onFinish(url);
				Pdialog.hiddenDialog();
				LogUtil.D("RequestUtil requestGet onFinish: " + url);
			};
		}, isNeedUserId);
	}

}
