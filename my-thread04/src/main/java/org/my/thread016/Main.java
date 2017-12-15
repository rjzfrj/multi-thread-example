package org.my.thread016;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	
	public static void main(String[] args) {
		BlockingQueue<Data> queue=new LinkedBlockingQueue<Data>();
		Provider p1=new Provider(queue);
		Provider p2=new Provider(queue);
		Provider p3=new Provider(queue);
		//消费者
		Consume c1=new Consume(queue);
		Consume c2=new Consume(queue);
		Consume c3=new Consume(queue);
		//创建线程池
		ExecutorService cachePool=Executors.newCachedThreadPool();
		cachePool.execute(p1);
		cachePool.execute(p2);
		cachePool.execute(p3);
		cachePool.execute(c1);
		cachePool.execute(c2);
		cachePool.execute(c3);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p1.stop();
		p2.stop();
		p3.stop();
	}

}
