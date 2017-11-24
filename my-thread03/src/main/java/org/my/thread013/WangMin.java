package org.my.thread013;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class WangMin implements Delayed{
	
	private int id;
	private String name;
	private long endTime;
	private TimeUnit timeUnit=TimeUnit.SECONDS;

	WangMin(int id,String name,long endTime){
		this.id=id;
		this.name=name;
		this.endTime=endTime;
	}
	
	//相互比较排序
	@Override
	public int compareTo(Delayed o) {
		WangMin wm=(WangMin)o;
	    return this.endTime - wm.endTime > 0 ? 1 : (this.endTime - wm.endTime < 0 ? -1 : 0);
	}

	//是否到了截止时间
	@Override
	public long getDelay(TimeUnit unit) {
		return this.endTime-System.currentTimeMillis();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	

}
