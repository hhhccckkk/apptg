package com.hck.apptg.util;

import com.hck.apptg.data.Constant;
import com.hck.apptg.data.MyData;
import com.hck.httpserver.HCKHttpClient;
import com.hck.httpserver.HCKHttpResponseHandler;
import com.hck.httpserver.RequestParams;

public class HttpUtil {

	private static final int TIME_OUT = 15 * 1000;
	public static final int REQUEST_SUCCESS = 0;
	private static HCKHttpClient client = new HCKHttpClient();
	static {
		client.setTimeout(TIME_OUT);
	}

	private static void requestPost(String method, Boolean isNeedUserId,
			RequestParams params, HCKHttpResponseHandler handler) {
//		if (params == null) {
//			client.post(Constant.MAINHOST + method, handler);
//		} else {
//			UserBean userBean = MyData.getData().getUserBean();
//			if (isNeedUserId) {
//				params.put("uid", userBean.getUid() + "");
//			}
//			client.post(Constant.MAINHOST + method, params, handler);
//		}
	}

	private static void requestGet(String method, RequestParams params,
			HCKHttpResponseHandler handler) {
//		if (params == null) {
//			client.get(Constant.MAINHOST + method, handler);
//		} else {
//			client.get(Constant.MAINHOST + method, params, handler);
//		}

	}

	private static void requestGet(String method, RequestParams params,
			HCKHttpResponseHandler handler, boolean isNeedUserId) {
		// if (params == null) {
		// client.get(Constant.MAINHOST + method, handler);
		// } else {
		// UserBean userBean = MyData.getData().getUserBean();
		// if (isNeedUserId) {
		// params.put("uid", userBean.getUid() + "");
		// }
		// client.post(Constant.MAINHOST + method, params, handler);
		// }

	}

}
