package grocery;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

public class OrderPage {

	private JFrame frame;
	private JTable table;
	private JTextField txtBill;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderPage window = new OrderPage();
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
	public OrderPage() {
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
	
	public void loadTable()
	{
		try {
			
			prestm = con.prepareStatement("select *from invoices");
			rst = prestm.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rst));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 855, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Order Management");
		ImageIcon img = new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\procurement (1).png");
		frame.setIconImage(img.getImage());
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 106, 823, 349);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Past Orders", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 802, 316);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(10, 11, 77, 35);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ManagePage managepageTab = new ManagePage();
				managepageTab.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Order Management");
		lblNewLabel.setBounds(262, 30, 326, 41);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Enter Bill No.");
		lblNewLabel_1.setBounds(262, 473, 123, 35);
		lblNewLabel_1.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel_1);
		
		txtBill = new JTextField();
		txtBill.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		txtBill.setBounds(395, 473, 229, 35);
		frame.getContentPane().add(txtBill);
		txtBill.setColumns(10);
		
		JButton btnNewButton_1_1 = new JButton("Delete Order");
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id = txtBill.getText().trim();
		        
		        if (id.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please enter a Bill Number to delete the order.", "Missing Input", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        try {
		            prestm = con.prepareStatement("delete from invoices where BillNumber=?");
		            prestm.setString(1, id);
		            
		            int rowsAffected = prestm.executeUpdate();
		            if (rowsAffected > 0) {
		                loadTable();
		                JOptionPane.showMessageDialog(null, "Item deleted successfully.");
		            } else {
		                JOptionPane.showMessageDialog(null, "No order found with the given Bill Number.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		            JOptionPane.showMessageDialog(null, "An error occurred while deleting the order.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btnNewButton_1_1.setBounds(649, 470, 168, 40);
		btnNewButton_1_1.setFont(new Font("Lucida Sans", Font.BOLD, 18));
		frame.getContentPane().add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\WhatsApp Image 2024-11-15 at 02.15.29_985fffb7 (2).jpg"));
		lblNewLabel_2.setBounds(0, 0, 843, 528);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}