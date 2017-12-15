package org.my.thread015;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * MasterWorker模式 worker是具体的活动类
 *
 */
public class Worker implements Runnable {

	private ConcurrentHashMap<String, Object> resultMap;
	private ConcurrentLinkedQueue<Task> workQueue;
	public void setWorkerQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue=workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap=resultMap;
	}
	
	@Override
	public void run() {
		while (true) {
			Task input=this.workQueue.poll();
			if (input==null) {
				break;
			}
			Object output=handle(input);
			this.resultMap.put(Integer.toString(input.getId()), output);
		}
		
	}

	//模拟真实的处理任务
	private Object handle(Task input) {
		Object output=null;
		try {
			//模拟数据处理耗时
			Thread.sleep(500);
			output=input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}



}

