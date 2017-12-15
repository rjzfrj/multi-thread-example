package org.my.thread0018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *自定义线程池使用：自定义线程池使用无界队列的实例
 *
 *
 */
public class UseThreadPollExecute1  {

	

	/**
	 * 使用有界队列的时，若有新的任务需要执行，如果线程池实际线程小鱼corePoolSize，则会有限创建线程
	 * 若大于corePooSize，则会讲任务加入队列，
	 * 若队列已满，则在总线程数不大于maxPoolSize的前题下，创建新的线程
	 * 若线程数大于maxNumPoolSize，则执行拒绝策略，或其他自定义方式。
	 */
	public static void main(String[] args) {
		
		
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(3);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1, //coreSize 核心线程数
				2, //maxSize 最大线程数
				60, //活跃时间
				TimeUnit.SECONDS, //活跃时间单位
				queue); //队列
//		for (int i = 0; i < 20; i++) {
//			pool.execute(new Task(i,"任务"+i));
//		}
		pool.execute(new Task(1,"任务1"));
		pool.execute(new Task(2,"任务2"));
		pool.execute(new Task(3,"任务3"));
		pool.execute(new Task(4,"任务4"));
		pool.execute(new Task(5,"任务5"));
		pool.execute(new Task(6,"任务6"));
		try {
			System.out.println("queue size：" + queue.size());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}
}
class Task implements  Runnable {
	private int taskId;
	private String taskName;
	
	public Task(int taskId,String taskName){
		this.taskId=taskId;
		this.taskName=taskName;
	}
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + "]";
	}

	public void run() {
		try {
			System.out.println("run task="+this);
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
