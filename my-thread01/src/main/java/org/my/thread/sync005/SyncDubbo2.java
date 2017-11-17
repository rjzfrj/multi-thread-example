package org.my.thread.sync005;

/**
 * 有父类和之类继承关系的时候这样用也是线程安全的
 * 父类中对变量减减子类中也对父类变量减减同时也调用父类发方法这样如果加锁了还是线程安全的
 *
 */
public class SyncDubbo2 {

	static class Parent {
		public int i = 10;

		public synchronized void methodParent() {

			try {
				i--;
				System.out.println(" Parent i=" + i);
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class Son extends Parent {

		public synchronized void methodSon() {

			try {
				while(i>0) {
				i--;
				System.out.println("Son i=" + i);
				Thread.sleep(200);
				this.methodParent();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}


	public static void main(String[] args) {
		final SyncDubbo2 syncDubbo1 = new SyncDubbo2();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Son son=new Son();
				son.methodSon();
			}
		});
		t1.start();
	}

}
