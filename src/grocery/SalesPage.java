package grocery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class SalesPage {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesPage window = new SalesPage();
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
	public SalesPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 852, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 838, 10);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("  Sales Report");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setFont(new Font("Lucida Sans", Font.PLAIN, 55));
		lblNewLabel.setBounds(39, 31, 386, 102);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(39, 144, 407, 351);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setBounds(227, 5, 0, 0);
		panel_1.add(table);
		
		table_1 = new JTable();
		table_1.setBounds(10, 16, 387, 324);
		panel_1.add(table_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(484, 239, 328, 256);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		table_2 = new JTable();
		table_2.setBounds(10, 11, 308, 234);
		panel_2.add(table_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(484, 21, 328, 210);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		table_3 = new JTable();
		table_3.setBounds(10, 11, 308, 188);
		panel_3.add(table_3);
	}
	
	public void setVisible(boolean b) {
    	frame.setVisible(b);
    }
}