package res;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import tools.Account;
import tools.Clothes;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Receipt extends JPanel {
	private Account account;


	// Create the receipt panel.
	public Receipt(List<Clothes> clothes, double money, Account account) {
		// get the clothes, money and username for the receipt
		this.account = account;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// the header panel
		JPanel headerPnl = new JPanel();
		headerPnl.setBackground(new Color(0,0,0));	
		
		// the title
		Label titleLbl = new Label("THE CLOTHING SHOP");
		titleLbl.setFont(new Font("Arial", Font.BOLD, 20));
		titleLbl.setForeground(new Color(255,255,255));	
		titleLbl.setAlignment(Label.CENTER);
		
		// the subtitle
		Label subtitleLbl = new Label("RECEIPT");
		subtitleLbl.setFont(new Font("Arial", Font.PLAIN, 15));
		subtitleLbl.setForeground(new Color(225,225,225));	
		subtitleLbl.setAlignment(Label.CENTER);
		
		headerPnl.add(titleLbl);
		headerPnl.add(subtitleLbl);
		
		// the receipt panel
		JPanel receiptPnl = new JPanel();
		receiptPnl.setLayout(new GridLayout(1,0));
		receiptPnl.setBorder( BorderFactory.createEmptyBorder(10,10,10,10));
		
		// the area where we all display the receipt
		JTextPane textArea = new JTextPane();
		
		// this object contains a function that centers the text
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		
		// we use this object for our textarea
		textArea.setParagraphAttributes(attribs, true);
		// make the text uneditable
		textArea.setEditable(false);
		
		// the receipt text, we insert all the info here
		// UUID - a unique set of characters; we used it for the receipt and RN then used only the characters up to 23 as it was the limited space we have
		// we set the username's width as 23 characters to fill up the gaps
		// we use a random function to generate numbers 1 - 10000 and set the width as 23 characters to fill up the gaps
		// we used a simple date format function to parse the current time and date and set the width as 23 characters to fill up the gaps
		String receiptTxt = 
				"***********  THE CLOTHING SHOP  ***********" + "\n" +
				"       The Clothing Shop Corporation       " + "\n" + 
				"-------------------------------------------" + "\n" +
				"  RECEIPT  #:  " + UUID.randomUUID().toString().substring(0, 23) + "\n" +
				"  UUID     #:  " + this.account.getUUID() + "\n" +
				"  CUSTOMER #:  " + String.format("%23d", (new Random().nextInt(10000) + 1)) + "\n" +
				"  USERNAME  :  " + String.format("%23s", this.account.getUsername()) + "\n" +
				"  FULLNAME  :  " + String.format("%23s", this.account.getFullname()) + "\n" +
				"  EMAIL     :  " + String.format("%23s", this.account.getEmail()) + "\n" +
				"  TELE NUM  :  " + String.format("%23s", this.account.getNumber()) + "\n" +
				"  ADDRESS   :  " + String.format("%23s", this.account.getAddress()) + "\n" +
				"  DATE      :  " +  String.format("%23s", (new SimpleDateFormat("yyyy-mm-dd hh:mm:ss")).format(Calendar.getInstance().getTime())) + "\n" +
				"-- Cart -----------------------------------" + "\n";

		// adding the table based on the items from the cart
		receiptTxt += String.format(" %-26s %10s\n", "Item", "Price");
		
		// iterate for every clothes
		double totalPrice = 0;
		for (int i = 0; i < clothes.size(); i++) {
			var curClothes = clothes.get(i);
			
			// add the info to the receipt string
			receiptTxt += String.format(" %3d %-21s P%10.2f\n", i, curClothes.getName(), curClothes.getPrice());
			
			// get the total price
			totalPrice += curClothes.getPrice();
		}
		
		// add other infos from the receipt string
		receiptTxt += "-------------------------------------------" + "\n";
		receiptTxt += String.format(" %-26s %10d\n", "Total Items on the Cart: ", clothes.size());
		receiptTxt += String.format(" %-25s P%10.2f\n", "Total Amount: ", totalPrice);
		receiptTxt += String.format(" %-25s P%10.2f\n", "Your Money: ", money);
		receiptTxt += String.format(" %-25s P%10.2f\n", "Your Change: ", money- totalPrice);
		receiptTxt += "---------- Customer's Copy Slip -----------\n";
		
		// add the receipt string to the text area
		textArea.setText(receiptTxt);
		
		// set the prefered zied to be able to fill the panel
		receiptPnl.setPreferredSize(new Dimension(650,500));
		receiptPnl.add(new JScrollPane(textArea));
		
		// the bottom panel, buttons etc
		JPanel bottomPnl = new JPanel();
		bottomPnl.setLayout(new GridLayout(1,2));
	
		JPanel btnsPnl = new JPanel();
		btnsPnl.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));

		JPanel main = this;
		
		// the okay button
		JButton okayBtn = new JButton("Okay");
		okayBtn.setPreferredSize(new Dimension(80,25));
		okayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
				// if the user wants to buy again the program will go back to the main menu panel
			    if (JOptionPane.showConfirmDialog(topFrame, "The items will be delivered soon!\nWould you like to buy again?",  "Purchase success!",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    	topFrame.setContentPane(new MainMenuPanel(account));
			    } else {
			    // if not then it will log out and the program will go back to the login panel
			    	topFrame.setContentPane(new LoginPanel());
			    }
			    
			    // refresh
				topFrame.revalidate();
				topFrame.repaint();
			}
		});
		
		// add the items
		btnsPnl.add(okayBtn);
		bottomPnl.add(btnsPnl);
		
		
		add(headerPnl);
		add(receiptPnl);
		add(bottomPnl);
		
	}

}
