package org.my.thread.sync005;

/**
 * 当线程处理任务或逻辑的过程中如果出现异常会对线程造成影响，所以要对异常进行处理具体
 * 1记录日志者跳过 (使用catch (Exception e)捕获  continue)
 * 2中断的方式 catch使用 InterruptedException 或者throw new RuntimeException() 异常
 * 两种处理方式
 *
 */
public class SyncException {

	int i = 50;

	public synchronized void method() {
		while (i > 0) {
			try {
				i--;
				System.out.println(" i=" + i);
				if (i == 39) {
					Integer.parseInt("12a");
				}
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
				//一般出现异常情况下记录日志或者跳过，或者中断，更具具体需求而定
				//中断的方式 catch使用 InterruptedException
				//throw new RuntimeException();
				//continue;
			}
		}
	}

	public static void main(String[] args) {
		final SyncException syncDubbo1 = new SyncException();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				SyncException se = new SyncException();
				se.method();
			}
		});
		t1.start();
	}

}
