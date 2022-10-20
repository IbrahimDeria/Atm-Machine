import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class sendMoney extends JFrame {

	private JPanel contentPane;
	private JTextField recepient;
	private JTextField amount;
	private JLabel lblConfirmPassword;
	private JPasswordField cPassword;
	private JButton btnNewButton_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sendMoney frame = new sendMoney();
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
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	public sendMoney() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		recepient = new JTextField();
		recepient.setBounds(350, 94, 400, 55);
		contentPane.add(recepient);
		recepient.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Recepient:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(170, 90, 150, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAmount.setBounds(170, 190, 150, 55);
		contentPane.add(lblAmount);
		
		amount = new JTextField();
		amount.setColumns(10);
		amount.setBounds(350, 190, 400, 55);
		contentPane.add(amount);
		
		lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(170, 280, 270, 55);
		contentPane.add(lblConfirmPassword);
		
		cPassword = new JPasswordField();
		cPassword.setBounds(350, 284, 400, 55);
		contentPane.add(cPassword);
		
		btnNewButton_3 = new JButton("Send");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int money = Integer.parseInt(amount.getText());
				String url = "jdbc:sqlserver://LAPTOP-TKA4KD49:1433;DatabaseName=account;encrypt=true;trustServerCertificate=true;";
				String user = "ibrahim";
				String password2 = "halladeria";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
					Connection connection = DriverManager.getConnection(url, user, password2);
					String sql = "Select * from loginAccounts where username=?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, recepient.getText());
					ResultSet rs = pst.executeQuery();
					if(!rs.next()) {
						JOptionPane.showMessageDialog(null, "The recepient does not exist");	
						connection.close();
						return;
						
					}
				}catch(ClassNotFoundException e) {
					e.printStackTrace();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
					Connection connection = DriverManager.getConnection(url, user, password2);
					String sql = "Select * from loginAccounts where password=?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, cPassword.getText());
					ResultSet rs = pst.executeQuery();
					if(!rs.next()) {
						JOptionPane.showMessageDialog(null, "Your password did not match your account!");	
						connection.close();
						return;
						
					}
				}catch(ClassNotFoundException e) {
					e.printStackTrace();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isNumeric(amount.getText())==false) {
					JOptionPane.showMessageDialog(null, "You entered a non-numeric value for the withdrawl amount! Try again!");
					return;
				}
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
					Connection connection = DriverManager.getConnection(url, user, password2);
					login login1 = new login();
					System.out.println(login1.lastUsername);
					Statement stmt = connection.createStatement();
					String duplicateQuery = "SELECT * FROM loginAccounts WHERE username='" + login1.lastUsername + "'";
					ResultSet rs = stmt.executeQuery(duplicateQuery);
					int currentBalance = 0;
					while(rs.next()) {
						 currentBalance = rs.getInt(6);
						 
					}
					System.out.println(currentBalance);
					int officialBalance = currentBalance - money;
					String sql = "UPDATE loginAccounts SET balance = '" + officialBalance + "' WHERE username='" + login1.lastUsername + "'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.executeUpdate();
					connection.close();
					
			}catch(ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
					Connection connection = DriverManager.getConnection(url, user, password2);
					login login1 = new login();
					System.out.println(login1.lastUsername);
					Statement stmt = connection.createStatement();
					String duplicateQuery = "SELECT * FROM loginAccounts WHERE username='" + recepient.getText() + "'";
					ResultSet rs = stmt.executeQuery(duplicateQuery);
					int currentBalance = 0;
					while(rs.next()) {
						 currentBalance = rs.getInt(6);
						 
					}
					System.out.println(currentBalance);
					int officialBalance = currentBalance + money;
					String sql = "UPDATE loginAccounts SET balance = '" + officialBalance + "' WHERE username='" + recepient.getText() + "'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.executeUpdate();
					connection.close();
					JOptionPane.showMessageDialog(null, "You sent "+money+ " to" + recepient.getText());	
					account account = new account();
					account.setVisible(true);
					setVisible(false);
			}catch(ClassNotFoundException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(375, 380, 140, 50);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account account = new account();
				account.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_4.setBounds(10, 11, 118, 38);
		contentPane.add(btnNewButton_4);
	}
}
