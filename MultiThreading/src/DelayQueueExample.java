import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed {
	private long duration;
	private String message;
	
	public DelayedWorker(String message, long duration) {
		this.message = message;
		this.duration = System.currentTimeMillis()+duration;
	}
	
	@Override
	public int compareTo(Delayed other) {
		if(duration<((DelayedWorker) other).getDuration())
			return -1;
		if(duration>((DelayedWorker) other).getDuration())
			return +1;
		return 0;
	}
	
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(duration-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "DelayWorker [message=" + message + "]";
	}
	
	
}
public class DelayQueueExample {
	public static void main(String[] args) {
		BlockingQueue<DelayedWorker> queue = new DelayQueue<>();
		
		try {
			queue.put(new DelayedWorker("This is the first message...",2000));//expiration occurs when getDelay method returns <=0
			queue.put(new DelayedWorker("This is the second message...",3000));
			queue.put(new DelayedWorker("This is the third message...",4000));
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		while(!queue.isEmpty()) {
			try {
				System.out.println(queue.take()); //prints the string returned by toString() method
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}