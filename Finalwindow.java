package newproject;

import java.awt.EventQueue;
import java.sql.*;


import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;


public class Finalwindow {

	private JFrame frame;
	private JTextField txtbookname;
	private JTextField txtbookid;
	private JTextField stock;
	private JTextField txtbid;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finalwindow window = new Finalwindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	/**
	 * Create the application.
	 */
	 public Finalwindow() {
		 initialize();
		 Connect();
		 table_load();
		 }
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	 
	public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/booktable", "root","");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	    }
	

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1055, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(384, 21, 7, 19);
		frame.getContentPane().add(formattedTextField);
		
		JPanel panel = new JPanel();
		panel.setBounds(342, 30, 10, 10);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BOOK SHOP");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(362, 34, 189, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 133, 520, 250);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("BOOK");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(24, 51, 123, 33);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Id");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(24, 114, 123, 33);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Stock");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_2.setBounds(24, 174, 123, 33);
		panel_1.add(lblNewLabel_2_2);
		
		txtbookname = new JTextField();
		txtbookname.setText("java");
		txtbookname.setBounds(208, 51, 231, 33);
		panel_1.add(txtbookname);
		txtbookname.setColumns(10);
		
		txtbookid = new JTextField();
		txtbookid.setText("8993");
		txtbookid.setBounds(208, 114, 231, 33);
		panel_1.add(txtbookid);
		txtbookid.setColumns(10);
		
		stock = new JTextField();
		stock.setText("20");
		stock.setBounds(208, 180, 231, 33);
		panel_1.add(stock);
		stock.setColumns(10);
		
		  
	
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookname, bookid,bstock;
				bookname = txtbookname.getText();
				bookid = txtbookid.getText();
				bstock =stock.getText();
				try {
				pst = con.prepareStatement("insert into booktable(txtbookname,txtbookid,stock)values(?,?,?)");
				pst.setString(1, bookname);
				pst.setString(2,bookid );
				pst.setString(3, bstock);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
				table_load();
				          
				txtbookname.setText("");
				txtbookid.setText("");
				stock.setText("");
				txtbookname.requestFocus();
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
			}
				 try
			     {
			    pst = con.prepareStatement("select * from bookid");
			    rs = pst.executeQuery();
			    table.setModel(DbUtils.resultSetToTableModel(rs));
			}
			     catch (SQLException e)
			     {
			     e.printStackTrace();
			  }
			}
			});
				
				
		
		btnNewButton.setBounds(41, 422, 113, 50);
		frame.getContentPane().add(btnNewButton);
		
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			return;
			}
		})
		;
		btnExit.setBounds(217, 422, 113, 50);
		frame.getContentPane().add(btnExit);
		
		JButton btnNewButton_1_1 = new JButton("REMOVE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	              String bid;
	              bid  = txtbid.getText();
	              try {
	              pst = con.prepareStatement("delete from book where id =?");
	                          pst.setString(1, bid);
	                          pst.executeUpdate();
	                          JOptionPane.showMessageDialog(null, "Record Removed!!!!!");
	                          table_load();
	                        
	                          txtbookname.setText("");
	                          txtbookid.setText("");
	                          stock.setText("");
	                          txtbookname.requestFocus();
	              }
	               
	                          catch (SQLException e1) {
	              e1.printStackTrace();
	              }
			}
		})
		;
		btnNewButton_1_1.setBounds(399, 422, 103, 50);
		frame.getContentPane().add(btnNewButton_1_1);
		
		table = new JTable();
		table.setBounds(640, 99, 1, 1);
		frame.getContentPane().add(table);
		
		table_1 = new JTable();
		table_1.setBounds(564, 107, 467, 365);
		frame.getContentPane().add(table_1);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
	/*	btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});*/
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String bookname,bookid,bstock,bid;
			bookname = txtbookname.getText();
			bookid = txtbookid.getText();
			bstock = stock.getText();
			bid  = txtbid.getText();
			try {
			pst = con.prepareStatement("update book set bookname= ?,bookid=?,stock=? where id =?");
			pst.setString(1, bookname);
			            pst.setString(2, bookid);
			            pst.setString(3, bstock);
			            pst.setString(4, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			          
			            txtbookname.setText("");
			            txtbookid.setText("");
			            stock.setText("");
			            txtbookname.requestFocus();
			}
			 
			            catch (SQLException e1) {
			e1.printStackTrace();
			}
			}
		});
		btnNewButton_1.setBounds(658, 513, 103, 50);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.addActionListener(new ActionListener() {
			
				public void keyReleased(KeyEvent e) {
					try {
					          
					            String id = txtbid.getText();
					 
					                pst = con.prepareStatement("select name,edition,price from book where id = ?");
					                pst.setString(1, id);
					                ResultSet rs = pst.executeQuery();
					 
					            if(rs.next()==true)
					            {
					              
					                String name = rs.getString(1);
					                String edition = rs.getString(2);
					                String price = rs.getString(3);
					                
					                txtbookname.setText(bookname);
					                txtboookid.setText(bookid);
					                stock.setText(stock);
					                
					                
					            }  
					            else
					            {
					             txtbookname.setText("");
					             txtbookid.setText("");
					                stock.setText("");
					                
					            }
					            
					 
					 
					        }
					catch (SQLException ex) {
					          
					        }
				
			}
		});
		
		btnNewButton_2.setBounds(791, 513, 85, 50);
		frame.getContentPane().add(btnNewButton_2);
	}
	private void table_load() {
		// TODO Auto-generated method stub
		 try
	     {
	    pst = con.prepareStatement("select * from bookid");
	    rs = pst.executeQuery();
	    table_1.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	}
}
	
