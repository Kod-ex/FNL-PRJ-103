package res;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tools.Account;
import java.awt.Component;

public class EditAccountForm_Admin extends JPanel {
	private JTextField fullnameTxt;
	private JTextField passwordTxt;
	private JTextField emailTxt;
	private JTextField numberTxt;
	private JTextField addressTxt;
	private JTextField usernameTxt;
	private JTextField UUIDTxt;

	/**
	 * Create the panel.
	 */
	public EditAccountForm_Admin(Account selected) {

		// this panel contains all the element with a constant size
		JPanel panel = new JPanel();
		// box layout will be used to appear the items from top to bottom
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// setting the size
		panel.setPreferredSize(new Dimension(400,450));
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
		
		JPanel headerPnl = new JPanel();
		
		// insert the header
		Label titleLabel = new Label("THE CLOTHING SHOP");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setAlignment(Label.CENTER);

		Label subtitleLabel = new Label("EDIT ACCOUNT");
		subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		subtitleLabel.setAlignment(Label.CENTER);
		
		headerPnl.add(titleLabel);
		headerPnl.add(subtitleLabel);
		
		
		// add all the elements to the center panel
		panel.add(headerPnl);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		
		
		

		Label UUIDLbl = new Label("UUID");
		
		UUIDTxt = new JTextField();
		UUIDTxt.setEditable(false);
		UUIDTxt.setColumns(0);
		
		
		Label fullnameLbl = new Label("Fullname");
		
		fullnameTxt = new JTextField();
		fullnameTxt.setToolTipText("Username");
		fullnameTxt.setColumns(0);
		
		Label emailLbl = new Label("Email");
		
		emailTxt = new JTextField();
		emailTxt.setColumns(0);
		
		Label numberLbl = new Label("Number");
		
		numberTxt = new JTextField();
		numberTxt.setColumns(0);
		
		Label addressLbl = new Label("Address");
		
		addressTxt = new JTextField();
		addressTxt.setColumns(0);
		
		Label usernameLbl = new Label("Username");
		
		usernameTxt = new JTextField();
		usernameTxt.setColumns(0);
		
		Label passwordLbl = new Label("Password");
		
		passwordTxt = new JTextField();
		passwordTxt.setColumns(0);

		

		// footer buttons
		JPanel btnPanel = new JPanel();
		// get the main panel to be used for the commands
		JPanel main = this;
		
		// exit button
		JButton addBtn = new JButton("Modify");
		// set the size
		addBtn.setPreferredSize(new Dimension(70,30));
		// add a command (on click)
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
				boolean hasEmpty = addressTxt.getText().isEmpty() || 
						emailTxt.getText().isEmpty() ||
						fullnameTxt.getText().isEmpty() ||
						numberTxt.getText().isEmpty() ||
						passwordTxt.getText().isEmpty() ||
						usernameTxt.getText().isEmpty();
				
				if (hasEmpty) {
					JOptionPane.showMessageDialog(topFrame, "There was an error parsing the input form.\nPlease check for mistakes and try again.", "Input error!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Account.modifyAccount(selected.getUUID(), usernameTxt.getText(), passwordTxt.getText(), fullnameTxt.getText(), numberTxt.getText(), addressTxt.getText(), emailTxt.getText());
					JOptionPane.showMessageDialog(topFrame, "You have modified this account successfully!", "Account modified successfully!", JOptionPane.INFORMATION_MESSAGE);
					
					topFrame.setContentPane(new res.MainMenuPanel_Admin());
				
					/* IMPORTANT */
					// this functions should be used for every call of the function setContentPane()
					// refreshes the GUI
					topFrame.revalidate();
					topFrame.repaint();
				}
			}
		});
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setPreferredSize(new Dimension(70, 30));
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
				
				if (JOptionPane.showConfirmDialog(topFrame,"Do you want to cancel account modification?",  "Confirm cancel", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					topFrame.setContentPane(new res.MainMenuPanel_Admin());
					
					/* IMPORTANT */
					// this functions should be used for every call of the function setContentPane()
					// refreshes the GUI
					topFrame.revalidate();
					topFrame.repaint();
			    }
			}
		});
		
		
		UUIDTxt.setText(selected.getUUID());
		fullnameTxt.setText(selected.getFullname());
		usernameTxt.setText(selected.getUsername());
		passwordTxt.setText(selected.getPassword());
		emailTxt.setText(selected.getEmail());
		addressTxt.setText(selected.getAddress());
		numberTxt.setText(selected.getNumber());
		
		btnPanel.add(cancelBtn);
		btnPanel.add(addBtn);
		
		panel.add(UUIDLbl);
		panel.add(UUIDTxt);
		panel.add(Box.createRigidArea(new Dimension(0, 3)));
		panel.add(fullnameLbl);
		panel.add(fullnameTxt);
		panel.add(emailLbl);
		panel.add(Box.createRigidArea(new Dimension(0,3)));
		panel.add(emailTxt);
		panel.add(numberLbl);
		panel.add(Box.createRigidArea(new Dimension(0,3)));
		panel.add(numberTxt);
		panel.add(addressLbl);
		panel.add(Box.createRigidArea(new Dimension(0,3)));
		panel.add(addressTxt);
		panel.add(usernameLbl);
		panel.add(Box.createRigidArea(new Dimension(0,3)));
		panel.add(usernameTxt);
		panel.add(passwordLbl);
		panel.add(Box.createRigidArea(new Dimension(0,3)));
		panel.add(passwordTxt);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(btnPanel);



	}

}
