package org.my.thread.sync002;

/**
 * 本实例说明 一个对象一把锁，两个不同的线程对象有两个不同的锁，多个现在执行锁之间不受影响。
 * 也就是说两个线程得到的分别得到的是m1对象的锁,m2的锁
 *
 */
public class MultiThread {
	private static int num=0;
	
	//如果是printNum方法加static修饰 同时加synchronized 相当于类级别的锁
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
	
	//如果是printNum2 方法加synchronized 相当于对象锁锁
	public  synchronized void printNum2(String tag) {
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
		
//		
//		Thread t1=new Thread(new Runnable() {
//			@Override
//			public void run() {
//				m1.printNum2("a");
//			}
//		});
//		Thread t2=new Thread(new Runnable() {
//			@Override
//			public void run() {
//				m2.printNum2("b");
//			}
//		});
		t1.start();
		t2.start();
	}
	/**
	 *  打印结果如下 ：
	 * 	tag b num set over200
	 *	tagb-- num:200
	 *	tag a num set over100
	 *	taga-- num:100
	 *  本实例知识点：
	 *  1当多个对象多个锁，当t1,t2两个线程执行两个对象的方法是相互不干扰，但是如果printNum方法
	 *  2如果使用了static修饰后就变成了类级别的锁这样t1,t2两个线程就的等到排队争夺锁了。
	 *     及知识点如果是静态方法如果加上了synchronized 修饰这样就是类级别的锁，这样无论多少个实例被多个线程调用都会去排队获取锁。
	 * 
	 * 
	 * 
	 * */

}
