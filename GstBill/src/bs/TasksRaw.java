package bs;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import net.proteanit.sql.DbUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.Toolkit;
import javax.swing.SwingConstants;



























public class TasksRaw
  extends JFrame
{
  private JPanel contentPane;
  private JTextField P;
  private JTextField Q;
  private JFormattedTextField D;
  private JTextField A;
  private JTable table;
  Robot robot = null;
  Connection con = null; Connection com = null;
  String cid = null;
  String zid = null;
  JDateChooser dateChooser;
  private JTextField invoice_no;
  private JTextField c_name;
  private JTextField c_contact;
  private JTextField TA;
  private JTextField TI;
  private int ti = 0;
  private double ta = 0.0D;
  int count = 1;
  private JTable table_1;
  private JTextField date;
  JButton btnNewButton_2;
  private JTextField time;
  double disc = 0.0D;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField timepmam;
  JTextPane textPane;
  int sno = 0;
  public String tempdate;
  public String temptime;
  private JTable table_3;
  private JTextField textField_13;
  private JTable table_4;
  private JTextField textField;
  private JLabel lblCustomer;
  
  private JTable table_2;
  private JComboBox comboBox;
  private JComboBox comboBox_1;
  private JTextField textField_4;
  
    
  protected void combobox_items()
  {
    LoginPage c = new LoginPage();
    
    com = c.dbcon();
    

    comboBox.removeAllItems();
    comboBox_1.removeAllItems();
    try {
      String sql = "select DISTINCT item_name from k_items order by item_name asc";
      PreparedStatement pst = com.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      
      while (rs.next())
      {

        String id = rs.getString("item_name");
        comboBox.addItem(id);
        comboBox_1.addItem(id);
      }
      pst.close();
      rs.close();
      AutoCompleteDecorator.decorate(comboBox_1);
      AutoCompleteDecorator.decorate(comboBox);
    }
    catch (SQLException e1)
    {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(null, e1);
    }
  }

  protected static double fromCMTToPPI(double cm)
  {
 	 return toPPI(cm * 0.393700787);
 	 
  }
  protected static double toPPI(double inch)
  {
 	 
 	 return inch * 72d;
  }
   

  public void printinvoice(String ino)
  {
    company(ino);
    product(ino);
    Paper paper=new Paper();
    paper.setSize(fromCMTToPPI(21.0),fromCMTToPPI(29.7));
    paper.setImageableArea(fromCMTToPPI(2.0), fromCMTToPPI(0.1), fromCMTToPPI(21.0)-fromCMTToPPI(0.5), fromCMTToPPI(29.7)-fromCMTToPPI(0.1));
    
    PageFormat pageFormat=new PageFormat();
   // pageFormat.setOrientation(PageFormat.LANDSCAPE);
    pageFormat.setPaper(paper);
    PrinterJob pj= PrinterJob.getPrinterJob();
    pj.setPrintable(textPane.getPrintable(null, null), pageFormat);
    
    PageFormat pf= pj.pageDialog(pageFormat);
    if(pj.printDialog())
    {
    	try {
    		pj.print();
    	} catch (PrinterException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

  }
  

  public void product(String ino)
  {
	  
	  
	  SimpleAttributeSet sas = new SimpleAttributeSet();
	    StyleConstants.setBold(sas, true);
	    
	  
	  String tempta = null;
  String cname=null,phone=null,address=null;
  try {
    String sql = "select  cust_name,cust_phone,address,grand_total from k_customers where invoice_no='" + ino + "'";
    
    PreparedStatement pst = com.prepareStatement(sql);
    ResultSet rs = pst.executeQuery();
    
    while (rs.next())
    {
      cname=rs.getString("cust_name");
      phone=rs.getString("cust_phone");
      address=rs.getString("address");
      
      
      tempta =rs.getString("grand_total");
    }  
    
   
    pst.close();
    rs.close();
    
    
  	   

  }
  catch (Exception e1)
  {

    e1.printStackTrace();
    JOptionPane.showMessageDialog(null, e1);
  }
  



  

  
  
  StyledDocument doc2 = (StyledDocument)textPane.getDocument();
  MutableAttributeSet style2 = doc2.addStyle("Courier New", null);
  StyleConstants.setFontSize(style2, 10);
  
  Document doc = textPane.getDocument();

  try {
  	
  	int len;
      int rlen=cname.length();
     
 
    doc.insertString(doc.getLength(), "Buyer:",null);
    doc.insertString( len=doc.getLength(), cname,null);
    textPane.getStyledDocument().setCharacterAttributes(len, rlen, sas, false);
    
    doc.insertString(doc.getLength(),"\nPhone:" + phone + "\n", null);
    doc.insertString(doc.getLength(), "Address:"+address+"\n", null);
    doc.insertString(doc.getLength(), "__________________________________________________________________________\n", null);

      String str="S.No  P_Name\t\tP_Qty\t P_Price\tP_Disc\tP_Amt\n";
      rlen=str.length();
      doc.insertString(len=doc.getLength(), str, null);
      textPane.getStyledDocument().setCharacterAttributes(len, rlen, sas, false);
      
      doc.insertString(doc.getLength(), "__________________________________________________________________________\n", null);
    }
    catch (BadLocationException e) {
      e.printStackTrace();
    }
    




    try
    {
      String sql = "select  P_name, p_price ,p_qty ,p_disc ,p_amt , serial_no from k_INVOICETABLE where I_no='" + ino + "'";
      PreparedStatement pst = com.prepareStatement(sql);
      ResultSet rs = pst.executeQuery();
      

    int totalItems=0;
      while (rs.next())
      {



        String name = rs.getString("P_NAME");
        String price = rs.getString("P_PRICE");
        String qty = rs.getString("P_QTY");
        String disc = rs.getString("P_DISC");
        String amt = rs.getString("P_AMT");
        String serial= rs.getString("serial_no");
        totalItems+=Integer.parseInt(qty);
        
        int n = name.length();
        
        if ((n <= 4) && (sno < 9))
        {
          sno += 1;
          
          try
          {
        	  doc.insertString(doc.getLength(), "  " + sno + "   ", null);
              int len=doc.getLength();
              int rlen=name.length();
              doc.insertString(doc.getLength(),name, null);
              
              textPane.getStyledDocument().setCharacterAttributes(len, rlen, sas, false);
              doc.insertString(doc.getLength(), "\t\t\t" + qty + "\t " + price + "\t" + disc + "\t" + amt + "\n", null);
              doc.insertString(doc.getLength(), "         S No-" + serial+"\n", style2);
              
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
        


        else if ((n < 4) && (sno > 9))
        {
          sno += 1;
          try
          {
        	  doc.insertString(doc.getLength(), "  " + sno + "   ", null);
              int len=doc.getLength();
              int rlen=name.length();
              doc.insertString(doc.getLength(),name, null);
              textPane.getStyledDocument().setCharacterAttributes(len, rlen, sas, false);
              doc.insertString(doc.getLength(), "\t\t\t" + qty + "\t " + price + "\t" + disc + "\t" + amt + "\n", null);
              doc.insertString(doc.getLength(), "         S No-" + serial+"\n", style2);
              
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
        



        else if (n > 15)
        {
          sno += 1;
          
          try
          {
        	  doc.insertString(doc.getLength(), "  " + sno + "   ", null);
              int len=doc.getLength();
              int rlen=name.length();
              doc.insertString(doc.getLength(),name, null);
              textPane.getStyledDocument().setCharacterAttributes(len, rlen, sas, false);
              doc.insertString(doc.getLength(), "\t" + qty + "\t " + price + "\t" + disc + "\t" + amt + "\n", null);
              doc.insertString(doc.getLength(), "         S No-" + serial+"\n", style2);
              
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
        



        else if ((n > 4) && (n < 16))
        {
          sno += 1;
          
          try
          {
        	  doc.insertString(doc.getLength(), "  " + sno + "   ", null);
              int len=doc.getLength();
              int rlen=name.length();
              doc.insertString(doc.getLength(),name, null);
              textPane.getStyledDocument().setCharacterAttributes(len, rlen, sas, false);
              doc.insertString(doc.getLength(), "\t\t" + qty + "\t " + price + "\t" + disc + "\t" + amt + "\n", null);
              doc.insertString(doc.getLength(), "         S No-" + serial+"\n", style2);
              
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
        




        else if ((n == 4) && (sno >= 9))
        {
          sno += 1;
          try
          {
        	  doc.insertString(doc.getLength(), "  " + sno + "   ", null);
              int len=doc.getLength();
              int rlen=name.length();
              doc.insertString(doc.getLength(),name, null);
              textPane.getStyledDocument().setCharacterAttributes(len, rlen, sas, false);
              doc.insertString(doc.getLength(), "\t\t" + qty + "\t " + price + "\t" + disc + "\t" + amt + "\n", null);
              doc.insertString(doc.getLength(), "         S No-" + serial+"\n", style2);
              
          }
          catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
          }
        }
      }
      

      if(sno==1) {  try {
    		doc.insertString(doc.getLength(),"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n", null);
    	

    	} catch (BadLocationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}    }

    	else if(sno==2) {  try {
    		doc.insertString(doc.getLength(),"\n\n\n\n\n\n\n\n\n\n\n\n\n\n", null);
    	} catch (BadLocationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}    }


    	else if(sno==3) {  try {
    		doc.insertString(doc.getLength(),"\n\n\n\n\n\n\n\n\n\n\n\n\n", null);
    	} catch (BadLocationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}    }

    	else if(sno==4) {  try {
    		doc.insertString(doc.getLength(),"\n\n\n\n\n\n\n\n\n\n\n\n", null);
    	} catch (BadLocationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}    }

    	else if(sno==5) {  try {
    		doc.insertString(doc.getLength(),"\n\n\n\n\n\n\n\n\n\n\n", null);
    	} catch (BadLocationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}    }

    	else if(sno==6) {  try {
    		doc.insertString(doc.getLength(),"\n\n\n\n\n\n\n\n\n\n", null);
    	} catch (BadLocationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}    }

    	else if(sno==7) {  try {
    		doc.insertString(doc.getLength(),"\n\n\n\n\n\n\n", null);
    	} catch (BadLocationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}    }

    	else if(sno==8) {  try {
    		doc.insertString(doc.getLength(),"\n\n\n\n\n", null);
    	} catch (BadLocationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}    }
    	else if(sno==9) {  try {
    		doc.insertString(doc.getLength(),"\n\n\n", null);
    	} catch (BadLocationException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}    }



      pst.close();
      rs.close();
      try
      {  int len;
      int rlen;;
	  double roundoff=Double.parseDouble(tempta);
	         roundoff=Math.round(roundoff);
	         double roundoffvalue=roundoff-Double.parseDouble(tempta);
	         roundoffvalue=Math.round(roundoffvalue * 100.0D) / 100.0D;
   doc.insertString(doc.getLength(), "__________________________________________________________________________\n", null);
   doc.insertString(len=doc.getLength(), "Rounded Off: "+roundoffvalue,null);
   		doc.insertString(doc.getLength(),"\t\t\t       Total Amount=\u20B9", null);
   		   textPane.getStyledDocument().setCharacterAttributes(len, 18, sas, false);
   		     
   		rlen=tempta.length();
   doc.insertString(len=doc.getLength(),  roundoff + "\n", null);
   doc.insertString(doc.getLength(), "\t\t\t\t        Total Items=" + totalItems + "\n", null);
   textPane.getStyledDocument().setCharacterAttributes(len, rlen, sas, false);
   doc.insertString(doc.getLength(), "Amount Chargeable(in words)\n", null);
        
       String inr=Rupees.convertToIndianCurrency(Double.toString(roundoff));
        rlen=inr.length();
       doc.insertString(len=doc.getLength(), inr+"\n", null);
       textPane.getStyledDocument().setCharacterAttributes(len, rlen, sas, false);
       
        doc.insertString(doc.getLength(), "__________________________________________________________________________\n", null);
        StyledDocument doc1 = (StyledDocument)textPane.getDocument();
        MutableAttributeSet style = doc1.addStyle("Courier New", null);
        StyleConstants.setFontSize(style, 8);
       
        doc.insertString(doc.getLength(),"                                                           ",null);
        doc.insertString(len=doc.getLength(),"for SYSTEM CARE\n\n\n", null);

        textPane.getStyledDocument().setCharacterAttributes(len, 15, sas, false);
        
        if(sno==1) {  try {
        	doc.insertString(doc.getLength(),"\n\n\n", null);
        } catch (BadLocationException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }    }

          if(sno==2) {  try {
        	doc.insertString(doc.getLength(),"\n\n", null);
        } catch (BadLocationException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }    }

         if(sno==3 || sno==4) {  try {
        	doc.insertString(doc.getLength(),"\n", null);
        } catch (BadLocationException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }    }

        
        
        doc.insertString(doc.getLength(),"Declaration\nWe declare that this invoice shows the actual price of the goods \ndescribed and that all particulars are true and correct.", style);
        
        
        
        doc.insertString(doc.getLength(),"\t\tAuthorised Signatory\n", null);
        doc.insertString(doc.getLength(), "__________________________________________________________________________\n", null);
        
        doc.insertString(len=doc.getLength(),"Note: Service should be taken by the service centre.\n", null);
        textPane.getStyledDocument().setCharacterAttributes(len, 51, sas, false);
        
        doc.insertString(doc.getLength(), "__________________________________________________________________________\n", null);
        
        doc.insertString(doc.getLength(), "                        ",null);  
        doc.insertString(doc.getLength(),"This is a Computer Generated Invoice", null);

       
        sno = 0;
      }
      catch (BadLocationException e) {
        e.printStackTrace();
      }
      
      return;
    }
    catch (SQLException e1)
    {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(null, e1);
    }
  }
  





  public void company(String ino)
  {
	   SimpleAttributeSet sas = new SimpleAttributeSet();
	    StyleConstants.setBold(sas, true);
	    
	    
	    //StyleConstants.setUnderline(und, true);
	    


	    StyledDocument doc1 = (StyledDocument)textPane.getDocument();
	    MutableAttributeSet style = doc1.addStyle("Courier New", null);
	    StyleConstants.setFontSize(style, 24);
	    MutableAttributeSet styli = doc1.addStyle("Courier New", null);
	    MutableAttributeSet zyl = doc1.addStyle("Courier New", null);
	    StyleConstants.setFontSize(styli, 16);
	    StyleConstants.setFontSize(zyl, 8);
	   
	   
	    SimpleAttributeSet und = new SimpleAttributeSet();
	    und.addAttribute(StyleConstants.CharacterConstants.Underline, Boolean.TRUE);
	    und.addAttributes(styli);
	  





	        try
	        {
	        	String sql = "select to_char(date,'DD-MM-YYYY') , time from k_customers where invoice_no='" + ino + "'";
	             
	            PreparedStatement pst = com.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();
	             table_4.setModel(DbUtils.resultSetToTableModel(rs));
	            tempdate = table_4.getModel().getValueAt(0, 0).toString();
	            temptime= table_4.getModel().getValueAt(0, 1).toString();
	            pst.close();
	            rs.close();
	           
	            

	            table_4.setModel(new DefaultTableModel(
	          	    	new Object[][] {
	          	    	},
	          	    	new String[] {
	          	    	}
	          	    ));
	         
	          
	          Document doc = textPane.getDocument();
	          int blen=0;
	          doc.insertString(doc.getLength(), "\t\t     ",null);
	          doc.insertString(blen=doc.getLength(),"Bill of Supply\n", styli);
	          textPane.getStyledDocument().setCharacterAttributes(blen, 14, sas, false);

	          doc.insertString(doc.getLength(), "\t  Composition Taxable Person, not eligible to Collect Tax on Supplies\n", zyl);
	          doc.insertString(doc.getLength(), "__________________________________________________________________________\n", null);
	                                             
	        
	          doc.insertString(blen=doc.getLength(), "SYSTEM CARE", style);
	          textPane.getStyledDocument().setCharacterAttributes(blen, 12, sas, false);
	          doc.insertString(doc.getLength(), "\n" + "N.A.C Road, Khagaria" +"\t\t\t\t",null);
	          doc.insertString(blen=doc.getLength(),"INVOICE No:",null); textPane.getStyledDocument().setCharacterAttributes(blen, 11, sas, false); 
	          doc.insertString(doc.getLength(),ino + "\nPh:" + "9931483036" +"\t\t\t\t",null);
	          doc.insertString(blen=doc.getLength(),"Date:",null);textPane.getStyledDocument().setCharacterAttributes(blen, 5, sas, false);
	          doc.insertString(doc.getLength(),tempdate +  "\nGSTIN:" + "10BAKPA1157K1Z6" +"\t\t\t\t",null);
	          doc.insertString(blen=doc.getLength(),	"Time:",null);textPane.getStyledDocument().setCharacterAttributes(blen, 5, sas, false);
	          doc.insertString(doc.getLength(),temptime + "\n", null);
	          doc.insertString(doc.getLength(), "__________________________________________________________________________\n\n", null);
       
      


    }
    catch (Exception e1)
    {

      e1.printStackTrace();
      JOptionPane.showMessageDialog(null, e1);
    }
  }
  
  public void run()
  {
    try
    {
      Tasks frame = new Tasks();
      frame.setVisible(true);
    }
    catch (Exception e) {
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
            date.setText(s.format(d));
            


            SimpleDateFormat s1 = new SimpleDateFormat("HH:mm:ss ");
            SimpleDateFormat s2 = new SimpleDateFormat("hh:mm:ss a");
            time.setText(s1.format(d));
            timepmam.setText(s2.format(d));
            

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
  








   




   








  public TasksRaw()
  {
  	setIconImage(Toolkit.getDefaultToolkit().getImage(TasksRaw.class.getResource("/archive/invoice-icon.png")));
    setDefaultCloseOperation(0);
    
    LoginPage c = new LoginPage();
    
    con = c.sharedb();
    com=con;




    setBounds(100, 100, 1240, 710);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(70,130,150));
    
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 0, 1204, 594);
    contentPane.add(tabbedPane);
    tabbedPane.setFont(new Font("Tahoma", 1, 12));
    



    Image imgadd = new ImageIcon(getClass().getResource("/add-item-icon74.png")).getImage();
    


    Image imggg = new ImageIcon(getClass().getResource("/submit32.png")).getImage();
    

    Image i = new ImageIcon(getClass().getResource("/add-item-icon74.png")).getImage();
    

    Image i2 = new ImageIcon(getClass().getResource("/delete-1-icon.png")).getImage();
    







    JPanel panel = new JPanel();
    panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    panel.setBackground(new Color(70,130,150));
    tabbedPane.addTab("BILLING       ", null, panel, null);
    panel.setLayout(null);
    
    JLabel lblProductprice_1 = new JLabel("Product_Price");
    lblProductprice_1.setForeground(Color.WHITE);
    lblProductprice_1.setFont(new Font("Tahoma", 1, 14));
    lblProductprice_1.setBounds(317, 127, 109, 20);
    panel.add(lblProductprice_1);
    
    JLabel lblProductquantity_1 = new JLabel("Qty");
    lblProductquantity_1.setForeground(Color.WHITE);
    lblProductquantity_1.setFont(new Font("Tahoma", 1, 15));
    lblProductquantity_1.setBounds(663, 130, 36, 20);
    panel.add(lblProductquantity_1);
    
    JLabel lblProductdate = new JLabel("Date");
    lblProductdate.setForeground(Color.WHITE);
    lblProductdate.setFont(new Font("Tahoma", 0, 15));
    lblProductdate.setBounds(536, 18, 36, 20);
    panel.add(lblProductdate);
    
    JLabel lblTotalAmount = new JLabel("Total ");
    lblTotalAmount.setForeground(Color.WHITE);
    lblTotalAmount.setFont(new Font("Tahoma", 1, 15));
    lblTotalAmount.setBounds(747, 132, 42, 20);
    panel.add(lblTotalAmount);
    
    JLabel lblDiscount = new JLabel("Disc");
    lblDiscount.setForeground(Color.WHITE);
    lblDiscount.setFont(new Font("Tahoma", 1, 15));
    lblDiscount.setBounds(429, 130, 50, 20);
    panel.add(lblDiscount);
    
    P = new JTextField();
    P.setBackground(new Color(255,255,102));
    P.setHorizontalAlignment(4);
    P.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 38)
        {
          Q.requestFocus();
        }
        



                



        if (e.getKeyCode() == 10)
        {
          D.setEnabled(true);
          D.requestFocus();
        }
        
      }
    });
    P.setFont(new Font("Tahoma", 0, 18));
    P.setColumns(10);
    P.setBounds(317, 152, 102, 26);
    panel.add(P);
    
    Q = new JTextField();
    Q.setText("1");
    Q.setHorizontalAlignment(4);
    Q.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent e)
      {
        


        if (e.getKeyCode() == 10)
        {


        	 try
             {

               double p = Double.parseDouble(P.getText());
               double q = Double.parseDouble(Q.getText());
               double d = Double.parseDouble(D.getText());
               
               double sum = q * (p - d);
               double newsum = Math.round(sum * 100.0D) / 100.0D;
               A.setText(Double.toString(newsum));
               

               A.setEnabled(true);
               A.requestFocus();

             }
             catch (Exception e2)
             {

               JOptionPane.showMessageDialog(null, "Enter valid number");
               P.requestFocus();

             }

        }
        



          


        
        


      }
      


    });
    Q.setFont(new Font("Tahoma", 0, 18));
    Q.setColumns(10);
    Q.setBounds(660, 152, 36, 26);
    panel.add(Q);
    
    DefaultFormatter format=new DefaultFormatter();
    D = new JFormattedTextField(format);
    D.setBackground(new Color(255, 255, 255));
    D.setHorizontalAlignment(4);
   
    D.setValue("0");
    D.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 38)
        {
          P.requestFocus();
        }
        


        


        if (e.getKeyCode() == 10)
        { textField_4.requestFocus();
                   
        }
        
      }
      

    });
    D.setFont(new Font("Tahoma", 0, 18));
    D.setColumns(10);
    D.setBounds(429, 152, 50, 26);
    panel.add(D);
    
    A = new JTextField();
    A.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {}
    });
    A.setHorizontalAlignment(4);
    A.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        

        
        









        if (e.getKeyCode() == 10)
        {
          try
          {
            String query = "insert into k_invoicetable (p_name,p_price,p_qty,p_disc,p_amt , i_no,serial_no) values (?,?,?,?,?,?,?)";
            
            PreparedStatement pst = con.prepareStatement(query);
            
  String combname=(String)comboBox.getSelectedItem();


            pst.setString(1, combname);
            pst.setString(2, P.getText());
            pst.setString(3, Q.getText());
            pst.setString(4, D.getText());
            pst.setString(5, A.getText());
            pst.setString(6, invoice_no.getText());
            pst.setString(7, textField_4.getText());
            




            pst.execute();
            
            pst.close();
            




            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.addRow(new String[] { combname, P.getText(), D.getText(), textField_4.getText(), Q.getText(),A.getText() });
            

            double d = Double.parseDouble(A.getText());
            ta = (Math.round((ta + d) * 100.0D) / 100.0D);
            
            ti += 1;
            TI.setText(Integer.toString(ti));
            TA.setText(Double.toString(ta));
            




            P.setText("");
            Q.setText("1");
            D.setText("0");
            A.setText("");
      
            textField_4.setText("");
            

          }
          catch (Exception e1)
          {


        	  P.setText("");
              Q.setText("1");
              D.setText("0");
              A.setText("");

              textField_4.setText("");
            comboBox.requestFocus();
          }
        }
        








        

      }
      


    });
    A.setFont(new Font("Tahoma", 0, 18));
    A.setColumns(10);
    A.setBounds(703, 152, 86, 26);
    panel.add(A);
       
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(30, 182, 759, 315);
    panel.add(scrollPane);
    
    table = new JTable();

    table.setSelectionBackground(new Color(255,255,102));
    table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent ej) {

      }
    });
    table.setFont(new Font("Tahoma", 0, 16));
    table.setModel(new DefaultTableModel(
      new Object[0][], 
      
      new String[] {
      "          P_Name", "          P_Price", "          P_Discount","          Serial No","          P_Qty", "          P_Amount" })
      {





        Class[] columnTypes = {
          String.class, String.class, String.class, String.class, String.class,String.class };
        
        public Class getColumnClass(int columnIndex) {
          return columnTypes[columnIndex];
        }
      });
    table.getColumnModel().getColumn(0).setPreferredWidth(87);
    table.getColumnModel().getColumn(0).setMinWidth(20);
    table.getColumnModel().getColumn(1).setPreferredWidth(109);
    table.getColumnModel().getColumn(1).setMinWidth(20);
    table.getColumnModel().getColumn(2).setPreferredWidth(86);
    table.getColumnModel().getColumn(2).setMinWidth(20);
    table.getColumnModel().getColumn(3).setPreferredWidth(93);
    table.getColumnModel().getColumn(3).setMinWidth(20);
    table.getColumnModel().getColumn(4).setMinWidth(20);
    table.getColumnModel().getColumn(5).setMinWidth(20);
    scrollPane.setViewportView(table);
    

   comboBox = new JComboBox();
    comboBox.setFont(new Font("Tahoma", 0, 17));

    comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if (keyChar == 10)
        {
          P.requestFocus();
         



        }
        


      }
      



    });
    JTextComponent editor = (JTextComponent)comboBox.getEditor().getEditorComponent();

    comboBox.setBounds(30, 153, 277, 26);
    panel.add(comboBox);

    JLabel lblCategoryname = new JLabel("Product_Name");
    lblCategoryname.setForeground(Color.WHITE);
    lblCategoryname.setFont(new Font("Tahoma", 1, 15));
    lblCategoryname.setBounds(30, 132, 132, 20);
    panel.add(lblCategoryname);
    btnNewButton_2 = new JButton("");
    btnNewButton_2.setVisible(false);
    btnNewButton_2.setFont(new Font("Dialog", 1, 15));
    btnNewButton_2.setIcon(new ImageIcon(imggg));
    

    btnNewButton_2.setBounds(1007, 18, 50, 35);
    panel.add(btnNewButton_2);
    
    JLabel lblCustomerName = new JLabel("Customer Name");
    lblCustomerName.setForeground(Color.WHITE);
    lblCustomerName.setFont(new Font("Tahoma", 0, 15));
    lblCustomerName.setBounds(717, 41, 121, 20);
    panel.add(lblCustomerName);
    
    JLabel lblInvoiceNo = new JLabel("Invoice no.");
    lblInvoiceNo.setForeground(Color.WHITE);
    lblInvoiceNo.setFont(new Font("Tahoma", 0, 15));
    lblInvoiceNo.setBounds(717, 18, 121, 20);
    panel.add(lblInvoiceNo);
    
    invoice_no = new JTextField();
    invoice_no.setBackground(new Color(255,255,102));
    invoice_no.setFont(new Font("Tahoma", 0, 17));
    invoice_no.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent e)
      {

        if (e.getKeyCode() == 10)
        {

          if (invoice_no.getText().length() != 0)
          {
            try
            {
              String query = "INSERT INTO k_customers (invoice_no,date, time) VALUES (?,parsedatetime(?, 'dd-MM-yyyy'),parsedatetime(?, 'HH:mm'))";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, invoice_no.getText());
              pst.setString(2, date.getText());
              pst.setString(3, time.getText());
              


              pst.execute();
              
              pst.close();
              
              btnNewButton_2.setVisible(true);
              c_name.setEnabled(true);
              c_name.requestFocus();
            }
            catch (Exception e1)
            { JOptionPane.showMessageDialog(null, e1);
            
              JOptionPane.showMessageDialog(null, "Invoice no.='" + invoice_no.getText() + "' Already Exist");
              invoice_no.setText("");

            }
            


          }
          else
          {

            try
            {

              String query = "INSERT INTO k_customers (date, time) VALUES (parsedatetime(?, 'dd-MM-yyyy'),parsedatetime(?, 'HH:mm:ss'))";
              
              PreparedStatement pst = con.prepareStatement(query);
              




              pst.setString(1, date.getText());
              pst.setString(2, time.getText());
              


              pst.execute();
              
              pst.close();
              
              btnNewButton_2.setVisible(true);
             
              c_name.requestFocus();
            }
            catch (Exception e1)
            { e1.printStackTrace();
              JOptionPane.showMessageDialog(null, e1);
              invoice_no.setText("");
            }
            
            try
            {
              String sql = "select top 1 invoice_no  from k_customers order by date desc, time desc";
              
              PreparedStatement pst = com.prepareStatement(sql);
              ResultSet rs = pst.executeQuery();
              
              while (rs.next())
              {
                invoice_no.setText(rs.getString("invoice_no"));
              }
              pst.close();
              rs.close();

            }
            catch (Exception e1)
            {

              e1.printStackTrace();
              JOptionPane.showMessageDialog(null, e1);


            }
            


          }
          


        }
        

      }
      


    });
    invoice_no.setBounds(838, 18, 139, 20);
    panel.add(invoice_no);
    invoice_no.setColumns(10);
    
    JLabel lblCustomerContact = new JLabel("Customer contact");
    lblCustomerContact.setForeground(Color.WHITE);
    lblCustomerContact.setFont(new Font("Tahoma", 0, 15));
    lblCustomerContact.setBounds(717, 66, 121, 20);
    panel.add(lblCustomerContact);
    
    c_name = new JTextField();
    c_name.setFont(new Font("Tahoma", 0, 17));
    


    c_name.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
                
        




        if (e.getKeyCode() == 38)
        {
          invoice_no.requestFocus();
        }
        






        if (e.getKeyCode() == 10)
        {
          c_contact.setEnabled(true);
          c_contact.requestFocus();

        }
        
      }
      

    });
    c_name.setColumns(10);
    c_name.setBounds(838, 41, 139, 20);
    panel.add(c_name);
    
    c_contact = new JTextField();
    c_contact.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 38)
        {
          c_name.requestFocus();
        }
        



               






        if (e.getKeyCode() == 10)
        {


          try
          {



            textField.requestFocus();

          }
          catch (Exception e1)
          {
            JOptionPane.showMessageDialog(null, e1);

          }
          

        }
        
      }
      

    });
    c_contact.setFont(new Font("Tahoma", 0, 17));
    c_contact.setColumns(10);
    c_contact.setBounds(838, 66, 139, 20);
    panel.add(c_contact);
    
    textField = new JTextField();
    textField.addKeyListener(new KeyAdapter() {
    	@Override
    	public void keyPressed(KeyEvent arg0) {
    		if(arg0.getKeyCode()==10)
    			
    		{
             try {   String query = "update k_customers set cust_name = ?  , cust_phone= ? ,address=? where invoice_no= '" + invoice_no.getText() + "' ";
                



                PreparedStatement pst = con.prepareStatement(query);
                




                pst.setString(1, c_name.getText());
                pst.setString(2, c_contact.getText());
                pst.setString(3, textField.getText());
                


                pst.execute();
                
                pst.close(); 
                comboBox.requestFocus();
                } catch(Exception e) {}
               
    			
    		}
    	}
    });
    textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
    textField.setColumns(10);
    textField.setBounds(838, 90, 139, 20);
    panel.add(textField);
    
    lblCustomer = new JLabel("Customer Address");
    lblCustomer.setForeground(Color.WHITE);
    lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 15));
    lblCustomer.setBounds(717, 90, 121, 20);
    panel.add(lblCustomer);
   






    JLabel lblTotalAmount_1 = new JLabel("Total Amount");
    lblTotalAmount_1.setForeground(Color.WHITE);
    lblTotalAmount_1.setFont(new Font("Tahoma", 0, 15));
    lblTotalAmount_1.setBounds(880, 423, 91, 19);
    panel.add(lblTotalAmount_1);
    
    TA = new JTextField();
    TA.setBackground(new Color(255,204,204));
    TA.setFont(new Font("Tahoma", 0, 17));
    TA.setColumns(10);
    TA.setBounds(981, 424, 139, 20);
    panel.add(TA);
    
    JLabel lblTotalItems = new JLabel("Total Items");
    lblTotalItems.setForeground(Color.WHITE);
    lblTotalItems.setFont(new Font("Tahoma", 0, 15));
    lblTotalItems.setBounds(880, 453, 91, 20);
    panel.add(lblTotalItems);
    
    TI = new JTextField();
    TI.setFont(new Font("Tahoma", 0, 17));
    TI.setBackground(new Color(255,204,204));
    TI.setColumns(10);
    TI.setBounds(981, 455, 139, 20);
    panel.add(TI);
    





    date = new JTextField();
    date.setEditable(false);
    date.setBackground(Color.WHITE);
    date.setDisabledTextColor(Color.BLACK);
    date.setFont(new Font("Tahoma", 0, 14));
    date.setBounds(573, 18, 91, 20);
    panel.add(date);
    date.setColumns(10);
    
    time = new JTextField();
    time.setEditable(false);
      time.setBackground(Color.WHITE);
    time.setDisabledTextColor(Color.BLACK);
    
    time.setFont(new Font("Tahoma", 0, 14));
    time.setColumns(10);
    time.setBounds(573, 47, 91, 20);
    panel.add(time);
    


    JLabel lblTime = new JLabel("Time");
    lblTime.setForeground(Color.WHITE);
    lblTime.setFont(new Font("Tahoma", 0, 15));
    lblTime.setBounds(536, 47, 36, 20);
    panel.add(lblTime);
    
    timepmam = new JTextField();
    timepmam.setEditable(false);
    timepmam.setBackground(Color.WHITE);
    timepmam.setFont(new Font("Tahoma", 0, 14));
    timepmam.setColumns(10);
    timepmam.setBounds(573, 72, 91, 20);
    panel.add(timepmam);
    
    JPanel panel_5 = new JPanel();
    panel_5.setBackground(new Color(70,130,150));
    panel_5.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    panel_5.setBounds(502, 8, 618, 110);
    panel.add(panel_5);
    
    JLabel lblNewLabel_1 = new JLabel("");
    lblNewLabel_1.setBounds(41, 11, 80, 93);
    panel.add(lblNewLabel_1);
    lblNewLabel_1.setIcon(new ImageIcon(imgadd));
    
    
    
    
    
    
    JPanel panel_1 = new JPanel();
    panel_1.setBorder(new EtchedBorder(0, null, null));
    panel_1.setBackground(new Color(70,130,150));
    tabbedPane.addTab("ADD & DELETE PRODUCTS  ", null, panel_1, null);
    panel_1.setLayout(null);
    
    JLabel lblProductname = new JLabel("Product_Name");
    lblProductname.setFont(new Font("Tahoma", 1, 16));
    lblProductname.setBounds(35, 195, 120, 20);
    panel_1.add(lblProductname);
    
    JTextField  textField_1 = new JTextField();
    textField_1.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          try
          {
            String query = "INSERT INTO k_items (item_NAME) VALUES (?)";
            
            PreparedStatement pst = con.prepareStatement(query);
            

            String uppercase = textField_1.getText().toUpperCase();
            
            pst.setString(1, uppercase);
            


            pst.execute();
            
            pst.close();
           combobox_items();
            //combobox_items_2();
          }
          catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          
          try
          {
            String sql = "select DISTINCT item_name from k_items ORDER BY item_name ASC;";
            
            PreparedStatement pst = com.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
           table_2.setModel(DbUtils.resultSetToTableModel(rs));
            
            pst.close();
            rs.close();

          }
          catch (SQLException e1)
          {

            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          
          textField_1.setText("");
        }
      }
    });
    textField_1.setColumns(10);
    textField_1.setBounds(165, 197, 214, 20);
    panel_1.add(textField_1);
    

    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setIcon(new ImageIcon(i));
    lblNewLabel.setBounds(327, 70, 66, 83);
    panel_1.add(lblNewLabel);
    




    JLabel lblDelete = new JLabel("");
    lblDelete.setIcon(new ImageIcon(i2));
    lblDelete.setBounds(995, 70, 72, 83);
    panel_1.add(lblDelete);
    
   comboBox_1 = new JComboBox();
    
    comboBox_1.setBounds(899, 197, 159, 20);
    panel_1.add(comboBox_1);
    comboBox_1.getEditor().getEditorComponent().addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if (keyChar == 10)
        {

          String st = (String)comboBox_1.getSelectedItem();
          

          int action = JOptionPane.showConfirmDialog(null, st, "Delete", 0);
          if (action == 0)
          {

            try
            {
              String sql = " DELETE FROM k_items WHERE  item_name='" + st + "'";
              
              PreparedStatement pst = con.prepareStatement(sql);
              
              pst.execute();
              
              pst.close();
              
          
             combobox_items();
            }
            catch (SQLException e1) {
              e1.printStackTrace();
              JOptionPane.showMessageDialog(null, e1);
            }
            
            try
            {
              String sql = "select DISTINCT item_name from K_items ORDER BY item_name ASC;";
              
              PreparedStatement pst = com.prepareStatement(sql);
              ResultSet rs = pst.executeQuery();
              table_2.setModel(DbUtils.resultSetToTableModel(rs));
              
              pst.close();
              rs.close();

            }
            catch (SQLException e1)
            {

              e1.printStackTrace();
              JOptionPane.showMessageDialog(null, e1);


            }
            


          }
          

        }
        

      }
      


    });
    JLabel label_1 = new JLabel("Product_Name");
    label_1.setFont(new Font("Tahoma", 1, 16));
    label_1.setBounds(773, 197, 127, 20);
    panel_1.add(label_1);
    
    JPanel panel_3 = new JPanel();
    panel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
    panel_3.setBackground(new Color(70,130,150));
    panel_3.setForeground(SystemColor.desktop);
    panel_3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)), "Add Items  ", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(255, 255, 255)));
    panel_3.setBounds(10, 56, 390, 269);
    panel_1.add(panel_3);
    
    JPanel panel_4 = new JPanel();
    panel_4.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(0, 0, 0), new Color(0, 0, 0)), "Delete Items  ", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(255, 255, 255)));
    panel_4.setBounds(734, 56, 344, 269);
    panel_4.setBackground(new Color(70,130,150));
    panel_1.add(panel_4);
    
    JScrollPane scrollPane_2 = new JScrollPane();
    scrollPane_2.setBounds(433, 56, 255, 474);
    panel_1.add(scrollPane_2);
    
    table_2 = new JTable();
    scrollPane_2.setViewportView(table_2);
    
    JButton btnNewButton = new JButton("Items List");
    btnNewButton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		
    		 try {
    	          String sql = "select DISTINCT item_name from k_items ORDER BY item_name ASC;";
    	          
    	          PreparedStatement pst = com.prepareStatement(sql);
    	          ResultSet rs = pst.executeQuery();
    	          table_2.setModel(DbUtils.resultSetToTableModel(rs));
    	          
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
    btnNewButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    btnNewButton.setBackground(new Color(70,130,150));
    btnNewButton.setForeground(Color.WHITE);
    btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
    btnNewButton.setBounds(503, 26, 89, 23);
    panel_1.add(btnNewButton);

    
    JPanel panel_2 = new JPanel();
    tabbedPane.addTab("BILLS HISTORY ", null, panel_2, null);
    panel_2.setBackground(new Color(70,130,150));
    
    panel_2.setLayout(null);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(0, 110, 643, 365);
    panel_2.add(scrollPane_1);
    
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
          String sql = "select i_no ,p_name,p_price,p_disc,serial_no,p_qty,p_amt from k_invoicetable where i_no='" + sid + "'";
          
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
      new Object[0][], 
      
      new String[] {
      "Invoice_no", "Date", "Time", "Cust_Name", "Cust_Phone","Cust_Add", "Total_Items", "Grand_Total" }));
    

    table_1.getColumnModel().getColumn(1).setPreferredWidth(92);
    scrollPane_1.setViewportView(table_1);
    
    JButton btnLoadDetaila = new JButton("Load Details");
    btnLoadDetaila.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    btnLoadDetaila.setBackground(new Color(70,130,150));
   
    btnLoadDetaila.setForeground(Color.WHITE);
    btnLoadDetaila.setFont(new Font("Tahoma", 0, 15));
    btnLoadDetaila.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        try {
          String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone, address, total_items,grand_total from k_customers order by date desc, time desc";
          
          PreparedStatement pst = com.prepareStatement(sql);
          ResultSet rs = pst.executeQuery();
          table_1.setModel(DbUtils.resultSetToTableModel(rs));
          
          pst.close();
          rs.close();

        }
        catch (Exception e1)
        {

          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, e1);
        }
        




        table_3.setModel(new DefaultTableModel(
          new Object[0][], 
          
          new String[] {
        		  "P_Name", "P_Price", "P_Discount","Serial No","P_Qty", "P_Amount" }));



      }
      



    });
    btnLoadDetaila.setBounds(10, 31, 113, 59);
    panel_2.add(btnLoadDetaila);
    
    dateChooser = new JDateChooser();
    dateChooser.getCalendarButton().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {}

    });
    dateChooser.setBounds(533, 35, 128, 20);
    panel_2.add(dateChooser);
    
    dateChooser.setDateFormatString("dd-MM-yyy");
    dateChooser.setFont(new Font("Tahoma", 0, 13));
    
    JLabel searchby = new JLabel("Search By Invoice_no:");
    searchby.setForeground(Color.WHITE);
    searchby.setFont(new Font("Tahoma", 0, 15));
    searchby.setBounds(145, 31, 153, 27);
    panel_2.add(searchby);
    
    textField_2 = new JTextField();
    textField_2.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          try
          {
            int i = Integer.parseInt(textField_2.getText());
            String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total from  k_customers  where INvoice_no='" + i + "'  order by date desc,time desc";
            
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
            		 "P_Name", "P_Price", "P_Discount","Serial No","P_Qty", "P_Amount" }));

        }
        

      }
      


    });
    textField_2.setBounds(296, 35, 94, 20);
    panel_2.add(textField_2);
    textField_2.setColumns(10);
    
    JLabel lblSearchByDate = new JLabel("Search By Date:");
    lblSearchByDate.setForeground(Color.WHITE);
    lblSearchByDate.setFont(new Font("Tahoma", 0, 15));
    lblSearchByDate.setBounds(419, 31, 118, 27);
    panel_2.add(lblSearchByDate);
    
    JButton button = new JButton("Load");
    button.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    button.setBackground(new Color(70,130,150));
    button.setForeground(Color.WHITE);
 
    
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String s = df.format(dateChooser.getDate());
        

        try
        {
          String sql = "\r\nselect INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total  from  k_customers   where date=parsedatetime(?, 'dd-MM-yyyy') order by date asc,time desc";
          
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
        		  "P_Name", "P_Price", "P_Discount","Serial No","P_Qty", "P_Amount" }));


      }
      



    });
    button.setBounds(597, 70, 64, 20);
    panel_2.add(button);
    
    final JDateChooser dateChooser_1 = new JDateChooser();
    dateChooser_1.setFont(new Font("Tahoma", 0, 13));
    dateChooser_1.setDateFormatString("dd-MM-yyy");
    dateChooser_1.setBounds(846, 35, 100, 20);
    panel_2.add(dateChooser_1);
    
    final JDateChooser dateChooser_2 = new JDateChooser();
    dateChooser_2.setFont(new Font("Tahoma", 0, 13));
    dateChooser_2.setDateFormatString("dd-MM-yyy");
    dateChooser_2.setBounds(1003, 35, 100, 20);
    panel_2.add(dateChooser_2);
    
    JButton button_2 = new JButton("Load");
    button_2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    button_2.setBackground(new Color(70,130,150));
    button_2.setForeground(Color.WHITE);
    button_2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String s1 = df.format(dateChooser_1.getDate());
        String s2 = df.format(dateChooser_2.getDate());
        


        try
        {
          String sql = "select INVOICe_no,TO_CHAR(date,'DD-MM-YYYY'),time,cUST_name , CUST_phone,total_items,grand_total  from  k_customers where   date  between parsedatetime(?, 'dd-MM-yyyy') and parsedatetime(?, 'dd-MM-yyyy') order by date desc,time desc";
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
        		  "P_Name", "P_Price", "P_Discount","Serial No","P_Qty", "P_Amount" }));

      }
      


    });
    button_2.setBounds(1039, 70, 64, 20);
    panel_2.add(button_2);
    
    JLabel lblSearchBwDate = new JLabel("Search B/W Date:");
    lblSearchBwDate.setForeground(Color.WHITE);
    lblSearchBwDate.setFont(new Font("Tahoma", 0, 15));
    lblSearchBwDate.setBounds(714, 31, 126, 27);
    panel_2.add(lblSearchBwDate);
    
    JLabel lblAnd = new JLabel("AND");
    lblAnd.setForeground(Color.WHITE);
    lblAnd.setFont(new Font("Tahoma", 0, 15));
    lblAnd.setBounds(956, 31, 37, 27);
    panel_2.add(lblAnd);
    
    JLabel lblDeleteInvoice = new JLabel("Delete Invoice:");
    lblDeleteInvoice.setForeground(Color.WHITE);
    lblDeleteInvoice.setFont(new Font("Tahoma", 0, 15));
    lblDeleteInvoice.setBounds(10, 486, 113, 27);
    panel_2.add(lblDeleteInvoice);
    
    textField_3 = new JTextField();
    textField_3.addKeyListener(new KeyAdapter()
    {

      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          String s = textField_3.getText();
          int action = JOptionPane.showConfirmDialog(null, s, "Delete", 0);
          if (action == 0)
          {

            try
            {
              String query = "delete from k_customers where invoice_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, textField_3.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            
            try
            {
              String query = "delete from k_invoicetable where i_no=?";
              
              PreparedStatement pst = con.prepareStatement(query);
              



              pst.setString(1, textField_3.getText());
              


              pst.execute();
              
              pst.close();
            }
            catch (SQLException e1)
            {
              JOptionPane.showMessageDialog(null, e1);
            }
            



            try
            {
              String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name ,CUST_phone,total_items,grand_total from  k_customers   order by date desc,time desc";
              
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
          }
          

          textField_3.setText("");
          table_3.setModel(new DefaultTableModel(
            new Object[0][], 
            
            new String[] {
            "Invoice", "P_Name", "P_Qty", "P_Price", "P_Disc", "P_Amt" }));

        }
        
      }
      

    });
    textField_3.setColumns(10);
    textField_3.setBounds(113, 491, 94, 20);
    panel_2.add(textField_3);
    
    JPanel panel_7 = new JPanel();
    panel_7.setForeground(Color.WHITE);
    panel_7.setBackground(new Color(70,130,150));
    panel_7.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    panel_7.setBounds(134, 11, 264, 87);
    panel_2.add(panel_7);
    
    JPanel panel_8 = new JPanel();
    panel_8.setBackground(new Color(70,130,150));
    panel_8.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    panel_8.setBounds(408, 11, 265, 87);
    panel_2.add(panel_8);
    
    JPanel panel_9 = new JPanel();
    panel_9.setBackground(new Color(70,130,150));
    panel_9.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    panel_9.setBounds(683, 11, 488, 87);
    panel_2.add(panel_9);
    
    JScrollPane scrollPane_3 = new JScrollPane();
    scrollPane_3.setBounds(653, 110, 536, 365);
    panel_2.add(scrollPane_3);
    
    table_3 = new JTable();
    table_3.setFont(new Font("Tahoma", 0, 15));
    table_3.setModel(new DefaultTableModel(
      new Object[0][], 
      
      new String[] {
      "Invoice", "P_Name", "P_Qty", "P_Price", "P_Disc", "P_Amt" }));
    

    scrollPane_3.setViewportView(table_3);
    



    JLabel lblPrintInvoice = new JLabel("Print Invoice:");
    lblPrintInvoice.setForeground(Color.WHITE);
    lblPrintInvoice.setFont(new Font("Tahoma", 0, 15));
    lblPrintInvoice.setBounds(663, 524, 113, 27);
    panel_2.add(lblPrintInvoice);
    
    textField_13 = new JTextField();
    textField_13.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {
          String ino = textField_13.getText();
          printinvoice(ino);
          textPane.setText("");
          
          textField_13.setText("");
        }
        
      }
      

    });
    textField_13.setColumns(10);
    textField_13.setBounds(766, 529, 94, 20);
    panel_2.add(textField_13);
    

    JPanel panel_6 = new JPanel();
    tabbedPane.addTab("Reset Data Factory", null, panel_6, null);
    panel_6.setBackground(new Color(70,130,150));
    
    panel_6.setLayout(null);
    Image da = new ImageIcon(getClass().getResource("/deleteall.png")).getImage();
    





    JButton btnDeleteAllItems = new JButton("Delete All Bills");
    Image dab = new ImageIcon(getClass().getResource("/deletebills.png")).getImage();
    btnDeleteAllItems.setIcon(new ImageIcon(dab));
    btnDeleteAllItems.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    btnDeleteAllItems.setBackground(new Color(70,130,150));
   
    btnDeleteAllItems.setForeground(Color.WHITE);
   
   
    btnDeleteAllItems.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int action = JOptionPane.showConfirmDialog(null, "All Billing History will be erased", "Delete All Data", 0);
        if (action == 0)
        {
          try
          {
            String sql = " DELETE FROM  k_customers";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.execute();
            
            pst.close();

          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          


          try
          {
            String sql = " DELETE FROM k_invoicetable ";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.execute();
            
            pst.close();

          }
          catch (SQLException e1)
          {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
          }
          
          try
          {
            String sql = "select INVOICe_no,to_char(date,'DD-MM-YYYY'),time,cUST_name ,CUST_phone,total_items,grand_total from  k_customers  order by date desc,time desc";
            
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
          
        }
        
      }
      

    });
    btnDeleteAllItems.setFont(new Font("Tahoma", 0, 17));
    btnDeleteAllItems.setBounds(122, 99, 205, 60);
    panel_6.add(btnDeleteAllItems);
    
    Image dai = new ImageIcon(getClass().getResource("/deleteitems.png")).getImage();
    







    Image imglogout = new ImageIcon(getClass().getResource("/Logout24.png")).getImage();
    

    Image imgchangepass = new ImageIcon(getClass().getResource("/changeDetails.png")).getImage();
    JButton logout = new JButton("Logout");
    logout.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    logout.setBackground(new Color(70,130,150));
    logout.setForeground(Color.WHITE);
    logout.setIcon(new ImageIcon(imglogout));
    logout.setFont(new Font("Tahoma", 0, 12));
    logout.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        dispose();
        new LoginPage().frame.setVisible(true);
      }
    });
    logout.setBounds(1095, 600, 119, 32);
    contentPane.add(logout);
    
    textPane = new JTextPane();
    textPane.setEditable(false);
    textPane.setFont(new Font("Courier New", Font.PLAIN, 11));
    textPane.setBounds(813, 182, 307, 199);
    panel.add(textPane);
    
    JButton btnPrintInvoice = new JButton("Print Invoice");
    btnPrintInvoice.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		
            
              try
              {

                double gt = Double.parseDouble(TA.getText());
                ti = Integer.parseInt(TI.getText());
                String in = invoice_no.getText();
                

                String query = " update k_customers set grand_total = '" + gt + "' , total_items='" + ti + "' where invoice_no='" + in + "'";
                
                PreparedStatement pst = con.prepareStatement(query);
                







                pst.execute();
                
                pst.close();
                printinvoice(in);
                textPane.setText("");
                btnNewButton_2.setVisible(false);
                invoice_no.requestFocus();
                ti = 0;
                ta = 0.0D;
                
                TI.setText("");
                TA.setText("");
                invoice_no.setText("");
                c_name.setText("");
                c_contact.setText("");
                textField.setText("");
                P.setText("");
                Q.setText("1");
                D.setText("0");
                A.setText("");
                textField_4.getText();
               
                table.setModel(new DefaultTableModel(null, new String[] {
                		  "          P_Name", "          P_Price", "          P_Discount","          Serial No","          P_Qty", "          P_Amount" }));
              }
              catch (Exception e2)
              {
                JOptionPane.showMessageDialog(null, e2);


              }
              


            
    		
    		
    	}
    });
    btnPrintInvoice.setForeground(Color.WHITE);
    btnPrintInvoice.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnPrintInvoice.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    btnPrintInvoice.setBackground(new Color(70, 130, 150));
    btnPrintInvoice.setBounds(320, 508, 119, 32);
    Image printimg = new ImageIcon(getClass().getResource("/printSmall.png")).getImage();
    btnPrintInvoice.setIcon(new ImageIcon(printimg));
  
    panel.add(btnPrintInvoice);
    
    JButton btnRemove = new JButton("Remove Item");
    btnRemove.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		
            int action = JOptionPane.showConfirmDialog(null, "Remove item?", "Remove", 0);
            if (action == 0)
            {

              try
              {
                int row = table.getSelectedRow();
                
                String name = table.getModel().getValueAt(row, 0).toString();
                String query = "Delete from k_invoicetable where i_no='" + invoice_no.getText() + "' and P_name='" + name + "'";
                
                PreparedStatement pst = con.prepareStatement(query);
                pst.execute();
                ti -= 1;
                TI.setText(Integer.toString(ti));

              }
              catch (Exception e)
              {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
              }
              


              DefaultTableModel model = (DefaultTableModel)table.getModel();
              

              try
              {
                int SelectedRowIndex = table.getSelectedRow();
                
                double d = Double.parseDouble(TA.getText());
                double tempamt = Double.parseDouble(table.getModel().getValueAt(SelectedRowIndex, 5).toString());
                

                ta = (Math.round((d - tempamt) * 100.0D) / 100.0D);
                

                TA.setText(Double.toString(ta));
                
                model.removeRow(SelectedRowIndex);
               
                comboBox.requestFocus();
              }
              catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
              }
              
            }
    		
    	}
    });
    btnRemove.setForeground(Color.WHITE);
    btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnRemove.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    btnRemove.setBackground(new Color(70, 130, 150));
    Image removimg = new ImageIcon(getClass().getResource("/remove.png")).getImage();
    btnRemove.setIcon(new ImageIcon(removimg));
   
    btnRemove.setBounds(30, 508, 119, 32);
    panel.add(btnRemove);
    
    JButton btnCancelInvoice = new JButton("Cancel Invoice");
    btnCancelInvoice.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		
    		int action = JOptionPane.showConfirmDialog(null, "Cancel Invoice?", "Cancel", 0);
            if (action == 0)
            {
              try {
                String query = "delete from k_customers where invoice_no=?";
                
                PreparedStatement pst = con.prepareStatement(query);
                



                pst.setString(1, invoice_no.getText());
                


                pst.execute();
                
                pst.close();
              }
              catch (SQLException e1)
              {
                JOptionPane.showMessageDialog(null, e1);
              }
              
              try
              {
                String query = "delete from k_invoicetable where i_no=?";
                
                PreparedStatement pst = con.prepareStatement(query);
                



                pst.setString(1, invoice_no.getText());
                


                pst.execute();
                
                pst.close();
              }
              catch (SQLException e1)
              {
                JOptionPane.showMessageDialog(null, e1);
              }
              





              btnNewButton_2.setVisible(false);
              invoice_no.requestFocus();
              ti = 0;
              ta = 0.0D;
              
              TI.setText("");
              TA.setText("");
              invoice_no.setText("");
              c_name.setText("");
              c_contact.setText("");
              
              P.setText("");
              Q.setText("1");
              D.setText("0");
              A.setText("");
              textField_4.setText("");
              
             
              table.setModel(new DefaultTableModel(null, new String[] {
            		  "          P_Name", "          P_Price", "          P_Discount","          Serial No","          P_Qty", "          P_Amount" }));
            }

    		
    		
    	}
    });
    btnCancelInvoice.setForeground(Color.WHITE);
    btnCancelInvoice.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnCancelInvoice.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    btnCancelInvoice.setBackground(new Color(70, 130, 150));
    Image returnimg = new ImageIcon(getClass().getResource("/back.png")).getImage();
    btnCancelInvoice.setIcon(new ImageIcon(returnimg));
   
    btnCancelInvoice.setBounds(670, 508, 119, 32);
    panel.add(btnCancelInvoice);
    
    textField_4 = new JTextField();
    textField_4.addKeyListener(new KeyAdapter() {
    	@Override
    	public void keyPressed(KeyEvent arg0) {
    		if(arg0.getKeyCode()==10) { Q.requestFocus();}
    	}
    });
    textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
    textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
    textField_4.setColumns(10);
    textField_4.setBounds(489, 152, 161, 26);
    panel.add(textField_4);
    
    JLabel lblSerialNumber = new JLabel("Serial Number");
    lblSerialNumber.setHorizontalAlignment(SwingConstants.CENTER);
    lblSerialNumber.setForeground(Color.WHITE);
    lblSerialNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblSerialNumber.setBounds(489, 130, 155, 20);
    panel.add(lblSerialNumber);
    
    
    table_4 = new JTable();
    table_4.setModel(new DefaultTableModel(
      new Object[0][], 
      
      new String[] {
      "d", "time" }));
    

    table_4.setBounds(75, 42, 1, 1);
    contentPane.add(table_4);
    
    combobox_items();
    currentdate();
    
  }
}
