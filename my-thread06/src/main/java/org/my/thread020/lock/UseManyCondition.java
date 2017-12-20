package org.my.thread020.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 锁多Condition的使用
 *
 */
public class UseManyCondition {
	//默认是非公平锁  ，如果传入ReentrantLock(true) 就是公平锁就是按随先调用先执行，非公平锁是按cpu的空闲时间分配的//默认是非公平锁  ，如果传入ReentrantLock(true) 就是公平锁就是按随先调用先执行随
	private Lock lock=new ReentrantLock(); 
	private Condition c1=lock.newCondition();
	private Condition c2=lock.newCondition();
	
	public void method1(){
			lock.lock();  //加锁
			System.out.println("线程"+Thread.currentThread().getName()+"进入method1。。。");
		try {
			Thread.sleep(1000);
			System.out.println("线程"+Thread.currentThread().getName()+"method1释放锁。。。");
			c1.await(); //释放锁同时线程等待
			System.out.println("线程"+Thread.currentThread().getName()+"method1继续执行");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void method2(){
		lock.lock();  //加锁
		System.out.println("线程"+Thread.currentThread().getName()+"进入method2。。。");
		try {
			Thread.sleep(1000);
			System.out.println("线程"+Thread.currentThread().getName()+"method2释放锁。。。");
			c1.await(); //释放锁同时线程等待
			System.out.println("线程"+Thread.currentThread().getName()+"method2继续执行");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void method3(){
		lock.lock(); // 加锁
		System.out.println("线程"+Thread.currentThread().getName()+"进入method3。。。");
		try {
			System.out.println("线程"+Thread.currentThread().getName()+"method3释放锁。。。");
			c2.await(); //通知唤醒等待的线程
			System.out.println("线程"+Thread.currentThread().getName()+"method3继续执行");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void method4(){
		lock.lock(); // 加锁
		System.out.println("线程"+Thread.currentThread().getName()+"method4");
		try {
			System.out.println("线程"+Thread.currentThread().getName()+"method4发出唤醒。。。");
			c1.signalAll(); //通知唤醒等待的线程
			System.out.println("线程"+Thread.currentThread().getName()+"method4继续执行");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public void method5(){
		lock.lock(); // 加锁
		System.out.println("线程"+Thread.currentThread().getName()+"method5");
		try {
			System.out.println("线程"+Thread.currentThread().getName()+"method5发出唤醒。。。");
			c2.signal(); //通知唤醒等待的线程
			System.out.println("线程"+Thread.currentThread().getName()+"method5继续执行");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final UseManyCondition useCondition=new UseManyCondition();
		
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
		Thread t3=new Thread(new Runnable() {
			@Override
			public void run() {
				useCondition.method3();
			}
		});
		Thread t4=new Thread(new Runnable() {
			@Override
			public void run() {
				useCondition.method4();
			}
		});
		Thread t5=new Thread(new Runnable() {
			@Override
			public void run() {
				useCondition.method5();
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
