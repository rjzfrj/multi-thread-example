package org.my.thread.sync005;

/**
 * 重入锁的概念
 *
 */
public class SyncDubbo1 {
	
	public synchronized void method1() {
		method2();
		System.out.println("method1:"+Thread.currentThread().getName());
	}
	public synchronized void method2() {
		System.out.println("method2:"+Thread.currentThread().getName());
		method3();
	}
	public synchronized void method3() {
		System.out.println("method3:"+Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
	}

}
