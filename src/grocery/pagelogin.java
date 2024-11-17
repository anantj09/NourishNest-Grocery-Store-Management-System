package grocery;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class pagelogin {

    private JFrame frame;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    pagelogin window = new pagelogin();
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
    public pagelogin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 853, 557);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setTitle("Login");
		ImageIcon img = new ImageIcon("C:\\\\Users\\\\anant\\\\Downloads\\\\College work\\\\Sem 3\\\\OOPs jAVA\\\\Project\\\\employee (1).png");
		frame.setIconImage(img.getImage());
        
        JLabel lblNourishnest = new JLabel("NourishNest: Wellness, Well-Stocked");
        lblNourishnest.setHorizontalAlignment(SwingConstants.CENTER);
        lblNourishnest.setForeground(Color.BLACK);
        lblNourishnest.setFont(new Font("Lucida Sans", Font.BOLD, 24));
        lblNourishnest.setBackground(Color.BLACK);
        lblNourishnest.setBounds(10, 11, 499, 75);
        frame.getContentPane().add(lblNourishnest);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\grocery-store-building-shop-market-or-supermarket-illustration-in-flat-style-vector__1_-removebg-preview (2).png"));
        lblNewLabel_2.setBounds(10, 115, 499, 399);
        frame.getContentPane().add(lblNewLabel_2);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Lucida Sans", Font.ITALIC, 18));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Store Manager", "Cashier"}));
        comboBox.setBounds(536, 175, 255, 45);
        frame.getContentPane().add(comboBox);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Lucida Sans", Font.BOLD, 14));
        passwordField.setBounds(536, 314, 255, 45);
        frame.getContentPane().add(passwordField);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
        lblPassword.setBackground(Color.BLACK);
        lblPassword.setBounds(597, 256, 132, 57);
        frame.getContentPane().add(lblPassword);
        
        JLabel lblUser = new JLabel("User");
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblUser.setForeground(Color.BLACK);
        lblUser.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
        lblUser.setBackground(Color.BLACK);
        lblUser.setBounds(604, 129, 119, 45);
        frame.getContentPane().add(lblUser);
        
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setForeground(new Color(0, 0, 0));
        lblLogin.setFont(new Font("Lucida Sans", Font.BOLD, 35));
        lblLogin.setBackground(Color.BLACK);
        lblLogin.setBounds(581, 23, 163, 75);
        frame.getContentPane().add(lblLogin);
        
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 0, 0));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        loginButton.setBounds(597, 422, 137, 51);
        frame.getContentPane().add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) comboBox.getSelectedItem();
                String enteredPassword = new String(passwordField.getPassword());

                if (selectedRole.equals("Store Manager") && enteredPassword.equals("manager@123")) {
                    ManagePage managePage = new ManagePage();
                    managePage.setVisible(true);
                    frame.dispose();
                } else if (selectedRole.equals("Cashier") && enteredPassword.equals("cashier@123")) {
                	BillingPage billingPage = new BillingPage("cashier", false);
                	billingPage.setVisible(true);
                    frame.dispose();
                } 
                else {
                    JOptionPane.showMessageDialog(frame, "Password is incorrect", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\anant\\Downloads\\College work\\Sem 3\\OOPs jAVA\\Project\\WhatsApp Image 2024-11-15 at 02.15.29_985fffb7 (2).jpg"));
        lblNewLabel.setBounds(0, 0, 841, 522);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel label = new JLabel("New label");
        label.setBounds(0, 0, 336, 65);
        frame.getContentPane().add(label);
    }
    
    public void setVisible(boolean b) {
    	frame.setVisible(b);
    }
}
