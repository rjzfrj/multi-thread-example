package com.bjsxt.base.conn011;

/**
 * 知识点：静态内部类实现单利模式，这种模式是现在最安全的，而且简单
 *
 */
public class InnerSingleton {
	
	private static class Singletion {
		private static Singletion single = new Singletion();
	}
	
	public static Singletion getInstance(){
		return Singletion.single;
	}
	
	
	public static void main(String[] args) {
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(InnerSingleton.getInstance().hashCode());
			}
		},"t1");
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(InnerSingleton.getInstance().hashCode());
			}
		},"t2");
		Thread t3=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(InnerSingleton.getInstance().hashCode());
			}
		},"t3");
		Thread t4=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(InnerSingleton.getInstance().hashCode());
			}
		},"t4");
		Thread t5=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(InnerSingleton.getInstance().hashCode());
			}
		},"t5");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
	
	
}
