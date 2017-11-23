package org.my.thread012;

import java.util.Iterator;
import java.util.Vector;

/**
 * 知识点：并发类容器在使用的过程中对于一些符合性操作，比如一个线程添加，另一个线程删除会抛出java.util.ConcurrentModificationException 异常
 * 
 *
 */
public class Tickets {
	
	public static void main(String[] args) {
		final Vector<String> tickets=new Vector<String>();
		
		for (int i = 0; i <=100; i++) {
			tickets.add("a"+i);
		}
		//使用迭代器进行移除就会出现 java.util.ConcurrentModificationException 异常
//		for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			tickets.remove(string);
//			
//		}
		//使用for是没问题的，如果使用Iterator是会有问题的
		for (int j=0;j<tickets.size();j++) {
			String string = (String) tickets.get(j);
			tickets.remove(string);
			
		}
		//下面的多个线程对 Vector 进行并发的移除是没问题的
//		for (int i = 0; i < 10; i++) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while (true) {
//						if (tickets.isEmpty()) {
//							break;
//						}else {
//							System.out.println(Thread.currentThread().getName()+"----"+tickets.remove(0));
//						}
//					}
//				}
//			}).start();
//		}
	}

}
