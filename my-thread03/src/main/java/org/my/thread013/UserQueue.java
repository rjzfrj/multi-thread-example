package org.my.thread013;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 知识点:高效无阻塞无界队列 ConcurrentLinkedQueue
 *
 */
public class UserQueue {
	
	public static void main(String[] args) throws Exception {
		
		//非阻塞队列
		/*ConcurrentLinkedQueue<String> q=new ConcurrentLinkedQueue<String>();
		q.add("a");
		q.add("b");
		q.add("c");
		q.offer("d");
		System.out.println(q.peek()); //只取不删除从头取
		System.out.println(q.size());
		System.out.println(q.poll());//取出并删除从头取
		System.out.println(q.size());
		*/
		
		//阻塞队列
		ArrayBlockingQueue<String> bq=new ArrayBlockingQueue<String>(5);  //有界队列必须初始化大小
		bq.put("a");
		bq.add("b");
		bq.put("c");
		bq.put("d");
		bq.put("e");
		bq.put("e");
		bq.offer("f");
		
		System.out.println(bq.offer("sss", 2,TimeUnit.SECONDS));
		
		
		LinkedBlockingQueue<String> lq=new LinkedBlockingQueue<String>();
		lq.put("a");
		lq.add("b");
		lq.put("c");
		lq.put("d");
		lq.put("e");
		
		System.out.println();
		
	}

}
