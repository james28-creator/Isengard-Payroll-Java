package isengardPayroll;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

//import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;

import net.proteanit.sql.DbUtils;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;

public class IsengardPayroll {
	JFrame frame;
	JFrame frame_1;
	private JTextField textSIL;
	private JTextField textBasic;
	private JTextField textOverTime;
	private JTextField textGrossPay;
	private JTextField textMonthPay;
	private JTextField textNightDifferential;
	private JTextField textPayDate;
	private JTextField textSSS;
	private JTextField textPhilHealth;
	private JTextField textPagIbig;
	private JTextField textTotalDeduction;
	private JTextField textPayOfThePeriod;
	private JTextField textCashBan;
	private JTextField textOthers;
	private JTextField textGrossSalary;
	private JTextField textTotalDeductions;
	private JTextField textNetSalary;
	//-----------------------------------------------------------------------------------------
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	DefaultTableModel model = new DefaultTableModel();
	//-----------------------------------------------------------------------------------------
	double Basic;
	double SIL;
	double OverTime;
	double NightDifferential;
	double MonthPay;
	double Gross;
	
	double TotalBasic;
	double TotalSIL;
	double TotalOverTime;
	double TotalNightDifferential;
	double TotalMonthPay;
	//-----------------------------------------------------------------------------------------
	double SSS;
	double PhilHealth;
	double PagIbig;
	double CashBan;
	double Others;
	double Deduction;
	//-----------------------------------------------------------------------------------------
	double GrossSalary;
	double TotalDeductions;
	double NetSalary;
	//-----------------------------------------------------------------------------------------
	double BasicRate;
	double SILRate;
	double OvertimeRate;
	double NightDiffRate;
	double MonthRate;
	
	private JTextField textBasicRate;
	private JTextField textSILRate;
	private JTextField textOvertimeRate;
	private JTextField textNightDiffRate;
	private JTextField textMonthRate;
	//-----------------------------------------------------------------------------------------

	//-----------------------------------------------------------------------------------------
// 	Launch the application.
	public static void main(String[] args) {
//		FlatCobalt2IJTheme.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IsengardPayroll window = new IsengardPayroll();
					window.frame_1.setExtendedState(JFrame.MAXIMIZED_BOTH);
					window.frame_1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}	
// 	Create the application.
	private JTable table;
	private JTextField textId;
	private JTextField textFirstname2;
	private JTextField textMiddlename2;
	private JTextField textLastname2;
	private JTextField textID2;
	private JTextField textPagIbigNo;
	private JTextField textPhilHealthNo;
	private JTextField textSSSNo;
	private JTextField textContactNo;
	private JTextField textAge;
	private JTextField textLastname;
	private JTextField textMiddlename;
	private JTextField textFirstname;
	private JTextField textEmpRefNo;
	private JTextField textStreet;
	private JTextField textBarangay;
	private JTextField textCity;
	
