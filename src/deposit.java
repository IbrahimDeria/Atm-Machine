import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class deposit extends JFrame {

	private JPanel contentPane;
	private JTextField withdrawAmount;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deposit frame = new deposit();
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
	public int addMoney(int amount) {
		String newWithdrawl = withdrawAmount.getText();
		int newWithdrawlNumber = Integer.valueOf(newWithdrawl);
		newWithdrawlNumber+=amount;
		return newWithdrawlNumber;
	}
	public deposit() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("1000");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int finalAmount = addMoney(1000);
				withdrawAmount.setText(Integer.toString(finalAmount));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(180, 175, 120, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("500");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int finalAmount = addMoney(500);
				withdrawAmount.setText(Integer.toString(finalAmount));
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(600, 175, 120, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("20");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int finalAmount = addMoney(20);
				withdrawAmount.setText(Integer.toString(finalAmount));
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(600, 300, 120, 50);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("100");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int finalAmount = addMoney(100);
				withdrawAmount.setText(Integer.toString(finalAmount));
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3.setBounds(180, 300, 120, 50);
		contentPane.add(btnNewButton_3);
		String lastUsername = "";
		JButton btnNewButton_3_1 = new JButton("Deposit");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = "jdbc:sqlserver://LAPTOP-TKA4KD49:1433;DatabaseName=account;encrypt=true;trustServerCertificate=true;";
				String user = "ibrahim";
				String password2 = "halladeria";
				if(isNumeric(withdrawAmount.getText())==false) {
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
					int officialBalance = currentBalance + Integer.parseInt(withdrawAmount.getText());
					String sql = "UPDATE loginAccounts SET balance = '" + officialBalance + "' WHERE username='" + login1.lastUsername + "'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "You desposited "+ withdrawAmount.getText());
					connection.close();
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
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_3_1.setBounds(731, 399, 143, 51);
		contentPane.add(btnNewButton_3_1);
		
		withdrawAmount = new JTextField();
		withdrawAmount.setHorizontalAlignment(SwingConstants.CENTER);
		withdrawAmount.setFont(new Font("Tahoma", Font.PLAIN, 40));
		withdrawAmount.setText("0");
		withdrawAmount.setBounds(300, 70, 300, 40);
		contentPane.add(withdrawAmount);
		withdrawAmount.setColumns(10);
		
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
		String url = "jdbc:sqlserver://LAPTOP-TKA4KD49:1433;DatabaseName=account;encrypt=true;trustServerCertificate=true;";
		String user = "ibrahim";
		String password2 = "halladeria";
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
				 JLabel lblNewLabel = new JLabel("Current Balance = " + currentBalance);
				 lblNewLabel.setBounds(66, 342, 149, 14);
					contentPane.add(lblNewLabel);
			}
			connection.close();
	}catch(ClassNotFoundException e) {
		e.printStackTrace();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
}
