package com.hsd.devops.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;


public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    
	private String code;
    
	private String pcode;
    
	private String pcodes;
    
	private String name;
    
	private String icon;
    
	private String url;
    
	private Integer num;
    
	private Integer levels;
    
	private Integer ismenu;
    
	private String tips;
    
	private Integer status;
    
	private Integer isopen;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPcodes() {
		return pcodes;
	}

	public void setPcodes(String pcodes) {
		this.pcodes = pcodes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer getIsmenu() {
		return ismenu;
	}

	public void setIsmenu(Integer ismenu) {
		this.ismenu = ismenu;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Menu{" +
			"id=" + id +
			", code=" + code +
			", pcode=" + pcode +
			", pcodes=" + pcodes +
			", name=" + name +
			", icon=" + icon +
			", url=" + url +
			", num=" + num +
			", levels=" + levels +
			", ismenu=" + ismenu +
			", tips=" + tips +
			", status=" + status +
			", isopen=" + isopen +
			"}";
	}
}
