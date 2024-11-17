package grocery;

import java.awt.BorderLayout;
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
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.EtchedBorder;

public class BillingPage {

	private JFrame frame;
	private JTable table;
	private JTextField textField_2;

	private String loginType;
	private boolean fromManagePage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillingPage window = new BillingPage();
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
	public BillingPage() {
		initialize();
	    BuildConnection();
	    loadTable();
	    calculateAndDisplayTotal();
	}
	public BillingPage(String loginType, boolean fromManagePage) {
	    this.loginType = loginType;
	    this.fromManagePage = fromManagePage;
	    initialize();
	    BuildConnection();
	    loadTable();
	    calculateAndDisplayTotal();
	}

	
	Connection con;
    PreparedStatement prestm;
    ResultSet rst;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_3;
    
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
	        prestm = con.prepareStatement("SELECT * FROM bill");
	        rst = prestm.executeQuery();
	        table.setModel(DbUtils.resultSetToTableModel(rst));

	        table.setDefaultEditor(Object.class, null);
	        table.getTableHeader().setReorderingAllowed(false);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	private void calculateAndDisplayTotal() {
	    
	    try {
	        
	        prestm = con.prepareStatement("SELECT SUM(totalPrice) AS grandTotal FROM bill");
			rst = prestm.executeQuery();

	        if (rst.next()) {
	            double grandTotal = rst.getDouble("grandTotal");
	            textField_3.setText(String.format("%.2f", grandTotal));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 853, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Billing Management");
		ImageIcon img = new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\bill (1).png");
		frame.setIconImage(img.getImage());

		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setBackground(new Color(0, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        frame.setVisible(false);
		        ManagePage managepageTab = new ManagePage();
		        managepageTab.setVisible(true);
		    }
		});
		btnNewButton_2.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnNewButton_2.setBounds(10, 11, 77, 35);
		btnNewButton_2.setVisible(fromManagePage);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(394, 460, 208, 35);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("Total Amount");
		lblNewLabel_3_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_2.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		lblNewLabel_3_1_2.setBounds(0, 0, 109, 35);
		panel_4.add(lblNewLabel_3_1_2);
		
		textField_3 = new JTextField();
		textField_3.setBackground(new Color(0, 0, 0));
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setBounds(110, 7, 88, 20);
		panel_4.add(textField_3);
		textField_3.setColumns(10);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("Log Out");
		btnNewButton_2_1.setBackground(new Color(0, 0, 0));
		btnNewButton_2_1.setForeground(new Color(255, 255, 255));
		btnNewButton_2_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        frame.dispose();
		        pagelogin loginPage = new pagelogin();
		        loginPage.setVisible(true);
		    }
		});
		btnNewButton_2_1.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnNewButton_2_1.setBounds(734, 11, 97, 35);
		btnNewButton_2_1.setVisible(!fromManagePage || loginType.equals("cashier"));
		frame.getContentPane().add(btnNewButton_2_1);

		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(24, 104, 578, 42);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Customer Name");
		lblNewLabel_3_1_1.setBounds(10, 10, 118, 21);
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		panel_2.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Contact Number");
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		lblNewLabel_3_1_1_1.setBounds(295, 10, 118, 21);
		panel_2.add(lblNewLabel_3_1_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		textField.setColumns(10);
		textField.setBounds(127, 8, 147, 26);
		panel_2.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		textField_1.setColumns(10);
		textField_1.setBounds(421, 8, 147, 26);
		panel_2.add(textField_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(24, 460, 306, 45);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "Cash", "UPI", "Debit Card", "Credit Card"}));
		comboBox.setBounds(179, 11, 117, 24);
		panel_3.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Mode of Payment");
		lblNewLabel_2.setBounds(10, 11, 162, 24);
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setFont(new Font("Lucida Sans", Font.PLAIN, 17));
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 160, 578, 289);
		frame.getContentPane().add(panel);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Bill", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 18, 558, 260);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnGenerateInvoice = new JButton("Generate Bill");
		btnGenerateInvoice.setForeground(new Color(255, 255, 255));
		btnGenerateInvoice.setBackground(new Color(0, 0, 0));
		btnGenerateInvoice.setBounds(629, 449, 183, 56);
		frame.getContentPane().add(btnGenerateInvoice);
		btnGenerateInvoice.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String customerName = textField.getText();
		        String customerContact = textField_1.getText();
		        String paymentMode = (String) comboBox.getSelectedItem();

		        
		        if (customerName.isEmpty() || customerContact.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please fill in all customer details.");
		            return;
		        }
		        if ("None".equals(paymentMode)) {
		            JOptionPane.showMessageDialog(frame, "Please select a valid payment option.");
		            return;
		        }

		        Date d = new Date();
		        String date = d.toString();

		        try {
		            int billNumber = 1000;
		            prestm = con.prepareStatement("SELECT MAX(BillNumber) FROM invoices");
		            rst = prestm.executeQuery();
		            if (rst.next() && rst.getInt(1) != 0) {
		                billNumber = rst.getInt(1) + 1;
		            }

		            int totalAmount = 0;
		            prestm = con.prepareStatement("SELECT SUM(totalPrice) AS Total FROM bill");
		            rst = prestm.executeQuery();
		            if (rst.next()) {
		                totalAmount = rst.getInt("Total");
		            }

		            prestm = con.prepareStatement(
		                "INSERT INTO invoices (BillNumber, CustomerName, CustomerContact, PaymentMode, Date, TotalAmount) VALUES (?, ?, ?, ?, ?, ?)"
		            );
		            prestm.setInt(1, billNumber);
		            prestm.setString(2, customerName);
		            prestm.setString(3, customerContact);
		            prestm.setString(4, paymentMode);
		            prestm.setString(5, date);
		            prestm.setInt(6, totalAmount);
		            prestm.executeUpdate();

		            prestm = con.prepareStatement("SELECT * FROM bill");
		            rst = prestm.executeQuery();
		            while (rst.next()) {
		                int itemId = rst.getInt("itemId");
		                int quantity = rst.getInt("quantity");

		                prestm = con.prepareStatement(
		                    "UPDATE stock SET quantity = quantity + 1 - ? WHERE itemId = ?"
		                );
		                prestm.setInt(1, quantity);
		                prestm.setInt(2, itemId);
		                prestm.executeUpdate();
		            }

		            String invoiceDetails = "Invoice No: " + billNumber + "\n" +
		                                    "Customer Name: " + customerName + "\n" +
		                                    "Contact Number: " + customerContact + "\n" +
		                                    "Payment Mode: " + paymentMode + "\n" +
		                                    "Date: " + date + "\n\n" +
		                                    "Items Purchased:\n";

		            prestm = con.prepareStatement("SELECT * FROM bill");
		            rst = prestm.executeQuery();
		            while (rst.next()) {
		                invoiceDetails += rst.getString("itemName") + " - " + rst.getInt("quantity") + " x " +
		                                  rst.getInt("pricePerItem") + " = " + rst.getInt("totalPrice") + "\n";
		            }

		            invoiceDetails += "\nTotal Amount: " + totalAmount;

		            JTextPane invoiceTextPane = new JTextPane();
		            invoiceTextPane.setText(invoiceDetails);
		            invoiceTextPane.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
		            JScrollPane invoiceScrollPane = new JScrollPane(invoiceTextPane);
		            JPanel invoicePanel = new JPanel();
		            invoicePanel.setLayout(new BorderLayout());
		            invoicePanel.add(invoiceScrollPane, BorderLayout.CENTER);

		            JFrame invoiceFrame = new JFrame("Invoice " + billNumber);
		            invoiceFrame.setSize(500, 500);
		            invoiceFrame.getContentPane().add(invoicePanel);
		            invoiceTextPane.setEditable(false);
		            invoiceFrame.setVisible(true);
		            invoiceFrame.setResizable(false);

		            prestm = con.prepareStatement("DELETE FROM bill");
		            prestm.executeUpdate();

		            loadTable();

		            textField.setText("");
		            textField_1.setText("");
		            textField_2.setText("");
		            textField_3.setText("");
		            comboBox.setSelectedIndex(0);

		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(frame, "Error generating invoice.");
		        }
		    }
		});






		btnGenerateInvoice.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnGenerateInvoice.setFont(new Font("Lucida Sans", Font.PLAIN, 21));
		
		JLabel lblNewLabel = new JLabel("Billing Management");
		lblNewLabel.setBounds(236, 24, 368, 59);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 35));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(629, 104, 183, 330);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Item Id:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Lucida Sans", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(24, 11, 136, 25);
		panel_1.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		textField_2.setBounds(47, 39, 85, 26);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Quantity");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Lucida Sans", Font.PLAIN, 17));
		lblNewLabel_3_1.setBounds(47, 91, 85, 25);
		panel_1.add(lblNewLabel_3_1);
		
		JButton btnNewButton = new JButton("Add Item");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String itemId = textField_2.getText();

		        if (itemId.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please enter Item ID");
		            return;
		        }

		        try {
		            int itemIdInt = Integer.parseInt(itemId);

		            prestm = con.prepareStatement("SELECT quantity FROM bill WHERE itemId = ?");
		            prestm.setInt(1, itemIdInt);
		            rst = prestm.executeQuery();

		            if (rst.next()) {
		                JOptionPane.showMessageDialog(frame, "Item ID already present. Please change the quantity.");
		                return;
		            }

		            prestm = con.prepareStatement("SELECT itemName, price, quantity FROM stock WHERE itemId = ?");
		            prestm.setInt(1, itemIdInt);
		            rst = prestm.executeQuery();

		            if (rst.next()) {
		                String itemName = rst.getString("itemName");
		                int pricePerItem = rst.getInt("price");
		                int stockQuantity = rst.getInt("quantity");

		                if (stockQuantity > 0) {
		                    int quantity = 1;
		                    int totalPrice = pricePerItem * quantity;

		                    prestm = con.prepareStatement(
		                        "INSERT INTO bill (itemId, itemName, pricePerItem, quantity, totalPrice) VALUES (?, ?, ?, ?, ?)"
		                    );
		                    prestm.setInt(1, itemIdInt);
		                    prestm.setString(2, itemName);
		                    prestm.setInt(3, pricePerItem);
		                    prestm.setInt(4, quantity);
		                    prestm.setInt(5, totalPrice);

		                    prestm.executeUpdate();

		                    prestm = con.prepareStatement("UPDATE stock SET quantity = quantity - 1 WHERE itemId = ?");
		                    prestm.setInt(1, itemIdInt);
		                    prestm.executeUpdate();

		                    JOptionPane.showMessageDialog(frame, "Item added to bill.");
		                    loadTable();
		                    calculateAndDisplayTotal();
		                } else {
		                    JOptionPane.showMessageDialog(frame, "Item out of stock.");
		                }
		            } else {
		                JOptionPane.showMessageDialog(frame, "Item ID not found in stock.");
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Please enter a valid numeric Item ID.");
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});



		btnNewButton.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		btnNewButton.setBounds(24, 190, 136, 38);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String itemId = textField_2.getText();

		        if (itemId.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please enter Item ID");
		            return;
		        }

		        try {
		            prestm = con.prepareStatement("SELECT quantity, pricePerItem FROM bill WHERE itemId = ?");
		            prestm.setInt(1, Integer.parseInt(itemId));
		            rst = prestm.executeQuery();

		            if (rst.next()) {
		                int currentQuantity = rst.getInt("quantity");
		                int pricePerItem = rst.getInt("pricePerItem");

		                prestm = con.prepareStatement("SELECT quantity FROM stock WHERE itemId = ?");
		                prestm.setInt(1, Integer.parseInt(itemId));
		                ResultSet stockResult = prestm.executeQuery();

		                if (stockResult.next()) {
		                    int availableStock = stockResult.getInt("quantity");

		                    if (currentQuantity + 1 > availableStock) {
		                        JOptionPane.showMessageDialog(frame, "Insufficient stock available for Item ID: " + itemId);
		                        return;
		                    }

		                    int newQuantity = currentQuantity + 1;
		                    int newTotalPrice = pricePerItem * newQuantity;

		                    prestm = con.prepareStatement("UPDATE bill SET quantity = ?, totalPrice = ? WHERE itemId = ?");
		                    prestm.setInt(1, newQuantity);
		                    prestm.setInt(2, newTotalPrice);
		                    prestm.setInt(3, Integer.parseInt(itemId));

		                    prestm.executeUpdate();
		                    JOptionPane.showMessageDialog(frame, "Quantity updated!");

		                    loadTable();
		                    calculateAndDisplayTotal();
		                } else {
		                    JOptionPane.showMessageDialog(frame, "Item ID not found in stock", "Message", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(frame, "Item ID not found in bill", "Message", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});


		btnNewButton_1.setBounds(24, 119, 61, 25);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("-");
		btnNewButton_1_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1_1.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String itemId = textField_2.getText();

		        if (itemId.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please enter Item ID");
		            return;
		        }

		        try {
		            prestm = con.prepareStatement("SELECT quantity, pricePerItem FROM bill WHERE itemId = ?");
		            prestm.setInt(1, Integer.parseInt(itemId));
		            rst = prestm.executeQuery();

		            if (rst.next()) {
		                int currentQuantity = rst.getInt("quantity");
		                int pricePerItem = rst.getInt("pricePerItem");

		                if (currentQuantity > 1) {
		                    int newQuantity = currentQuantity - 1;
		                    int newTotalPrice = pricePerItem * newQuantity;

		                    prestm = con.prepareStatement("UPDATE bill SET quantity = ?, totalPrice = ? WHERE itemId = ?");
		                    prestm.setInt(1, newQuantity);
		                    prestm.setInt(2, newTotalPrice);
		                    prestm.setInt(3, Integer.parseInt(itemId));

		                    prestm.executeUpdate();
		                    JOptionPane.showMessageDialog(frame, "Quantity updated!");

		                    loadTable();
		                    calculateAndDisplayTotal();
		                } else {
		                    JOptionPane.showMessageDialog(frame, "Quantity cannot be less than 1. Consider removing the item.", "Message", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(frame, "Item ID not found in bill", "Message", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btnNewButton_1_1.setBounds(99, 119, 61, 25);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnRemove = new JButton("Remove Item");
		btnRemove.setForeground(new Color(0, 0, 0));
		btnRemove.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String itemId = textField_2.getText();

		        if (itemId.isEmpty()) {
		            JOptionPane.showMessageDialog(frame, "Please enter Item ID to remove.");
		            return;
		        }

		        try {
		            prestm = con.prepareStatement("SELECT * FROM bill WHERE itemId = ?");
		            prestm.setInt(1, Integer.parseInt(itemId));
		            rst = prestm.executeQuery();

		            if (rst.next()) {
		                prestm = con.prepareStatement("DELETE FROM bill WHERE itemId = ?");
		                prestm.setInt(1, Integer.parseInt(itemId));
		                prestm.executeUpdate();

		                JOptionPane.showMessageDialog(frame, "Item removed successfully!");

		                loadTable();
		                calculateAndDisplayTotal();
		            } else {
		                JOptionPane.showMessageDialog(frame, "Item ID not found in the bill.", "Message", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(frame, "Invalid Item ID. Please enter a numeric value.", "Message", JOptionPane.ERROR_MESSAGE);
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btnRemove.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		btnRemove.setBounds(24, 232, 136, 38);
		panel_1.add(btnRemove);
		
		JButton btnCkearBill = new JButton("Clear Bill");
		btnCkearBill.setForeground(new Color(0, 0, 0));
		btnCkearBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					prestm = con.prepareStatement("delete from bill");
					prestm.executeUpdate();

					loadTable();
					
					JOptionPane.showMessageDialog(null, "Bill cleared successfully");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnCkearBill.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		btnCkearBill.setBounds(24, 281, 136, 38);
		panel_1.add(btnCkearBill);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\WhatsApp Image 2024-11-15 at 02.15.29_985fffb7 (2).jpg"));
		lblNewLabel_4.setBounds(0, 0, 841, 528);
		frame.getContentPane().add(lblNewLabel_4);
	}
	
	public void setVisible(boolean b) {
    	frame.setVisible(b);
    }
}