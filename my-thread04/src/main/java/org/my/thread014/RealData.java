package org.my.thread014;

public class RealData implements Data{

	private String result;
	//构造函数模拟加载数据过程
	public RealData(String questStr) {
		System.out.println("根据"+questStr+"进行查询，这是一个耗时的操作。。。");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("操作完毕，获取结果");
		this.result="结果集数据";
	}

	@Override
	public String getRequest() {
		return result;
	}

}
