package org.my.sync004;

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
	public synchronized void getValue() {
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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dirtyRead.getValue();
	}

}
