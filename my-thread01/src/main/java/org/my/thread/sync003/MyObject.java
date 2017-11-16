package org.my.thread.sync003;

/**
 * 此实例说明如果同一个对象的两个方法(加同步锁)多个线程执行是需要排队的
 * 如果一个加锁一个不枷锁是相互不影响的
 *
 */
public class MyObject {
	
	
	public synchronized void  method1() {
		System.out.println("method1:"+Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void method2() {
		System.out.println("method2:"+Thread.currentThread().getName());
	}
	
	
	public static void main(String[] args) {
		
		final MyObject myObject=new MyObject();
		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				myObject.method1();
				
			}
		});
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				myObject.method2();
			}
		});
		
		t1.start();
		t2.start();
		//线程2method2只能等线程一执行完3秒后执行
	}

}
