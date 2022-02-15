import java.io.File;
import java.util.Scanner;
import java.util.Formatter;

public class outFile {
	Scanner scanner;
	Formatter frm;
	
	//deschide fisierul de intrare
	public void Filein() {
		try {
			scanner = new Scanner(new File("da.txt"));
		}
		catch (Exception e){
			System.out.println("fisierul nu poate fi deschis");
		}
	}
	
	// deschide fisierul cu rezultate
	public void Fileout() {
		try {
			frm = new Formatter("Out-Test-3.txt");
		}
		catch (Exception e){
			System.out.println("fisierul nu poate fi deschis");
		}
	}
	
	
	// citire data din in file
	public data scan() {

		data da = new data(); 
		
		
		da.setN(scanner.nextInt());		//N
		da.setQ(scanner.nextInt());		//Q
		da.setDurata(scanner.nextInt());		//durata
		
		String buffer;
		Integer split;
		
		
		buffer = scanner.next();
		split = buffer.indexOf(",");											///split intre min si max
		
		da.setMin_arrival(Integer.parseInt(buffer.substring(0, split)));			//min arrival
		da.setMax_arrival(Integer.parseInt(buffer.substring(split + 1, buffer.length())));		//max arrival
		
		
		buffer = scanner.next();
		split = buffer.indexOf(",");
		
		da.setMin_service(Integer.parseInt(buffer.substring(0, split)));			//min service
		da.setMax_service(Integer.parseInt(buffer.substring(split + 1, buffer.length())));			////max arrival
		
		return da;
	}
	
	
	public void scriereFisier(String out) {
		frm.format("%s", out);
	} 
	
	
	
	//inchidere fisiere
	public void closeFilein() {
		scanner.close();
	}
	
		public void closeFileout() {
			frm.close();
	}
}
