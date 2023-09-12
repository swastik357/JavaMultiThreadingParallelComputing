import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FirstWorker implements Runnable {
	
	private BlockingQueue<Integer> queue;
	
	public FirstWorker(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		int counter = 0;
		while(true) {
			try {
				queue.put(counter);
				System.out.println("Putting item into the queeue..." + counter);
				counter++;
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	
}

class SecondWorker implements Runnable {
	
	private BlockingQueue<Integer> queue;
	
	public SecondWorker(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				int counter = queue.take();
				System.out.println("Taking item from the queue..." + counter);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
	
}

public class BlockingQueueExample {
	public static void main (String args[]) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		FirstWorker firstWorker = new FirstWorker(queue);
		SecondWorker secondWorker = new SecondWorker(queue);
		
		new Thread(firstWorker).start();
		new Thread(secondWorker).start();
		
	}
}