package org.my.thread015;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * MasterWorker模式负者承载分发任务
 *
 */
public class Master {

	//1承装任务的集合
   	private ConcurrentLinkedQueue<Task> workQueue=new ConcurrentLinkedQueue<Task>();
   	
   	//2使用HashMap去承装所有的worder对象
   	private HashMap<String, Thread> workers=new HashMap<String,Thread>();
   	
   	//3使用一个容器承装每一个worker并非执行任务的结果集
   	private ConcurrentHashMap<String, Object> resultMap=new ConcurrentHashMap<String,Object>();
   	//4构造方法
   	public Master (Worker worker,int workerCount){
   		//每一个worker对象都需要有Master的引用workQueue用于任务的领取，resutlMap用于任务的提交
   		worker.setWorkerQueue(this.workQueue);
   		worker.setResultMap(this.resultMap);
   		for (int i = 0; i < workerCount; i++) {
   			//key标示每一个worker的名字，value标示现场咨询对象
   			workers.put("node"+Integer.toString(i), new Thread(worker));
		}
   	}
   	//5提交方法
   	public void submit (Task task){
   		this.workQueue.add(task);
   	}
   	//6需要有一个执行的方法，启动引用程序，让所有的worker工作
   	public void execute(){
   		for (Map.Entry<String, Thread> me:workers.entrySet()) {
			me.getValue().start();
		}
   	}
   	//8判断线程是否执行完毕
	public boolean isComplete() {
		for (Map.Entry<String, Thread> me:workers.entrySet()) {
			if(me.getValue().getState()!=Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}
	//9返回结果集数据
	public int getResult(){
		int ret=0;
		for (Map.Entry<String, Object> me:resultMap.entrySet()) {
			ret+=(Integer)me.getValue();
		}
		return ret;
	}
}
