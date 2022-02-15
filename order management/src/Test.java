import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.xdevapi.Result;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * The Class Test.
 */
public class Test {
	
	/**
	 * The main method.
	 *
	 * @param argv the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] argv) throws IOException
	{
		
		File f = new File("cereri.txt");
		
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		while ((line = br.readLine()) != null){
	
			String[] primul = text(line);  
			String[] parametri = text2(primul[1]);  
			Test pro = new Test();
			if(primul[1].equals("client"))
			{
			pro.raportClienti();
			}
			else if (primul[0].equals("Insert client"))
			{
				
				pro.insertClienti(parametri);
				
			}
			else if (primul[0].equals("Insert product"))
			{
				pro.insertProduse(parametri);
			}
			else if (primul[1].equals("product"))
			{
				pro.raportProduse();
				
			}
			else if (primul[1].equals("order"))
			{
				pro.raportOrders();
				
			}
			else if (primul[0].equals("Delete Product"))
			{
				pro.delProduse(primul);
				
			}
			else if (primul[0].equals("Delete client"))
			{
				pro.delClient(parametri);
				
			}
			else if (primul[0].equals("Order"))
			{
				pro.orders(parametri);
				pro.updateCant(parametri);
				pro.Bill();
				
			}
			else
			{
				System.out.println("NU");
			}
		}
		br.close();
		fr.close();	
		
	}
	


	/**
	 * Text.
	 *
	 * @param line fiecare linie a fisierului text	
	 * 
	 * @return string[]
	 */
	public static String[] text(String line)
	{
		String[] cerere = null;
		if(line.contains(":"))
		{
			cerere = line.split(": ");
				
		}
		else if(line.contains("Report"))
		{
			cerere = line.split(" ");
		}

		return cerere;
		
	}
	
	/**
	 * Text 2.
	 *
	 * @param da a doua parte a diecarei linii(urmatoarele 2 sau 3 cuvinte)
	 * 
	 * @return string[]
	 */
	public static String[] text2(String da)
	{
		String[] denumiri = null;
		if(da.contains(", "))
		{
			denumiri = da.split(", ");
		}
		return denumiri;
	}
	
