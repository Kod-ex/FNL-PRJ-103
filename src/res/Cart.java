package res;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tools.Account;
import tools.Clothes;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;

public class Cart extends JPanel {
	
	private Account account;
	

	// Create the cart panel.
	public Cart(List<Clothes> clothes, Account account) {
		JTable table;
		JTextField moneyTxt;
		
		// get the username and the clothes selection
		this.account = account;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// header panel
		JPanel headerPnl = new JPanel();
		headerPnl.setBackground(new Color(0,0,0));	
		
		// the title label
		Label titleLbl = new Label("THE CLOTHING SHOP");
		titleLbl.setFont(new Font("Arial", Font.BOLD, 20));
		titleLbl.setForeground(new Color(255,255,255));	
		titleLbl.setAlignment(Label.CENTER);
		
		// the subtitle label
		Label subtitleLbl = new Label("CART");
		subtitleLbl.setFont(new Font("Arial", Font.PLAIN, 15));
		subtitleLbl.setForeground(new Color(225,225,225));	
		subtitleLbl.setAlignment(Label.CENTER);
		
		// add to the header panel
		headerPnl.add(titleLbl);
		headerPnl.add(subtitleLbl);
		
		
		// create a table which will be the container off all the items we have bought
		table = new JTable(new DefaultTableModel(new Object[]{"Product", "Price"}, 0)){
			public boolean isCellEditable(int row, int column) {
			       // makes the cell uneditable
			       return false;
			    }
			};
		// sets the color of the border
		table.setBorder(new MatteBorder(1, 1, 0, 0, new Color(220,220,220)));
		// sets the color of the inner border
		table.setGridColor(new Color(220,220,220));
		
		// get table model, all the data comes here
		var tableModel = (DefaultTableModel) table.getModel();
		// get all the clothes
		for (Clothes c : clothes) {
			// add each clothes to the row
			tableModel.addRow(new Object[]{c.getName(), c.getPrice()});
		}
		
		// insert the table to the scrollpane to make it scrollable
		JScrollPane tableScr  = new JScrollPane(table);
		// sets a border color
		tableScr.setBorder( BorderFactory.createLineBorder(new Color(170,170,170)));
	
		// this is the table panel which adds margins
		JPanel tablePnl = new JPanel();
		tablePnl.setLayout(new GridLayout(1,0));
		tablePnl.add(tableScr);
		tablePnl.setBorder( BorderFactory.createEmptyBorder(10,10,10,10));
				
		// the bottom panel
		JPanel bottomPnl = new JPanel();
		bottomPnl.setLayout(new BoxLayout(bottomPnl, BoxLayout.X_AXIS));
	
		// the infos come here ame as before but computed immediately
		JLabel info = new JLabel("");
		double totalPrice = 0;
		for (Clothes selC : clothes) {
			totalPrice += selC.getPrice();
		}
		
    	info.setText(String.format("<html><b>TOTAL ITEMS:</b> %d<br/><b>TOTAL PRICE:</b> P%.2f</html>", clothes.size(), totalPrice));
		info.setOpaque(true);
		info.setBorder(new EmptyBorder(0,10,0,0));
		
		
		// the payment panel
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		// payment method label
		JLabel methodLbl = new JLabel("Payment method: ");
		panel.add(methodLbl);
		
		// adds a payment method
		JComboBox<String> methodBx = new JComboBox<String>();
		methodBx.addItem("Cash");
		methodBx.addItem("GCash");
		methodBx.addItem("Maya");
		methodBx.addItem("BDO");
		methodBx.addItem("BPI");
		methodBx.setSelectedIndex(0);
		methodBx.setPreferredSize(new Dimension(80,23));
		panel.add(methodBx);
		
		// here we insert the money
		JLabel payLbl = new JLabel("     Your money: ");
		panel.add(payLbl);
		
		// the label changes based on the payment method 
		methodBx.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	// if cash 
		    	if (methodBx.getSelectedItem().equals("Cash") )
		    		payLbl.setText("     Your money: ");
		    	// if other methods
		    	else
		    		payLbl.setText("     " + "Your " + methodBx.getSelectedItem() + " money: ");
		    }
		});
		moneyTxt = new JTextField();
		moneyTxt.setColumns(10);
		

		// the footer buttons
		JPanel btnsPnl = new JPanel();
		btnsPnl.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));

		JPanel main = this;
		
		// the exit button
		JButton exitBtn = new JButton("Exit");
		exitBtn.setPreferredSize(new Dimension(80,25));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
			    if (JOptionPane.showConfirmDialog(topFrame,"Do you want to exit?",  "Confirm exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					topFrame.dispose();
			    }
			}
		});
		
		// the back button
		JButton backBtn = new JButton("Cancel");
		backBtn.setPreferredSize(new Dimension(80,25));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
				// if confirms, brings you back to the MainMenu
			    if (JOptionPane.showConfirmDialog(topFrame, "Do you want to cancel your purchase?",  "Confirm cancellation",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					topFrame.setContentPane(new MainMenuPanel(account));
					topFrame.revalidate();
					topFrame.repaint();
			    }
			}
		});
		btnsPnl.add(exitBtn);
		btnsPnl.add(backBtn);
		bottomPnl.add(info);
		bottomPnl.add(btnsPnl);
		
		// the buy button
		JButton buyBtn = new JButton("Buy");
		buyBtn.setPreferredSize(new Dimension(80,25));	
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double totalPrice = 0;
				for (Clothes selC : clothes) {
					totalPrice += selC.getPrice();
				}
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
				if (!moneyTxt.getText().isEmpty()) {
					try {
						double money = Double.parseDouble(moneyTxt.getText());
						
						if (money < totalPrice) {
							JOptionPane.showMessageDialog(topFrame,"Money should be higher than the total cart price!",  "Error while buying the items!", JOptionPane.INFORMATION_MESSAGE);
						} else {
							topFrame.setContentPane(new Receipt(clothes, money, account));
							topFrame.revalidate();
							topFrame.repaint();
						}
						
					} catch (Exception e2) {
					    JOptionPane.showMessageDialog(topFrame,"Money value should be in numerical form!",  "Error while buying the items!", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(topFrame,"Money should not be empty!",  "Error while buying the items!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
		
		JButton deleteBtn = new JButton("Delete Item");
		deleteBtn.setPreferredSize(new Dimension(120, 25));
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int s : table.getSelectedRows()) {
					for (Clothes cl : clothes) {
						if (cl.getName().equals(tableModel.getDataVector().get(s).get(0).toString()))
						{
							clothes.remove(cl);
							break;
						}
					}
				}
				
				tableModel.setRowCount(0);
				
				double totalPrice = 0;
				for (Clothes c : clothes) {
					// add each clothes to the row
					tableModel.addRow(new Object[]{c.getName(), c.getPrice()});
					totalPrice += c.getPrice();
				}

				info.setText(String.format("<html><b>TOTAL ITEMS:</b> %d<br/><b>TOTAL PRICE:</b> P%.2f</html>", clothes.size(), totalPrice));

				JOptionPane.showMessageDialog(null, "Item Deleted!", "Item Deleted", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
				
				
		btnsPnl.add(deleteBtn);
		btnsPnl.add(buyBtn);		
		
		panel.add(moneyTxt);
		

		// add all items
		add(headerPnl);
		add(tablePnl);
		add(panel);
		add(bottomPnl);
		
		
	}

}
