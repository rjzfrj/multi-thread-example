package org.my.thread.sync006;

import org.my.thread.sync005.SyncException;

/**
 * 字符串常量锁会使得线程一直被占用其他线程无法进入也就是t2一直无法进入获得锁，所以避免使用字符串常量锁
 *
 */
public class StringLock {
	
	
	public  void method1() {
		//new String("aa")
		synchronized("aa") {  //不要使用字符串常量锁容易造成死循环 可以使用字符串对象
			try {
				while(true) {
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
		final StringLock objectLock = new StringLock();

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
