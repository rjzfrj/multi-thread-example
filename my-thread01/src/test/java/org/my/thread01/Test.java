package org.my.thread01;

public class Test {
	
	public static void main(String[] args) {
		
		System.out.println("---12次经过的总路程"+sum(12));
		System.out.println("--9次经过的总路程"+sum(9));
		System.out.println("12次经过的总路程"+sum2(12));
		System.out.println("9次经过的总路程"+sum2(9));
		
	System.out.println( new StringBuilder("abcde").reverse().toString());  
	}
	
	public static double sum(int num) {
		double n=100;
		double y=0;
		for (int i = 1; i <= num; i++) {
			n=n/2;
			y=y+n*2;
		}
		double sum=100+y-n;
		return sum;
	}
	public static double sum2(int num) {
		
		double y=0;
		double n=100;
		for (int i = 1; i <= num; i++) {
			double cushu=Math.pow(2, i);
			 n=100/cushu;
			y=y+n*2;
		}
		double sum=100+y-n;
		return sum;
	}
	
	

}
