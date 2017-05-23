package com.hck.apptg.downapp;

import android.content.Context;

public class UpdateUtil {
	private static final String ID = "1";
	private UpdateAppCallBack callBack;

	public interface UpdateAppCallBack {
		//void backAppInfo(BanBenBean bean);
	}

	public void isUpdate(Context context) {
		callBack = (UpdateAppCallBack) context;
		getInfo();
	}

	private void getInfo() {
		
	}

}
