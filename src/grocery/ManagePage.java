package grocery;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ManagePage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagePage window = new ManagePage();
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
	public ManagePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 865, 598);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Manage");
		ImageIcon img = new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\free-gears-icon-1170-thumb.png");
		frame.setIconImage(img.getImage());
		
		JLabel lblNewLabel_3_4 = new JLabel("");
		lblNewLabel_3_4.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\procurement (1).png"));
		lblNewLabel_3_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_4.setBounds(53, 337, 62, 57);
		frame.getContentPane().add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_3 = new JLabel("");
		lblNewLabel_3_3.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\bill (1).png"));
		lblNewLabel_3_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_3.setBounds(53, 418, 62, 57);
		frame.getContentPane().add(lblNewLabel_3_3);
		
		JLabel lblWelcome = new JLabel("WELCOME !");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(Color.BLACK);
		lblWelcome.setFont(new Font("Lucida Sans", Font.ITALIC, 30));
		lblWelcome.setBackground(Color.BLACK);
		lblWelcome.setBounds(538, 140, 193, 75);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\black-and-white-suitcase (1).png"));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(51, 155, 62, 57);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\employee (1).png"));
		lblNewLabel_3.setBounds(53, 242, 62, 57);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\464a44290ee8bb133b2e8fd49cb78719 (1).png"));
		lblNewLabel_1.setBounds(443, 190, 370, 370);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBackground(new Color(0, 0, 0));
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        frame.dispose();
		        
		        pagelogin loginPage = new pagelogin();
		        loginPage.setVisible(true);
		    }
		});

		btnLogOut.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		btnLogOut.setBounds(730, 20, 107, 33);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnBillingSystem = new JButton("Billing System");
		btnBillingSystem.setBackground(new Color(0, 0, 0));
		btnBillingSystem.setForeground(new Color(255, 255, 255));
		btnBillingSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				BillingPage billingPage = new BillingPage("manager", true);
				billingPage.setVisible(true);
			}
		});
		btnBillingSystem.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		btnBillingSystem.setBounds(131, 421, 240, 50);
		frame.getContentPane().add(btnBillingSystem);
		
		JButton btnOrderManagement = new JButton("Order Management");
		btnOrderManagement.setBackground(new Color(0, 0, 0));
		btnOrderManagement.setForeground(new Color(255, 255, 255));
		btnOrderManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				OrderPage orderManagementTab = new OrderPage();
				orderManagementTab.setVisible(true);
			}
		});
		btnOrderManagement.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		btnOrderManagement.setBounds(131, 337, 240, 50);
		frame.getContentPane().add(btnOrderManagement);
		
		JButton btnStockManagement = new JButton("Stock Management");
		btnStockManagement.setBackground(new Color(0, 0, 0));
		btnStockManagement.setForeground(new Color(255, 255, 255));
		btnStockManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				StockManagement stockManagementTab = new StockManagement();
				stockManagementTab.setVisible(true);
			}
		});
		btnStockManagement.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		btnStockManagement.setBounds(131, 161, 240, 50);
		frame.getContentPane().add(btnStockManagement);
		
		JButton btnShowAll = new JButton("Employee Details");
		btnShowAll.setBackground(new Color(0, 0, 0));
		btnShowAll.setForeground(new Color(255, 255, 255));
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				EmployeePage stockManagementTab = new EmployeePage();
				stockManagementTab.setVisible(true);
			}
		});
		btnShowAll.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		btnShowAll.setBounds(131, 246, 240, 50);
		frame.getContentPane().add(btnShowAll);
		
		JLabel lblNewLabel = new JLabel("Grocery Management System");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(146, 25, 541, 75);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 35));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(new Color(244, 244, 244));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\WhatsApp Image 2024-11-15 at 02.15.29_985fffb7 (2).jpg"));
		lblNewLabel_2.setBounds(0, 0, 853, 563);
		frame.getContentPane().add(lblNewLabel_2);
		
		
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
