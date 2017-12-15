package org.my.thread014;

public class App{ 
  public static void main(String[] args) {
	  FutureClient fc=new FutureClient();
	   Data data=fc.request("请求参数");
	   System.out.println("请求数据已经发送成！");
	   System.out.println("做其他的事情。。。");
	   String result=data.getRequest();
	   System.out.println(result);
	   
   }
}
