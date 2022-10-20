import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class account extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					account frame = new account();
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
	public account() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
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
				 JLabel lblNewLabel = new JLabel("Balance: " + currentBalance);
					lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
					lblNewLabel.setBounds(380, 40, 160, 25);
					contentPane.add(lblNewLabel);
			}
			connection.close();
	}catch(ClassNotFoundException e) {
		e.printStackTrace();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		JButton btnNewButton = new JButton("Deposit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deposit deposit = new deposit();
				deposit.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(200, 100, 130, 70);
		contentPane.add(btnNewButton);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdraw withdraw = new withdraw();
				withdraw.setVisible(true);
				setVisible(false);
			}
		});
		btnWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnWithdraw.setBounds(600, 100, 130, 70);
		contentPane.add(btnWithdraw);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMoney sendMoney = new sendMoney();
				sendMoney.setVisible(true);
				setVisible(false);
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSend.setBounds(405, 306, 130, 70);
		contentPane.add(btnSend);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initalPage initial = new initalPage();
				initial.setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(756, 412, 118, 38);
		contentPane.add(btnExit);
	}
}
