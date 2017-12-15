package org.my.thread014;

public class FutureData implements Data {
	
	private RealData realData;
	private boolean isReady=false;
	
	public synchronized void setRealData(RealData realData) {
		//如果已经装载完毕了就直接返回
		if (isReady) {
			return;
		}
		//真实的装载
		this.realData=realData;
		this.isReady=true;
		notify(); //进行通知
	}
	@Override
	public synchronized String getRequest(){
		//如果数据没装载好就等待阻塞着
		while (!isReady) {
			try {
				System.out.println("阻塞耗时中。。。");
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//装载好直接获取数据即可
		return this.realData.getRequest();
	}

	

}
