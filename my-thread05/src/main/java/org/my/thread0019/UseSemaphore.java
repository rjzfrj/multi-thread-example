package org.my.thread0019;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量非常适合高并发访问，在系统上线之前
 *
 */
public class UseSemaphore {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		// 信号量
		final Semaphore semp = new Semaphore(5);

		// 模拟20个客户访问
		for (int i = 0; i < 20; i++) {
			final int num = i;
			Runnable run = new Runnable() {
				@Override
				public void run() {
					try {
						// 获取许可
						semp.acquire();
						System.out.println("Accessing" + num);
						// 模拟实际业务需要耗时
//						Thread.sleep((long) (Math.random() * 10000));
						Thread.sleep(1000*new Random().nextInt(10));
						semp.release();// 释放
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			exec.submit(run);
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
