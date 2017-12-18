package org.my.thread0019;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class UseCyclicBarrier {

	 static class Runner implements Runnable{
		private CyclicBarrier barrier;
		private String name;
		
		public Runner(CyclicBarrier barrier,String name){
			this.barrier=barrier;
			this.name=name;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(1000* new Random().nextInt(5));
				System.out.println(name+"准备oK");
					barrier.await();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name+"Go！！");
		}
	}
	 
	 public static void main(String[] args) {
		 CyclicBarrier barrier=new CyclicBarrier(3);
		 
		 Thread t1=new Thread(new Runner(barrier,"T1"));
		 Thread t2=new Thread(new Runner(barrier,"T2"));
		 Thread t3=new Thread(new Runner(barrier,"T3"));
		 
		 t1.start();
		 t2.start();
		 t3.start();
		 
	}
	 
}
