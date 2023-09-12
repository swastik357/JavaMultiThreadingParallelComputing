import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
	INSTANCE;
	private Semaphore semaphore = new Semaphore(5,true);
	public void download() {
		try {
			semaphore.acquire();
			downloadData();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
	
	private void downloadData() {
		try {
			Thread.sleep(2000);
			System.out.println("thread is " + Thread.currentThread().getName());
//			System.out.println("Downloading data from the web...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


public class SemaphoreExample {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		
		for(int i = 0;i<16;i++) {
			service.execute(new Runnable() {
				public void run() {
					Downloader.INSTANCE.download();
				}
			});
		}
	}
}