package org.my.thread016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consume implements Runnable {

	private BlockingQueue<Data> queue;
	
	public Consume(BlockingQueue<Data> queue){
		this.queue=queue;
	}
	private static Random r=new Random();
	@Override
	public void run() {
		while (true) {
			try {
				Data data=this.queue.take();
				//模拟数据消费耗时
				Thread.sleep(r.nextInt(1000));
				System.out.println("当前线程："+Thread.currentThread().getName()+"消费了一条数据："+data.toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
