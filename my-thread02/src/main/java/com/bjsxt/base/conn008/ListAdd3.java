package com.bjsxt.base.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
/**
 * 知识点：线程间同行 wait notify 的使用
 *      1 wait notify必须作用在同一个对象锁上
 *      2 必须配合synchronized 使用
 * 程序代码解说：
 * t2线程先启动，t2 线程获得对象锁(代码52行) 下面 lock.wait() 是释放锁，但是是t2是阻塞着 等待着
 * t1线程启动这时候由于lock锁的释放所以t1获得锁进行添加，等添加到39行瞒住5条记录时候 lock.notify();通知t2线程
 * 但是通知了但是lock锁还是t1线程占有着等t1执行完了t2才会唤醒并收到通知 。显然是不是实时的
 * 
 * 如何实现实时通知可以使用
 */
public class ListAdd3 {
	private volatile static List list = new ArrayList();	
	
	public void add(){
		list.add("bjsxt");
	}
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		final ListAdd3 list2 = new ListAdd3();
		//final Object lock = new Object();  //(第一种方式) 普通对象锁方式配合 nofiy(); synchronized 使用
		final CountDownLatch countDownLatch=new CountDownLatch(1);  //一次通知
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//synchronized (lock) { //(第一种方式)
						System.out.println("t1启动..");
						for(int i = 0; i <10; i++){
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if(list2.size() == 5){
								System.out.println("已经发出通知..");
//								lock.notify(); //第一种方式
								countDownLatch.countDown();
							}
//						}						
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
//				synchronized (lock) {  //(第一种方式)  t2线程先启动，t2 线程获得对象锁(代码52行) 下面 lock.wait() 是释放锁，但是是t2是阻塞着 等待着
					System.out.println("t2启动..");
					if(list2.size() != 5){
						try {
//							lock.wait(); //
							countDownLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
//				}
			}
		}, "t2");	
		t2.start();
		t1.start();
		
		
		
		
		
	}
	
}
