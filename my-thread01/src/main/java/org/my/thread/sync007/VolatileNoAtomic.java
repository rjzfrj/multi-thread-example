package org.my.thread.sync007;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本实例知识点：说明volatile关键字修饰的变量如果多线程访问操作不能保证原子性，
 * 如果要保证原子性要使用 AtomicInteger 原子类型的相关操作
 *
 * 模拟多个线程对变量++ 如果是int 类型使用volatile 修饰不能保证原子性操作， 不采用同步是不安全的添加
 * atomic包下的都是原子性操作类。
 */
public class VolatileNoAtomic extends Thread {

//	private static volatile int num;
	private static AtomicInteger num=new AtomicInteger(0);
	private static void addCount() {
		for (int i = 0; i < 1000; i++) {
//			num++;
			num.incrementAndGet();
		}
		System.out.println("当前线程:" + Thread.currentThread().getName() + "累加结束 num：" + num);
	}

	public void run() {
		addCount();
	}

	public static void main(String[] args) {
		final VolatileNoAtomic[] rt = new VolatileNoAtomic[10];
		for (int i = 0; i < 10; i++) {
			rt[i] = new VolatileNoAtomic();
		}
		for (int j = 0; j < 10; j++) {
			rt[j].start();
		}
	}

}
