package res;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tools.Account;

public class LoginPanel extends JPanel {

	
	// creates the login panel
	public LoginPanel() {
		
		// this panel contains all the element with a constant size
		JPanel panel = new JPanel();
		// box layout will be used to appear the items from top to bottom
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// setting the size
		panel.setPreferredSize(new Dimension(400,210));
		panel.setMinimumSize(panel.getPreferredSize());
		panel.setMaximumSize(panel.getPreferredSize());
		
		// box layout was used to make the previous panel appear on center
		Box box = new Box(BoxLayout.Y_AXIS);
		// add glue for the support
		box.add(Box.createVerticalGlue());
		box.add(panel);
		box.add(Box.createVerticalGlue());
		
		// set the layout as border layout to make sure that the items will fill up the entire frame
		setLayout(new BorderLayout());
		add(box);
		
		// insert the header
		Label titleLabel = new Label("THE CLOTHING SHOP");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setAlignment(Label.CENTER);
		
		// insert the username field
		Label userLabel = new Label("Username");
		JTextField userTextField = new JTextField();
		userTextField.setToolTipText("Username");
		userTextField.setColumns(0);
		
		// insert the password field
		Label passLabel = new Label("Password");
		JPasswordField passPasswordField = new JPasswordField();
		passPasswordField.setToolTipText("Password");
		passPasswordField.setColumns(0);
		
		// footer buttons
		JPanel btnPanel = new JPanel();
		// get the main panel to be used for the commands
		JPanel main = this;
		
		// exit button
		JButton exitBtn = new JButton("Exit");
		// set the size
		exitBtn.setPreferredSize(new Dimension(70,30));
		// add a command (on click)
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);

				// confirms the exit
			    if (JOptionPane.showConfirmDialog(topFrame,"Do you want to exit?",  "Confirm exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					topFrame.dispose();
			    }
			}
		});
		
		// login button
		JButton loginBtn = new JButton("Login");
		// set the size
		loginBtn.setPreferredSize(new Dimension(70,30));
		// add a command (on click)
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
				Account account;
				System.out.println(Account.accountList);

				// find if the user exists to the list
				if ((account = Account.checkAccount(userTextField.getText(), new String(passPasswordField.getPassword()))) != null){
					// chow a meessage that he/she successfully logged
					JOptionPane.showMessageDialog(null, "Successfully logged in the account.\nWelcome " + userTextField.getText() + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
					// bring the user to the MainMenu if he/she successfully logged in
					
					if (userTextField.getText().equals("admin")) 
						topFrame.setContentPane(new res.MainMenuPanel_Admin());
					else 
						topFrame.setContentPane(new res.MainMenuPanel(account));
					 
					/* IMPORTANT */
					// this functions should be used for every call of the function setContentPane()
					// refreshes the GUI
					topFrame.revalidate();
					topFrame.repaint();
				}
				
				else {
					// no user matched
					JOptionPane.showMessageDialog(null, "Username and password not found.\nPlease try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});		
		btnPanel.add(exitBtn);
		btnPanel.add(loginBtn);
		
		
		// add all the elements to the center panel
		panel.add(titleLabel);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(userLabel);
		panel.add(userTextField);
		panel.add(passLabel);
		panel.add(passPasswordField);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(btnPanel);
		
		
		
	}

}
