import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import java.awt.Panel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.Button;
import javax.swing.ScrollPaneConstants;

public class IRestaurantProcessing {
	
	static ArrayList<Items> meniu;
	static ArrayList<String> comanda;
	static ArrayList<String> available;
	static ArrayList<Order> orders;
	String header[] = new String[] {"Nume","Pret"};
	String header2[] = new String[] {"Id","Time", "Table", "Food"};
	String header3[] = new String[] {"Id","Mancare"};
	int row, col, row2, col2, row3, col3;
	private JFrame frame;
	private JTextField textField_1;
	private final JTable table = new JTable();
	private final JTable OrderTable = new JTable();
	private final JTable comenzi = new JTable();
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		available = new ArrayList<>();
		meniu = new ArrayList<>();
		comanda = new ArrayList<>();
		orders = new ArrayList<>();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IRestaurantProcessing window = new IRestaurantProcessing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
	        double... percentages) {
	    double total = 0;
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        total += percentages[i];
	    }
	 
	    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	        TableColumn column = table.getColumnModel().getColumn(i);
	        column.setPreferredWidth((int)
	                (tablePreferredWidth * (percentages[i] / total)));
	    }
	}
	
	private void Clear()
	{
		textField_1.requestFocus();
		textField_1.setText("");
	}
	
	private static int Randomnr(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static String[] edit(String line)  ///proceseaza textul
	{
		String[] cerere = null;
		cerere = line.split(", ");
		return cerere;
		
	}
	

	/**
	 * Create the application.
	 */
	public IRestaurantProcessing() {
		initialize();
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setLayout(null);
		
		Panel layered_panel = new Panel();
		layered_panel.setBackground(Color.LIGHT_GRAY);
		layered_panel.setBounds(0, 100, 434, 161);
		frame.getContentPane().add(layered_panel);
		layered_panel.setLayout(new CardLayout(0, 0));
		
		Panel WAITER = new Panel();
		layered_panel.add(WAITER, "name_8482801225600");
		WAITER.setLayout(null);
		
		Panel CHEF = new Panel();
		layered_panel.add(CHEF, "name_8487304128500");
		CHEF.setLayout(null);
		
		
		
		
		Panel ADMINISTRATOR = new Panel();
		layered_panel.add(ADMINISTRATOR, "name_8485584599300");
		ADMINISTRATOR.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(126, 34, 169, 23);
		ADMINISTRATOR.add(textField_1);
		textField_1.setColumns(10);
		
		
		DefaultTableModel dtm3;
		dtm3 = new DefaultTableModel(header3, 0);
		comenzi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row3 = comenzi.getSelectedRow();
				col3 = comenzi.getSelectedColumnCount();
				System.out.println(row3 + col3);
			}
		});
		comenzi.setModel(dtm3);
		comenzi.setBounds(10, 47, 414, 114);
		CHEF.add(comenzi);
		
		
		
		DefaultTableModel dtm2;
		dtm2 = new DefaultTableModel(header2, 0);
		OrderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row2 = OrderTable.getSelectedRow();
				col2 = OrderTable.getSelectedColumnCount();
				System.out.println(row2 + col2);
			}
		});
		OrderTable.setModel(dtm2);
		
		OrderTable.setBounds(10, 68, 414, 93);
		setJTableColumnsWidth(OrderTable, 300, 10, 5, 35, 50);
		WAITER.add(OrderTable);
		
		textField_2 = new JTextField();
		textField_2.setBounds(219, 11, 46, 20);
		WAITER.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Table:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(163, 14, 46, 14);
		WAITER.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.WHITE);
		textField_3.setForeground(Color.DARK_GRAY);
		textField_3.setBounds(219, 33, 205, 20);
		WAITER.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Order Items:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBackground(Color.WHITE);
		lblNewLabel_5.setForeground(Color.DARK_GRAY);
		lblNewLabel_5.setBounds(140, 36, 68, 14);
		WAITER.add(lblNewLabel_5);
		
		
		/////////
		DefaultTableModel dtm;
		dtm = new DefaultTableModel(header, 0);
		table.setBounds(10, 68, 414, 93);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				col = table.getSelectedColumnCount();
				System.out.println(row + col);
			}
		});
		table.setModel(dtm);
		
		ADMINISTRATOR.add(table);
		
		JButton btnNewButton_5 = new JButton("Add Item");
		btnNewButton_5.setBounds(26, 0, 89, 23);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String da = textField_1.getText();
				String[] test = edit(da);
				String nume = test[0];
				int pret = Integer.parseInt(test[1]);
				available.add(nume);
				meniu.add(new Items(nume, pret));
				dtm.setRowCount(0);
				for(int i = 0; i<meniu.size(); i++)
				{
					Object[] objs = {meniu.get(i).nume, meniu.get(i).pret};
					dtm.addRow(objs);
					
				}

				Clear();
				
			}
		});
	
		btnNewButton_5.setForeground(Color.DARK_GRAY);
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		ADMINISTRATOR.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Del Item");
		btnNewButton_6.setBounds(304, 0, 89, 23);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.removeRow(row);
				meniu.remove(row);
				dtm.setRowCount(0);
				for(int i = 0; i<meniu.size(); i++)
				{
					Object[] objs = {meniu.get(i).nume, meniu.get(i).pret};
					dtm.addRow(objs);
				}

				Clear();

			}
		});
		btnNewButton_6.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton_6.setForeground(Color.DARK_GRAY);
		btnNewButton_6.setBackground(Color.WHITE);
		ADMINISTRATOR.add(btnNewButton_6);
		
		
		JButton btnNewButton_7 = new JButton("Update Price");
		btnNewButton_7.setBounds(151, 0, 115, 23);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String da = textField_1.getText();
				int newprice = Integer.parseInt(da);
				System.out.println(newprice);
				meniu.get(row).pret = newprice;
				dtm.setRowCount(0);
				for(int i = 0; i<meniu.size(); i++)
				{
					Object[] objs = {meniu.get(i).nume, meniu.get(i).pret};
					dtm.addRow(objs);
				}

				Clear();
			}
		});
		btnNewButton_7.setForeground(Color.DARK_GRAY);
		btnNewButton_7.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton_7.setBackground(Color.WHITE);
		ADMINISTRATOR.add(btnNewButton_7);

	
		JButton btnNewButton_8 = new JButton("Comnada Noua!");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_8.setVisible(false);
			}
		});
		btnNewButton_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_8.setForeground(Color.RED);
		btnNewButton_8.setBackground(Color.LIGHT_GRAY);
		btnNewButton_8.setBounds(262, 11, 142, 25);
		btnNewButton_8.setVisible(false);
		CHEF.add(btnNewButton_8);
		
		JButton btnNewButton_4 = new JButton("Generate Bill");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prod;
				int total = 0;

				prod = orders.get(row2).mancare.toString();
				
				String[] fiecare = edit(prod);
				
				for(int i = 0; i< fiecare.length; i++)
				{
					for(int j = 0; j< meniu.size(); j++)
					{

						if(meniu.get(j).nume.equals(fiecare[i]))
						{
							total += meniu.get(j).pret;
						}
					}
				}
			    try{
		
			        File file = new File("bill.txt");
			        file.createNewFile();
			        FileWriter fw = new FileWriter(file);
			        BufferedWriter bw = new BufferedWriter(fw);
			        bw.write("Id comanda: " + orders.get(row2).OrderId + "\n");
			        bw.write("Table: " + orders.get(row2).Table + "\n");
			        bw.write("Data si ora: " + orders.get(row2).data + "\n");
			        bw.write("Produse: " + orders.get(row2).mancare + "\n");
			        bw.write("Pret total: " + total);
			        bw.flush();
			        bw.close();

			    }catch(IOException e1){
			    e1.printStackTrace();
			    }
			}
		});
		btnNewButton_4.setForeground(Color.DARK_GRAY);
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setBounds(10, 32, 110, 23);
		WAITER.add(btnNewButton_4);
		
		
		JButton btnNewButton_3 = new JButton("Add Order");
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int id =Randomnr(100, 999);
				int Table = Integer.parseInt(textField_2.getText());
				String food = textField_3.getText();
				String[] test2 = edit(food);
				for(int j = 0; j<test2.length; j++)
				{
					comanda.add(test2[j]);
				}
				String time = new SimpleDateFormat("HH:mm:ss,dd.MM.yyyy").format(new Date());
				
				
			
				if(available.containsAll(comanda) == true)
				{
					orders.add(new Order(id, Table, time, food));
					btnNewButton_8.setVisible(true);
					dtm2.setRowCount(0);
					dtm3.setRowCount(0);
					for(int i = 0; i<orders.size(); i++)
					{
						Object[] objs = {orders.get(i).OrderId, orders.get(i).Table, orders.get(i).data, orders.get(i).mancare};
						dtm2.addRow(objs);
						Object[] objs2 = {orders.get(i).OrderId, orders.get(i).mancare};
						dtm3.addRow(objs2);
						
					}
					
					
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Unul din felurile de mancare nu se afla in meniu");
				}
				
				comanda.clear();
				
				

			
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(10, 10, 110, 23);
		WAITER.add(btnNewButton_3);
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Comenzi:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setBounds(149, 11, 123, 25);
		CHEF.add(lblNewLabel_2);
		
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("RESTAURANT MANAGEMENT SYSTEM");
		lblNewLabel.setBounds(65, 11, 295, 18);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ALEGE UN ROL");
		lblNewLabel_1.setBounds(168, 40, 87, 16);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("WAITER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WAITER.setVisible(true);
				ADMINISTRATOR.setVisible(false);
				CHEF.setVisible(false);
			}
		});
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnNewButton.setBounds(54, 60, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ADMIN");
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WAITER.setVisible(false);
				ADMINISTRATOR.setVisible(true);
				CHEF.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnNewButton_1.setBounds(155, 60, 117, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CHEF");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WAITER.setVisible(false);
				ADMINISTRATOR.setVisible(false);
				CHEF.setVisible(true);
			}
		});
		btnNewButton_2.setForeground(Color.DARK_GRAY);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnNewButton_2.setBounds(284, 60, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
