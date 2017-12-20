package org.my.thread020.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 锁
 *
 */
public class UseCondition {
	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	
	public void method1(){
			lock.lock();  //加锁
			System.out.println("线程"+Thread.currentThread().getName()+"进入method1。。。");
		try {
			Thread.sleep(3000);
			System.out.println("线程"+Thread.currentThread().getName()+"method1释放锁。。。");
			condition.await(); //释放锁同时线程等待
			System.out.println("线程"+Thread.currentThread().getName()+"method1继续执行");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void method2(){
		lock.lock(); // 加锁
		System.out.println("线程"+Thread.currentThread().getName()+"进入method2。。。");
		try {
			Thread.sleep(3000);
			System.out.println("线程"+Thread.currentThread().getName()+"method2发出唤醒。。。");
			condition.signal(); //通知唤醒等待的线程
			System.out.println("线程"+Thread.currentThread().getName()+"method2继续执行");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final UseCondition useCondition=new UseCondition();
		
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				useCondition.method1();
			}
		});
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				useCondition.method2();
			}
		});
		
		t1.start();
		t2.start();
	}
}
