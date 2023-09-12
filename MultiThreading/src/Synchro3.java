import java.util.ArrayList;
import java.util.List;

class Processor {
	private List<Integer> list = new ArrayList<>();
	private static final int UPPER_LIMIT = 5;
	private static final int LOWER_LIMIT = 0;
	private final Object lock = new Object();
	private int value = 0;
	
	public void producer() throws InterruptedException {
		synchronized (lock) {
			while(true) {
				if(list.size() == UPPER_LIMIT) {
					System.out.println("Waiting for removing items...");
					Thread.sleep(2000);
					lock.wait();
				} else {
					System.out.println("Adding: "+value);
					Thread.sleep(2000);
					list.add(value);
					value++;
					lock.notify();
				}
			}
		}
	}
	
	public void consumer() throws InterruptedException {
		synchronized (lock) {
			while(true) {
				if(list.size() == LOWER_LIMIT) {
					System.out.println("Waiting for adding items...");
					Thread.sleep(2000);
					value = 0;
					lock.wait();
				} else {
					System.out.println("Removing: "+list.remove(list.size()-1));
					Thread.sleep(2000);
					lock.notify();
				}
				Thread.sleep(2000);
			}
		}
	}
}
	

public class Synchro3 {
	public static void main(String[] args) {
		Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
	}
}