package com.hsd.devops.core.support;

import java.util.Date;


public class DateTime extends Date{
	private static final long serialVersionUID = -5395712593979185936L;
	
	
	public static DateTime parse(Date date) {
		return new DateTime(date);
	}
	
	
	public DateTime() {
		super();
	}
	
	
	public DateTime(Date date) {
		this(date.getTime());
	}
	
	
	public DateTime(long timeMillis) {
		super(timeMillis);
	}
	
	@Override
	public String toString() {
		return DateTimeKit.formatDateTime(this);
	}
	
	public String toString(String format) {
		return DateTimeKit.format(this, format);
	}
	
	
	public String toMsStr() {
		return DateTimeKit.format(this, DateTimeKit.NORM_DATETIME_MS_PATTERN);
	}
	
	
	public Date toDate() {
		return new Date(this.getTime());
	}
}
