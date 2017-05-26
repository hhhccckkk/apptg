package com.hck.apptg.model;

import com.hck.apptg.bean.User;
import com.hck.httpserver.RequestParams;

public class Params {
	static RequestParams getUserLoginParams(User user) {
		RequestParams params = new RequestParams();
		params.put("imei", user.getImei() + "");
		params.put("phone", user.getPhone() + "");
		params.put("name", user.getNicheng() + "");
		params.put("address", user.getAddress() + "");
		params.put("jindu", user.getJingdu() + "");
		params.put("weidu", user.getWeidu() + "");
		params.put("touxiang", user.getTouxiang() + "");
		params.put("phoneName", user.getPhonetype() + "");
		params.put("gongsi", user.getGongsi() + "");
		params.put("jieshao", user.getJieshao() + "");
		params.put("qqId", user.getQqid() + "");
		params.put("qq", user.getQq() + "");
		params.put("weixin", user.getWeixin() + "");
		params.put("sex", user.getSex() + "");
		params.put("type", user.getUsertype() + "");
		return params;
	}


	static RequestParams prefectUser(User user) {
		RequestParams params = new RequestParams();
		params.put("address", user.getAddress());
		params.put("qq", user.getQq());
		params.put("weixin", user.getWeixin());
		params.put("gongsi", user.getGongsi());
		params.put("jieshao", user.getJieshao());
		params.put("phone", user.getPhone());
		return params;
	}
}
