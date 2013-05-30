package ru.pastukh.study;


public class FirstProject {

	public static void main(String[] args) {
		Runnable threadJob = new MyRunnable();
		Thread myThread1 = new Thread(threadJob);
		Thread myThread2 = new Thread(threadJob);
		myThread1.setName("Alpha");
		myThread2.setName("Beta");
		myThread1.start();
		myThread2.start();
		System.out.println("Main thread");
	}
		
}
