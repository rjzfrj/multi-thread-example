package org.my.thread.sync006;

import org.my.thread.sync005.SyncException;

/**
 * 对象锁如果对象的内容属性发生变化对象锁还是线程安全的
 *
 */
public class ModifyLock {
	
	private String name;
	private int age;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public synchronized void setLock(String name,int age) {
			try {
					System.out.println("当前线程 :"+Thread.currentThread().getName()+"开始");
					this.setAge(age);
					this.setName(name);
					System.out.println("当前线程 :"+Thread.currentThread().getName()+"修改对象内容是:"+this.getName()+this.getAge());
					Thread.sleep(2000);
					System.out.println("当前线程:"+Thread.currentThread().getName()+"结束");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	
	public static void main(String[] args) {
		final ModifyLock objectLock = new ModifyLock();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				objectLock.setLock("张三", 1);
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				objectLock.setLock("我三", 2);
			}
		},"t2");
		
		t1.start();
		t2.start();
	}

}
