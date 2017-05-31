package com.hck.apptg.bean;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hck.apptg.bean.User;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class ZiYuanBean {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("content")
	private String content;
	@JsonProperty("isok")
	private Integer isok;
	@JsonProperty("jiage")
	private Integer jiage;
	@JsonProperty("xitong")
	private String xitong;
	@JsonProperty("jiesuantime")
	private String jiesuantime;
	@JsonProperty("fabukind")
	private String fabukind;
	@JsonProperty("fabutime")
	private String fabutime;
	@JsonProperty("apptype")
	private String apptype;
	@JsonProperty("hedui")
	private String hedui;
	@JsonProperty("istj")
	private Integer istj;
	@JsonProperty("huifunum")
	private Long huifunum;
	@JsonProperty("qq")
	private String qq;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("weixin")
	private String weixin;
	@JsonProperty("appName")
	private String appName;

	/**
	 * 用户信息
	 */
	@JsonProperty("uid")
	private long uid;
	@JsonProperty("touxiang")
	private String touxiang;
	@JsonProperty("jifeng")
	private long jifeng;
	@JsonProperty("jinbi")
	private long jinbi;
	@JsonProperty("sex")
	private int sex;
	@JsonProperty("vip")
	private int vip;
	@JsonProperty("name")
	private String name;
	@JsonProperty("userPhone")
	private String userPhone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsok() {
		return isok;
	}

	public void setIsok(Integer isok) {
		this.isok = isok;
	}

	public Integer getJiage() {
		return jiage;
	}

	public void setJiage(Integer jiage) {
		this.jiage = jiage;
	}

	public String getXitong() {
		return xitong;
	}

	public void setXitong(String xitong) {
		this.xitong = xitong;
	}

	public String getJiesuantime() {
		return jiesuantime;
	}

	public void setJiesuantime(String jiesuantime) {
		this.jiesuantime = jiesuantime;
	}

	public String getFabukind() {
		return fabukind;
	}

	public void setFabukind(String fabukind) {
		this.fabukind = fabukind;
	}

	public String getFabutime() {
		return fabutime;
	}

	public void setFabutime(String fabutime) {
		this.fabutime = fabutime;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getHedui() {
		return hedui;
	}

	public void setHedui(String hedui) {
		this.hedui = hedui;
	}

	public Integer getIstj() {
		return istj;
	}

	public void setIstj(Integer istj) {
		this.istj = istj;
	}

	public Long getHuifunum() {
		return huifunum;
	}

	public void setHuifunum(Long huifunum) {
		this.huifunum = huifunum;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getTouxiang() {
		return touxiang;
	}

	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}

	public long getJifeng() {
		return jifeng;
	}

	public void setJifeng(long jifeng) {
		this.jifeng = jifeng;
	}

	public long getJinbi() {
		return jinbi;
	}

	public void setJinbi(long jinbi) {
		this.jinbi = jinbi;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}
