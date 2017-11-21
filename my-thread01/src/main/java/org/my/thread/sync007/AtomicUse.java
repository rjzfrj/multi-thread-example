package org.my.thread.sync007;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本实例知识点：atomic提供的相关类对象只能保证调用一次方法的原子性，不能保证多次调用的原子性addCount 方法的原子性
 */
public class AtomicUse{

	private static AtomicInteger num=new AtomicInteger(0);
	// 加上 synchronized 能够保证 addCount 的这次操作是原子性的每次每个线程都是加上10，如果不加不能保证
	public synchronized int  addCount() {
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			num.addAndGet(1);
			num.addAndGet(2);
			num.addAndGet(3);
			num.addAndGet(4);
			return num.get();
	}


	public static void main(String[] args) {
		final AtomicUse rt = new AtomicUse();
		List<Thread> ts=new ArrayList<Thread>();
		
		for (int i = 0; i < 100; i++) {
			ts.add(new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(rt.addCount());
				}
			}));
		}
		
		for (Thread thread : ts) {
			thread.start();
		}
	}

}
