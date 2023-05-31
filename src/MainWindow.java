import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import tools.Account;


// the main container/ main jFrame of the app
public class MainWindow {

	private JFrame frame;

	// launches the application
	public static void main(String[] args) {
		Account.initAccounts();
		// invokes a new instance of the main window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					// shows the window
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create an instance of the application.
	public MainWindow() {
		initialize();
	}

	
	// Initialize the contents of the frame.
	private void initialize() {
		// set the look and feel of the widget element like native widgets either for Windows, macOS, or Linux
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// use this as the size of the window
		var sz = new Dimension(800,600);
		
		frame = new JFrame();
		// sets the title of the jFrame
		frame.setTitle("The Clothing Shop");
		// set the current page as the loginpage
		frame.setContentPane(new res.LoginPanel());
		// set the window's size
		frame.setSize(sz);
		// make the window unresizable
		frame.setResizable(false);
		// centers the window
		frame.setLocationRelativeTo(null);
		// default operation as dispose the entire application
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
