package org.my.thread016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable{
	private volatile boolean isRunning=true;
	//共享缓存区
	private BlockingQueue<Data> queue;
	//id生成器
	private static AtomicInteger count=new AtomicInteger();
	//随机对象
	private static Random r=new Random();
	
	public Provider(BlockingQueue queue){
		this.queue=queue;
	}
	
	
	@Override
	public void run() {
		while (isRunning) {
			try {
				//随机休眠0-1000毫秒用来模拟获取数据所用的耗时
				Thread.sleep(r.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int id=count.incrementAndGet();
			Data data=new Data(Integer.toString(id),"数据"+id);
			System.out.println("当前线程："+Thread.currentThread().getName()+"，获取了数据，id为："+id+",进行装载公共缓存区域中...");
			try {
				if (!this.queue.offer(data,2,TimeUnit.SECONDS)) {
					System.out.println("提交缓存区失败。。。");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		this.isRunning=false;
	}

}
