import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Test {
	static Map<String, Long> durata = new HashMap<String, Long>();
	static List<String> labels = new ArrayList<String>();
	static List<String> numarzi = new ArrayList<String>();
	static List<MonitoredData> activitati = new ArrayList<MonitoredData>();
	
	public static void main(String[] args) throws ParseException, IOException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fileName = "D:\\Tema5\\Tema5\\Activities.txt";
		
		
		
		
		//citesc din fisier cu stream
		
		try (Stream<String> list = Files.lines(Paths.get(fileName))) {
			
			activitati = list.map(l -> l.split("\t\t"))
				.map(arr -> new MonitoredData(arr[0], arr[1], arr[2]))
				.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//////

		
		
		
		//////Task2
		
		int last = (activitati.size() - 1); 
		String ziua1 = activitati.get(0).start_time;
		String ultimazi = activitati.get(last).end_time;
		String[] z1 = separatedays(ziua1);
		String[] lastday = separatedays(ultimazi);	
		LocalDate date1 = LocalDate.parse(z1[0], dtf);
		LocalDate date2 = LocalDate.parse(lastday[0], dtf);
		long daysBetween = Duration.between(date1.atStartOfDay(), date2.atStartOfDay()).toDays();  ///days betwen imi numara fara prima zi
		long Zile = daysBetween +1;
		PrintWriter writer = new PrintWriter("D:\\Tema5\\Tema5\\Task1.txt", "UTF-8");
		writer.println("Numarul zilelor distincte care apar in data monitorizata: " + Zile);
		writer.close();
	    System.out.println ("Numarul zilelor distincte care apar in data monitorizata: " + Zile);
	    
	    
	   
	    
	    //activitati
	    
	    labels = activitati.stream().map(a -> a.activity_label).collect(Collectors.toList());
	    
	    Map<String, Integer> count = new HashMap<String, Integer>();
	    
	   
	   labels.stream().forEach(l ->{
		   if (count.containsKey(l)) {
               count.put(l, count.get(l) + 1);
           } else {
               count.put(l, 1);}
	   });
	   
	    	
	    System.out.println("\nNumarul de aparitii al fiecarei activitati: ");
	    
	    
	    PrintWriter writer2 = new PrintWriter("D:\\Tema5\\Tema5\\Task2.txt", "UTF-8");
		writer2.println("Numarul de aparitii al fiecarei activitati: ");
		
	   
	    
	    count.forEach((k, v) -> {System.out.println(k + ": " + v);
	    						writer2.println(k + ": " + v);});
	    
	    writer2.close();
	    
	    ////acts per day
 
	    activitati.forEach(a->{
	    	String[] s = separatedays(a.start_time);
		    String[] e = separatedays(a.end_time);
		    LocalTime l1 = LocalTime.parse(s[1]);
	        LocalTime l2 = LocalTime.parse(e[1]);
	        long mins = l1.until(l2, MINUTES);
	        for(String word: labels) {
		    	if (durata.containsKey(word)) {
		    		durata.put(word, durata.get(word) + mins);
	           } else {
	        	   durata.put(word, mins);
	           }
		    }
	    
	    });
	    
	    
	    PrintWriter writer3 = new PrintWriter("D:\\Tema5\\Tema5\\Task3.txt", "UTF-8");
		writer3.println("Numarul de minute cumulate al fiecarei activitati: ");
		
	    
	    System.out.println("\nNumarul de minute cumulate al fiecarei activitati: ");
	    
	    durata.forEach((k, v) -> {System.out.println(k + ": " + v + " minute");
		writer3.println(k + ": " + v + " minute");});

	    
	    writer3.close();
	   
	    
	}
	
	public static String[] separatedays(String da)
	{
		String[] nu = da.split(" ");
		return nu;
		
	}
}

