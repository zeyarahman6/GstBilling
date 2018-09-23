package bs;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import javax.swing.border.LineBorder;

public class EditBox extends JFrame {
	
	Connection con;

	Connection com;
	EditBox frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JFormattedTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTable table_3;
	private JTextField textField_2;
	private JTable table_1;
	private JTextField tempquantity;
	private JButton btnUpdate;
	private JTextField tempserial;
	public void run() {
		try {
		    frame = new EditBox();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public EditBox() {
		LoginPage c = new LoginPage();
        con = c.sharedb();
        com=con;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditBox.class.getResource("/archive/invoice-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 11, 1381, 727);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70,130,150));
		contentPane.setBorder(new LineBorder(new Color(255, 255, 102)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
	    panel_2.setBackground(new Color(70,130,150));
	    contentPane.add(panel_2);
	    panel_2.setLayout(null);
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(10, 108, 575, 396);
	    contentPane.add(scrollPane_1);
	    
	    table_1 = new JTable();
	    table_1.setSelectionBackground(new Color(255,255,102));
	    table_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    table_1.addMouseListener(new MouseAdapter()
	    {

	      public void mouseClicked(MouseEvent e)
	      {
	        int row = table_1.getSelectedRow();
	        String sid = table_1.getModel().getValueAt(row, 0).toString();
	        

	        try
	        {
	          String sql = "select i_no ,p_name,hsn,serial_no ,p_qty,p_price,p_disc ,p_amt , cgst, sgst, total from invoicetable where i_no='" + sid + "'";
	          
	          PreparedStatement pst = com.prepareStatement(sql);
	          ResultSet rs = pst.executeQuery();
	          table_3.setModel(DbUtils.resultSetToTableModel(rs));
	          
	          pst.close();
	          rs.close();

	        }
	        catch (SQLException e1)
	        {

	          e1.printStackTrace();
	          JOptionPane.showMessageDialog(null, e1);

	        }
	        

	      }
	      

	    });
	    table_1.setModel(new DefaultTableModel(
	    	new Object[][] {
	    	},
	    	new String[] {
	    	}
	    ));
	    scrollPane_1.setViewportView(table_1);
	    
	    JButton btnLoadDetaila = new JButton("Load Details");
	    btnLoadDetaila.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
	    btnLoadDetaila.setForeground(Color.WHITE);
	    btnLoadDetaila.setBackground(new Color(70, 130, 150));
	    btnLoadDetaila.setFont(new Font("Segoe Print", Font.BOLD, 15));
	    btnLoadDetaila.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent arg0)
	      {
	        try {
	          String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,address,total_items,grand_total from customers order by date desc, time desc";
	          
	          PreparedStatement pst = com.prepareStatement(sql);
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
	        




	        table_3.setModel(new DefaultTableModel(
	          new Object[0][], 
	          
	          new String[] {
	          "I_NO", "P_Name","HSN","Serial No", "P_Qty", "P_Price", "P_Disc", "P_Amt" , "CGST", "SGST", "TOTAL"}));



	      }
	      



	    });
	    btnLoadDetaila.setBounds(10, 64, 132, 33);
	    Image loadimg = new ImageIcon(getClass().getResource("/folder-invoices.png")).getImage();
	    btnLoadDetaila.setIcon(new ImageIcon(loadimg));
	  
	    contentPane.add(btnLoadDetaila);
	    
	    JDateChooser dateChooser = new JDateChooser();
	    dateChooser.getCalendarButton().addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e) {}

	    });
	    dateChooser.setBounds(586, 35, 113, 20);
	    contentPane.add(dateChooser);
	    
	    dateChooser.setDateFormatString("dd-MM-yyy");
	    dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    
	    JLabel searchby = new JLabel("Search By Invoice:");
	    searchby.setForeground(Color.WHITE);
	    searchby.setFont(new Font("Segoe Print", Font.BOLD, 15));
	    searchby.setBounds(167, 31, 147, 27);
	    contentPane.add(searchby);
	    
	    textField_2 = new JTextField();
	    textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_2.setBackground(new Color(255, 255, 102));
	    textField_2.addKeyListener(new KeyAdapter()
	    {
	      public void keyPressed(KeyEvent e)
	      {
	        if (e.getKeyCode() == 10)
	        {
	          try
	          {
	            int i = Integer.parseInt(textField_2.getText());
	            String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,address,total_items,grand_total from  customers  where INvoice_no='" + i + "' ";
	            
	            PreparedStatement pst = com.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();
	            table_1.setModel(DbUtils.resultSetToTableModel(rs));
	            
	            pst.close();
	            rs.close();
	            
	            textField_2.setText("");
	          }
	          catch (SQLException e1)
	          {
	            e1.printStackTrace();
	            JOptionPane.showMessageDialog(null, e1);
	          }
	          



	          table_3.setModel(new DefaultTableModel(
	            new Object[0][], 
	            
	            new String[] {
	            "Invoice", "P_Name","HSN","Serial No", "P_Qty", "P_Price", "P_Disc", "P_Amt" , "CGST", "SGST", "TOTAL"}));

	        }
	        

	      }
	      


	    });
	    textField_2.setBounds(315, 35, 94, 20);
	    contentPane.add(textField_2);
	    textField_2.setColumns(10);
	    
	    JLabel lblSearchByDate = new JLabel("Search By Date:");
	    lblSearchByDate.setForeground(Color.WHITE);
	    lblSearchByDate.setFont(new Font("Segoe Print", Font.BOLD, 15));
	    lblSearchByDate.setBounds(449, 31, 132, 27);
	    contentPane.add(lblSearchByDate);
	    
	    JButton button = new JButton("Load");
	    button.setForeground(Color.WHITE);
	    button.setFont(new Font("Segoe Print", Font.BOLD, 12));
	    button.setBackground(new Color(70, 130, 150));
	    button.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
	    button.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent arg0) {
	        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	        String s = df.format(dateChooser.getDate());
	        

	        try
	        {
	          String sql = "\r\nselect INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,address,total_items,grand_total  from  customers   where date=parsedatetime(?, 'dd-MM-yyyy') order by date asc,time desc";
	          
	          PreparedStatement pst = com.prepareStatement(sql);
	          
	          pst.setString(1, s);
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
	        



	        table_3.setModel(new DefaultTableModel(
	          new Object[0][], 
	          
	          new String[] {
	          "Invoice", "P_Name","HSN","Serial No", "P_Qty", "P_Price", "P_Disc", "P_Amt" , "CGST", "SGST", "TOTAL"}));


	      }
	      



	    });
	    button.setBounds(635, 70, 64, 20);
	    contentPane.add(button);
	    
	    final JDateChooser dateChooser_1 = new JDateChooser();
	    dateChooser_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    dateChooser_1.setDateFormatString("dd-MM-yyy");
	    dateChooser_1.setBounds(895, 35, 102, 20);
	    contentPane.add(dateChooser_1);
	    
	    final JDateChooser dateChooser_2 = new JDateChooser();
	    dateChooser_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    dateChooser_2.setDateFormatString("dd-MM-yyy");
	    dateChooser_2.setBounds(1062, 35, 139, 20);
	    contentPane.add(dateChooser_2);
	    
	    JButton button_2 = new JButton("Load");
	    button_2.setForeground(Color.WHITE);
	    button_2.setFont(new Font("Segoe Print", Font.BOLD, 12));
	    button_2.setBackground(new Color(70, 130, 150));
	    button_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
	    button_2.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	        String s1 = df.format(dateChooser_1.getDate());
	        String s2 = df.format(dateChooser_2.getDate());
	        


	        try
	        {
	          String sql = "select INVOICe_no,TO_CHAR(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,address,total_items,grand_total  from  customers where   date  between parsedatetime(?, 'dd-MM-yyyy') and parsedatetime(?, 'dd-MM-yyyy') order by date desc,time desc";
	          PreparedStatement pst = com.prepareStatement(sql);
	          
	          pst.setString(1, s1);
	          pst.setString(2, s2);
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
	        



	        table_3.setModel(new DefaultTableModel(
	          new Object[0][], 
	          
	          new String[] {
	          "Invoice", "P_Name", "HSN","Serial No","P_Qty", "P_Price", "P_Disc", "P_Amt", "CGST", "SGST", "TOTAL"}));

	      }
	      


	    });
	    button_2.setBounds(1137, 70, 64, 20);
	    contentPane.add(button_2);
	    
	    JLabel lblSearchBwDate = new JLabel("Search B/W Date:");
	    lblSearchBwDate.setForeground(Color.WHITE);
	    lblSearchBwDate.setFont(new Font("Segoe Print", Font.BOLD, 15));
	    lblSearchBwDate.setBounds(738, 31, 147, 27);
	    contentPane.add(lblSearchBwDate);
	    
	    JLabel lblAnd = new JLabel("AND");
	    lblAnd.setForeground(Color.WHITE);
	    lblAnd.setFont(new Font("Segoe Print", Font.BOLD, 15));
	    lblAnd.setBounds(1007, 31, 45, 27);
	    contentPane.add(lblAnd);
	    
	    JPanel panel_7 = new JPanel();
	    panel_7.setBackground(new Color(70, 130, 150));
	    panel_7.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
	    panel_7.setBounds(152, 11, 268, 87);
	    contentPane.add(panel_7);
	    
	    JPanel panel_8 = new JPanel();
	    panel_8.setBackground(new Color(70, 130, 150));
	    panel_8.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
	    panel_8.setBounds(430, 11, 284, 87);
	    contentPane.add(panel_8);
	    
	    JPanel panel_9 = new JPanel();
	    panel_9.setBackground(new Color(70, 130, 150));
	    panel_9.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
	    panel_9.setBounds(724, 11, 511, 87);
	    contentPane.add(panel_9);
	    
	    JScrollPane scrollPane_3 = new JScrollPane();
	    scrollPane_3.setBounds(591, 108, 665, 396);
	    contentPane.add(scrollPane_3);
	    
	    table_3 = new JTable();
	    table_3.setSelectionBackground(new Color(255,255,102));
	    table_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    table_3.addMouseListener(new MouseAdapter()
	    {
	      public void mouseClicked(MouseEvent me)
	      {
	        try
	        {
	          int row = table_3.getSelectedRow();
	          String sid = table_3.getModel().getValueAt(row, 2).toString();
	        //  String sqty=table_3.getModel().getValueAt(row, 3).toString();
	          String id=table_3.getModel().getValueAt(row, 0).toString();
	          
	          String query = "select P_name,P_qty,p_price,p_disc,hsn,I_NO,serial_no from invoicetable where hsn='" + sid + "' and i_no='"+id+"' ";
	          PreparedStatement pst = con.prepareStatement(query);
	          ResultSet rs = pst.executeQuery();
	          while (rs.next())
	          {
	            textField_1.setText(rs.getString("P_Name"));
	            textField_6.setText(rs.getString("P_Qty"));
	            textField_4.setText(rs.getString("P_Price"));
	            textField_5.setText(rs.getString("P_Disc"));
	            textField_3.setText(rs.getString("HSN"));
	            textField.setText(rs.getString("I_NO"));

	            textField_8.setText(rs.getString("serial_no"));
	            tempserial.setText(rs.getString("serial_no"));
	          }
	          textField_4.requestFocus();
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	        
	      }
	      
	    });
	    table_3.setModel(new DefaultTableModel(
	      new Object[0][], 
	      
	      new String[] {
	      "Invoice", "P_Name","HSN", "P_Qty", "P_Price", "P_Disc", "P_Amt" ,"GST", "CGST", "SGST", "TOTAL"}));
	    

	    scrollPane_3.setViewportView(table_3);
	    
	    textField_1 = new JTextField();
	    textField_1.setEditable(false);
	    textField_1.setBackground(Color.WHITE);
	    textField_1.addKeyListener(new KeyAdapter()
	    {
	      public void keyPressed(KeyEvent e)
	      {
	        if (e.getKeyCode() == 10)
	        {



	          textField_9.requestFocus();
	        }
	        
	      }
	      

	    });
	    textField_1.setBounds(1268, 167, 80, 20);
	    contentPane.add(textField_1);
	    textField_1.setColumns(10);
	    
	    textField_6 = new JTextField();
	    textField_6.setEditable(false);
	    textField_6.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_6.addKeyListener(new KeyAdapter()
	    {

	      public void keyPressed(KeyEvent e)
	      {
	                
	    	  if (e.getKeyCode() == 10)
	          {
	    	    	


	        	 
	        	 
	           
	      	   
	         }
	          
	         	          


	      }
	      

	    });
	    textField_6.setColumns(10);
	    textField_6.setBounds(1268, 304, 80, 20);
	    contentPane.add(textField_6);
	    
	    textField_4 = new JTextField();
	    textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_4.addKeyListener(new KeyAdapter()
	    {

	      public void keyPressed(KeyEvent e)
	      {

	        if (e.getKeyCode() == 10)
	        {



	          textField_5.requestFocus();
	        }
	        

	               

	      }
	      

	    });
	    textField_4.setColumns(10);
	    textField_4.setBounds(1268, 236, 80, 20);
	    contentPane.add(textField_4);
	    
	    

	   
	    textField_5 = new JFormattedTextField();
	    textField_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_5.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField_5.addKeyListener(new KeyAdapter()
	    {
	      public void keyPressed(KeyEvent e)
	      {
	        if (e.getKeyCode() == 10)
	        {
	        
	        	
	        	
		      	  
	        	 try
	             {

	               double p = Double.parseDouble(textField_4.getText());
	               int q = Integer.parseInt(textField_6.getText());
	               double d = Double.parseDouble(textField_5.getText());
	                      p=p-d;
	               double sum =  (p*100/118 );
	                      sum =sum*q;
	               double newsum = Math.round(sum * 100.0D) / 100.0D;
	               textField_7.setText(Double.toString(newsum));
	               
	               double gst;
	               gst= (9 * newsum/100.0D);
	               gst=Math.round(gst * 100.0D) / 100.0D;
	               textField_9.setText(Double.toString(gst));
	               textField_10.setText(Double.toString(gst));
	              
	               newsum=newsum+(2*gst);
	               newsum = Math.round(newsum * 100.0D) / 100.0D;
	               textField_11.setText(Double.toString(newsum));
	               btnUpdate.requestFocus();
	   	        

	             }
	             catch (Exception e2)
	             {

	               JOptionPane.showMessageDialog(null, "Enter valid number");
	               textField_4.requestFocus();

	             }

	        	 
	        	
	        	
	        	
	        }
	        








	        
	      }
	      

	    });
	    textField_5.setColumns(10);
	    textField_5.setBounds(1268, 271, 80, 20);
	    contentPane.add(textField_5);
	    
	    textField_7 = new JTextField();
	    textField_7.setEditable(false);
	    textField_7.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_7.addKeyListener(new KeyAdapter()
	    {

	      public void keyPressed(KeyEvent e)
	      {
	        if (e.getKeyCode() == 38)
	        {
	          textField_5.requestFocus();
	        }
	        
	      }
	      

	    });
	    textField_7.setColumns(10);
	    textField_7.setBounds(1268, 335, 80, 20);
	    contentPane.add(textField_7);
	    
	    JLabel lblPname = new JLabel("P_NAME");
	    lblPname.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPname.setForeground(Color.WHITE);
	    lblPname.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblPname.setBounds(1254, 152, 80, 14);
	    contentPane.add(lblPname);
	    
	    JLabel lblP = new JLabel("P_DISC");
	    lblP.setHorizontalAlignment(SwingConstants.CENTER);
	    lblP.setForeground(Color.WHITE);
	    lblP.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblP.setBounds(1250, 256, 70, 14);
	    contentPane.add(lblP);
	    
	    JLabel lblPprice = new JLabel("P_PRICE");
	    lblPprice.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPprice.setForeground(Color.WHITE);
	    lblPprice.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblPprice.setBounds(1254, 222, 74, 14);
	    contentPane.add(lblPprice);
	    
	    JLabel lblPdisc = new JLabel("P_QTY");
	    lblPdisc.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPdisc.setForeground(Color.WHITE);
	    lblPdisc.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblPdisc.setBounds(1254, 291, 64, 14);
	    contentPane.add(lblPdisc);
	    
	    
	    
	    
	    
	    
	    btnUpdate = new JButton("Update");
	    btnUpdate.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
	    btnUpdate.setForeground(Color.WHITE);
	    btnUpdate.setBackground(new Color(70, 130, 150));
	    btnUpdate.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent arg0) {
	        int row = table_3.getSelectedRow();
	       
	        double d = Double.parseDouble(table_3.getModel().getValueAt(row, 10).toString());
	        
	        
	        
	        	
	        	double gt = 0.0D;
	          
	   try {
		   
		   String query = "update invoicetable set P_Qty='" + textField_6.getText() + "'," + 
		            " P_price='" + textField_4.getText() + "' , P_Disc='" + textField_5.getText() + "' ,P_Amt='" + textField_7.getText() + "',serial_no='"+textField_8.getText()+"', cgst='"+textField_9.getText()+"', sgst='"+textField_10.getText()+"', total='"+textField_11.getText()+"' where i_no ='" + textField.getText() + "' and serial_no='"+tempserial.getText()+"' and hsn='"+textField_3.getText()+"'";
		          PreparedStatement pst = con.prepareStatement(query);
		          pst.execute();
		          pst.close();
		          

		   
		   
		   
		   
		   
		   

		   
		   
		   
	            String query1 = "select  grand_total from customers where invoice_no='" + textField.getText() + "' ";
	            PreparedStatement pst1 = con.prepareStatement(query1);
	            ResultSet rs = pst1.executeQuery();
	            while (rs.next())
	            {

	              gt = Double.parseDouble(rs.getString("GRAND_TOTAL"));
	            
	            }
	            
	            pst1.close();
	            rs.close();
	            



	            gt -= d;
	           
	            double amt = Double.parseDouble(textField_11.getText());
	            gt = Math.round((gt + amt) * 100.0D) / 100.0D;
		         





	            String sql = "update customers set grand_total='" + gt + "'  where invoice_no='" + textField.getText() + "'";
	            
	            PreparedStatement pst2 = com.prepareStatement(sql);
	            pst2.execute();
	            pst2.close();
	            
	             
	            
	            

	            String sql1 = "select i_no ,p_name,hsn,serial_no,p_qty,p_price,p_disc ,p_amt , cgst, sgst, total from invoicetable where i_no='" + textField.getText() + "'";
	            
	            PreparedStatement pst3 = com.prepareStatement(sql1);
	            ResultSet rs1 = pst3.executeQuery();
	            table_3.setModel(DbUtils.resultSetToTableModel(rs1));
	            
	            pst3.close();
	            rs1.close();
	            


	         

	            String sql2 = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,address,total_items,grand_total from customers where invoice_no='" + textField.getText() + "'";
	            
	            PreparedStatement pt = com.prepareStatement(sql2);
	            ResultSet r = pt.executeQuery();
	            table_1.setModel(DbUtils.resultSetToTableModel(r));
	            
	            pt.close();
	            r.close();
	            textField_1.setText("");
	            textField_6.setText("");
	            textField_4.setText("");
	            textField_5.setText("");
	            textField_7.setText("");
	            textField_3.setText("");
	            textField_9.setText(""); textField_8.setText("");
	            textField_10.setText("");
	            textField_11.setText("");
	            textField.setText("");
	            tempquantity.setText("");

	            tempserial.setText("");
	            
	   } catch(Exception e) {JOptionPane.showMessageDialog(null, e);}
	        	
	        	
	        }
	        


	      


	    });
	    btnUpdate.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    btnUpdate.setBounds(1268, 493, 80, 20);
	    contentPane.add(btnUpdate);
	    
	    JLabel lblReturnnbox = new JLabel("EDIT");
	    lblReturnnbox.setHorizontalAlignment(SwingConstants.CENTER);
	    lblReturnnbox.setForeground(Color.WHITE);
	    lblReturnnbox.setBounds(1268, 530, 80, 20);
	    lblReturnnbox.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
	    contentPane.add(lblReturnnbox);
	    
	    JLabel lblReturnnbox_1 = new JLabel("BOX");
	    lblReturnnbox_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblReturnnbox_1.setForeground(Color.WHITE);
	    lblReturnnbox_1.setBounds(1268, 547, 80, 20);
	    lblReturnnbox_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 14));
	    contentPane.add(lblReturnnbox_1);
	    
	    JLabel lblPamt_1 = new JLabel("P_AMT");
	    lblPamt_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblPamt_1.setForeground(Color.WHITE);
	    lblPamt_1.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblPamt_1.setBounds(1254, 323, 64, 14);
	    contentPane.add(lblPamt_1);
	    
	    JLabel lblGst = new JLabel("Serial No");
	    lblGst.setHorizontalAlignment(SwingConstants.CENTER);
	    lblGst.setForeground(Color.WHITE);
	    lblGst.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblGst.setBounds(1265, 355, 64, 14);
	    contentPane.add(lblGst);
	    
	    JLabel lblCgst = new JLabel("CGST");
	    lblCgst.setHorizontalAlignment(SwingConstants.CENTER);
	    lblCgst.setForeground(Color.WHITE);
	    lblCgst.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblCgst.setBounds(1254, 389, 64, 14);
	    contentPane.add(lblCgst);
	    
	    JLabel lblSgst = new JLabel("SGST");
	    lblSgst.setHorizontalAlignment(SwingConstants.CENTER);
	    lblSgst.setForeground(Color.WHITE);
	    lblSgst.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblSgst.setBounds(1254, 423, 64, 14);
	    contentPane.add(lblSgst);
	    
	    JLabel lblTotal = new JLabel("TOTAL");
	    lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblTotal.setForeground(Color.WHITE);
	    lblTotal.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblTotal.setBounds(1255, 456, 54, 14);
	    contentPane.add(lblTotal);
	    
	    textField_8 = new JTextField();
	    textField_8.setForeground(Color.BLACK);
	    textField_8.setBackground(new Color(255,255,102));
	    textField_8.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_8.setColumns(10);
	    textField_8.setBounds(1268, 369, 80, 20);
	    contentPane.add(textField_8);
	    
	    textField_9 = new JTextField();
	    textField_9.setEditable(false);
	    textField_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_9.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField_9.setColumns(10);
	    textField_9.setBounds(1268, 403, 80, 20);
	    contentPane.add(textField_9);
	    
	    textField_10 = new JTextField();
	    textField_10.setEditable(false);
	    textField_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_10.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField_10.setColumns(10);
	    textField_10.setBounds(1268, 436, 80, 20);
	    contentPane.add(textField_10);
	    
	    textField_11 = new JTextField();
	    textField_11.setEditable(false);
	    textField_11.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_11.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField_11.setColumns(10);
	    textField_11.setBounds(1268, 470, 80, 20);
	    contentPane.add(textField_11);
	    
	    JLabel lblHsnsac_1 = new JLabel("HSN/SAC");
	    lblHsnsac_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblHsnsac_1.setForeground(Color.WHITE);
	    lblHsnsac_1.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblHsnsac_1.setBounds(1254, 188, 80, 14);
	    contentPane.add(lblHsnsac_1);
	    
	    textField_3 = new JTextField();
	    textField_3.setEditable(false);
	    textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField_3.setColumns(10);
	    textField_3.setBounds(1268, 203, 80, 20);
	    textField_3.setBackground(new Color(255,255,102));
	    contentPane.add(textField_3);
	    
	    textField = new JTextField();
	    textField.setEditable(false);
	    textField.setBackground(Color.WHITE);
	    textField.setHorizontalAlignment(SwingConstants.RIGHT);
	    textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
	    textField.setColumns(10);
	    textField.setBounds(1268, 132, 80, 20);
	    contentPane.add(textField);
	    
	    JLabel lblInvoice = new JLabel("INVOICE_NO");
	    lblInvoice.setForeground(Color.WHITE);
	    lblInvoice.setFont(new Font("Segoe Print", Font.BOLD, 11));
	    lblInvoice.setBounds(1269, 115, 80, 14);
	    contentPane.add(lblInvoice);
	    

	    JPanel panel_11 = new JPanel();
	    panel_11.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)));
	    panel_11.setBackground(new Color(70, 130, 150));
	    panel_11.setBounds(1260, 107, 93, 470);
	    contentPane.add(panel_11); 
	    
	    
	    
	    tempquantity = new JTextField();
	    tempquantity.setBorder(null);
	    tempquantity.setBackground(new Color(70,130,150));
	    tempquantity.setBounds(1289, 57, 6, 20);
	    contentPane.add(tempquantity);
	    tempquantity.setColumns(10);
	    
	    JLabel lblHandleWithCare = new JLabel("HANDLE  WITH  CARE !!");
	    lblHandleWithCare.setHorizontalAlignment(SwingConstants.CENTER);
	    lblHandleWithCare.setForeground(Color.WHITE);
	    lblHandleWithCare.setFont(new Font("Segoe Print", Font.BOLD, 30));
	    lblHandleWithCare.setBounds(250, 515, 635, 52);
	    contentPane.add(lblHandleWithCare);
	    
	    tempserial = new JTextField();
	    tempserial.setBorder(null);
	    tempserial.setBackground(new Color(70,130,150));
	    tempserial.setBounds(1262, 11, 86, 20);
	    contentPane.add(tempserial);
	    tempserial.setColumns(10);

		
		
	}
}



