package org.my.thread0018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *自定义线程池使用：自定义线程池使用无界队列的实例
 *
 *
 */
public class UseThreadPollExecute2 implements Runnable {

	private static AtomicInteger count = new AtomicInteger(0);

	@Override
	public void run() {
		int temp = count.incrementAndGet();
		System.out.println("任务" + temp+"-- 执行线程名称"+Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	    //使用有界队列最大核心线程数起作用，当任务大于核心线程时候会创建线程，最多创建到最大线程数量
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
		//使用无界队列的时候最大线程数不起作用每次有核心线程数线程执行，多余的任务存放到缓存队列中
//		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		ExecutorService executor = new ThreadPoolExecutor(
				3, //核心线程数
				10, //最大线程数
				120L,//线程活跃时间
				TimeUnit.SECONDS, //活跃时间单位
				queue);//队列
		for (int i = 0; i < 20; i++) {
			executor.execute(new UseThreadPollExecute2());
		}
		try {
			Thread.sleep(1000);
			System.out.println("queue size：" + queue.size());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
