package com.zxm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Task implements Runnable {
	volatile int sum = 300;

	@Override
	public void run() {

		while (true) {
			synchronized (this) {
				if (sum > 0) {
					sum--;
					System.out.println("当前" + Thread.currentThread().getName()
							+ " : " + sum);
				} else {
					break;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService pool=Executors.newFixedThreadPool(4);
		Task c = new Task();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(c);
		Thread t4 = new Thread(c);
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		// 关闭线程池
        pool.shutdown();
		
	}
}
