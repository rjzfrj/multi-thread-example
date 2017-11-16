package org.my.thread.sync001;

/**
 * 第一个小例子 线程安全的学习
 * 5个线程执行MyThread类中的run方法执行对num减减操作 
 * 加锁和不枷锁的表现从而说明线程安全
 *
 */
public class MyThread extends Thread
{
	private int num=5;
	
	
    @Override
	public  void run() {
    	num--;
    	System.out.println(this.currentThread().getName()+"---"+num);
	}

	/**
	 * 分析：本实例当5个线程去start执行的时候如果myThread类的run不加锁synchronized ，这样打印结果不可预知，这说明线程不安全
	 * 因为线程是按cup分配的先后顺序而定的，有可能同一时刻两个线程操作num类成员变量这样也就是否容易出现两个多个线程打印的结果是相同的
	 * 如果在run方法加上锁 synchronized 这样5个线程会按cup的分配先后顺序情况
	 *  1去争夺run方法的获得锁
	 *  2那个线程争夺到了锁就去执行run方法中的
	 * 内容，这样执行完了释放后其他排队的线程再去争夺，争夺到的再去自己执行，这样就保证了执行run的现在只有一个，这样当然结果是依次递减的了
	 * 
	 * 总结：线程执行是以排队的机制，具体的是CPU去分配锁给某一个线程锁的权限，所以多个线程未获得锁的会在某线程释放锁后去同时竞争锁权限。
	 */
	public static void main( String[] args )
    {
		MyThread myThread=new MyThread();
		Thread t1=new Thread(myThread,"t1");
		Thread t2=new Thread(myThread,"t2");
		Thread t3=new Thread(myThread,"t3");
		Thread t4=new Thread(myThread,"t4");
		Thread t5=new Thread(myThread,"t5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
       
    }
}
