package com.hsd.devops.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;


public class Relation extends Model<Relation> {

    private static final long serialVersionUID = 1L;

    
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    
	private Integer menuid;
    
	private Integer roleid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Relation{" +
			"id=" + id +
			", menuid=" + menuid +
			", roleid=" + roleid +
			"}";
	}
}
