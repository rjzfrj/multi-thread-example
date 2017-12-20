package org.my.thread020.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁 reentrantLock
 *
 */
public class UseReentrantLock {

	
	private Lock lock=new ReentrantLock();
	
	public void method1(){
		
		try {
			lock.lock();
			System.out.println("线程"+Thread.currentThread().getName()+"进入method1");
			Thread.sleep(1000); //模拟耗时
			System.out.println("线程"+Thread.currentThread().getName()+"退出method1");
			Thread.sleep(1000); //模拟耗时
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	public void method2(){
		try {
			lock.lock();
			System.out.println("线程"+Thread.currentThread().getName()+"进入method2");
			Thread.sleep(2000); //模拟耗时
			System.out.println("线程"+Thread.currentThread().getName()+"退出method2");
			Thread.sleep(1000); //模拟耗时
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	
	public static void main(String[] args) {
		final UseReentrantLock useReentrantLock=new UseReentrantLock();
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				useReentrantLock.method1();
				useReentrantLock.method2();
			}
		},"t1");
		t1.start();
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				useReentrantLock.method2();
				useReentrantLock.method1();
			}
		},"t2");
		t2.start();
	}
}
