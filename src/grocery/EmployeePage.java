package grocery;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class EmployeePage {

	private JFrame frame;
	private JTable table;
	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtId;
	private JTextField txtMobile;
	private JTextField txtSalary;
	private JTextField txtAddress;
	private JPasswordField passwordField;
	private JComboBox comboType, comboStatus, comboDesignation, comboShift, comboGender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePage window = new EmployeePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeePage() {
		initialize();
		BuildConnection();
		loadTable();
	}
	
	Connection con;
    PreparedStatement prestm;
    ResultSet rst;
    
	public void BuildConnection() {
	 	         
	        try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerymanagement", "root", "root");
				System.out.println("Established connection");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}    
	}
	
	public void loadTable() {
	    try {
	        prestm = con.prepareStatement("SELECT * FROM employee");
	        rst = prestm.executeQuery();
	        table.setModel(DbUtils.resultSetToTableModel(rst));

	        table.setDefaultEditor(Object.class, null);
	        table.getTableHeader().setReorderingAllowed(false);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 957, 598);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Employee Management");
		ImageIcon img = new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\employee (1).png");
		frame.setIconImage(img.getImage());
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ManagePage managepageTab = new ManagePage();
				managepageTab.setVisible(true);
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBackground(new Color(0, 0, 0));
		btnClear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        txtId.setText("");
		        txtName.setText("");
		        txtMobile.setText("");
		        txtEmail.setText("");
		        txtSalary.setText("");
		        txtAddress.setText("");
		        passwordField.setText("");

		        comboGender.setSelectedIndex(0);
		        comboDesignation.setSelectedIndex(0);
		        comboShift.setSelectedIndex(0);
		        comboType.setSelectedIndex(0);
		        comboStatus.setSelectedIndex(0);
		    }
		});

		btnClear.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnClear.setBounds(829, 104, 107, 36);
		frame.getContentPane().add(btnClear);
		btnBack.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnBack.setBounds(10, 11, 87, 33);
		frame.getContentPane().add(btnBack);
		
		JLabel lblEmployee = new JLabel("Employee Management");
		lblEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployee.setFont(new Font("Lucida Sans", Font.BOLD, 30));
		lblEmployee.setBounds(294, 21, 367, 44);
		frame.getContentPane().add(lblEmployee);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(0, 0, 0));
		btnSearch.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id = txtId.getText();

		        try {
		            prestm = con.prepareStatement("SELECT *FROM employee WHERE EmployeeId = ?");
		            prestm.setString(1, id);
		            rst = prestm.executeQuery();

		            if (rst.next()) {
		                txtId.setText(rst.getString("EmployeeId"));
		                txtName.setText(rst.getString("Name"));
		                txtMobile.setText(rst.getString("Mobile"));
		                txtEmail.setText(rst.getString("Email"));
		                txtSalary.setText(rst.getString("Salary"));
		                txtAddress.setText(rst.getString("Address"));
		                comboGender.setSelectedItem(rst.getString("Gender"));
		                comboDesignation.setSelectedItem(rst.getString("Designation"));
		                comboShift.setSelectedItem(rst.getString("Shift"));
		                comboType.setSelectedItem(rst.getString("Type"));
		                comboStatus.setSelectedItem(rst.getString("Status"));
		            } else {
		                JOptionPane.showMessageDialog(frame, "Employee not found.");
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnSearch.setBounds(829, 59, 107, 36);
		frame.getContentPane().add(btnSearch);
		btnSearch.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(0, 0, 0));
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	String password = new String(passwordField.getPassword());
	            if (!password.equals("manager@123")) {
	                JOptionPane.showMessageDialog(frame, "Incorrect password. Details cannot be saved.", "Message", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
		        String id = txtId.getText();

		        try {
		            prestm = con.prepareStatement("DELETE FROM employee WHERE EmployeeId=?");
		            prestm.setString(1, id);

		            int rowsDeleted = prestm.executeUpdate();
		            if (rowsDeleted > 0) {
		                JOptionPane.showMessageDialog(frame, "Employee deleted successfully.");
		                loadTable();
		            } else {
		                JOptionPane.showMessageDialog(frame, "Delete failed. Employee ID not found.", "Message", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error deleting employee.", "Message", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnDelete.setBounds(829, 271, 107, 36);
		frame.getContentPane().add(btnDelete);
		btnDelete.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(0, 0, 0));
		btnUpdate.setBounds(829, 224, 107, 36);
		frame.getContentPane().add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	String password = new String(passwordField.getPassword());
	            if (!password.equals("manager@123")) {
	                JOptionPane.showMessageDialog(frame, "Incorrect password. Details cannot be saved.", "Message", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
		        String id = txtId.getText();
		        String name = txtName.getText();
		        String mobile = txtMobile.getText();
		        String email = txtEmail.getText();
		        String salary = txtSalary.getText();
		        String address = txtAddress.getText();
		        String gender = (String) comboGender.getSelectedItem();
		        String designation = (String) comboDesignation.getSelectedItem();
		        String shift = (String) comboShift.getSelectedItem();
		        String type = (String) comboType.getSelectedItem();
		        String status = (String) comboStatus.getSelectedItem();

		        try {
		            prestm = con.prepareStatement(
		                "UPDATE employee SET Name=?, Mobile=?, Email=?, Salary=?, Address=?, Gender=?, Designation=?, Shift=?, Type=?, Status=? WHERE EmployeeId=?"
		            );
		            prestm.setString(1, name);
		            prestm.setString(2, mobile);
		            prestm.setString(3, email);
		            prestm.setString(4, salary);
		            prestm.setString(5, address);
		            prestm.setString(6, gender);
		            prestm.setString(7, designation);
		            prestm.setString(8, shift);
		            prestm.setString(9, type);
		            prestm.setString(10, status);
		            prestm.setString(11, id);

		            int rowsUpdated = prestm.executeUpdate();
		            if (rowsUpdated > 0) {
		                JOptionPane.showMessageDialog(frame, "Employee details updated successfully.");
		                loadTable();
		            } else {
		                JOptionPane.showMessageDialog(frame, "Update failed. Employee ID not found.", "Message", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error updating employee details.", "Message", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnUpdate.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String password = new String(passwordField.getPassword());
	            if (!password.equals("manager@123")) {
	                JOptionPane.showMessageDialog(frame, "Incorrect password. Details cannot be saved.", "Message", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            String id = txtId.getText();
	            String name = txtName.getText();
	            String mobile = txtMobile.getText();
	            String email = txtEmail.getText();
	            String salary = txtSalary.getText();
	            String address = txtAddress.getText();
	            String gender = (String) comboGender.getSelectedItem();
	            String designation = (String) comboDesignation.getSelectedItem();
	            String shift = (String) comboShift.getSelectedItem();
	            String type = (String) comboType.getSelectedItem();
	            String status = (String) comboStatus.getSelectedItem();

	            try {
	                PreparedStatement prestm = con.prepareStatement(
	                    "INSERT INTO employee (EmployeeId, Name, Mobile, Email, Salary, Address, Gender, Designation, Shift, Type, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
	                );
	                prestm.setString(1, id);
	                prestm.setString(2, name);
	                prestm.setString(3, mobile);
	                prestm.setString(4, email);
	                prestm.setString(5, salary);
	                prestm.setString(6, address);
	                prestm.setString(7, gender);
	                prestm.setString(8, designation);
	                prestm.setString(9, shift);
	                prestm.setString(10, type);
	                prestm.setString(11, status);

	                prestm.executeUpdate();
	                JOptionPane.showMessageDialog(frame, "Employee details saved successfully.");

	                txtId.setText("");
	                txtName.setText("");
	                txtMobile.setText("");
	                txtEmail.setText("");
	                txtSalary.setText("");
	                txtAddress.setText("");
	                passwordField.setText("");
	                comboGender.setSelectedIndex(0);
	                comboDesignation.setSelectedIndex(0);
	                comboShift.setSelectedIndex(0);
	                comboType.setSelectedIndex(0);
	                comboStatus.setSelectedIndex(0);

	                loadTable();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(frame, "Error saving employee details.", "Message", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });



		btnNewButton.setBounds(829, 177, 107, 36);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Employee Entry Form", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 105, 809, 212);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 26, 128, 25);
		panel.add(lblNewLabel);
		
		JLabel lblEmployeeSalary = new JLabel("Employee Salary");
		lblEmployeeSalary.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblEmployeeSalary.setBounds(385, 97, 128, 33);
		panel.add(lblEmployeeSalary);
		
		JLabel lblEmployeeEmail = new JLabel("Employee Email");
		lblEmployeeEmail.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblEmployeeEmail.setBounds(10, 97, 128, 33);
		panel.add(lblEmployeeEmail);
		
		JLabel lblDesignation = new JLabel("Employee Name");
		lblDesignation.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblDesignation.setBounds(385, 22, 128, 25);
		panel.add(lblDesignation);
		
		JLabel lblEmployeeMobile = new JLabel("Employee Mobile");
		lblEmployeeMobile.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblEmployeeMobile.setBounds(10, 62, 137, 33);
		panel.add(lblEmployeeMobile);
		
		JLabel lblEmployeeDob = new JLabel("Designation");
		lblEmployeeDob.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblEmployeeDob.setBounds(10, 133, 107, 35);
		panel.add(lblEmployeeDob);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		txtEmail.setColumns(10);
		txtEmail.setBounds(145, 105, 209, 25);
		panel.add(txtEmail);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		txtName.setColumns(10);
		txtName.setBounds(524, 22, 269, 25);
		panel.add(txtName);
		
		txtId = new JTextField();
		txtId.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		txtId.setColumns(10);
		txtId.setBounds(145, 24, 209, 25);
		panel.add(txtId);
		
		txtMobile = new JTextField();
		txtMobile.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		txtMobile.setColumns(10);
		txtMobile.setBounds(145, 61, 209, 33);
		panel.add(txtMobile);
		
		txtSalary = new JTextField();
		txtSalary.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		txtSalary.setColumns(10);
		txtSalary.setBounds(540, 102, 253, 26);
		panel.add(txtSalary);
		
		comboDesignation = new JComboBox();
		comboDesignation.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		comboDesignation.setModel(new DefaultComboBoxModel(new String[] {"None", "Store Manager", "Helping Staff", "Cleaning Staff", "Cashier", "Supervisor"}));
		comboDesignation.setBounds(145, 136, 209, 33);
		panel.add(comboDesignation);
		
		JLabel lblEmployeeAddress_1 = new JLabel("Address Line");
		lblEmployeeAddress_1.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblEmployeeAddress_1.setBounds(10, 179, 128, 19);
		panel.add(lblEmployeeAddress_1);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		txtAddress.setColumns(10);
		txtAddress.setBounds(116, 176, 317, 27);
		panel.add(txtAddress);
		
		JLabel lblAddressLine_1_1_1 = new JLabel("Working Shift");
		lblAddressLine_1_1_1.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblAddressLine_1_1_1.setBounds(568, 58, 115, 29);
		panel.add(lblAddressLine_1_1_1);
		
		comboShift = new JComboBox();
		comboShift.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		comboShift.setModel(new DefaultComboBoxModel(new String[] {"None", "Morning", "Evening", "Night"}));
		comboShift.setBounds(693, 58, 100, 29);
		panel.add(comboShift);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblPassword.setBounds(465, 175, 81, 26);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		passwordField.setBounds(560, 176, 236, 24);
		panel.add(passwordField);
		
		comboGender = new JComboBox();
		comboGender.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		comboGender.setModel(new DefaultComboBoxModel(new String[] {"None", "Male", "Female", "Other"}));
		comboGender.setBounds(456, 58, 81, 29);
		panel.add(comboGender);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblGender.setBounds(381, 58, 65, 29);
		panel.add(lblGender);
		
		JLabel lblEmployement = new JLabel("Employment Type & Status");
		lblEmployement.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblEmployement.setBounds(385, 141, 215, 26);
		panel.add(lblEmployement);
		
		comboType = new JComboBox();
		comboType.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		comboType.setModel(new DefaultComboBoxModel(new String[] {"None", "Full-Time", "Part-Time", "Contract"}));
		comboType.setBounds(606, 141, 81, 29);
		panel.add(comboType);
		
		comboStatus = new JComboBox();
		comboStatus.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		comboStatus.setModel(new DefaultComboBoxModel(new String[] {"None", "Active", "On Leave", "Resigned"}));
		comboStatus.setBounds(706, 141, 87, 29);
		panel.add(comboStatus);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Employee Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 330, 926, 222);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane_1.setBounds(10, 22, 906, 189);
		panel_2.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane(table);
	    scrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    panel_2.add(scrollPane_2);
		
		table = new JTable();
		table.setGridColor(new Color(0, 0, 0));
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane_1.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\WhatsApp Image 2024-11-15 at 02.15.29_985fffb7 (3).jpg"));
		lblNewLabel_1.setBounds(0, 0, 945, 563);
		frame.getContentPane().add(lblNewLabel_1);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}