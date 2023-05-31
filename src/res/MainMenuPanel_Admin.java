package res;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
import java.awt.Component;

public class MainMenuPanel_Admin extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenuPanel_Admin() {
		// this panel contains all the element with a constant size
		JPanel panel = new JPanel();
		// box layout will be used to appear the items from top to bottom
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// setting the size
		panel.setPreferredSize(new Dimension(400,250));
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

		Label subtitleLabel = new Label("ADMIN");
		subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		subtitleLabel.setAlignment(Label.CENTER);
		
		headerPnl.add(titleLabel);
		headerPnl.add(subtitleLabel);
		
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
		btnPanel.add(exitBtn);
		
		

		JButton backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(70, 30));
		// add a command (on click)
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);

				// confirms the exit
			    if (JOptionPane.showConfirmDialog(topFrame,"Do you want to leave the admin menu?",  "Confirm leaving", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

					topFrame.setContentPane(new res.LoginPanel());
				
					/* IMPORTANT */
					// this functions should be used for every call of the function setContentPane()
					// refreshes the GUI
					topFrame.revalidate();
					topFrame.repaint();
			    }
			}
		});
		btnPanel.add(backBtn);


		JButton showBtn = new JButton(" Show Accounts ");
		showBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);

				topFrame.setContentPane(new res.ShowAccounts_Admin());
			
				/* IMPORTANT */
				// this functions should be used for every call of the function setContentPane()
				// refreshes the GUI
				topFrame.revalidate();
				topFrame.repaint();
			}
		});
		
		
		JButton addBtn = new JButton("   Add Account   ");
		addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);

				topFrame.setContentPane(new res.AddAccount_Admin());
			
				/* IMPORTANT */
				// this functions should be used for every call of the function setContentPane()
				// refreshes the GUI
				topFrame.revalidate();
				topFrame.repaint();
			}
		});
		
		JButton remBtn = new JButton("Remove Account");
		remBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		remBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);

				topFrame.setContentPane(new res.DeleteAccount_Admin());
			
				/* IMPORTANT */
				// this functions should be used for every call of the function setContentPane()
				// refreshes the GUI
				topFrame.revalidate();
				topFrame.repaint();
			}
		});
		
		JButton editBtn = new JButton("   Edit Account   ");
		editBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
				topFrame.setContentPane(new res.EditAccountChoice_Admin());
			
				/* IMPORTANT */
				// this functions should be used for every call of the function setContentPane()
				// refreshes the GUI
				topFrame.revalidate();
				topFrame.repaint();
			}
		});
		

		
		
		// add all the elements to the center panel
		panel.add(headerPnl);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(showBtn);
		panel.add(Box.createRigidArea(new Dimension(0, 3)));
		panel.add(addBtn);
		panel.add(Box.createRigidArea(new Dimension(0,3)));
		panel.add(remBtn);
		panel.add(Box.createRigidArea(new Dimension(0,3)));
		panel.add(editBtn);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(btnPanel);
		
	}

}
