import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockWorker {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void produce() throws InterruptedException {
		lock.lock();
		System.out.println("Producer method...");
		condition.await();
		System.out.println("Again the producer method...");
		lock.unlock();
	}
	
	public void consume() throws InterruptedException {
		Thread.sleep(2000);
		lock.lock();
		System.out.println("Consumer method...");
		condition.signal();		
		lock.unlock();
		Thread.sleep(2000);
		System.out.println("Again the consumer method...");
	}
	
}

public class Synchro5 {
	public static void main(String[] args) {
		LockWorker worker = new LockWorker();
		Thread t1 = new Thread (new Runnable() {
			@Override
			public void run() {
				try {
					worker.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
    
		Thread t2 = new Thread (new Runnable() {
			@Override
			public void run() {
				try {
					worker.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
        t1.start();
        t2.start();
        
        try {
        	t1.join();
        	t2.join();
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
	}
}