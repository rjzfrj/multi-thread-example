package org.my.thread.sync007;

/**
 * 知识点：volatile 关键字修饰的变量多个线程见是可见的
 * 
 * 主线程29行new 出的线程t1线程中引用了isRuning变量
 * 现在主现在下面开始修改了isRuning 看看t1线程的循环能不能看到isRuning的值的改变
 * 结论如果不加volatile修饰的 主线程修改了 t1是知道不可见变量值改变的，因为每个线程有单独的一个变量引用空间
 *
 */
public class RunThread extends Thread {

	private volatile boolean isRuning = true;


	public boolean isRuning() {
		return isRuning;
	}

	public void setRuning(boolean isRuning) {
		this.isRuning = isRuning;
	}

	public void run() {
		System.out.println("当前线程 :" + Thread.currentThread().getName() + "开始");
		while (isRuning) {
			
		}
		System.out.println("当前线程:" + Thread.currentThread().getName() + "结束");
	}

	public static void main(String[] args) {
		final RunThread rt = new RunThread();
		rt.start();
		try {
			Thread.sleep(3000);
			rt.setRuning(false);
			System.out.println("isRuning的值被改了:false" );
			Thread.sleep(1000);
			System.out.println("isRuning的值:" +rt.isRuning);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
