package org.my.thread0019;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String>{

	private String para;
	private int use; //模拟实际任务耗时这里外部传入模拟
	
	public UseFuture(String para, int use){
		this.para=para;
		this.use=use;
	}
	@Override
	public String call() throws Exception {
		Thread.sleep(1000*use);
		return this.para+"处理完成";
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String queryStr= "query";
		FutureTask<String> futureTask1=new FutureTask<String>(new UseFuture(queryStr+"1",new Random().nextInt(5)));
		FutureTask<String> futureTask2=new FutureTask<String>(new UseFuture(queryStr+"2",new Random().nextInt(5)));
		FutureTask<String> futureTask3=new FutureTask<String>(new UseFuture(queryStr+"3",new Random().nextInt(5)));
		ExecutorService executor= Executors.newFixedThreadPool(3);
		Future f1=executor.submit(futureTask1);
		Future f2=executor.submit(futureTask2);
		Future f3=executor.submit(futureTask3);
		System.out.println("请求完毕");
		//Thread.sleep(3);
		System.out.println(""+futureTask1.get());
		System.out.println(""+futureTask2.get());
		System.out.println(""+futureTask3.get());
		executor.shutdown();
		
	}

}
