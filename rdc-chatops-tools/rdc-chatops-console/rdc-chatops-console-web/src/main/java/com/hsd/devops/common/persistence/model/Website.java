package com.hsd.devops.common.persistence.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
 * Class Description
 * 
 * @version Oct 18, 20173:27:04 PM
 * @author Ford.CHEN
 */
public class Website extends Model<Website> implements Comparable<Website> {
    
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    private Long classifyId;
    
    private String name;
    
    private String url;
    
    private String description;
    
    private String Thumbnail;
    
    private Integer order;
    
    private Date createDate;
    
    private Long createId;
    
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Website site) {
        return this.order > site.getOrder() ? 1 : -1;
    }
    
    
    
    
    
    
    /**
     * @return the order
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return Thumbnail;
    }
    
    /**
     * @param thumbnail the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
    
    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * @return the createId
     */
    public Long getCreateId() {
        return createId;
    }
    
    /**
     * @param createId the createId to set
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * @return the classifyId
     */
    public Long getClassifyId() {
        return classifyId;
    }

    /**
     * @param classifyId the classifyId to set
     */
    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

}
