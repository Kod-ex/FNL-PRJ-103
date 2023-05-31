package res;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import tools.Account;
import tools.Closet;
import tools.Clothes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenuPanel extends JPanel {

	private List<Clothes> selClothes;
	private Account account;
	   
	
	// loads the main panel 
	public MainMenuPanel(Account account) {
		this.account = account;
    	load();
	  
	}
	
    private void load() {

        setOpaque(true);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// initialize a new instance of closet
		Closet closet = new Closet();
		selClothes = new ArrayList<>();
		
		
		// creates a header panel
		JPanel headerPnl = new JPanel();
		headerPnl.setBackground(new Color(0,0,0));	
		
		// the title
		Label titleLbl = new Label("THE CLOTHING SHOP");
		// set the font as bold
		titleLbl.setFont(new Font("Arial", Font.BOLD, 20));
		// set the bg color
		titleLbl.setForeground(new Color(255,255,255));	
		// align to the center
		titleLbl.setAlignment(Label.CENTER);
		
		headerPnl.add(titleLbl);
		
		
		// panel which hold all the items
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setOpaque(true);
		panel.setBackground(new Color(200,200,200));
		
		// wrap the panel with scroll pane
		JScrollPane scrollPane = new JScrollPane(
				panel,   
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// prevent some trailing/flickering issues
		scrollPane.setWheelScrollingEnabled(false);
		// sets the scrolling increment into 25 (faster)
		scrollPane.getVerticalScrollBar().setUnitIncrement(25);
		
		// grid bag constraints was used for creating a constraints for the grid bag layout 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        
		// set the footer items and buttons
		JPanel bottomPnl = new JPanel();
		bottomPnl.setLayout(new GridLayout(1,2));
		
		// set the label info
		JLabel info = new JLabel("<html><b>TOTAL ITEMS:</b> 0<br/><b>TOTAL PRICE:</b> P0.00</html>");
		info.setOpaque(true);
		info.setBorder(new EmptyBorder(0,10,0,0));

		
		

		JPanel btnsPnl = new JPanel();
		btnsPnl.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
		

		JPanel main = this;
		
		// back buttons
		JButton backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(80, 25));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
			    if (JOptionPane.showConfirmDialog(topFrame,"Do you want to go back to the login menu?",  "Confirm leaving", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					topFrame.setContentPane(new res.LoginPanel());
					topFrame.revalidate();
					topFrame.repaint();

			    }
			}
		});

		// footer buttons
		JButton exitBtn = new JButton("Exit");
		exitBtn.setPreferredSize(new Dimension(80,25));
		// exit button command
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);
			    if (JOptionPane.showConfirmDialog(topFrame,"Do you want to exit?",  "Confirm exit", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					topFrame.dispose();
			    }
			}
		});
		
		JButton continueBtn = new JButton("Continue");
		continueBtn.setPreferredSize(new Dimension(80,25));
		continueBtn.setEnabled(false);
		
		// continue command
		continueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame topFrame = (JFrame) SwingUtilities.getRoot(main);

				// brings the user to the cart panel
				topFrame.setContentPane(new res.Cart(selClothes, account));
				topFrame.revalidate();
				topFrame.repaint();
				
			}
		});
		
		
		
		// add the components
		btnsPnl.add(backBtn);
		btnsPnl.add(exitBtn);	
		btnsPnl.add(continueBtn);
		
		bottomPnl.add(info);
		bottomPnl.add(btnsPnl);
		
		// get the iterator
		var categoryIter = closet.getCategoryIter();
		int Y = 0;
		// loop while the iterator has next object
		while (categoryIter.hasNext()) {
			// get the current category
			String category = categoryIter.next();
			
			// the category label
			Label categoryLbl = new Label(category);
			categoryLbl.setFont(new Font("Arial", Font.BOLD, 20));
			categoryLbl.setAlignment(Label.CENTER);
			categoryLbl.setForeground(new Color(255,255,255));	
			categoryLbl.setBackground(new Color(100,100,100));	
			
			// put the label on the left most of the panel
	        gbc.gridx = 0;
	        // and put it on the current Y position
	        gbc.gridy = Y++;
	        // which extends its grid width to 3
	        gbc.gridwidth = 3;
	        // remove the borders/margins
	        gbc.insets = new Insets(0,0,0,0);
	        gbc.weightx = 1;
	        
	        // add the category label to the panel with its gbc
			panel.add(categoryLbl, gbc);

			// reset the border/margins
			gbc.insets = new Insets(8,8,8,8);
			// get the iterator for every clothes
			var clothesIter = closet.getClothesIter();
			int cY = 0;

			// loop while the iterator has next object
			while (clothesIter.hasNext()) {
				// get the current clothes
				Clothes clothes = clothesIter.next();
			
				// get all the the clothes with the current category
				if (clothes.getCategory().equals(category)) {
					try {
				        // the clothes panel
				        JPanel clothesPnl = new JPanel();
				        clothesPnl.setBackground(new Color(240,240,240));	
				        clothesPnl.setLayout(new BorderLayout());
				        var brdr = BorderFactory.createStrokeBorder(new BasicStroke(2.0f), Color.black);
				        clothesPnl.setBorder(brdr);	
				        
						// try to get the image from the clothes url
						BufferedImage image = ImageIO.read(new File(clothes.getImageURL()));
						// scale the image, make it smaller 
						var scaledImage = new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
						
						// set the label as the scaled image
						JLabel img = new JLabel(scaledImage);
						img.setOpaque(true);
						
						// get the name and set it as a label
						Label name = new Label(clothes.getName(), Label.CENTER);
						name.setFont(new Font("Arial", Font.PLAIN, 15));
						
						// get the price and set it as a label
						Label price = new Label(String.format("P%.2f", clothes.getPrice()), Label.CENTER);			
				
						// set the current Y position as the floor division of 3 and the current id of the clothes
						// floor division was the integer part of the quotient
						cY = Y + Math.floorDiv(clothes.getCategoryID(),3);

						// set the gbc layout
						// sets the current x position base on category id
						// module gets the quotient remainders
						gbc.gridx = clothes.getCategoryID() % 3;
				        gbc.gridy = cY;
				        gbc.gridwidth = 1;
				        gbc.weightx = 1;
				        
				        // add all the elements
				        clothesPnl.add(img, BorderLayout.NORTH);	
				        clothesPnl.add(name, BorderLayout.CENTER);		
				        clothesPnl.add(price, BorderLayout.SOUTH);	
				        
				        // create a mouse listener in every clothes panel
				        MouseListener ml = new MouseAdapter()
				        {
				            @Override
				            public void mousePressed(MouseEvent e)
				            {
				            	// if not selected
				            	if (clothesPnl.getBorder().equals(brdr)) {
				            		// make the color of the border red
				            		clothesPnl.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f), Color.red));
				            		// add the clothes to the selection
				            		selClothes.add(clothes);
				            	}
				            	else {
				            		// make the color of the border black
				            		clothesPnl.setBorder(brdr);
				            		// remove the clothes to the selection
				            		selClothes.remove(clothes);
				            	}
				            	
				            	// compute for the total price
				            	double totalPrice = 0;
				        		for (Clothes selC : selClothes) {
				        			totalPrice += selC.getPrice();
				        		}
				        		
				        		// add the computed price from the info
				            	info.setText(String.format("<html><b>TOTAL ITEMS:</b> %d<br/><b>TOTAL PRICE:</b> P%.2f</html>", selClothes.size(), totalPrice));

				        		// make the continue button enabled if an item was chosen
			        			continueBtn.setEnabled(selClothes.size() != 0);
				            }
				        };
				        
				        // add the mouse listener for every element from the panel, making sure it can be clicked anywhere inside the panel
				        clothesPnl.addMouseListener(ml);
				        name.addMouseListener(ml);
				        price.addMouseListener(ml);
				        
				        // add the elements
				        panel.add(clothesPnl, gbc);	
					
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}					
				}
			}
			// add the current Y to the previous Y
			Y += cY;
			
		}
		

		// add all the elements
		add(headerPnl);
		add(scrollPane);	
		add(bottomPnl);
		
		
    }
    

}