	/**
	 * Orders.
	 *
	 * @param parametri
	 * 
	 */
	void orders(String[] parametri)
	{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!");
		
		java.sql.Statement stnt = con.createStatement();
		
		String in = "insert into comenzi (nume, fruct, cantitate)" +
					"values('" + parametri[0] + "', '" + parametri[1] + "', '" + parametri[2] + "')";
		stnt.executeUpdate(in);
		stnt.close();
		con.close();

	
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		}
	}
	
	/**
	 * Update cant.
	 *
	 * @param parametri
	 */
	void updateCant(String[] parametri)
	{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!");
		java.sql.Statement stnt = con.createStatement();
		
		String in = "update produse set cantitate = cantitate -" + parametri[2] + " where nume = '"+ parametri[1] +"'";
		stnt.executeUpdate(in);
		
		stnt.close();
		con.close();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		}
	}
	
	
	/**
	 * Bill.
	 */
	void Bill()
	{
		try {
			String pdf = "D:\\Tema3\\Tema3\\Bills.pdf";
			Document doc = new Document();
			
				PdfWriter.getInstance(doc, new FileOutputStream(pdf));
			
			doc.open();
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!"); 
		java.sql.Statement stnt = con.createStatement();
		ResultSet rs = stnt.executeQuery("select comenzi.cantitate, comenzi.nume, comenzi.fruct, produse.pret from comenzi inner join produse ON comenzi.fruct = produse.Nume");
			while(rs.next()) {
				int c = rs.getInt("cantitate");
				int p = rs.getInt("pret");
				int t = c*p;
				Paragraph para = new Paragraph("Nume: " + rs.getString("nume")+"  || Comanda: " + rs.getString("fruct")+"  || Pret: " + t);
				doc.add(para);
				doc.add(new Paragraph(" "));
			}
			doc.close();
			stnt.close();
			con.close();
		
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Raport orders.
	 */
	void raportOrders()
	{
		try {
			String pdf = "D:\\Tema3\\Tema3\\Raport Comenzi.pdf";
			Document doc = new Document();
			
				PdfWriter.getInstance(doc, new FileOutputStream(pdf));
			
			doc.open();
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!"); 
		java.sql.Statement stnt = con.createStatement();
		ResultSet rs = stnt.executeQuery("SELECT * FROM comenzi");
			while(rs.next()) {
				Paragraph para = new Paragraph("Nume: " + rs.getString("nume")+"  || Comanda: " + rs.getString("fruct")+"  || Cantitatea: " + rs.getString("cantitate"));
				doc.add(para);
				doc.add(new Paragraph(" "));
			}
			doc.close();
			stnt.close();
			con.close();
		
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Raport clienti.
	 */

	void raportClienti()
	{
		try {
			String pdf = "D:\\Tema3\\Tema3\\Raport Clienti.pdf";
			Document doc = new Document();
			
				PdfWriter.getInstance(doc, new FileOutputStream(pdf));
			
			doc.open();
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!"); 
		java.sql.Statement stnt = con.createStatement();
		ResultSet rs = stnt.executeQuery("SELECT * FROM clienti");
			while(rs.next()) {
				Paragraph para = new Paragraph(rs.getString("Nume")+" " + rs.getString("Oras"));
				doc.add(para);
				doc.add(new Paragraph(" "));
			}
			doc.close();
			stnt.close();
			con.close();
		
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Raport produse.
	 */
	void raportProduse()
	{
		try {
			String pdfp = "D:\\Tema3\\Tema3\\Raport Produse.pdf";
			Document docp = new Document();
			
				PdfWriter.getInstance(docp, new FileOutputStream(pdfp));
			
			docp.open();
			
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!"); 
		java.sql.Statement stnt = con.createStatement();
		ResultSet rs = stnt.executeQuery("SELECT * FROM produse");
			while(rs.next()) {
				int cantitate = + rs.getInt("cantitate");
				if (cantitate > 0) {
				Paragraph para = new Paragraph(rs.getString("nume")+" "+ cantitate + " " + rs.getString("pret"));
				docp.add(para);
				docp.add(new Paragraph(" "));
				}
				else 
				{
					Paragraph para = new Paragraph(rs.getString("nume")+" Stoc Terminat " + rs.getString("pret"));
					docp.add(para);
					docp.add(new Paragraph(" "));
				}
			}
			docp.close();
			stnt.close();
			con.close();
		
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Insert clienti.
	 *
	 * @param parametri the parametri
	 */
	void insertClienti(String[] parametri)
	{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!");
		
		java.sql.Statement stnt = con.createStatement();
		String in = "insert into clienti " +
					"(Nume, Oras)" +
					"values('"+parametri[0] +"', '" + parametri[1] + "')";
		stnt.executeUpdate(in);
		stnt.close();
		con.close();

	
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		}
	}
	
	/**
	 * Insert produse.
	 *
	 * @param parametri the parametri
	 */
	void insertProduse(String[] parametri)
	{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!");
		java.sql.Statement stnt = con.createStatement();
		String in = "insert into produse " + "(nume, cantitate, pret)" + "values('" + parametri[0] + "', '" + parametri[1] + "', '" + parametri[2] + "')";
		stnt.executeUpdate(in);
		stnt.close();
		con.close();

		
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		}
	}

	
	/**
	 * Del produse.
	 *
	 * @param parametri the parametri
	 */
	void delProduse(String[] parametri)
	{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!");
		java.sql.Statement stnt = con.createStatement();
		String in = "delete from produse where nume = '"+ parametri[1] +"'";
		stnt.executeUpdate(in);
		stnt.close();
		con.close();

		
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		}
	}
	
	/**
	 * Del client.
	 *
	 * @param parametri the parametri
	 */
	void delClient(String[] parametri)
	{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clienti", "root", "Lucian99!");
		java.sql.Statement stnt = con.createStatement();
		String in = "delete from clienti where Nume = '"+ parametri[0] +"' and Oras = '" + parametri[1] +"'";
		stnt.executeUpdate(in);
		System.out.println("merge");
		stnt.close();
		con.close();
	
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		} catch (SQLException ex) {
			Logger.getLogger(Test.class.getName()).isLoggable(Level.SEVERE);
		}
	}
}
