package org.my.thread013;

import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {
	
	public static void main(String[] args) {
		PriorityBlockingQueue<Task> q=new PriorityBlockingQueue<Task>();
		
		Task t1=new Task();
		t1.setId(3);
		t1.setName("任务1");
		Task t2=new Task();
		t2.setId(2);
		t2.setName("任务2");
		Task t3=new Task();
		t3.setId(1);
		t3.setName("任务3");
		
		q.add(t1);
		q.add(t2);
		q.add(t3);
		System.out.println(q.size());
		//for循环自己打印顺序是没变的，这个队列不是加完就排序完成了而是需要调用 poll()方法取元素自动取到的是排序最小的那个了
//		for (Task task : q) {
//			System.out.println(task);
//		}
//		Task [id=1, name=任务3]
//		Task [id=3, name=任务1]
//		Task [id=2, name=任务2]
		for(int i=0;i<3;i++) {
			System.out.println(q.poll());
		}
//		Task [id=1, name=任务3]
//		Task [id=2, name=任务2]
//		Task [id=3, name=任务1]

	}

}
