import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallProcessor implements Callable<String> {
	private int id;
	
	public CallProcessor(int id) {
		this.id = id;
	}
	
	@Override
	public String call() throws Exception {
		Thread.sleep(2000);
		return "Id: "+id;
	}
}

public class CallFuture {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		List<Future<String>> list = new ArrayList<>();
		
		for(int i=0;i<100;++i) {
			Future<String> future = service.submit(new CallProcessor(i+1));
			list.add(future);
		}
		
		for(Future<String> f : list) {
			try {
				System.out.println(f.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		service.shutdown();

	}
}