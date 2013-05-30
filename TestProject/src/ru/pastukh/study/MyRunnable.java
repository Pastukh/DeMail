package ru.pastukh.study;

public class MyRunnable implements Runnable {

	public void run() {
for (int i = 0; i < 25; i++) {
	//			try {
	//				Thread.sleep(2000);
	//			}	catch (InterruptedException ex){
	//				ex.printStackTrace();
	//			}
	String threadName = Thread.currentThread().getName();
	System.out.println(threadName + " is working");
}
		}

}
