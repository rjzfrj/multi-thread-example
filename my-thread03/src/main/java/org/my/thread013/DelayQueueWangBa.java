package org.my.thread013;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * 使用DelayQueue队列来实现过期时间
 *
 */
public class DelayQueueWangBa implements Runnable{

	private  DelayQueue<WangMin> queue=new DelayQueue<WangMin>();
	
	@Override
	public void run() {
		
		while(true) {
			try {
				WangMin wm =  queue.take();
				xiaji(wm);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * @param id
	 * @param name
	 * @param money 元
	 */
	public void shangji(int id,String name,int money) {
		WangMin wm=new WangMin(id, name, System.currentTimeMillis()+money*1000);
		System.out.println("姓名:"+name+"身份证号："+id+"已经交钱"+money+"元开始上机...");
		queue.add(wm);
	}
	
	public void xiaji(WangMin wm) {
		System.out.println("姓名:"+wm.getName()+ "-身份证号："+wm.getId()+" 时间到开始下机...");
	}
	
	public static void main(String[] args) {
		DelayQueueWangBa wb=new DelayQueueWangBa();
		Thread shangwang=new Thread(wb);
		shangwang.start();
		wb.shangji(100, "张三", 20);
		wb.shangji(200, "王五", 15);
		wb.shangji(300, "李四", 3);
	}

}
