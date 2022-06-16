package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.HomeController;

public class Home extends JFrame {
	
	private HomeController controller=HomeController.getInstance();
	
	private Home() {
		this.init();
	}
	
	private void init() {
		JLabel loginLabel = new JLabel("Login");
		JTextField loginJTextField = new JTextField();
		JLabel pwdLabel = new JLabel("Mot de passe");
		JPasswordField pwdJPasswordField = new JPasswordField();
		JLabel lastnameLabel = new JLabel("Nom");
		JTextField lastnameJTextField = new JTextField();
		JLabel firstnameLabel = new JLabel("Prénom");
		JTextField firstnameJTextField = new JTextField();
		JButton cancelButton = new JButton("Cancel");
		JButton saveButton = new JButton("Save");
		controller.setLogin(loginJTextField.getDocument());
		controller.setPwd(pwdJPasswordField.getDocument());
		controller.setLastname(lastnameJTextField.getDocument());
		controller.setFirstname(firstnameJTextField.getDocument());				
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		cancelButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.cancel();
			}
		});
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		panel.add(loginLabel);
		panel.add(loginJTextField);
		panel.add(pwdLabel);
		panel.add(pwdJPasswordField);		
		panel.add(lastnameLabel);
		panel.add(lastnameJTextField);
		panel.add(firstnameLabel);
		panel.add(firstnameJTextField);
		panel.add(cancelButton);
		panel.add(saveButton);
		this.getRootPane().setLayout(new FlowLayout());
		this.getRootPane().add(panel);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Home home = new Home();		
	}

}
