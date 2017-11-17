package org.my.thread.sync006;

import org.my.thread.sync005.SyncException;

/**
 * 说明锁的几种用法或者说是语法
 *
 */
public class ObjectLock {
	
	
	public  void method1() {
		synchronized(this) {  //this是指本类对象锁 是一个对象锁
			try {
				System.out.println("method1:"+Thread.currentThread().getName());
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public  void method2() {
		synchronized(ObjectLock.class) {  //类锁
			try {
				System.out.println("method2:"+Thread.currentThread().getName());
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private  Object lock=new Object();
	public  void method3() {
		synchronized(lock) {  //任意对象锁
			try {
				System.out.println("method3:"+Thread.currentThread().getName());
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final ObjectLock objectLock = new ObjectLock();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				objectLock.method1();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				objectLock.method2();
			}
		},"t2");
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				objectLock.method3();
			}
		},"t3");
		t1.start();
		t2.start();
		t3.start();
	}

}
