package bs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;

public class CancelInvoice extends JFrame {

	private JPanel contentPane;
	CancelInvoice frame;
	private JTable table_1;
	Connection con;
	private JTable table_2;
	private JScrollPane scrollPane_1;
	private JTextField textField;
	private JLabel lblDate;
	private JLabel lblTime;
	/**
	 * Launch the application.
	 */
			public void run() {
				try {
				    frame = new CancelInvoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			  protected void currentdate()
			  {
			    Thread Clock = new Thread()
			    {

			      public void run()
			      {

			        try
			        {

			          for (;;)
			          {
			            Date d = new Date();
			            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
			           lblDate.setText(s.format(d));
			            


			            SimpleDateFormat s1 = new SimpleDateFormat("HH:mm:ss");
			            
			            lblTime.setText(s1.format(d));
			          
			            

			            sleep(1000L);
			          }
			        }
			        catch (Exception e)
			        {
			          e.printStackTrace();
			        }
			        
			      }
			      
			    };
			    Clock.start();
			  }
			  

	/**
	 * Create the frame.
	 */
	public CancelInvoice() {
		
		
		
		
		LoginPage c = new LoginPage();
        con = c.sharedb();
		setIconImage(Toolkit.getDefaultToolkit().getImage(CancelInvoice.class.getResource("/archive/invoice-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 11, 1355, 715);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70,130,150));
		contentPane.setBorder(new LineBorder(new Color(255,255,102)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 585, 396);
		contentPane.add(scrollPane);
		
        	  table_1 = new JTable();
        	  table_1.addMouseListener(new MouseAdapter() {
        	  	@Override
        	  	public void mouseClicked(MouseEvent arg0) {
        	  		
        	  		int row = table_1.getSelectedRow();
    		        String sid = table_1.getModel().getValueAt(row, 0).toString();
    		        

    		        try
    		        {
    		          String sql = "select i_no ,p_name,hsn,serial_No,p_qty,p_price,p_disc ,p_amt , cgst, sgst, total from canvoicetable where i_no='" + sid + "'";
    		          
    		          PreparedStatement pst = con.prepareStatement(sql);
    		          ResultSet rs = pst.executeQuery();
    		          table_2.setModel(DbUtils.resultSetToTableModel(rs));
    		          
    		          pst.close();
    		          rs.close();

    		        }
    		        catch (Exception e1)
    		        {

    		          e1.printStackTrace();
    		          JOptionPane.showMessageDialog(null, e1);

    		        }

        	  		
        	  	}
        	  });
        	  table_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		     table_1.setSelectionBackground(new Color(255,255,102));
		    
		   		    table_1.setModel(new DefaultTableModel(
		    	new Object[][] {
		    	},
		    	new String[] {
		    	}
		    ));
		    scrollPane.setViewportView(table_1);
		    
		    scrollPane_1 = new JScrollPane();
		    scrollPane_1.setBounds(605, 150, 724, 396);
		    contentPane.add(scrollPane_1);
		    
		    table_2 = new JTable();
		    scrollPane_1.setViewportView(table_2);
		    
		    JButton btnLoadCancelInvoices = new JButton("Cancelled Invoices");
		    btnLoadCancelInvoices.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		
		    		 try {
		    	          String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,address,total_items,grand_total from cansomers order by date desc, time desc";
		    	          
		    	          PreparedStatement pst = con.prepareStatement(sql);
		    	          ResultSet rs = pst.executeQuery();
		    	          table_1.setModel(DbUtils.resultSetToTableModel(rs));
		    	          
		    	          pst.close();
		    	          rs.close();

		    	        }
		    	        catch (SQLException e1)
		    	        {

		    	          e1.printStackTrace();
		    	          JOptionPane.showMessageDialog(null, e1);
		    	        }
		    		   table_2.setModel(new DefaultTableModel(
		    			          new Object[0][], 
		    			          
		    			          new String[] {
		    			          "I_NO", "P_Name","HSN","Serial No", "P_Qty", "P_Price", "P_Disc", "P_Amt" , "CGST", "SGST", "TOTAL"}));

		    		
		    		
		    	}
		    });
		    btnLoadCancelInvoices.setForeground(Color.WHITE);
		    btnLoadCancelInvoices.setFont(new Font("Segoe Print", Font.BOLD, 13));
		    btnLoadCancelInvoices.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
		    btnLoadCancelInvoices.setBackground(new Color(70, 130, 150));
		    btnLoadCancelInvoices.setBounds(10, 100, 182, 41);
		    Image imglogout = new ImageIcon(getClass().getResource("/folder-invoices-(1).png")).getImage();
		    btnLoadCancelInvoices.setIcon(new ImageIcon(imglogout));
		    
		    contentPane.add(btnLoadCancelInvoices);
		    
		    textField = new JTextField();
		    textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		    textField.addKeyListener(new KeyAdapter() {
		    	@Override
		    	public void keyPressed(KeyEvent arg0) {
		    		if(arg0.getKeyCode()==10)
		    		{
		    			
		    			 int action = JOptionPane.showConfirmDialog(null, "Cancel Invoice?", "Cancel!", 0);
		     	        if (action == 0)
		     	        {
		     	    
		    			
		    		try {
		    			int stock=0;
		    			long hsnl[]=new long[50];
		    			int qtyi[]=new int[50];
		    			String p_name[]=new String[50];
                        double p_price[]=new double[50];
		    			int count=0,i=0;
		    			String sql="select hsn, p_qty,p_price,p_name from invoicetable where i_no='"+textField.getText()+"'";
		    			  PreparedStatement pst = con.prepareStatement(sql);
		    			   ResultSet rs = pst.executeQuery();
		    	            while (rs.next())
		    	            {     
		    	            	  hsnl[count]=Long.parseLong(rs.getString("hsn"));
		    	            	  qtyi[count]=Integer.parseInt(rs.getString("p_qty"));
		    	            	  p_name[count]=rs.getString("p_name");
		    	            	  p_price[count]=Double.parseDouble(rs.getString("p_price"));
		    	            	count++;} 
		    	            
		    		for(i=0;i<count;i++)
		    		{       
		    	            sql="select qty from items where hsn='"+hsnl[i]+"' and item_name='"+p_name[i]+"'";
		    	            pst=con.prepareStatement(sql);
		    	            rs = pst.executeQuery();
		    	            if (rs.next())
		    	            {
		    	            	stock=Integer.parseInt(rs.getString("qty"));
		    	            	
		    	            
		    	            stock=stock+qtyi[i];
		    	             sql = "update items set qty='"+stock+"' where hsn='"+hsnl[i]+"' and item_name='"+p_name[i]+"' ";
		    	             pst=con.prepareStatement(sql);
		    	             pst.execute();
		    	             pst.close();
		    	             stock=0;
		    	            } else {
		    	            	try
		    	                {   
		    	                  String query = "INSERT INTO items (item_NAME,hsn,qty,rate) VALUES (?,?,?,?)";
		    	                  
		    	                  PreparedStatement pt = con.prepareStatement(query);
		    	                  
		    	                  String qtyzero="0";
		    	                  
		    	                  pt.setString(1, p_name[i]);
		    	                  
		    	                  pt.setString(2, Long.toString(hsnl[i]));
		    	                  pt.setString(3, qtyzero);
		    	                  p_price[i]= Math.round(p_price[i] * 100.0D) / 100.0D;
		    	                  pt.setString(4, Double.toString(p_price[i])); 
		    	                  


		    	                  pt.execute();
		    	                  
		    	                  pt.close();
		    	                 
		    	                  
		    	                  stock=stock+qtyi[i];
				    	             sql = "update items set qty='"+stock+"' where hsn='"+hsnl[i]+"' and item_name='"+p_name[i]+"'";
				    	           PreparedStatement  p=con.prepareStatement(sql);
				    	             p.execute();
				    	             p.close();
				    	             stock=0; 
				    	          
		    	                 
		    	                  
		    	                }
		    	                catch (Exception x) {
		    	                  x.printStackTrace();
		    	                  JOptionPane.showMessageDialog(null, x);
		    	                }

		    	            	
		    	                  }

		    		}
		    	            
		   		String query = "insert into cansomers select * from customers where  invoice_no='"+textField.getText()+"'";
		            
		             pst = con.prepareStatement(query);
		            pst.execute();
		              pst.close();
		              
		               query = "insert into canvoicetable select * from invoicetable where  i_no='"+textField.getText()+"'";
			            pst = con.prepareStatement(query);
			            pst.execute();
			              pst.close();
			              
			              
			              
			              
			              
			                query = "update cansomers set date= parsedatetime(?, 'dd-MM-yyyy') , time= parsedatetime(?, 'HH:mm:ss') where invoice_no= '"+textField.getText()+"' ";
                             pst = con.prepareStatement(query);
                            pst.setString(1, lblDate.getText());
			                pst.setString(2, lblTime.getText());

			                pst.execute();
			                pst.close(); 
			                
			               

			                query = "Delete from invoicetable where  i_no='"+textField.getText()+"'";
				            pst = con.prepareStatement(query);
				            pst.execute();
				            pst.close();
				            
				            
				            
                           textField.setText("");

				           
                           String sqll = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,address,total_items,grand_total from cansomers order by date desc, time desc";
 		    	          
 		    	          PreparedStatement pstl = con.prepareStatement(sqll);
 		    	          ResultSet rsl = pstl.executeQuery();
 		    	          table_1.setModel(DbUtils.resultSetToTableModel(rsl));
 		    	          
 		    	          pst.close();
 		    	          rs.close();

 		    	        		    		   table_2.setModel(new DefaultTableModel(
 		    			          new Object[0][], 
 		    			          
 		    			          new String[] {
 		    			          "I_NO", "P_Name","HSN","Serial No", "P_Qty", "P_Price", "P_Disc", "P_Amt" , "CGST", "SGST", "TOTAL"}));
                         
 		    	        		    		  Tasks t=new Tasks();
 		   		    	                  t.combobox_items();
			            
		    		} catch(Exception e) {JOptionPane.showMessageDialog(null, e);}
		            
		     	        }
		    		}
		    	}
		    });
		    textField.setBounds(478, 110, 96, 25);
		    contentPane.add(textField);
		    textField.setColumns(10);
		    
		    JLabel lblNewLabel = new JLabel("Enter Invoice no to Cancel:");
		    lblNewLabel.setForeground(Color.WHITE);
		    lblNewLabel.setFont(new Font("Segoe Print", Font.BOLD, 18));
		    lblNewLabel.setBounds(230, 110, 245, 23);
		    contentPane.add(lblNewLabel);
		    
		    lblDate = new JLabel("Date");
		    lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		    lblDate.setForeground(Color.WHITE);
		    lblDate.setFont(new Font("Segoe UI Black", Font.BOLD, 35));
		    lblDate.setBounds(1098, 11, 221, 30);
		    contentPane.add(lblDate);
		    
		    lblTime = new JLabel("Time");
		    lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		    lblTime.setForeground(Color.WHITE);
		    lblTime.setFont(new Font("Segoe UI Black", Font.BOLD, 35));
		    lblTime.setBounds(1137, 63, 182, 35);
		    contentPane.add(lblTime);
		    
		    JLabel lblCancelledInvoicesList = new JLabel("Cancelled Invoices List");
		    lblCancelledInvoicesList.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		    lblCancelledInvoicesList.setForeground(Color.WHITE);
		    lblCancelledInvoicesList.setFont(new Font("Segoe Print", Font.BOLD, 60));
		    lblCancelledInvoicesList.setBounds(270, 10, 723, 57);
		    Image cancel = new ImageIcon(getClass().getResource("/deletebills.png")).getImage();
		    lblCancelledInvoicesList.setIcon(new ImageIcon(cancel));
		   
		    contentPane.add(lblCancelledInvoicesList);
		    currentdate();
	}
}
