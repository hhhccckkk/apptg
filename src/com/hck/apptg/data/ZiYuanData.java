package com.hck.apptg.data;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hck.apptg.bean.User;
import com.hck.apptg.bean.ZiYuanBean;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class ZiYuanData {
	@JsonProperty("ziyuanInfo")
	private ZiYuanBean mZiYuanData;

	public ZiYuanBean getmZiYuanData() {
		return mZiYuanData;
	}

	public void setmZiYuanData(ZiYuanBean mZiYuanData) {
		this.mZiYuanData = mZiYuanData;
	}
	
	

}
