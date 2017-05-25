package com.hck.apptg.interfaces;

public interface RequestCallBack<T> {
	void onFailure(int code, String message);

	void onSuccess(int code,T data);
}
