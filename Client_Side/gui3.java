
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gui3 {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void frame3() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui3 window = new gui3();
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
	public gui3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("ANNOTATOR");
		frame.setBounds(100, 100, 569, 314);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(155, 47, 253, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnNovelty = new JRadioButton("Novelty");
		rdbtnNovelty.setBounds(72, 158, 74, 37);
		frame.getContentPane().add(rdbtnNovelty);
		
		JRadioButton rdbtnNonnovelty = new JRadioButton("Non-Novelty");
		rdbtnNonnovelty.setBounds(314, 158, 111, 37);
		frame.getContentPane().add(rdbtnNonnovelty);
		
		JRadioButton rdbtnFeature = new JRadioButton("Feature");
		rdbtnFeature.setBounds(195, 158, 91, 37);
		frame.getContentPane().add(rdbtnFeature);
		
		JRadioButton rdbtnFeauture = new JRadioButton("Feauture");
		rdbtnFeauture.setBounds(444, 158, 91, 37);
		frame.getContentPane().add(rdbtnFeauture);
		
		JLabel lblScore = new JLabel("Score = 1");
		lblScore.setBounds(76, 123, 70, 14);
		frame.getContentPane().add(lblScore);
		
		JLabel lblScore_1 = new JLabel("Score = 0.75");
		lblScore_1.setBounds(195, 123, 74, 14);
		frame.getContentPane().add(lblScore_1);
		
		JLabel lblScore_2 = new JLabel("Score = 0");
		lblScore_2.setBounds(331, 123, 70, 14);
		frame.getContentPane().add(lblScore_2);
		
		JLabel lblScore_3 = new JLabel("Score = 0.25");
		lblScore_3.setBounds(444, 123, 74, 14);
		frame.getContentPane().add(lblScore_3);
		
		JButton btnSubmit = new JButton("submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Your Data Has Been Successfully submitted");
				this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}
		});
		btnSubmit.setBounds(236, 226, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}
}

