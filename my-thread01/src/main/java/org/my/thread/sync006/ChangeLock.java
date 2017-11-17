package org.my.thread.sync006;

import org.my.thread.sync005.SyncException;

/**
 * volatile 关键字的作用是使变量在多个线程间可见
 *
 */
public class ChangeLock {
	
	private String lock="abc";
	public  void method1() {
		//new String("aa")
		
		synchronized(lock) {  //不要使用字符串常量锁容易造成死循环 可以使用字符串对象
			try {
				while(true) {
					lock="456";  //如果锁发生变化锁就控制不住其他线程了
					System.out.println("当前线程 :"+Thread.currentThread().getName()+"开始");
					Thread.sleep(1000);
					System.out.println("当前线程:"+Thread.currentThread().getName()+"结束");
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		final ChangeLock objectLock = new ChangeLock();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				objectLock.method1();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				objectLock.method1();
			}
		},"t2");
		
		t1.start();
		t2.start();
	}

}
