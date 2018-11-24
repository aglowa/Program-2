import java.awt.EventQueue;

import javax.swing.JFrame;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JTextField username;
	private JPasswordField password;
	
	

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 869, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setBounds(593, 92, 233, 33);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(470, 98, 112, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(470, 195, 113, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password.setBounds(593, 187, 233, 33);
		frame.getContentPane().add(password);
		
		JButton btnNewButton = new JButton("Login");
		Image img1 = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img1));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "select * from Studentlist where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, username.getText());
					pst.setString(2, password.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next())
					{
						count = count+1;
					}
					
					if(count == 1)
					{
						JOptionPane.showMessageDialog(null, "Valid Username and Password");
						frame.dispose();
						Studentlist stdlist = new Studentlist();
						stdlist.setVisible(true);
					}
					
					else if(count > 1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username or Password");
					}
					
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid Username and Password");
					}
					
					rs.close();
					pst.close();
				}
				
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(624, 264, 181, 44);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(215, 92, 205, 210);
		frame.getContentPane().add(label);
	}
}
