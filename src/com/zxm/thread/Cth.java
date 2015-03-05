package com.zxm.thread;

/**
 * ¹²ÏíÂôÆ±
 * 
 * @author zxm
 * 
 */
public class Cth implements Runnable {
	volatile int sum = 300;

	@Override
	public void run() {

		while (true) {
			synchronized (this) {
				if (sum > 0) {
					sum--;
					System.out.println("µ±Ç°" + Thread.currentThread().getName()
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
		
		Cth c = new Cth();
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(c);
		Thread t4 = new Thread(c);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
