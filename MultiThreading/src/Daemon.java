//class Runner1 implements Runnable{
//	@Override
//	public void run() {
//		for(int i=0;i<100;++i)
//			System.out.println("Runner1 "+i);
//		
//	}
//}
//

class DaemonWorker implements Runnable {

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Daemon thread is running...");
		}	
	}
}

class NormalWorker implements Runnable {

	@Override
	public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Normal thread finishes execution...");
		}	
}

class WorkerThread implements Runnable {
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println(i);
		}
	}
}

public class Daemon {
	public static void main(String[] args) {
		
//		Thread t1 = new Thread(new DaemonWorker());
//		Thread t2 = new Thread(new NormalWorker());		
		
		
//		t1.setDaemon(true);
//		t1.start();
//		t2.start();
		
		
//		System.out.println(Thread.currentThread().getName());
//		System.out.println(Thread.currentThread().getPriority());
		Thread t = new Thread(new WorkerThread());
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
		System.out.println("This is in the main thread...");
	}
}