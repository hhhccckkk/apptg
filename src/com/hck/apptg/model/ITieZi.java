package com.hck.apptg.model;

import java.util.List;

import com.hck.apptg.bean.Ziyuan;
import com.hck.apptg.data.ZiYuanData;
import com.hck.apptg.interfaces.RequestCallBack;

public interface ITieZi {

	public void addZiYuan(Ziyuan ziyuan, Boolean isAlert,
			RequestCallBack<Ziyuan> callBack);

	public void getZiYuan(int page, Boolean isAlert,
			RequestCallBack<ZiYuanData> callBack);

}
