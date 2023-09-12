package parallelSaveOperation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
//import com.sun.xml.internal.txw2.output.StreamSerializer;

public class ParallelSaveOperation {
	public static final String DIRECTORY = System.getProperty("user.dir") + "/test/";
	
	public static void main(String[] args) throws IOException {
		
		Files.createDirectories(Paths.get(DIRECTORY));
		ParallelSaveOperation app = new ParallelSaveOperation();
        List<Person> people = app.generatePeople(1000000);
        
        long currentTime = System.currentTimeMillis();
        people.stream().forEach(ParallelSaveOperation::save);
        System.out.println("Time taken (sequential): "+(System.currentTimeMillis()-currentTime));
        
        currentTime = System.currentTimeMillis();
        people.parallelStream().forEach(ParallelSaveOperation::save);
        System.out.println("Time taken parallel: "+(System.currentTimeMillis()-currentTime));
        
	}
	
	private static void save(Person person) {
		try (FileOutputStream fos = new FileOutputStream(new File(DIRECTORY + person.getPersonId()+".txt"))) { 	
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	private List<Person> generatePeople(int num) {
		return Stream.iterate(0,n->n+1)
				.limit(num)
				.map(x -> {
					return new Person(x);
				})
				.collect(Collectors.toList());
	}

}
