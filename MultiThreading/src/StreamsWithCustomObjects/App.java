package StreamsWithCustomObjects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) throws IOException {
		
		List<Book> books = new ArrayList<>();
		books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
		books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
		books.add(new Book("Death on The Nile", "Agatha Christie", 370, Type.THRILLER));
		books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
		books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
		books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
		books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));
		
//		books.stream().forEach(System.out::println);
//		List<Book> result = books.stream().filter(t->t.getType() == Type.NOVEL).collect(Collectors.toList());
//		System.out.println(Arrays.toString(result.toArray()));
//		result.stream().forEach(System.out::println);
		
//		List<String> result = books.stream().filter(b->b.getType() == Type.NOVEL)
//				.sorted(Comparator.comparing(Book::getPages))
//				.map(Book::getTitle)
//				.collect(Collectors.toList());
		
//		result.stream().forEach(System.out::println);
//		
//		Map<Type,List<Book>> booksByType = books.stream().collect(Collectors.groupingBy(Book::getType));
//		
//		booksByType.entrySet().stream().forEach(System.out::println);		
//				books.stream().filter(p->p.getPages()>500).map(Book::getTitle).limit(2).collect(Collectors.toList());
//		
//		List<String> longestBooks = books.stream().filter(p->p.getPages()>500).map(Book::getTitle).limit(2).collect(Collectors.toList());
//		longestBooks.stream().forEach(System.out::println);
		
//		List<String> twoWordTitleBooks = books.stream().filter(p->p.getTitle().split(" ").length == 2).map(Book::getTitle).collect(Collectors.toList());
//		twoWordTitleBooks.stream().forEach(System.out::println);
		
//		List<String> titles = new ArrayList<>();
//		Iterator<Book> iterator = books.iterator();
//		while(iterator.hasNext()) {
//			titles.add(iterator.next().getTitle());
//		}
//		
//		List<String> titles2 = books.stream().map(Book::getTitle).collect(Collectors.toList());
//		titles2.forEach(e->System.out.println(e));
		
		
		String path = "/Users/swastik./eclipse-workspace/MultiThreading/src/StreamsWithCustomObjects/name";
		Stream<String> namesStream = Files.lines(Paths.get(path));
		List<String> names = namesStream.filter(x->x.startsWith("S")).collect(Collectors.toList());
		names.forEach(System.out::println);
		
		List<String> longestBooks = books.stream()
				.filter(p-> {
					System.out.println("Filtering "+p.getTitle());
					return p.getPages()>500;
				})
				.map(b->{
					System.out.println("Mapping "+b.getTitle());
					return b.getTitle();
				})
				.limit(2)
				.collect(Collectors.toList());
		
		List<String> words = Arrays.asList("Adam","Ana","Daniel");
		List<Integer> lengths = words.stream().map(String::length).collect(Collectors.toList());
		lengths.stream().forEach(System.out::println);
		List<Integer> nums = Arrays.asList(1,2,3,4);
		nums.stream().map(x->x*x).collect(Collectors.toList()).forEach(System.out::println);	
		
		String[] array = {"hello","shell"};
		List<String> unique = Arrays.stream(array).map(w->w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
		unique.stream().forEach(System.out::println);
		
		List<Integer> nums1 = Arrays.asList(1,2,3);
		List<Integer> nums2 = Arrays.asList(4,5);
		
		List<List<Integer>> pairs = nums1.stream().flatMap(i->nums2.stream().map(j->Arrays.asList(i,j))).collect(Collectors.toList());
        System.out.println(Arrays.toString(pairs.toArray()));
        
        List<Integer> numsList = Arrays.asList(3,6,34,5);
        numsList.stream().reduce(Integer::max).ifPresent(System.out::println);
        
        books.stream().map(Book::getPages).reduce(Integer::max).ifPresent(System.out::println);
        
        Optional<Book> longest = books.stream().reduce((b1,b2) -> b1.getPages()>b2.getPages() ? b1:b2);
        longest.ifPresent(System.out::println);
        
        IntStream s = books.stream().mapToInt(Book::getPages);
        System.out.println(s.sum());
        
        OptionalInt result = books.stream().mapToInt(Book::getPages).max();
        System.out.println(result.orElse(0));       
//      Stream<Integer> ss = s.boxed();
        
        boolean res = books.stream().allMatch(b->b.getPages()>100);
        System.out.println(res);
        
        boolean res2 = books.stream().noneMatch(b->b.getPages()>100);
        System.out.println(res2);
        
        books.stream().filter(b->b.getType() == Type.HISTORY).findAny().ifPresent(System.out::println);
        books.stream().filter(b->b.getType() == Type.HISTORY).findFirst().ifPresent(System.out::println);
        
	}

}
