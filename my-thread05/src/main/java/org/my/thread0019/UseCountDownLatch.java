package org.my.thread0019;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch {

	public static void main(String[] args) {
		final CountDownLatch countDownLatch=new CountDownLatch(2);
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("线程"+Thread.currentThread().getName()+"开始准备等待其他线程初始化...");
					//模拟程序耗时1秒钟
					countDownLatch.await();
					System.out.println("线程"+Thread.currentThread().getName()+"其他线程初始完成继续执行");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"T1");
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("线程"+Thread.currentThread().getName()+"开始准备...");
					Thread.sleep(2000);
					//模拟程序耗时1秒钟
					countDownLatch.countDown();
					System.out.println("线程"+Thread.currentThread().getName()+"执行完成");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"T2");
		Thread t3=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("线程"+Thread.currentThread().getName()+"开始准备...");
					Thread.sleep(1000);
					//模拟程序耗时1秒钟
					countDownLatch.countDown();
					System.out.println("线程"+Thread.currentThread().getName()+"执行完成");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"T3");
		
		
		t1.start();
		t2.start();
		t3.start();
	}
}
