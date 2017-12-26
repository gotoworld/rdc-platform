package com.hsd.devops.common.persistence.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;


public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

    
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    
	private String title;
    
	private Integer type;
    
	private String content;
    
	private Date createtime;
    
	private Integer creater;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Notice{" +
			"id=" + id +
			", title=" + title +
			", type=" + type +
			", content=" + content +
			", createtime=" + createtime +
			", creater=" + creater +
			"}";
	}
}
