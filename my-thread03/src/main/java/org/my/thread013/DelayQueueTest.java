package org.my.thread013;

import java.util.Calendar;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest implements Runnable {
    DelayQueue<Customer1> queue = new DelayQueue<Customer1>();

    public void onCumputer(Integer id, String name, Integer money) {
        Customer1 customer = new Customer1(id, name, money);
        queue.put(customer);
        System.out.println("身份证：" + id + "姓名：" + name + "上机时间" + money);
    }

    public void outCumputer(Customer1 customer) {
        System.out.println("身份证：" + customer.getId() + "姓名：" + customer.getName() + "下机");
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("检查.....");
            try {
                Customer1 customer = queue.take();
                outCumputer(customer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        DelayQueueTest queueTest = new DelayQueueTest();
        System.out.println("开始营业...");
        new Thread(queueTest).start();
        queueTest.onCumputer(123, "张三", 3);
        queueTest.onCumputer(124, "张三", 5);
        queueTest.onCumputer(126, "张三", 10);
//        while (true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Calendar.getInstance().get(Calendar.SECOND));
//        }

    }

}

class Customer1 implements Delayed {
    // 身份证号
    private Integer id;
    // 姓名
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 下机时间 一元一秒
    private long endTime;

    public Customer1(Integer id, String name, Integer money) {
        this.id = id;
        this.name = name;
        this.endTime = money * 1000 + System.currentTimeMillis();
    }

    // 设置优先级
    @Override
    public int compareTo(Delayed o) {
        Customer1 c = (Customer1) o;
        return this.endTime - c.endTime > 0 ? 1 : (this.endTime - c.endTime < 0 ? -1 : 0);
    }

    // 判断过期时间
    @Override
    public long getDelay(TimeUnit unit) {
        return this.endTime - System.currentTimeMillis();
    }

}