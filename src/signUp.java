import java.awt.EventQueue;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class signUp extends JFrame {

	private JPanel contentPane;
	private JTextField fName;
	private JButton btnNewButton;
	private JTextField lName;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField cPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signUp frame = new signUp();
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
	public static boolean checkString(String check) {
		for(char c : check.toCharArray()) {
		    if(Character.isDigit(c)) {
		        return true;
		    }
		}
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(check);
		boolean b = m.find();
		if(b) {
			return true;
		}
		return false;
	}
	public signUp() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fName = new JTextField();
		fName.setBounds(237, 60, 322, 38);
		contentPane.add(fName);
		fName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First Name:");
		lblNewLabel.setBounds(150, 72, 95, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(150, 150, 95, 14);
		contentPane.add(lblLastName);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		lblNewLabel_2.setBounds(150, 230, 95, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setBounds(150, 310, 95, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Confirm Password:");
		lblNewLabel_4.setBounds(117, 390, 163, 14);
		contentPane.add(lblNewLabel_4);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initalPage initial = new initalPage();
				initial.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 11, 118, 38);
		contentPane.add(btnNewButton);
		
		lName = new JTextField();
		lName.setColumns(10);
		lName.setBounds(237, 138, 322, 38);
		contentPane.add(lName);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(237, 218, 322, 38);
		contentPane.add(username);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = "jdbc:sqlserver://LAPTOP-TKA4KD49:1433;DatabaseName=account;encrypt=true;trustServerCertificate=true;";
				String user = "ibrahim";
				String password2 = "halladeria";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
					Connection connection = DriverManager.getConnection(url, user, password2);
					String sql = "insert into loginAccounts (firstname, lastname, username, password) values (?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(sql);
					if(username.getText().equals("")||username.getText().length()<=5) {
						JOptionPane.showMessageDialog(null, "Invalid Username (Must be greater than 5 letters)");
						username.setText("");
						connection.close();
						return;
					}
					if(checkString(fName.getText())||checkString(lName.getText())) {
						JOptionPane.showMessageDialog(null, "Make sure your First and Last name has no numbers or symbols in it!");
						connection.close();
						return;
					}
					if(password.getText().equals("")||password.getText().length()<=5) {
						JOptionPane.showMessageDialog(null, "Invalid Password (Must be greater than 5 letters)");
						password.setText("");
						connection.close();
						return;
					}
					Statement stmt = connection.createStatement();
					
					String duplicateQuery = "SELECT * FROM loginAccounts WHERE username='" + username.getText() + "'";
					ResultSet rs = stmt.executeQuery(duplicateQuery);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "This Username is taken. Please try a different username");
						username.setText("");
						connection.close();
						return;
					}
					if(!password.getText().equals(cPassword.getText())) {
						JOptionPane.showMessageDialog(null, "Your password did not match. Try again.");
						cPassword.setText("");
						connection.close();
						return;
					}
					pst.setString(1, fName.getText());
					pst.setString(2, lName.getText());
					pst.setString(3, username.getText());
					pst.setString(4, password.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "You have signed up successfully!");
					connection.close();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignUp.setBounds(700, 208, 118, 51);
		contentPane.add(btnSignUp);
		
		password = new JPasswordField();
		password.setBounds(237, 298, 322, 38);
		contentPane.add(password);
		
		cPassword = new JPasswordField();
		cPassword.setBounds(237, 378, 322, 38);
		contentPane.add(cPassword);
	}
}
