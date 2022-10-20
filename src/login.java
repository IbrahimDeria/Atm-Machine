import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.*;
import javax.swing.JPasswordField;
public class login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	static String lastUsername = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static void changeString(String value) {
		lastUsername = value;
	}
	public login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(150, 115, 90, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(150, 200, 90, 20);
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setBounds(250, 100, 390, 45);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password.setBounds(250, 190, 390, 45);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initalPage initial = new initalPage();
				initial.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 10, 100, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = "jdbc:sqlserver://LAPTOP-TKA4KD49:1433;DatabaseName=account;encrypt=true;trustServerCertificate=true;";
				String user = "ibrahim";
				String password2 = "halladeria";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
					Connection connection = DriverManager.getConnection(url, user, password2);
					String sql = "Select * from loginAccounts where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(sql);
					String lastUsername = username.getText();
					pst.setString(1, username.getText());
					pst.setString(2, password.getText());
					ResultSet rs = pst.executeQuery();
					if(rs.next()) {
						changeString(username.getText());
						JOptionPane.showMessageDialog(null, "You have logged in successfully!");	
						account account = new account();
						account.setVisible(true);
						setVisible(false);
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Your Username or Password did not match the account. Please try again.");
					}
				}catch(ClassNotFoundException e) {
					e.printStackTrace();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(381, 328, 118, 51);
		contentPane.add(btnNewButton_1);
	
}
}
