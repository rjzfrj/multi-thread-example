package org.my.thread.sync005;

/**
 * 重入锁的概念
 *
 */
public class SyncDubbo1{
	
	
	class Test implements Runnable{

		@Override
		public void run() {
			System.out.println("test");
		}
		
	}
	static class  Test2 implements Runnable{
		
		@Override
		public void run() {
			System.out.println("test");
		}
		
	}
	public synchronized void method1() {
		System.out.println("method1:"+Thread.currentThread().getName());
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		method2();
		
	}
	public synchronized void method2() {
		System.out.println("method2:"+Thread.currentThread().getName());
		method3();
	}
	public synchronized void method3() {
		System.out.println("method3:"+Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		final SyncDubbo1  syncDubbo1=new SyncDubbo1();
		
		
		 Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				syncDubbo1.method1();
			}
		});
		 
		 Test t3=new SyncDubbo1().new Test();
		 Test2 t4=new SyncDubbo1.Test2();
		 Thread t2=new Thread(t4);
		 t1.start();
	}

}
