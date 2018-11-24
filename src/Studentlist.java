import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Studentlist extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox selectCombox;
	private JComboBox comboBoxSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Studentlist frame = new Studentlist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JTextField textFieldID;
	private JTextField textFieldFirstname;
	private JTextField textFieldLastname;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldAge;
	private JTextField textFieldSubject;
	private JTextField textFieldSearch;
	
	public void refreshTable()
	{
		try 
		{
			String query = "select * from Studentlist";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void useComboBox()
	{
		try 
		{
			String query = "select * from Studentlist";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				selectCombox.addItem(rs.getString("Username"));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Create the frame.
	 */
	public Studentlist() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 97, 515, 297);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try 
				{	
					
					int row = table.getSelectedRow();
					String Username_ = (table.getModel().getValueAt(row, 3).toString());
					
					String query = "select * from Studentlist where Username='"+Username_+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					while(rs.next())
					{
						textFieldID.setText(rs.getString("ID"));
						textFieldFirstname.setText(rs.getString("Firstname"));
						textFieldLastname.setText(rs.getString("Lastname"));
						textFieldUsername.setText(rs.getString("Username"));
						textFieldPassword.setText(rs.getString("Password"));
						textFieldAge.setText(rs.getString("Age"));
						textFieldSubject.setText(rs.getString("Subject"));
						
					}
					
					pst.close();
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
				refreshTable();
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Load Student Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{
					String query = "select ID,Firstname,Age,Subject from Studentlist";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(361, 0, 445, 42);
		contentPane.add(btnNewButton);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldID.setBounds(109, 90, 141, 31);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldFirstname = new JTextField();
		textFieldFirstname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldFirstname.setColumns(10);
		textFieldFirstname.setBounds(109, 143, 141, 31);
		contentPane.add(textFieldFirstname);
		
		textFieldLastname = new JTextField();
		textFieldLastname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldLastname.setColumns(10);
		textFieldLastname.setBounds(109, 194, 141, 31);
		contentPane.add(textFieldLastname);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(109, 247, 141, 31);
		contentPane.add(textFieldUsername);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(109, 289, 141, 31);
		contentPane.add(textFieldPassword);
		
		textFieldAge = new JTextField();
		textFieldAge.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(109, 331, 141, 31);
		contentPane.add(textFieldAge);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblId.setBounds(10, 93, 35, 23);
		contentPane.add(lblId);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFirstname.setBounds(10, 143, 88, 23);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastname.setBounds(10, 194, 89, 23);
		contentPane.add(lblLastname);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(10, 247, 101, 23);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(10, 294, 101, 23);
		contentPane.add(lblPassword);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAge.setBounds(10, 339, 35, 23);
		contentPane.add(lblAge);
		
		textFieldSubject = new JTextField();
		textFieldSubject.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldSubject.setColumns(10);
		textFieldSubject.setBounds(109, 375, 141, 31);
		contentPane.add(textFieldSubject);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubject.setBounds(10, 383, 88, 23);
		contentPane.add(lblSubject);
		
		JButton btnSave = new JButton("Insert");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					String query = "insert into Studentlist (ID,Firstname,Lastname,Username,Password,Age,Subject) values (?,?,?,?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,textFieldID.getText());
					pst.setString(2,textFieldFirstname.getText());
					pst.setString(3,textFieldLastname.getText());
					pst.setString(4,textFieldUsername.getText());
					pst.setString(5,textFieldPassword.getText());
					pst.setString(6,textFieldAge.getText());
					pst.setString(7,textFieldSubject.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data succesfully saved to Database");
					
					pst.close();
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
				refreshTable();
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBounds(268, 405, 127, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					String query = "update Studentlist set ID='"+textFieldID.getText()+"',Firstname ='"+textFieldFirstname.getText()+"',Lastname='"+textFieldLastname.getText()+"', Username='"+textFieldUsername.getText()+"',Password='"+textFieldPassword.getText()+"',Age='"+textFieldAge.getText()+"',Subject='"+textFieldSubject.getText()+"' where ID='"+textFieldID.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data succesfully Updated");
					
					pst.close();
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
				refreshTable();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(412, 405, 127, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int action = JOptionPane.showConfirmDialog(null, "Do you want to delete this data?","Delete", JOptionPane.YES_NO_CANCEL_OPTION);
				if (action == 0)
				{
					
				
				try 
				{
					String query = "delete from Studentlist where ID='"+textFieldID.getText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data succesfully Deleted");
					
					pst.close();
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
				refreshTable();
				
			}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(268, 439, 127, 23);
		contentPane.add(btnDelete);
		
		selectCombox = new JComboBox();
		selectCombox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					String query = "select * from Studentlist where Username=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String)selectCombox.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next())
					{
						textFieldID.setText(rs.getString("ID"));
						textFieldFirstname.setText(rs.getString("Firstname"));
						textFieldLastname.setText(rs.getString("Lastname"));
						textFieldUsername.setText(rs.getString("Username"));
						textFieldPassword.setText(rs.getString("Password"));
						textFieldAge.setText(rs.getString("Age"));
						textFieldSubject.setText(rs.getString("Subject"));
						
					}
					
					pst.close();
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
				refreshTable();
				
			}
		});
		selectCombox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		selectCombox.setBounds(361, 53, 141, 31);
		contentPane.add(selectCombox);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldID.setText(null);
				textFieldFirstname.setText(null);
				textFieldLastname.setText(null);
				textFieldUsername.setText(null);
				textFieldPassword.setText(null);
				textFieldAge.setText(null);
				textFieldSubject.setText(null);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnClear.setBounds(412, 439, 127, 23);
		contentPane.add(btnClear);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter()
				{
			public void keyReleased(KeyEvent arg0)
			{
		
				try
				{
					String selecteditem = (String)comboBoxSearch.getSelectedItem();
					String query = "select * from Studentlist where "+selecteditem+"=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldSearch.setBounds(519, 53, 141, 33);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"ID", "Firstname", "Lastname", "Username", "Age", "Subject"}));
		comboBoxSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBoxSearch.setBounds(665, 53, 141, 31);
		contentPane.add(comboBoxSearch);
		refreshTable();
		useComboBox();
		
		refreshTable();
		useComboBox();
	}
}
