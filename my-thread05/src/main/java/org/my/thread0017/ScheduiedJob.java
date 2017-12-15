package org.my.thread0017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduiedJob  {
	public static void main(String[] args) {
		Temp command=new Temp();
		ScheduledExecutorService scheduler=Executors.newScheduledThreadPool(1);
		//command是实现Thread的类的实例,1是延迟加载时间，3是每几秒执行一次，最后指定单位
		scheduler.scheduleWithFixedDelay(command, 1, 3, TimeUnit.SECONDS);
		
	}

}

class Temp extends Thread {
	@Override
	public void run() {
		System.out.println("run");
	}
}