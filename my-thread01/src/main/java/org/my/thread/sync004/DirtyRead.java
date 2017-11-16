package org.my.thread.sync004;

/**
 * 脏读概念经典实例
 * 如果setValue加了同步锁，getValue没加同步锁就会造成同步锁问题
 * 
 */
public class DirtyRead {
	
	private String username="abc";
	private String password="123";
	
	// 模拟设置值需要2秒钟
	public synchronized void setValue(String username,String password) {
		this.username=username;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.password=password;
		System.out.println("setValue最终结果: [username]:"+username+" [password]:"+password);
	}
	public  void getValue() {
		System.out.println("getValue最终结果: [username]:"+username+" [password]:"+password);
	}
	public static void main(String[] args) {
		final DirtyRead dirtyRead=new DirtyRead();
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				dirtyRead.setValue("abcd", "321");
			}
		});
		t1.start();
		//main函数主线程在29行实例化了一个t1线程 t1线程dirtyRead对象的setValue方法先设置了username 然后才休眠3秒，然后才设置password
		// 然后主线程接着往下走执行了休眠了一秒钟，然后调用getValue 这时很明显线程t1还没有执行完，这时username一加是修改过的值了，而password还是未改的数据
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dirtyRead.getValue();
	}

}
