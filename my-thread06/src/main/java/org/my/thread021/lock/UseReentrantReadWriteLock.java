package org.my.thread021.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 知识点：  ReentrantReadWriteLock 其核心就是实现读写分离的锁，在高并发访问下，尤其是读多写少的情况下
 * 性能高于重入锁
 * 读写锁的用法 ：读和读的锁是可以并发的 (读读共享， 读写互斥，写写互斥)
 *
 */
public class UseReentrantReadWriteLock {
	//默认是非公平锁  ，如果传入ReentrantLock(true) 就是公平锁就是按随先调用先执行，非公平锁是按cpu的空闲时间分配的//默认是非公平锁  ，如果传入ReentrantLock(true) 就是公平锁就是按随先调用先执行随
	private ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock(); 
	private ReadLock readLock=rwlock.readLock();
	private WriteLock writeLock=rwlock.writeLock();
	
	public void read(){
		readLock.lock();  //加锁
			System.out.println("线程"+Thread.currentThread().getName()+"进入method1。。。");
		try {
			Thread.sleep(2000);
			System.out.println("线程"+Thread.currentThread().getName()+"method1执行完毕退出");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			readLock.unlock();
		}
	}
	public void write(){
		writeLock.lock();  //加锁
		System.out.println("线程"+Thread.currentThread().getName()+"进入method2。。。");
		try {
			Thread.sleep(2000);
			System.out.println("线程"+Thread.currentThread().getName()+"method2继续执行");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			writeLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final UseReentrantReadWriteLock uReadWriteLock=new UseReentrantReadWriteLock();
		
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				uReadWriteLock.read();
			}
		});
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				uReadWriteLock.read();
			}
		});
		Thread t3=new Thread(new Runnable() {
			@Override
			public void run() {
				uReadWriteLock.write();
			}
		});
		Thread t4=new Thread(new Runnable() {
			@Override
			public void run() {
				uReadWriteLock.write();
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
