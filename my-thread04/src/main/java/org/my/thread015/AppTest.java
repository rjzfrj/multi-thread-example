
package org.my.thread015;

/**
 * 实例测试MasterWorker模式实例
 *
 */
public class AppTest {

	public static void main(String[] args) {
		Master master=new Master(new Worker(),2);
		for (int i = 0; i <= 100; i++) {
			Task t=new Task();
			t.setId(i);
			t.setName("任务"+i);	
			t.setPrice(5);
			 master.submit(t);
		}
		master.execute();
		long start=System.currentTimeMillis();
		
		while(true){
			if(master.isComplete()){
				long end=System.currentTimeMillis();
				int ret=master.getResult();
				System.out.println("任务处理完毕结果是："+ret+",耗时："+(end-start));
				break;
			}
		}
	}
}
