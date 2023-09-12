import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Workers implements Runnable {
	private int id;
	private CountDownLatch latch;
	
	public Workers(int id,CountDownLatch latch) {
		this.id = id;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		doWork();
		latch.countDown();
		System.out.println("Thread with ID " + this.id + " completed...");
	}
	
	private void doWork() {
		try {
			System.out.println("Thread with ID " + this.id + " starts working...");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


public class Latch {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		for(int i=0;i<5;i++)
			service.execute(new Workers(i,latch));
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All tasks have been finished...");
		service.shutdown();
	}
}