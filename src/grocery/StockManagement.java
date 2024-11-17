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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

public class StockManagement {

	private JFrame frame;
	private JTable table;
	private JTextField txtItemId;
	private JTextField txtId;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JTextField txtExpiry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockManagement window = new StockManagement();
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
	public StockManagement() {
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
	        prestm = con.prepareStatement("SELECT * FROM stock");
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
		frame.setBounds(100, 100, 866, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Stock Management");
		ImageIcon img = new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\black-and-white-suitcase (1).png");
		frame.setIconImage(img.getImage());
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 853, 563);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"None", "Item I'd", "Item Name", "Quantity", "Price", "Expiry"}));
		comboBox.setBounds(522, 139, 111, 33);
		panel.add(comboBox);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ManagePage managepageTab = new ManagePage();
				managepageTab.setVisible(true);
			}
		});
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.setBackground(new Color(0, 0, 0));
		btnShowAll.setForeground(new Color(255, 255, 255));
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable();
			}
		});
		btnShowAll.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnShowAll.setBounds(690, 81, 111, 33);
		panel.add(btnShowAll);
		btnBack.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnBack.setBounds(20, 24, 87, 33);
		panel.add(btnBack);
		
		JButton btnSort = new JButton("Sort");
		btnSort.setBackground(new Color(0, 0, 0));
		btnSort.setForeground(new Color(255, 255, 255));
		btnSort.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedSortField = comboBox.getSelectedItem().toString();
		        String query = "SELECT * FROM stock";

		        switch (selectedSortField) {
		            case "Item I'd":
		                query += " ORDER BY ItemID";
		                break;
		            case "Item Name":
		                query += " ORDER BY ItemName";
		                break;
		            case "Quantity":
		                query += " ORDER BY Quantity";
		                break;
		            case "Price":
		                query += " ORDER BY Price";
		                break;
		            case "Expiry":
		                query += " ORDER BY Expiry";
		                break;
		            default:
		                JOptionPane.showMessageDialog(null, "Please select a valid sorting option.");
		                return;
		        }

		        try {
		            prestm = con.prepareStatement(query);
		            rst = prestm.executeQuery();
		            table.setModel(DbUtils.resultSetToTableModel(rst));
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btnSort.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnSort.setBounds(690, 139, 111, 33);
		panel.add(btnSort);

		
		JLabel lblNewLabel_2_6 = new JLabel("Filter By:");
		lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6.setFont(new Font("Lucida Sans", Font.BOLD, 17));
		lblNewLabel_2_6.setBounds(425, 139, 87, 33);
		panel.add(lblNewLabel_2_6);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search Item", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1.setBounds(425, 211, 382, 96);
		panel.add(panel_2_1);
		
		txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		txtId.setColumns(10);
		txtId.setBounds(119, 36, 106, 30);
		panel_2_1.add(txtId);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add/Update Item", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(425, 335, 382, 204);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JTextField txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		txtName.setColumns(10);
		txtName.setBounds(136, 55, 118, 25);
		panel_2.add(txtName);
		
		JLabel lblNewLabel_2_2 = new JLabel("Enter Item I'd:");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblNewLabel_2_2.setBounds(15, 38, 100, 23);
		panel_2_1.add(lblNewLabel_2_2);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id = txtId.getText();
		        
		        try {
		            prestm = con.prepareStatement("SELECT * FROM stock WHERE ItemId = ?");
		            prestm.setString(1, id);
		            rst = prestm.executeQuery();
		            
		            if (rst.next()) {
		                txtItemId.setText(rst.getString(1));
		                txtName.setText(rst.getString(2));
		                txtQuantity.setText(rst.getString(3));
		                txtPrice.setText(rst.getString(4));
		                txtExpiry.setText(rst.getString(5));
		            } else {
		                JOptionPane.showMessageDialog(null, "Item ID not found!", "Search Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnNewButton.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnNewButton.setBounds(248, 35, 106, 30);
		panel_2_1.add(btnNewButton);
		
		
		txtItemId = new JTextField();
		txtItemId.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		txtItemId.setHorizontalAlignment(SwingConstants.CENTER);
		txtItemId.setBounds(27, 55, 96, 25);
		panel_2.add(txtItemId);
		txtItemId.setColumns(10);

		
		JLabel lblNewLabel_2 = new JLabel("Item I'd");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblNewLabel_2.setBounds(27, 29, 96, 25);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Enter Item Name");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(137, 29, 118, 25);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_3 = new JLabel("Quantity");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblNewLabel_2_3.setBounds(281, 29, 68, 25);
		panel_2.add(lblNewLabel_2_3);
		
		txtQuantity = new JTextField();
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantity.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(267, 55, 96, 25);
		panel_2.add(txtQuantity);
		
		JLabel lblNewLabel_2_4 = new JLabel("Price per item");
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_4.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblNewLabel_2_4.setBounds(75, 85, 106, 23);
		panel_2.add(lblNewLabel_2_4);
		
		txtPrice = new JTextField();
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		txtPrice.setColumns(10);
		txtPrice.setBounds(75, 107, 106, 25);
		panel_2.add(txtPrice);
		
		JLabel lblNewLabel_2_5 = new JLabel("Expiry (MM-DD-YY)");
		lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_5.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblNewLabel_2_5.setBounds(200, 82, 130, 26);
		panel_2.add(lblNewLabel_2_5);
		
		txtExpiry = new JTextField();
		txtExpiry.setHorizontalAlignment(SwingConstants.CENTER);
		txtExpiry.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		txtExpiry.setColumns(10);
		txtExpiry.setBounds(211, 107, 106, 25);
		panel_2.add(txtExpiry);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String id, name, quantity, price, expiry;
		        id = txtItemId.getText().trim();
		        name = txtName.getText().trim();
		        quantity = txtQuantity.getText().trim();
		        price = txtPrice.getText().trim();
		        expiry = txtExpiry.getText().trim();
		        
		        if (id.isEmpty() || name.isEmpty() || quantity.isEmpty() || price.isEmpty() || expiry.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please fill in all fields before adding the item.", "Missing Input", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        try {
		            prestm = con.prepareStatement("insert into stock(ItemID,ItemName,Quantity,Price,Expiry)values(?,?,?,?,?)");
		            prestm.setString(1, id);
		            prestm.setString(2, name);
		            prestm.setString(3, quantity);
		            prestm.setString(4, price);
		            prestm.setString(5, expiry);
		            
		            prestm.executeUpdate();
		            
		            loadTable();
		            JOptionPane.showMessageDialog(null, "Item added successfully.");
		            
		            txtItemId.setText("");
		            txtName.setText("");
		            txtQuantity.setText("");
		            txtPrice.setText("");
		            txtExpiry.setText("");
		            txtItemId.requestFocus();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		            JOptionPane.showMessageDialog(null, "An error occurred while adding the item.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


		
		
		btnAdd.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnAdd.setBounds(17, 160, 106, 30);
		panel_2.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id,name,quantity,price,expiry;
				id = txtItemId.getText();
				name = txtName.getText();
				quantity = txtQuantity.getText();
				price = txtPrice.getText();
				expiry = txtExpiry.getText();
				
				try {
					prestm = con.prepareStatement("update stock set ItemID=?,ItemName=?,Quantity=?,Price=?,Expiry=? where ItemId=?");
					prestm.setString(1,id);
					prestm.setString(2,name);
					prestm.setString(3,quantity);
					prestm.setString(4,price);
					prestm.setString(5,expiry);
					prestm.setString(6, id);
					
					prestm.executeUpdate();
					
					loadTable();
					JOptionPane.showMessageDialog(null, "Item updated successfully");
					
					txtItemId.setText("");
					txtName.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					txtExpiry.setText("");
					
					txtItemId.requestFocus();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnUpdate.setBounds(136, 160, 106, 30);
		panel_2.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id = txtItemId.getText();
				
				try {
					prestm=con.prepareStatement("delete from stock where ItemId=?");
					prestm.setString(1,id);
					
					prestm.executeUpdate();
					loadTable();
					
					txtItemId.setText("");
					txtName.setText("");
					txtQuantity.setText("");
					txtPrice.setText("");
					txtExpiry.setText("");
					
					JOptionPane.showMessageDialog(null, "Item delete successfully");

					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnRemove.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		btnRemove.setBounds(257, 160, 106, 30);
		panel_2.add(btnRemove);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Stock", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(24, 96, 362, 443);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 342, 411);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JLabel lblNewLabel_1 = new JLabel("Stock Management");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Lucida Sans", Font.BOLD, 30));
		lblNewLabel_1.setBounds(254, 24, 352, 61);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\WhatsApp Image 2024-11-15 at 02.15.29_985fffb7 (2).jpg"));
		lblNewLabel.setBounds(0, 0, 853, 563);
		panel.add(lblNewLabel);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
