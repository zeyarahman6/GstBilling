package bs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;




public class ChangePassword
  extends JFrame
{
  private JPanel contentPane;
  private JTextField textField_1;
  private JTextField textField_2;
  ChangePassword frame;
  Connection con;
  
  public void run()
  {
    try
    {
      frame = new ChangePassword();
      frame.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  





  public ChangePassword()
  {
  	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  	setIconImage(Toolkit.getDefaultToolkit().getImage(ChangePassword.class.getResource("/archive/invoice-icon.png")));
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setBackground(new Color(70,130,150));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel label = new JLabel("Change your password");
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Segoe Print", Font.BOLD, 25));
    label.setBounds(90, 11, 299, 55);
    contentPane.add(label);
    
    JLabel label_1 = new JLabel("Current Password");
    label_1.setForeground(Color.WHITE);
    label_1.setFont(new Font("Segoe Print", Font.BOLD, 15));
    label_1.setBounds(55, 97, 142, 19);
    contentPane.add(label_1);
    
    JLabel label_2 = new JLabel("New Password");
    label_2.setForeground(Color.WHITE);
    label_2.setFont(new Font("Segoe Print", Font.BOLD, 15));
    label_2.setBounds(80, 146, 117, 19);
    contentPane.add(label_2);
    
    textField_1 = new JTextField();
    textField_1.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {

          textField_2.requestFocus();
        }
        
      }
      

    });
    textField_1.setColumns(10);
    textField_1.setBounds(207, 98, 182, 20);
    contentPane.add(textField_1);
    
    textField_2 = new JTextField();
    textField_2.setColumns(10);
    textField_2.setBounds(207, 147, 182, 20);
    contentPane.add(textField_2);
    
    JButton button = new JButton("Change Password");
    button.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE, Color.BLACK, Color.BLACK));
    button.setForeground(Color.WHITE);
    button.setBackground(new Color(70,130,150));
    
    Image imglogout = new ImageIcon(getClass().getResource("/edit-icon.png")).getImage();
    button.setIcon(new ImageIcon(imglogout));
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        LoginPage c = new LoginPage();
        con = c.sharedb();
        try
        {
          String query = "select * from admintable where  PASSWORD=?";
          
          PreparedStatement pst = con.prepareStatement(query);
          pst.setString(1, textField_1.getText());
          
          ResultSet rs = pst.executeQuery();
          if (rs.next())
          {

            query = "update admintable set password='" + textField_2.getText() + "' where password ='" + textField_1.getText() + "' ";
            PreparedStatement pt = con.prepareStatement(query);
            
            boolean b = pt.execute();
            if (!b)
            {
              JOptionPane.showMessageDialog(null, "Password Changed");
              
              dispose();
            }
            
          }
          else
          {
            JOptionPane.showMessageDialog(null, "wrong username or passoword"); }
          pst.close();
          rs.close();
          con.close();
        } catch (Exception e1) {
          e1.printStackTrace();
        }
        
      }
      
    });
    button.setFont(new Font("Segoe Print", Font.BOLD, 13));
    button.setBounds(207, 192, 182, 41);
    contentPane.add(button);
  }
}
