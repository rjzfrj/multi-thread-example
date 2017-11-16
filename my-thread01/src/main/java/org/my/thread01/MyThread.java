package org.my.thread01;

/**
 * Hello world!
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
	 * 如果在run方法加上锁 synchronized 这样5个线程会按cup的分配先后顺序情况去争夺run方法的锁，那个线程争夺到了，去执行run方法中的
	 * 内容，这样执行完了释放后其他排队的线程再去争夺，争夺到的再去自己执行，这样就保证了执行run的现在只有一个，这样当然结果是依次递减的了
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
