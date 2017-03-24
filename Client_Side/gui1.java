
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import java.net.*;

public class gui1 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	FrontEnd fe;
	Socket s;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui1 window = new gui1();
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
	public gui1() throws Exception{
		s = new Socket("localhost", 9999);
		fe = new FrontEnd(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("LOGIN SCREEN");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.getContentPane().setLayout(null);
		
		
		
		 
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				


				String username = textField.getText();
				String password = textField_1.getText();
				if(fe.Login(username,pass)) {
					gui2 nw = new gui2();
					nw.frame2();
				} else {

					JOptionPane.showMessageDialog(null,"Wrong Password / Username");

				}


                  //gui2 nw = new gui2();
                  //nw.frame2();
			
				
			}
		});
		LoginButton.setBounds(178, 217, 89, 23);
		frame.getContentPane().add(LoginButton);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(71, 66, 97, 14);
		frame.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(68, 94, 199, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(71, 136, 89, 14);
		frame.getContentPane().add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(71, 161, 199, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		// JLabel lblNewUser = new JLabel("New User");
		// lblNewUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		// lblNewUser.setForeground(Color.WHITE);
		// lblNewUser.setBounds(406, 66, 89, 14);
		// frame.getContentPane().add(lblNewUser);
		
		// JLabel lblClickOnRegister = new JLabel("Click on REGISTER");
		// lblClickOnRegister.setBounds(406, 97, 135, 14);
		// frame.getContentPane().add(lblClickOnRegister);
		
		// JButton btnRegister = new JButton("Register");
		// btnRegister.addActionListener(new ActionListener() {
		// 	public void actionPerformed(ActionEvent arg0) {
		// 	}
		// // });
		// btnRegister.setBounds(406, 136, 89, 23);
		// frame.getContentPane().add(btnRegister);
		frame.setBounds(100, 100, 567, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

