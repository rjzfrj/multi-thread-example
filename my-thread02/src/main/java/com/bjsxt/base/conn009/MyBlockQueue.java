package com.bjsxt.base.conn009;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuzf
 * 模拟实现阻塞队列
 *
 */
public class MyBlockQueue {
	
	//定义队列最小值最大值
	private final int maxSize;
	private final int minSize = 0;
	//原子计数器
	private final AtomicInteger count=new AtomicInteger(0);
	//队列
	private final LinkedList<Object> list = new LinkedList<Object>();
	Object lock=new Object();
	public AtomicInteger getCount() {
		return count;
	}
	public void addCount() {
		count.addAndGet(1);
	}
	
	MyBlockQueue(int maxSize){
		this.maxSize=maxSize;
	}
	
	//put
	
	public void put(Object obj) {
		synchronized (lock) {
			if(count.get()>=this.maxSize) {
				try {
					System.out.println("线程"+Thread.currentThread().getName() +"put元素队列已满线程阻塞等待中...");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list.add(obj);
			count.addAndGet(1);
			lock.notify();
			System.out.println("线程"+Thread.currentThread().getName() +"put元素队列一个元素"+"当前队列元素个数："+count.get());
		}
	}
	
	//take
	public Object take() {
		synchronized (lock) {
			if (count.get()==this.minSize) {
				try {
					System.out.println("线程"+Thread.currentThread().getName() +"take队列一个元素"+"当前队列元素个数："+count.get()+"队列已经空队列阻塞等待中。。。");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count.addAndGet(-1);
			lock.notify();
			System.out.println("线程"+Thread.currentThread().getName() +"take队列一个元素"+"当前队列元素个数："+count.get()+"并发出通知");
			return list.removeFirst();
		}
	}
	
	public static void main(String[] args) {
		
		final MyBlockQueue queue=new MyBlockQueue(5);
		
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					queue.put("a"+i);
				}
				
			}
		},"t1");
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					Object obj=queue.take();
					System.out.println("线程"+Thread.currentThread().getName() +"从队列中弹出了一个元素:"+obj.toString());
				}
			
			}
		},"t2");
		
		t1.start();
		t2.start();
	}
	
	

}
