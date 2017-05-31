package com.hck.apptg.data;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hck.apptg.bean.User;
import com.hck.apptg.bean.ZiYuanBean;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class ZiYuanData {
	@JsonProperty("ziyuanInfo")
	private List<ZiYuanBean> mZiYuanDatas;

	public List<ZiYuanBean> getmZiYuanDatas() {
		return mZiYuanDatas;
	}

	public void setmZiYuanDatas(List<ZiYuanBean> mZiYuanDatas) {
		this.mZiYuanDatas = mZiYuanDatas;
	}

	
	
	

}
