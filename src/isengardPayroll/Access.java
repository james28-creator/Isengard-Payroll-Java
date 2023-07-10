package isengardPayroll;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Access {

	private JFrame frame;
	private JTextField textUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		FlatCobalt2IJTheme.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Access window = new Access();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Access() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Admin login");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\eclipse-workspace\\IsengardPayrollManagementSystem3\\Images\\isengard.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 551, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 535, 301);
		frame.getContentPane().add(contentPane);
		
		JLabel lblNewLabel = new JLabel("ADMIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(102, 45, 75, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(35, 100, 100, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(35, 136, 100, 30);
		contentPane.add(lblNewLabel_1_1);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		textUsername.setColumns(10);
		textUsername.setBounds(125, 100, 120, 30);
		contentPane.add(textUsername);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textUsername.getText();
				String password = passwordField.getText();
				
				if (username.equals("admin") && password.equals("admin")) {					
					IsengardPayroll isengardpayroll = new IsengardPayroll();
					isengardpayroll.frame_1.setVisible(true);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Invalid Username or Password");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(35, 177, 210, 30);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					
					String username = textUsername.getText();
					String password = passwordField.getText();
					
					if (username.equals("admin") && password.equals("admin")) {					
						IsengardPayroll isengardpayroll = new IsengardPayroll();
						isengardpayroll.frame_1.setVisible(true);
						isengardpayroll.frame_1.setExtendedState(JFrame.MAXIMIZED_BOTH);
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(contentPane, "Invalid Username or Password");
					}
				}
			}
		});
		passwordField.setBounds(125, 136, 120, 30);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/isengard.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(255, 34, 255, 200);
		contentPane.add(label);
	}

}
