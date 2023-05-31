package res;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import tools.Account;
import tools.Clothes;

public class ShowAccounts_Admin extends JPanel {

	/**
	 * Create the panel.
	 */
	public ShowAccounts_Admin() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// header panel
		JPanel headerPnl = new JPanel();
		
		// the title label
		Label titleLbl = new Label("THE CLOTHING SHOP");
		titleLbl.setFont(new Font("Arial", Font.BOLD, 20));
		titleLbl.setAlignment(Label.CENTER);
		
		// the subtitle label
		Label subtitleLbl = new Label("ALL ACCOUNTS");
		subtitleLbl.setFont(new Font("Arial", Font.PLAIN, 15));
		subtitleLbl.setAlignment(Label.CENTER);
		
		// add to the header panel
		headerPnl.add(titleLbl);
		headerPnl.add(subtitleLbl);
		
		
		// create a table which will be the container off all the items we have bought
		var table = new JTable(new DefaultTableModel(new Object[]{"UUID", "Username", "Password", "Fullname", "Number", "Address", "Email"}, 0)){
			public boolean isCellEditable(int row, int column) {
			       // makes the cell uneditable
			       return false;
			    }
			};
		// sets the color of the border
		table.setBorder(new MatteBorder(1, 1, 0, 0, new Color(220,220,220)));
		// sets the color of the inner border
		table.setGridColor(new Color(220,220,220));
		
		table.setCellSelectionEnabled(false);
		
		// get table model, all the data comes here
		var tableModel = (DefaultTableModel) table.getModel();
		// get all the accounts
	
		for (Account account : Account.accountList) {
			tableModel.addRow(new Object[]{account.getUUID(), account.getUsername(), account.getPassword(), account.getFullname(), account.getNumber(), account.getAddress(), account.getEmail()});
			
		}
	
		
		// insert the table to the scrollpane to make it scrollable
		JScrollPane tableScr  = new JScrollPane(table);
		tableScr.setPreferredSize(new Dimension(1000,1800));
		// sets a border color
		tableScr.setBorder( BorderFactory.createLineBorder(new Color(170,170,170)));
	
		// this is the table panel which adds margins
		JPanel tablePnl = new JPanel();
		tablePnl.setLayout(new GridLayout(1,0));
		tablePnl.add(tableScr);
		tablePnl.setBorder( BorderFactory.createEmptyBorder(0,10,0,10));
				
		// the bottom panel
		JPanel bottomPnl = new JPanel();
		bottomPnl.setLayout(new GridLayout(1,2));
		

		// the footer buttons
		JPanel btnsPnl = new JPanel();
		btnsPnl.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));

		JPanel main = this;
		
		// the back button
		JButton backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(80,25));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
				
				topFrame.setContentPane(new res.MainMenuPanel_Admin());
				topFrame.revalidate();
				topFrame.repaint();

			}
		});
		
		btnsPnl.add(backBtn);
		bottomPnl.add(btnsPnl);
		
	
		// add all items
		add(headerPnl);
		add(tablePnl);
		add(bottomPnl);
	}

}
