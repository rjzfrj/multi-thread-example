package org.my.thread01;

public class MultiThread {
	private static int num=0;
	public static synchronized void printNum(String tag) {
		try {
			if (tag.equals("a")) {
				num=100;
				Thread.sleep(1000);
				System.out.println("tag a num set over"+num );
			} else if(tag.equals("b")){
				num=200;
				System.out.println("tag b num set over"+num );
			}
			System.out.println("tag"+tag+"-- num:"+num);
		} catch (Exception e) {
		}
	}
	
	public static void main(String[] args) {
		final MultiThread m1=new MultiThread();
		final MultiThread m2=new MultiThread();
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				m1.printNum("a");
			}
		});
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				m2.printNum("b");
			}
		});
		t1.start();
		t2.start();
	}
	/**
	 *  打印结果如下 ：
	 * 	tag b num set over200
	 *	tagb-- num:200
	 *	tag a num set over100
	 *	taga-- num:100
	 *  本实例知识点：当多个对象多个锁，当t1,t2两个线程执行两个对象的方法是相互不干扰，但是如果printNum方法使用了static修饰后
	 *  就变成了类级别的锁这样t1,t2两个线程就的等到排队争夺锁了。
	 *  及知识点如果是静态方法如果加上了synchronized 修饰这样就是类级别的锁，这样无论多少个实例被多个线程调用都会去排队获取锁。
	 * 
	 * 
	 * 
	 * */

}
