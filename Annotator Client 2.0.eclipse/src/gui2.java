
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gui2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void frame2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui2 window = new gui2();
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
	public gui2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("SOURCE AND TARGET FILES");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 722, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnTargetFile = new JButton("Target File1");
		btnTargetFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gui3 nw = new gui3();
				nw.frame3();
			}
		});
		btnTargetFile.setBounds(436, 110, 129, 23);
		frame.getContentPane().add(btnTargetFile);
		
		JButton btnTargetFile_1 = new JButton("Target File2");
		btnTargetFile_1.setBounds(436, 170, 129, 23);
		frame.getContentPane().add(btnTargetFile_1);
		
		JButton btnSourceFile = new JButton("Source File 1");
		btnSourceFile.setBounds(61, 110, 143, 23);
		frame.getContentPane().add(btnSourceFile);
		
		JButton btnNewButton = new JButton("Source File 2");
		btnNewButton.setBounds(61, 170, 143, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblSourceFileList = new JLabel("SOURCE FILE LIST");
		lblSourceFileList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSourceFileList.setForeground(Color.WHITE);
		lblSourceFileList.setBounds(61, 56, 143, 14);
		frame.getContentPane().add(lblSourceFileList);
		
		JLabel lblTargetFilesList = new JLabel("TARGET FILES LIST");
		lblTargetFilesList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTargetFilesList.setForeground(Color.WHITE);
		lblTargetFilesList.setBounds(436, 56, 143, 14);
		frame.getContentPane().add(lblTargetFilesList);
	}

}

