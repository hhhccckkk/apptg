package com.hck.apptg.bean;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Ziyuan implements java.io.Serializable {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("uid")
	private Long uid;
	@JsonProperty("title")
	private String title;
	@JsonProperty("image")
	private String image;
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
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Ziyuan() {
	}

	/** full constructor */
	public Ziyuan(Long uid, String title, String image, String content,
			Integer isok, Integer jiage, String xitong, String jiesuantime,
			String fabukind, String fabutime, String apptype, String hedui,
			Integer istj, Long huifunum, String qq, String phone, String weixin) {
		this.uid = uid;
		this.title = title;
		this.image = image;
		this.content = content;
		this.isok = isok;
		this.jiage = jiage;
		this.xitong = xitong;
		this.jiesuantime = jiesuantime;
		this.fabukind = fabukind;
		this.fabutime = fabutime;
		this.apptype = apptype;
		this.hedui = hedui;
		this.istj = istj;
		this.huifunum = huifunum;
		this.qq = qq;
		this.phone = phone;
		this.weixin = weixin;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsok() {
		return this.isok;
	}

	public void setIsok(Integer isok) {
		this.isok = isok;
	}

	public Integer getJiage() {
		return this.jiage;
	}

	public void setJiage(Integer jiage) {
		this.jiage = jiage;
	}

	public String getXitong() {
		return this.xitong;
	}

	public void setXitong(String xitong) {
		this.xitong = xitong;
	}

	public String getJiesuantime() {
		return this.jiesuantime;
	}

	public void setJiesuantime(String jiesuantime) {
		this.jiesuantime = jiesuantime;
	}

	public String getFabukind() {
		return this.fabukind;
	}

	public void setFabukind(String fabukind) {
		this.fabukind = fabukind;
	}

	public String getFabutime() {
		return this.fabutime;
	}

	public void setFabutime(String fabutime) {
		this.fabutime = fabutime;
	}

	public String getApptype() {
		return this.apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getHedui() {
		return this.hedui;
	}

	public void setHedui(String hedui) {
		this.hedui = hedui;
	}

	public Integer getIstj() {
		return this.istj;
	}

	public void setIstj(Integer istj) {
		this.istj = istj;
	}

	public Long getHuifunum() {
		return this.huifunum;
	}

	public void setHuifunum(Long huifunum) {
		this.huifunum = huifunum;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeixin() {
		return this.weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

}