	public static Connection ConnectDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\admin\\eclipse-workspace\\IsengardPayrollManagementSystem3\\EmployeeInformations.db");
			return con;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection error");
			return null;
		}
	}
	
	public IsengardPayroll() {
		initialize();
		con = ConnectDB();
	}
	
	public void refreshTable() {
		try {
			String query = "SELECT * FROM EmployeeInformations";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
//	Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLocation(-617, -197);
		frame_1 = new JFrame("Isengard Employee and Payroll Management System");
		frame_1.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\eclipse-workspace\\IsengardPayrollManagementSystem3\\Images\\isengard.png"));
		frame_1.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Calendar timer =  Calendar.getInstance();
				timer.getTime();
				SimpleDateFormat tTime = new SimpleDateFormat("HH-MM");
				tTime.format(timer.getTime());
				SimpleDateFormat tDate = new SimpleDateFormat("MMM/d/y");
				tDate.format(timer.getTime());
				textPayDate.setText(tDate.format(timer.getTime()));
			}
		});
		frame_1.setBounds(0, 0, 1366, 768);
		frame_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_1.getContentPane().setLayout(null);
		//-----------------------------------------------------------------------------------------
		JTabbedPane tabbedPane =  new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tabbedPane.setBounds(0, 0, 1366, 768);
		frame_1.getContentPane().add(tabbedPane);
		
				JPanel panel_1 = new JPanel();
				panel_1.setLocation(-420, -211);
				tabbedPane.addTab("Employee Management", null, panel_1, null);
				panel_1.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel.setBounds(624, 438, 150, 28);
				panel_1.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel_1_2_4_1 = new JLabel("Employee Database");
				lblNewLabel_1_2_4_1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_1_2_4_1.setBounds(10, 0, 130, 28);
				panel.add(lblNewLabel_1_2_4_1);
				
				JPanel panel_5_2_1 = new JPanel();
				panel_5_2_1.setLayout(null);
				panel_5_2_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_5_2_1.setBounds(100, 11, 155, 28);
				panel_1.add(panel_5_2_1);
				
				JLabel lblNewLabel_1_2_4 = new JLabel("Personal Information");
				lblNewLabel_1_2_4.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_1_2_4.setBounds(10, 0, 140, 28);
				panel_5_2_1.add(lblNewLabel_1_2_4);
				
				JPanel panel_4_2_1 = new JPanel();
				panel_4_2_1.setLayout(null);
				panel_4_2_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_4_2_1.setBounds(10, 24, 338, 360);
				panel_1.add(panel_4_2_1);
				
				JLabel lblNewLabel_1_1_1_2_3_1 = new JLabel("Search ERN");
				lblNewLabel_1_1_1_2_3_1.setBounds(10, 30, 93, 25);
				panel_4_2_1.add(lblNewLabel_1_1_1_2_3_1);
				lblNewLabel_1_1_1_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				textId = new JTextField();
				textId.setBounds(152, 28, 90, 30);
				panel_4_2_1.add(textId);
				textId.setFont(new Font("Tahoma", Font.BOLD, 14));
				textId.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {		
						try {
							String query = "SELECT * FROM EmployeeInformations WHERE EmpRefNo=?";
							pst = con.prepareStatement(query);
							pst.setString(1, textId.getText());
							rs = pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
							pst.close();
						}catch (Exception el) {
							el.printStackTrace();
						}
					}
				});
				textId.setColumns(10);
				
				JButton btnNewButton_4 = new JButton("Search");
				btnNewButton_4.setBounds(253, 27, 75, 30);
				panel_4_2_1.add(btnNewButton_4);
				btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				JLabel lblNewLabel_1_1_1_2_3 = new JLabel("Emp.Ref.No.");
				lblNewLabel_1_1_1_2_3.setBounds(10, 66, 93, 25);
				panel_4_2_1.add(lblNewLabel_1_1_1_2_3);
				lblNewLabel_1_1_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				textEmpRefNo = new JTextField();
				textEmpRefNo.setBounds(152, 64, 90, 30);
				panel_4_2_1.add(textEmpRefNo);
				textEmpRefNo.setFont(new Font("Tahoma", Font.BOLD, 14));
				textEmpRefNo.setColumns(10);
				
				JLabel lblNewLabel_1_1_1_2_4 = new JLabel("Firstname");
				lblNewLabel_1_1_1_2_4.setBounds(10, 102, 93, 25);
				panel_4_2_1.add(lblNewLabel_1_1_1_2_4);
				lblNewLabel_1_1_1_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				textFirstname = new JTextField();
				textFirstname.setBounds(152, 100, 176, 30);
				panel_4_2_1.add(textFirstname);
				textFirstname.setFont(new Font("Tahoma", Font.BOLD, 14));
				textFirstname.setColumns(10);
				
				JLabel lblNewLabel_1_1_1_2_5 = new JLabel("Middlename");
				lblNewLabel_1_1_1_2_5.setBounds(10, 138, 93, 25);
				panel_4_2_1.add(lblNewLabel_1_1_1_2_5);
				lblNewLabel_1_1_1_2_5.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				textMiddlename = new JTextField();
				textMiddlename.setBounds(152, 136, 176, 30);
				panel_4_2_1.add(textMiddlename);
				textMiddlename.setFont(new Font("Tahoma", Font.BOLD, 14));
				textMiddlename.setColumns(10);
				
				JLabel lblNewLabel_1_1_1_2_6 = new JLabel("Lastname");
				lblNewLabel_1_1_1_2_6.setBounds(10, 174, 93, 25);
				panel_4_2_1.add(lblNewLabel_1_1_1_2_6);
				lblNewLabel_1_1_1_2_6.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				textLastname = new JTextField();
				textLastname.setBounds(152, 172, 176, 30);
				panel_4_2_1.add(textLastname);
				textLastname.setFont(new Font("Tahoma", Font.BOLD, 14));
				textLastname.setColumns(10);
				
				JLabel lblNewLabel_1_1_1_2_7 = new JLabel("Gender");
				lblNewLabel_1_1_1_2_7.setBounds(10, 207, 93, 25);
				panel_4_2_1.add(lblNewLabel_1_1_1_2_7);
				lblNewLabel_1_1_1_2_7.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JLabel lblNewLabel_1_1_1_2_8 = new JLabel("Age");
				lblNewLabel_1_1_1_2_8.setBounds(10, 274, 93, 25);
				panel_4_2_1.add(lblNewLabel_1_1_1_2_8);
				lblNewLabel_1_1_1_2_8.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				textAge = new JTextField();
				textAge.setBounds(152, 272, 176, 30);
				panel_4_2_1.add(textAge);
				textAge.setFont(new Font("Tahoma", Font.BOLD, 14));
				textAge.setColumns(10);
				
				JLabel lblNewLabel_1_1_1_2_8_7 = new JLabel("Contact Number");
				lblNewLabel_1_1_1_2_8_7.setBounds(10, 310, 132, 25);
				panel_4_2_1.add(lblNewLabel_1_1_1_2_8_7);
				lblNewLabel_1_1_1_2_8_7.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				textContactNo = new JTextField();
				textContactNo.setBounds(152, 308, 176, 30);
				panel_4_2_1.add(textContactNo);
				textContactNo.setFont(new Font("Tahoma", Font.BOLD, 14));
				textContactNo.setColumns(10);
				
				JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
				rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				rdbtnNewRadioButton.setBounds(152, 205, 60, 30);
				panel_4_2_1.add(rdbtnNewRadioButton);
				
				JRadioButton rdbtnFemale = new JRadioButton("Female");
				rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 13));
				rdbtnFemale.setBounds(214, 205, 70, 30);
				panel_4_2_1.add(rdbtnFemale);
				
				JLabel lblNewLabel_1_1_1_2_8_2 = new JLabel("Birthdate");
				lblNewLabel_1_1_1_2_8_2.setBounds(10, 243, 93, 25);
				panel_4_2_1.add(lblNewLabel_1_1_1_2_8_2);
				lblNewLabel_1_1_1_2_8_2.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JDateChooser dateChooser = new JDateChooser();
				dateChooser.setForeground(new Color(255, 255, 255));
				dateChooser.setDateFormatString("MMM/d/y\r\n");
				dateChooser.setBounds(152, 238, 130, 30);
				panel_4_2_1.add(dateChooser);
				
				JPanel panel_7 = new JPanel();
				panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_7.setBounds(10, 451, 1343, 218);
				panel_1.add(panel_7);
				panel_7.setLayout(null);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setViewportBorder(null);
				scrollPane_1.setBounds(10, 29, 1323, 135);
				panel_7.add(scrollPane_1);
				
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {},
					new String[] {
						"EmpRefNo", "Firstname", "Middlename", "Lastname", "Gender", "Birthdate", "Age", "ContactNo", "Street", "Barangay", "City", "SSSNo","PhilHealthNo","PagIbigNo","1x1Picture","DateHired","ComDep" 
					}
				));
				table.setFont(new Font("Tahoma", Font.BOLD, 14));
				scrollPane_1.setViewportView(table);
				
				JButton btnNewButton_1 = new JButton("Load Data");
				btnNewButton_1.setBounds(10, 175, 100, 30);
				panel_7.add(btnNewButton_1);
				btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				JButton btnExit_1_1 = new JButton("Print Table");
				btnExit_1_1.setBounds(120, 175, 100, 30);
				panel_7.add(btnExit_1_1);
				btnExit_1_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {				
						MessageFormat header = new MessageFormat("Printing in progress");
						MessageFormat footer = new MessageFormat("Page {0, number, integer}");
						try {
							table.print();
						} catch(java.awt.print.PrinterException ev) {
							System.err.format("No Printer found", ev.getMessage());
						}
					}
				});
				btnExit_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						refreshTable();
					}
				});
				
				JPanel panel_5_2_2 = new JPanel();
				panel_5_2_2.setLayout(null);
				panel_5_2_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_5_2_2.setBounds(818, 256, 160, 28);
				panel_1.add(panel_5_2_2);
				
				JLabel lblNewLabel_1_2_5 = new JLabel("Company Deployment");
				lblNewLabel_1_2_5.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_1_2_5.setBounds(10, 0, 140, 28);
				panel_5_2_2.add(lblNewLabel_1_2_5);
				
				JPanel panel_4_2_2 = new JPanel();
				panel_4_2_2.setLayout(null);
				panel_4_2_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_4_2_2.setBounds(723, 269, 350, 115);
				panel_1.add(panel_4_2_2);
				
				JLabel lblNewLabel_1_1_1_2_8_10_1 = new JLabel("Company Deployment");
				lblNewLabel_1_1_1_2_8_10_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_1_1_1_2_8_10_1.setBounds(10, 66, 160, 25);
				panel_4_2_2.add(lblNewLabel_1_1_1_2_8_10_1);
				
				JComboBox comboBox_1_1 = new JComboBox();
				comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"ACLC", "CABADBARAN OFFICE"}));
				comboBox_1_1.setEditable(true);
				comboBox_1_1.setBounds(180, 65, 160, 30);
				panel_4_2_2.add(comboBox_1_1);
				
				JLabel lblNewLabel_1_1_1_2_8_10_1_1 = new JLabel("Date Hired");
				lblNewLabel_1_1_1_2_8_10_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_1_1_1_2_8_10_1_1.setBounds(10, 30, 145, 25);
				panel_4_2_2.add(lblNewLabel_1_1_1_2_8_10_1_1);
				
				JList list = new JList();
				list.setBounds(165, 172, 1, 1);
				panel_4_2_2.add(list);
				
				JDateChooser dateChooser_1 = new JDateChooser();
				dateChooser_1.setForeground(Color.BLACK);
				dateChooser_1.setDateFormatString("MMM/d/y\r\n");
				dateChooser_1.setBounds(180, 25, 130, 30);
				panel_4_2_2.add(dateChooser_1);
				
				JPanel panel_5_2_3 = new JPanel();
				panel_5_2_3.setLayout(null);
				panel_5_2_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_5_2_3.setBounds(461, 211, 150, 28);
				panel_1.add(panel_5_2_3);
				
				JLabel lblNewLabel_1_2_6 = new JLabel("Government Details");
				lblNewLabel_1_2_6.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_1_2_6.setBounds(10, 0, 135, 28);
				panel_5_2_3.add(lblNewLabel_1_2_6);
				
				JPanel panel_4_2_3 = new JPanel();
				panel_4_2_3.setLayout(null);
				panel_4_2_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_4_2_3.setBounds(398, 224, 275, 160);
				panel_1.add(panel_4_2_3);
				
				textSSSNo = new JTextField();
				textSSSNo.setBounds(152, 28, 113, 30);
				panel_4_2_3.add(textSSSNo);
				textSSSNo.setFont(new Font("Tahoma", Font.BOLD, 14));
				textSSSNo.setColumns(10);
				
				textPhilHealthNo = new JTextField();
				textPhilHealthNo.setBounds(152, 67, 113, 30);
				panel_4_2_3.add(textPhilHealthNo);
				textPhilHealthNo.setFont(new Font("Tahoma", Font.BOLD, 14));
				textPhilHealthNo.setColumns(10);
				
				textPagIbigNo = new JTextField();
				textPagIbigNo.setBounds(152, 106, 113, 30);
				panel_4_2_3.add(textPagIbigNo);
				textPagIbigNo.setFont(new Font("Tahoma", Font.BOLD, 14));
				textPagIbigNo.setColumns(10);
				
				JLabel lblNewLabel_1_1_1_2_8_8 = new JLabel("SSS Number");
				lblNewLabel_1_1_1_2_8_8.setBounds(10, 30, 93, 25);
				panel_4_2_3.add(lblNewLabel_1_1_1_2_8_8);
				lblNewLabel_1_1_1_2_8_8.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JLabel lblNewLabel_1_1_1_2_8_9 = new JLabel("PhilHealth Number");
				lblNewLabel_1_1_1_2_8_9.setBounds(10, 69, 132, 25);
				panel_4_2_3.add(lblNewLabel_1_1_1_2_8_9);
				lblNewLabel_1_1_1_2_8_9.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JLabel lblNewLabel_1_1_1_2_8_10 = new JLabel("Pag-Ibig Number");
				lblNewLabel_1_1_1_2_8_10.setBounds(10, 108, 120, 25);
				panel_4_2_3.add(lblNewLabel_1_1_1_2_8_10);
				lblNewLabel_1_1_1_2_8_10.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JPanel panel_5_2_3_1 = new JPanel();
				panel_5_2_3_1.setLayout(null);
				panel_5_2_3_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_5_2_3_1.setBounds(478, 11, 115, 28);
				panel_1.add(panel_5_2_3_1);
				
				JLabel lblNewLabel_1_2_6_1 = new JLabel("Home Address");
				lblNewLabel_1_2_6_1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_1_2_6_1.setBounds(10, 0, 100, 28);
				panel_5_2_3_1.add(lblNewLabel_1_2_6_1);
				
				JPanel panel_4_2_3_1 = new JPanel();
				panel_4_2_3_1.setLayout(null);
				panel_4_2_3_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_4_2_3_1.setBounds(398, 24, 275, 160);
				panel_1.add(panel_4_2_3_1);
				
				textStreet = new JTextField();
				textStreet.setFont(new Font("Tahoma", Font.BOLD, 14));
				textStreet.setColumns(10);
				textStreet.setBounds(152, 28, 113, 30);
				panel_4_2_3_1.add(textStreet);
				
				textBarangay = new JTextField();
				textBarangay.setFont(new Font("Tahoma", Font.BOLD, 14));
				textBarangay.setColumns(10);
				textBarangay.setBounds(152, 67, 113, 30);
				panel_4_2_3_1.add(textBarangay);
				
				textCity = new JTextField();
				textCity.setFont(new Font("Tahoma", Font.BOLD, 14));
				textCity.setColumns(10);
				textCity.setBounds(152, 106, 113, 30);
				panel_4_2_3_1.add(textCity);
				
				JLabel lblNewLabel_1_1_1_2_8_8_2 = new JLabel("House No. / Street");
				lblNewLabel_1_1_1_2_8_8_2.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_1_1_1_2_8_8_2.setBounds(10, 30, 132, 25);
				panel_4_2_3_1.add(lblNewLabel_1_1_1_2_8_8_2);
				
				JLabel lblNewLabel_1_1_1_2_8_9_2 = new JLabel("Barangay / District");
				lblNewLabel_1_1_1_2_8_9_2.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_1_1_1_2_8_9_2.setBounds(10, 69, 132, 25);
				panel_4_2_3_1.add(lblNewLabel_1_1_1_2_8_9_2);
				
				JLabel lblNewLabel_1_1_1_2_8_10_2 = new JLabel("City / Province");
				lblNewLabel_1_1_1_2_8_10_2.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_1_1_1_2_8_10_2.setBounds(10, 108, 113, 25);
				panel_4_2_3_1.add(lblNewLabel_1_1_1_2_8_10_2);
				
				JPanel panel_5_2_3_2 = new JPanel();
				panel_5_2_3_2.setLayout(null);
				panel_5_2_3_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_5_2_3_2.setBounds(836, 11, 125, 28);
				panel_1.add(panel_5_2_3_2);
				
				JLabel lblNewLabel_1_2_6_2 = new JLabel("Selecting Image");
				lblNewLabel_1_2_6_2.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_1_2_6_2.setBounds(10, 0, 110, 28);
				panel_5_2_3_2.add(lblNewLabel_1_2_6_2);
				
				JPanel panel_4_2_1_1 = new JPanel();
				panel_4_2_1_1.setLayout(null);
				panel_4_2_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_4_2_1_1.setBounds(780, 24, 240, 215);
				panel_1.add(panel_4_2_1_1);
				
				JPanel panel_3_2_1_1 = new JPanel();
				panel_3_2_1_1.setLayout(null);
				panel_3_2_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_3_2_1_1.setBounds(59, 35, 120, 120);
				panel_4_2_1_1.add(panel_3_2_1_1);
				
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setBounds(10, 11, 100, 100);
				panel_3_2_1_1.add(lblNewLabel);
				
				JButton btnNewButton_5 = new JButton("Browse");
				btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton_5.setBounds(80, 166, 80, 30);
				panel_4_2_1_1.add(btnNewButton_5);
				
				JPanel panel_4_2_1_1_1 = new JPanel();
				panel_4_2_1_1_1.setLayout(null);
				panel_4_2_1_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_4_2_1_1_1.setBounds(1125, 57, 120, 290);
				panel_1.add(panel_4_2_1_1_1);
				
				JButton btnCreate = new JButton("Save");
				btnCreate.setBounds(10, 11, 100, 50);
				panel_4_2_1_1_1.add(btnCreate);
				btnCreate.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				JButton btnUpdate = new JButton("Update");
				btnUpdate.setBounds(10, 65, 100, 50);
				panel_4_2_1_1_1.add(btnUpdate);
				btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				JButton btnNewButton = new JButton("Delete");
				btnNewButton.setBounds(10, 120, 100, 50);
				panel_4_2_1_1_1.add(btnNewButton);
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				JButton btnReset_1 = new JButton("Reset");
				btnReset_1.setBounds(10, 175, 100, 50);
				panel_4_2_1_1_1.add(btnReset_1);
				btnReset_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				JButton btnExit_1 = new JButton("Exit");
				btnExit_1.setBounds(10, 230, 100, 50);
				panel_4_2_1_1_1.add(btnExit_1);
				btnExit_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				
						//-----------------------------------------------------------------------------------------
						JPanel panel_2 = new JPanel();
						panel_2.setLocation(-397, -179);
						tabbedPane.addTab("Payroll Management", null, panel_2, null);
						panel_2.setLayout(null);
						
						JPanel panel_5_3_1_1 = new JPanel();
						panel_5_3_1_1.setLayout(null);
						panel_5_3_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_5_3_1_1.setBounds(20, 11, 130, 28);
						panel_2.add(panel_5_3_1_1);
						
						JLabel lblNewLabel_1_4_1_1 = new JLabel("Employee Details");
						lblNewLabel_1_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_1_4_1_1.setBounds(10, 0, 115, 28);
						panel_5_3_1_1.add(lblNewLabel_1_4_1_1);
						
						JPanel panel_5_3_1 = new JPanel();
						panel_5_3_1.setLayout(null);
						panel_5_3_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_5_3_1.setBounds(264, 179, 107, 28);
						panel_2.add(panel_5_3_1);
						
						JLabel lblNewLabel_1_4_1 = new JLabel("Rate Per Day");
						lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_1_4_1.setBounds(10, 0, 87, 28);
						panel_5_3_1.add(lblNewLabel_1_4_1);
						
						JPanel panel_5_1 = new JPanel();
						panel_5_1.setLayout(null);
						panel_5_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_5_1.setBounds(474, 179, 92, 28);
						panel_2.add(panel_5_1);
						
						JLabel lblNewLabel_1_3 = new JLabel("Deductions");
						lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_1_3.setBounds(10, 0, 75, 28);
						panel_5_1.add(lblNewLabel_1_3);
						//-----------------------------------------------------------------------------------------
						JPanel panel_5 = new JPanel();
						panel_5.setLayout(null);
						panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_5.setBounds(44, 179, 175, 28);
						panel_2.add(panel_5);
						
						JLabel lblNewLabel_1 = new JLabel("Gross Salary Calculation");
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_1.setBounds(10, 0, 155, 28);
						panel_5.add(lblNewLabel_1);
						
						JPanel panel_5_3 = new JPanel();
						panel_5_3.setLayout(null);
						panel_5_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_5_3.setBounds(-10, 188, 175, 28);
						panel_5.add(panel_5_3);
						
						JLabel lblNewLabel_1_4 = new JLabel("Gross Salary Calculation");
						lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_1_4.setBounds(10, 0, 155, 28);
						panel_5_3.add(lblNewLabel_1_4);
						
						JPanel panel_3_1 = new JPanel();
						panel_3_1.setLayout(null);
						panel_3_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_3_1.setBounds(10, 24, 1343, 110);
						panel_2.add(panel_3_1);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_2_3 = new JLabel("Date of Pay");
						lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_2_3.setBounds(536, 26, 86, 25);
						panel_3_1.add(lblNewLabel_1_2_3);
						
						textPayDate = new JTextField();
						textPayDate.setEditable(false);
						textPayDate.setFont(new Font("Tahoma", Font.BOLD, 14));
						textPayDate.setColumns(10);
						textPayDate.setBounds(671, 24, 135, 30);
						panel_3_1.add(textPayDate);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_2_3_2 = new JLabel("Pay for the Period");
						lblNewLabel_1_2_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_2_3_2.setBounds(536, 62, 125, 25);
						panel_3_1.add(lblNewLabel_1_2_3_2);
						
						textPayOfThePeriod = new JTextField();
						textPayOfThePeriod.setFont(new Font("Tahoma", Font.BOLD, 14));
						textPayOfThePeriod.setColumns(10);
						textPayOfThePeriod.setBounds(671, 60, 135, 30);
						panel_3_1.add(textPayOfThePeriod);
						
						JButton btnNewButton_3 = new JButton("Load Details");
						btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnNewButton_3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									pst = con.prepareStatement("select Firstname, Middlename, Lastname from EmployeeInformations where EmpRefNo = ?");
									int id = Integer.parseInt(textID2.getText());
									pst.setInt(1, id);
									rs = pst.executeQuery();
									if (rs.next() == false) {
										JOptionPane.showMessageDialog(null, "Record not found");
										textID2.setText("");
										textFirstname2.setText("");
										textMiddlename2.setText("");
										textLastname2.setText("");
									} else {
										textFirstname2.setText(rs.getString("Firstname"));
										textMiddlename2.setText(rs.getString("Middlename"));
										textLastname2.setText(rs.getString("Lastname"));
									}					
								} catch (Exception el) {
									el.printStackTrace();
								}
							}
						});
						btnNewButton_3.setBounds(230, 24, 110, 30);
						panel_3_1.add(btnNewButton_3);
						
						JLabel lblNewLabel_1_2_3_2_1 = new JLabel("Fullname");
						lblNewLabel_1_2_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_2_3_2_1.setBounds(10, 62, 75, 25);
						panel_3_1.add(lblNewLabel_1_2_3_2_1);
						
						textFirstname2 = new JTextField();
						textFirstname2.setEditable(false);
						textFirstname2.setFont(new Font("Tahoma", Font.BOLD, 14));
						textFirstname2.setColumns(10);
						textFirstname2.setBounds(95, 60, 125, 30);
						panel_3_1.add(textFirstname2);
						
						textMiddlename2 = new JTextField();
						textMiddlename2.setEditable(false);
						textMiddlename2.setFont(new Font("Tahoma", Font.BOLD, 14));
						textMiddlename2.setColumns(10);
						textMiddlename2.setBounds(230, 60, 125, 30);
						panel_3_1.add(textMiddlename2);
						
						textLastname2 = new JTextField();
						textLastname2.setEditable(false);
						textLastname2.setFont(new Font("Tahoma", Font.BOLD, 14));
						textLastname2.setColumns(10);
						textLastname2.setBounds(365, 60, 125, 30);
						panel_3_1.add(textLastname2);
						
						JLabel lblNewLabel_1_2_3_2_4 = new JLabel("EmpRefNo");
						lblNewLabel_1_2_3_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_2_3_2_4.setBounds(10, 25, 75, 25);
						panel_3_1.add(lblNewLabel_1_2_3_2_4);
						
						textID2 = new JTextField();
						textID2.setFont(new Font("Tahoma", Font.BOLD, 14));
						textID2.setColumns(10);
						textID2.setBounds(95, 24, 125, 30);
						panel_3_1.add(textID2);
						
						JPanel panel_4 = new JPanel();
						panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_4.setBounds(11, 192, 373, 296);
						panel_2.add(panel_4);
						panel_4.setLayout(null);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_1_1_2 = new JLabel("Basic Salary");
						lblNewLabel_1_1_1_2.setBounds(10, 29, 93, 25);
						lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_4.add(lblNewLabel_1_1_1_2);
						
						textBasic = new JTextField();
						textBasic.setBounds(152, 27, 85, 30);
						textBasic.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_4.add(textBasic);
						textBasic.setColumns(10);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_2_1 = new JLabel("SIL");
						lblNewLabel_1_2_1.setBounds(10, 68, 26, 25);
						lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_4.add(lblNewLabel_1_2_1);
						
						textSIL = new JTextField();
						textSIL.setBounds(152, 66, 85, 30);
						textSIL.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_4.add(textSIL);
						textSIL.setColumns(10);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Over Time");
						lblNewLabel_1_1_1_1_1.setBounds(10, 107, 77, 25);
						lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_4.add(lblNewLabel_1_1_1_1_1);
						
						textOverTime = new JTextField();
						textOverTime.setBounds(152, 105, 85, 30);
						textOverTime.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_4.add(textOverTime);
						textOverTime.setColumns(10);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Night Differential");
						lblNewLabel_1_1_1_1_1_1.setBounds(10, 146, 132, 25);
						lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_4.add(lblNewLabel_1_1_1_1_1_1);
						
						textNightDifferential = new JTextField();
						textNightDifferential.setBounds(152, 144, 85, 30);
						textNightDifferential.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_4.add(textNightDifferential);
						textNightDifferential.setColumns(10);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_1_1_3 = new JLabel("13th Month Pay");
						lblNewLabel_1_1_1_3.setBounds(10, 185, 117, 25);
						lblNewLabel_1_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
						panel_4.add(lblNewLabel_1_1_1_3);
						
						textMonthPay = new JTextField();
						textMonthPay.setBounds(152, 183, 85, 30);
						panel_4.add(textMonthPay);
						textMonthPay.setFont(new Font("Tahoma", Font.BOLD, 14));
						textMonthPay.setColumns(10);
						
						textBasicRate = new JTextField();
						textBasicRate.setFont(new Font("Tahoma", Font.BOLD, 14));
						textBasicRate.setColumns(10);
						textBasicRate.setBounds(264, 27, 85, 30);
						panel_4.add(textBasicRate);
						
						textSILRate = new JTextField();
						textSILRate.setFont(new Font("Tahoma", Font.BOLD, 14));
						textSILRate.setColumns(10);
						textSILRate.setBounds(264, 66, 85, 30);
						panel_4.add(textSILRate);
						
						textOvertimeRate = new JTextField();
						textOvertimeRate.setFont(new Font("Tahoma", Font.BOLD, 14));
						textOvertimeRate.setColumns(10);
						textOvertimeRate.setBounds(264, 105, 85, 30);
						panel_4.add(textOvertimeRate);
						
						textNightDiffRate = new JTextField();
						textNightDiffRate.setFont(new Font("Tahoma", Font.BOLD, 14));
						textNightDiffRate.setColumns(10);
						textNightDiffRate.setBounds(264, 144, 85, 30);
						panel_4.add(textNightDiffRate);
						
						textMonthRate = new JTextField();
						textMonthRate.setFont(new Font("Tahoma", Font.BOLD, 14));
						textMonthRate.setColumns(10);
						textMonthRate.setBounds(264, 183, 85, 30);
						panel_4.add(textMonthRate);
						
						JButton btnNewButton_2 = new JButton("Load");
						btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
						btnNewButton_2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {				
								BasicRate = 350;
								SILRate = 4.50;
								OvertimeRate = 5.23;
								NightDiffRate = 10.32;
								MonthRate = 25.44;
	
								String BasicRating = String.format("P %.2f", BasicRate);
								textBasicRate.setText(BasicRating);				
								String SILRating = String.format("P %.2f", SILRate);
								textSILRate.setText(SILRating);
								String OvertimeRating = String.format("P %.2f", OvertimeRate);
								textOvertimeRate.setText(OvertimeRating);
								String NightDiffRating = String.format("P %.2f", NightDiffRate);
								textNightDiffRate.setText(NightDiffRating);
								String MonthRating = String.format("P %.2f", MonthRate);
								textMonthRate.setText(MonthRating);
							}
						});
						btnNewButton_2.setBounds(264, 224, 85, 30);
						panel_4.add(btnNewButton_2);
						//-----------------------------------------------------------------------------------------
						JPanel panel_6 = new JPanel();
						panel_6.setLayout(null);
						panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_6.setBounds(11, 499, 373, 50);
						panel_2.add(panel_6);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_2_2 = new JLabel("Gross Salary");
						lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_2_2.setBounds(10, 11, 85, 25);
						panel_6.add(lblNewLabel_1_2_2);
						
						textGrossPay = new JTextField();
						textGrossPay.setBounds(185, 9, 130, 30);
						panel_6.add(textGrossPay);
						textGrossPay.setFont(new Font("Tahoma", Font.BOLD, 14));
						textGrossPay.setColumns(10);
						//-----------------------------------------------------------------------------------------
						JPanel panel_6_1 = new JPanel();
						panel_6_1.setLayout(null);
						panel_6_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_6_1.setBounds(403, 499, 232, 48);
						panel_2.add(panel_6_1);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_2_2_1 = new JLabel("Total Deduction");
						lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_2_2_1.setBounds(10, 11, 109, 25);
						panel_6_1.add(lblNewLabel_1_2_2_1);
						
						textTotalDeduction = new JTextField();
						textTotalDeduction.setBounds(132, 9, 90, 30);
						panel_6_1.add(textTotalDeduction);
						textTotalDeduction.setFont(new Font("Tahoma", Font.BOLD, 14));
						textTotalDeduction.setColumns(10);
						//-----------------------------------------------------------------------------------------
						JPanel panel_5_2 = new JPanel();
						panel_5_2.setLayout(null);
						panel_5_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_5_2.setBounds(695, 179, 143, 28);
						panel_2.add(panel_5_2);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_2 = new JLabel("Net Pay Calculation");
						lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
						lblNewLabel_1_2.setBounds(10, 0, 124, 28);
						panel_5_2.add(lblNewLabel_1_2);
						
						JPanel panel_4_2 = new JPanel();
						panel_4_2.setLayout(null);
						panel_4_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_4_2.setBounds(653, 192, 232, 296);
						panel_2.add(panel_4_2);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Gross Salary");
						lblNewLabel_1_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_1_1_2_2.setBounds(10, 29, 96, 25);
						panel_4_2.add(lblNewLabel_1_1_1_2_2);
						
						textGrossSalary = new JTextField();
						textGrossSalary.setFont(new Font("Tahoma", Font.BOLD, 14));
						textGrossSalary.setColumns(10);
						textGrossSalary.setBounds(127, 27, 95, 30);
						panel_4_2.add(textGrossSalary);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_2_1_2 = new JLabel("Total Deduction");
						lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_2_1_2.setBounds(10, 68, 117, 25);
						panel_4_2.add(lblNewLabel_1_2_1_2);
						
						textTotalDeductions = new JTextField();
						textTotalDeductions.setFont(new Font("Tahoma", Font.BOLD, 14));
						textTotalDeductions.setColumns(10);
						textTotalDeductions.setBounds(127, 66, 95, 30);
						panel_4_2.add(textTotalDeductions);
						//-----------------------------------------------------------------------------------------
						JPanel panel_6_2 = new JPanel();
						panel_6_2.setLayout(null);
						panel_6_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_6_2.setBounds(653, 499, 232, 48);
						panel_2.add(panel_6_2);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_2_2_2 = new JLabel("Net Pay");
						lblNewLabel_1_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_2_2_2.setBounds(10, 11, 59, 25);
						panel_6_2.add(lblNewLabel_1_2_2_2);
						
						textNetSalary = new JTextField();
						textNetSalary.setBounds(92, 9, 130, 30);
						panel_6_2.add(textNetSalary);
						textNetSalary.setFont(new Font("Tahoma", Font.BOLD, 14));
						textNetSalary.setColumns(10);
						//-----------------------------------------------------------------------------------------
						JPanel panel_1_2 = new JPanel();
						panel_1_2.setLayout(null);
						panel_1_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						panel_1_2.setBounds(901, 179, 452, 48);
						panel_2.add(panel_1_2);
						
						JLabel lblNewLabel_1_3_1 = new JLabel("P A Y S L I P");
						lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 30));
						lblNewLabel_1_3_1.setBounds(132, 11, 186, 26);
						panel_1_2.add(lblNewLabel_1_3_1);
						//-----------------------------------------------------------------------------------------
						JPanel rtfPaySlip = new JPanel();
						rtfPaySlip.setToolTipText("");
						rtfPaySlip.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						rtfPaySlip.setBounds(901, 238, 452, 335);
						panel_2.add(rtfPaySlip);
						rtfPaySlip.setLayout(null);
						
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(10, 11, 430, 313);
						rtfPaySlip.add(scrollPane);
						
						JTextArea textPaySlip = new JTextArea();
						textPaySlip.setEditable(false);
						textPaySlip.setFont(new Font("Monospaced", Font.PLAIN, 15));
						scrollPane.setViewportView(textPaySlip);
						//-----------------------------------------------------------------------------------------
						JButton btnTotal = new JButton("Calculate");
						btnTotal.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								//-----------------------------------------------------------------------------------------
								Basic = Double.parseDouble(textBasic.getText());
								SIL = Double.parseDouble(textSIL.getText());
								OverTime = Double.parseDouble(textOverTime.getText());
								NightDifferential = Double.parseDouble(textNightDifferential.getText());
								MonthPay = Double.parseDouble(textMonthPay.getText());
											
								TotalBasic = Basic * BasicRate;
								TotalSIL = SIL * SILRate;
								TotalOverTime = OverTime * OvertimeRate;
								TotalNightDifferential = NightDifferential * NightDiffRate;
								TotalMonthPay = MonthPay * MonthRate;
								
								Gross = TotalBasic + TotalSIL + TotalOverTime + TotalNightDifferential + TotalMonthPay;				
								String GrossPay = String.format("P %.2f", Gross);
								textGrossPay.setText(GrossPay);
								//-----------------------------------------------------------------------------------------				
								SSS = Double.parseDouble(textSSS.getText());
								PhilHealth = Double.parseDouble(textPhilHealth.getText());
								PagIbig = Double.parseDouble(textPagIbig.getText());
								CashBan = Double.parseDouble(textCashBan.getText());
								Others = Double.parseDouble(textOthers.getText());
								
								Deduction = SSS + PhilHealth + PagIbig + CashBan + Others;				
								String TotalDeduction = String.format("P %.2f", Deduction);
								textTotalDeduction.setText(TotalDeduction);
								//-----------------------------------------------------------------------------------------
								String GrossSalary = String.format("P %.2f", Gross);
								textGrossSalary.setText(GrossSalary);				
								String TotalDeductions = String.format("P %.2f", Deduction);
								textTotalDeductions.setText(TotalDeductions);
								NetSalary = Gross - Deduction;
								String NetPay = String.format("P %.2f", NetSalary);
								textNetSalary.setText(NetPay);
							}
						});
						btnTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnTotal.setBounds(960, 584, 100, 35);
						panel_2.add(btnTotal);
						
						JButton btnPaySlip = new JButton("Pay Slip");
						btnPaySlip.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								textPaySlip.append("\t       I S E N G A R D\n");
								textPaySlip.append("\t       SECURITY AGENCY\n");
								textPaySlip.append("\t        P A Y S L I P\n\n");
								textPaySlip.append("============================================\n");
								textPaySlip.append(" Emp.Ref.No: " + textID2.getText() + "\n");
								textPaySlip.append(" Name: " + textFirstname2.getText() + " " + textMiddlename2.getText() + " " + textLastname2.getText() +"\n");
								textPaySlip.append(" Date of Pay: " + textPayDate.getText() + "\n");
								textPaySlip.append(" Pay of the Period: " + textPayOfThePeriod.getText() + "\n");
								textPaySlip.append("============================================\n");
								textPaySlip.append("\t\t  G R O S S\n");
								textPaySlip.append("============================================\n");
								textPaySlip.append(" Basic Salary: " + TotalBasic + "\n");
								textPaySlip.append(" SIL : " + TotalSIL + "\n");
								textPaySlip.append(" OverTime: " + TotalOverTime + "\n");
								textPaySlip.append(" Night Differential: " + TotalNightDifferential + "\n");
								textPaySlip.append(" 13th Month Pay: " + TotalMonthPay + "\n");
								textPaySlip.append("\t\n");
								textPaySlip.append(" Gross Salary: " + "P " + Gross + "\n");
								textPaySlip.append("============================================\n");
								textPaySlip.append("\t     D E D U C T I O N S\n");
								textPaySlip.append("============================================\n");
								textPaySlip.append(" SSS: " + textSSS.getText() + "\n");
								textPaySlip.append(" PhilHealth: " + textPhilHealth.getText() + "\n");
								textPaySlip.append(" PagIbig: " + textPagIbig.getText() + "\n");
								textPaySlip.append(" Cash Ban: " + textCashBan.getText() + "\n");
								textPaySlip.append(" Others: " + textOthers.getText() + "\n");
								textPaySlip.append("\t\n");
								textPaySlip.append(" Total Deduction: " + textTotalDeduction.getText() + "\n");
								textPaySlip.append("============================================\n");
								textPaySlip.append(" Net Amount Received: " + "P " + NetSalary + "\n");
								textPaySlip.append("============================================\n");
								textPaySlip.append("	I acknowledge to have received from\n");
								textPaySlip.append(" ISENGARD SECURITY AGENCY the amount stated\n");
								textPaySlip.append(" above as full compensation for the service\n");
								textPaySlip.append(" rendered during the period and I have no\n");
								textPaySlip.append(" complaints whatsoever.\n");
								textPaySlip.append("\n");
								textPaySlip.append("      ____________         _____________\n");
								textPaySlip.append("          Date		     Signature");
							}
						});
						btnPaySlip.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnPaySlip.setBounds(1084, 584, 100, 35);
						panel_2.add(btnPaySlip);
						
						JButton btnReset = new JButton("Reset");
						btnReset.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								textID2.setText(null);
								textFirstname2.setText(null);
								textMiddlename2.setText(null);
								textLastname2.setText(null);
								textBasic.setText(null);
								textSIL.setText(null);
								textOverTime.setText(null);
								textNightDifferential.setText(null);
								textMonthPay.setText(null);
								textGrossPay.setText(null);
								textSSS.setText(null);
								textPhilHealth.setText(null);
								textPagIbig.setText(null);
								textCashBan.setText(null);
								textOthers.setText(null);
								textTotalDeduction.setText(null);
								textPaySlip.setText(null);
								textGrossSalary.setText(null);
								textTotalDeductions.setText(null);
								textNetSalary.setText(null);
								
								textBasicRate.setText(null);
								textSILRate.setText(null);
								textOvertimeRate.setText(null);
								textNightDiffRate.setText(null);
								textMonthRate.setText(null);
							}
						});
						btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnReset.setBounds(1020, 630, 100, 35);
						panel_2.add(btnReset);
						
						JButton btnExit = new JButton("Exit");
						btnExit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {			
								frame = new JFrame("Exit");
								if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Payroll Management",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
									System.exit(0);
								}
							}
						});
						btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnExit.setBounds(1149, 630, 100, 35);
						panel_2.add(btnExit);
						
						JButton btnPrint = new JButton("Print");
						btnPrint.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {				
								MessageFormat header = new MessageFormat("Printing in progress");
								MessageFormat footer = new MessageFormat("Page {0, number, integer}");				
								try {
									textPaySlip.print();
								} catch(java.awt.print.PrinterException ev) {
									System.err.format("No Printer found", ev.getMessage());
								}
							}
						});
						btnPrint.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnPrint.setBounds(1208, 584, 100, 35);
						panel_2.add(btnPrint);
						//-----------------------------------------------------------------------------------------
						JPanel panel_4_1 = new JPanel();
						panel_4_1.setBounds(403, 192, 232, 296);
						panel_2.add(panel_4_1);
						panel_4_1.setLayout(null);
						panel_4_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_1_1_2_1 = new JLabel("SSS");
						lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_1_1_2_1.setBounds(10, 29, 30, 25);
						panel_4_1.add(lblNewLabel_1_1_1_2_1);
						
						textSSS = new JTextField();
						textSSS.setFont(new Font("Tahoma", Font.BOLD, 14));
						textSSS.setColumns(10);
						textSSS.setBounds(132, 27, 90, 30);
						panel_4_1.add(textSSS);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_2_1_1 = new JLabel("PhilHealth");
						lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_2_1_1.setBounds(10, 68, 78, 25);
						panel_4_1.add(lblNewLabel_1_2_1_1);
						
						textPhilHealth = new JTextField();
						textPhilHealth.setFont(new Font("Tahoma", Font.BOLD, 14));
						textPhilHealth.setColumns(10);
						textPhilHealth.setBounds(132, 66, 90, 30);
						panel_4_1.add(textPhilHealth);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Pag-IBIG");
						lblNewLabel_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_1_1_1_1_2.setBounds(10, 107, 69, 25);
						panel_4_1.add(lblNewLabel_1_1_1_1_1_2);
						
						textPagIbig = new JTextField();
						textPagIbig.setFont(new Font("Tahoma", Font.BOLD, 14));
						textPagIbig.setColumns(10);
						textPagIbig.setBounds(132, 105, 90, 30);
						panel_4_1.add(textPagIbig);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_1_1_1_1_2_1 = new JLabel("Cash Ban");
						lblNewLabel_1_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_1_1_1_1_2_1.setBounds(10, 146, 68, 25);
						panel_4_1.add(lblNewLabel_1_1_1_1_1_2_1);
						
						textCashBan = new JTextField();
						textCashBan.setFont(new Font("Tahoma", Font.BOLD, 14));
						textCashBan.setColumns(10);
						textCashBan.setBounds(132, 144, 90, 30);
						panel_4_1.add(textCashBan);
						//-----------------------------------------------------------------------------------------
						JLabel lblNewLabel_1_1_1_1_1_2_2 = new JLabel("Others");
						lblNewLabel_1_1_1_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
						lblNewLabel_1_1_1_1_1_2_2.setBounds(10, 185, 51, 25);
						panel_4_1.add(lblNewLabel_1_1_1_1_1_2_2);
						
						textOthers = new JTextField();
						textOthers.setBounds(132, 183, 90, 30);
						panel_4_1.add(textOthers);
						textOthers.setFont(new Font("Tahoma", Font.BOLD, 14));
						textOthers.setColumns(10);
				btnExit_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame = new JFrame("Exit");
						if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Employee Management", 
							JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
							System.exit(0);
						}
					}
				});
				btnReset_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textId.setText(null);
						textEmpRefNo.setText(null);
						textFirstname.setText(null);
						textMiddlename.setText(null);
						textLastname.setText(null);
						textAge.setText(null);
						textContactNo.setText(null);
						textStreet.setText(null);
						textBarangay.setText(null);
						textCity.setText(null);
						textSSSNo.setText(null);
						textPhilHealthNo.setText(null);
						textPagIbigNo.setText(null);
					}
				});
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String textPassField = textId.getText();
						try {
							if(textPassField.equals("")) {
								JOptionPane.showMessageDialog(null, "Please search the Employee Reference Number to delete data.");
							} else {
								String query = "DELETE FROM EmployeeInformations WHERE EmpRefNo = '"+textId.getText()+"' ";
								pst = con.prepareStatement(query);
								pst.execute();
								JOptionPane.showMessageDialog(null, "Data Deleted");
								pst.close();
							}
						} catch (Exception ex){
							ex.printStackTrace();
						}
						refreshTable();
					}
				});
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {			
						try {
							String query = "UPDATE EmployeeInformations SET Firstname = '"+textFirstname.getText()+"', Middlename = '"+textMiddlename.getText()+"', Lastname = '"+textLastname.getText()+"', Age = '"+textAge.getText()+"', ContactNo = '"+textContactNo.getText()+"', Street = '"+textStreet.getText()+"', Barangay = '"+textBarangay.getText()+"', City = '"+textCity.getText()+"', SSSNo = '"+textSSSNo.getText()+"', PhilHealthNo = '"+textPhilHealthNo.getText()+"', PagIbigNo = '"+textPagIbigNo.getText()+"' WHERE EmpRefNo = '"+textId.getText()+"' ";
							pst = con.prepareStatement(query);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Data Updated");
							pst.close();
						} catch(Exception el) {
							el.printStackTrace();
						}
						refreshTable();
					}
				});
				btnCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {				
						try {
							String query = "INSERT INTO EmployeeInformations(EmpRefNo, Firstname, Middlename, Lastname, Age, ContactNo, Street, Barangay, City, SSSNo, PhilHealthNo, PagIbigNo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
							pst = con.prepareStatement(query);
							pst.setString(1, textEmpRefNo.getText());
							pst.setString(2, textFirstname.getText());
							pst.setString(3, textMiddlename.getText());	
							pst.setString(4, textLastname.getText());
							pst.setString(5, textAge.getText());
							pst.setString(6, textContactNo.getText());
							pst.setString(7, textStreet.getText());
							pst.setString(8, textBarangay.getText());
							pst.setString(9, textCity.getText());
							pst.setString(10, textSSSNo.getText());
							pst.setString(11, textPhilHealthNo.getText());	
							pst.setString(12, textPagIbigNo.getText());
							pst.execute();
							JOptionPane.showMessageDialog(null, "Data Saved");
							pst.close();
						} catch (Exception ev){
							ev.printStackTrace();
						}
						refreshTable();
					}
				});
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {				
					try {
						pst = con.prepareStatement("SELECT EmpRefNo, Firstname, Middlename, Lastname, Age, ContactNo, Street, Barangay, City, SSSNo, PhilHealthNo, PagIbigNo FROM EmployeeInformations WHERE EmpRefNo = ?");
						int id = Integer.parseInt(textId.getText());
						pst.setInt(1, id);
						rs = pst.executeQuery();
						if (rs.next() == false) {
							JOptionPane.showMessageDialog(null, "Record not found");
							textId.setText("");
							textEmpRefNo.setText("");
							textFirstname.setText("");
							textMiddlename.setText("");
							textLastname.setText("");
							textAge.setText("");
							textContactNo.setText("");
							textStreet.setText("");
							textBarangay.setText("");
							textCity.setText("");
							textSSSNo.setText("");
							textPhilHealthNo.setText("");
							textPagIbigNo.setText("");
						} else {
							textEmpRefNo.setText(rs.getString("EmpRefNo"));
							textFirstname.setText(rs.getString("Firstname"));
							textMiddlename.setText(rs.getString("Middlename"));
							textLastname.setText(rs.getString("Lastname"));
							textAge.setText(rs.getString("Age"));
							textContactNo.setText(rs.getString("ContactNo"));
							textStreet.setText(rs.getString("Street"));
							textBarangay.setText(rs.getString("Barangay"));
							textCity.setText(rs.getString("City"));
							textSSSNo.setText(rs.getString("SSSNo"));
							textPhilHealthNo.setText(rs.getString("PhilHealthNo"));
							textPagIbigNo.setText(rs.getString("PagIbigNo"));
						}				
					} catch (Exception el) {
						el.printStackTrace();
					}
					}
				});
	}
}
