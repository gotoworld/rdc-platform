package com.hsd.devops.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;


public class Dept extends Model<Dept> {

    private static final long serialVersionUID = 1L;

    
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    
	private Integer num;
    
	private Integer pid;
    
	private String pids;
    
	private String simplename;
    
	private String fullname;
    
	private String tips;
    
	private Integer version;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public String getSimplename() {
		return simplename;
	}

	public void setSimplename(String simplename) {
		this.simplename = simplename;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Dept{" +
			"id=" + id +
			", num=" + num +
			", pid=" + pid +
			", pids=" + pids +
			", simplename=" + simplename +
			", fullname=" + fullname +
			", tips=" + tips +
			", version=" + version +
			"}";
	}
}
